package ecommerce.controller;

import ecommerce.business.CustomerEntity;
import ecommerce.business.ShopEntity;
import ecommerce.data.CustomerDB;
import ecommerce.data.ShopDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet(name = "applyUserDataServlet")
public class ApplyUserDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/productServlet?action=hintProduct";
        HttpSession session = request.getSession();
        String action = (String) session.getAttribute("action");

        // data from jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String customername = request.getParameter("customername");
        String customerphonenumber = request.getParameter("customerphonenumber");
        String customeraddress = request.getParameter("customeraddress");
        String role = request.getParameter("role");

        String shopName = request.getParameter("shopname");
        String shoplocation = request.getParameter("shoplocation");

        // database
        if(action.equals(Constants.ACTION_EDIT)){
            url = "/profileServlet?action=userProfile";

            CustomerEntity existCus = CustomerDB.getCustomerByUserName(username);

            existCus.setPassword(password);
            existCus.setCustomername(customername);
            existCus.setCustomerphonenumber(customerphonenumber);
            existCus.setCustomeraddress(customeraddress);

            // edit
            if(role != null && role.equals("shopRole")){
                existCus.setRole(Constants.ROLE_SHOP);
                CustomerDB.update(existCus);

                ShopEntity shop = ShopDB.getShopByManagerID(existCus.getCustomerid());
                if(shop == null){
                    shop = new ShopEntity();
                    shop.setShopmanager(existCus.getCustomerid());
                }
                shop.setShopname(shopName);
                shop.setShoplocation(shoplocation);

                ShopDB.update(shop);
            }
            else{
                existCus.setRole(Constants.ROLE_CUSTOMER);
                CustomerDB.update(existCus);
            }

            CustomerDB.loginedCustomer = existCus;
            request.setAttribute("user", existCus);
        }else if (action.equals(Constants.ACTION_INSERT)){
            url = "/productServlet?action=hintProduct";

            CustomerEntity newCus = new CustomerEntity();

            newCus.setUsername(username);
            newCus.setPassword(password);
            newCus.setCustomername(customername);
            newCus.setCustomerphonenumber(customerphonenumber);
            newCus.setCustomeraddress(customeraddress);

            // Insert
            if(role != null && role.equals("shopRole")){
                newCus.setRole(Constants.ROLE_SHOP);
                CustomerDB.insert(newCus);

                // insert database thành công => tạo ID tự động => newCus.getCustomerid()
                ShopEntity shop = new ShopEntity();
                shop.setShopmanager(newCus.getCustomerid());
                shop.setShopname(shopName);
                shop.setShoplocation(shoplocation);

                ShopDB.insert(shop);
            }
            else{
                newCus.setRole(Constants.ROLE_CUSTOMER);
                CustomerDB.insert(newCus);
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
