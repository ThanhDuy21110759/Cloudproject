package ecommerce.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import ecommerce.business.BillEntity;
import ecommerce.business.CartEntity;
import ecommerce.business.CustomerEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.data.BillDB;
import ecommerce.data.CustomerDB;
import ecommerce.util.EmailUtility;
import ecommerce.util.ConfirmUtility;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmailSendingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action){
            case "confirmBill":
                confirmBill(request, response);
                break;
            case "forgotPassword":
                forgotPassword(request, response);
                break;
        }

    }

    private void confirmBill(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        //Get session order
        HttpSession session = request.getSession();
        session.getAttribute("order");
        CartEntity order = (CartEntity) session.getAttribute("order");
        List<LineitemsEntity> lineitems = order.getLineItems();

        //Get Info User
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");

        String message = "";

        try {
            if (CustomerDB.checkExistUsername(CustomerDB.loginedCustomer.getUsername())
                    && !lineitems.isEmpty()
                    && CustomerDB.isLogin){

                //Adding User
                CustomerEntity customer = new CustomerEntity();
                customer.setUsername(username);
                customer.setCustomeraddress(address);
                customer.setCustomername(name);
                customer.setCustomerphonenumber(phonenumber);

                //Send mail confirm
                ConfirmUtility.sendEmail(host, port, user, pass,
                                        CustomerDB.loginedCustomer.getUsername(),
                                        customer, lineitems);

                //Adding Bill
                BillEntity bill = new BillEntity();

                bill.setBilldate(new Timestamp(System.currentTimeMillis()));
                bill.setCustomerId(CustomerDB.
                        getCustomerIdByEmail(CustomerDB.loginedCustomer.getUsername()));
                bill.setLineItems(lineitems, BillDB.lastId());
                bill.setStatus("INQUEUE");
                BillDB.insert(bill);

                //Sub amount of list product in inventory


                //Remove
                session.removeAttribute("order");

                message = "The Confirm Mail was sent successfully";
            } else message = "Login to checkout please !!!";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = "Login to checkout please !!!";
        } finally {
            request.setAttribute("msg", message);
            getServletContext().getRequestDispatcher("/shopCart.jsp")
                    .forward(request, response);
        }
    }

    private void forgotPassword(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String recipient = request.getParameter("userName");
        String message = "";

        try {
            if (CustomerDB.checkExistUsername(recipient)){
                EmailUtility.sendEmail(host, port, user, pass, recipient);
                message = "The e-mail was sent successfully";
            } else message = "E-mail does not exist.";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("msg", message);
            getServletContext().getRequestDispatcher("/forgotPassword.jsp")
                    .forward(request, response);
        }
    }
}