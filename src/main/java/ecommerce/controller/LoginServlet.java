package ecommerce.controller;

import java.io.*;

import ecommerce.data.CustomerDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/productServlet?action=hintProduct";

        String action = request.getParameter("action");

        // session
        HttpSession session = request.getSession();
        if(action.equals("login")){
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");

            // check input
            if(userName != null && userName.trim().length() > 0){
                // database
                if(CustomerDB.checkLogin(userName, password)){
                    //Save Login Info
                    CustomerDB.loginedCustomer = CustomerDB.getCustomerByUserName(userName);

                    //Adding LoginUser
                    session.setAttribute(Constants.USER_LOGGED, userName);

                    // cookies
                    if(remember!=null && remember.equals("stayRemember")){
                        CustomerDB.isRemember = true;

                        Cookie cookieUname = new Cookie("cookUname", userName);
                        Cookie cookiePass = new Cookie("cookPass", password);
                        Cookie cookieRemember = new Cookie("cookRemember", remember);

                        // cho cookies tồn tại 15h
                        cookieUname.setMaxAge(60 * 60 * 15);
                        cookiePass.setMaxAge(60 * 60 * 15);
                        cookiePass.setMaxAge(60 * 60 * 15);

                        response.addCookie(cookieUname);
                        response.addCookie(cookiePass);
                        response.addCookie(cookieRemember);
                    }else{
                        CustomerDB.isRemember = false;
                    }
                }else {
                    session.setAttribute(Constants.MESSAGE_HOME, "User name or password is incorrect");
                    url = "/login.jsp";
                }
            }else {
                session.setAttribute(Constants.MESSAGE_HOME, "Invalid input");
                url = "/login.jsp";
            }
        }else if(action.equals("logout")){
            CustomerDB.isLogin = false;

            //delete the cookie
            if(!CustomerDB.isRemember){
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            session.invalidate();
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
