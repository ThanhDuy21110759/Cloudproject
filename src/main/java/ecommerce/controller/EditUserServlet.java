//Mục yêu của Servlet này:
// + Load thông tin từ database lên trang JSP khi người dùng UPDATE thông tin

package ecommerce.controller;

import ecommerce.business.CustomerEntity;
import ecommerce.data.CustomerDB;
import ecommerce.data.ShopDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "EditUserServlet")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{
        String url = "/userEdit.jsp";
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        session.setAttribute("action", action);

        // load data from database to JSP file
        if(CustomerDB.isLogin) {
            CustomerEntity customer = CustomerDB.loginedCustomer;
            if (CustomerDB.loginedCustomer != null){
                request.setAttribute("user", customer);
                request.setAttribute("shop", ShopDB.getShopByManagerID(customer.getCustomerid()));
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
