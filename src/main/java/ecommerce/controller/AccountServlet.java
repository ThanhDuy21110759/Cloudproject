package ecommerce.controller;

import java.io.*;
import java.util.*;
import ecommerce.business.CustomerEntity;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "AccountServlet", value = "/accountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
//      Sample data
        CustomerEntity newAcc1 = new CustomerEntity("Truong", "123");
        CustomerEntity newAcc2 = new CustomerEntity("Duy", "123");
        CustomerEntity newAcc3 = new CustomerEntity("Nhan", "123");

        List<CustomerEntity> listAccount = new ArrayList<CustomerEntity>(){{
            add(newAcc1);
            add(newAcc2);
            add(newAcc3);
        }};

//      load: /pages/home.jsp
        request.setAttribute("listAccount", listAccount);
        String url = "/pages/account.jsp";

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
