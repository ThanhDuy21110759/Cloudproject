package ecommerce.controller;

import java.io.*;
import java.util.List;

import ecommerce.business.ProductEntity;
import ecommerce.business.ReviewEntity;
import ecommerce.data.ReviewDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ecommerce.data.ProductDB;

@WebServlet(name = "ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/productDetail.jsp";
        String id = request.getParameter("Id");

        ProductEntity product = ProductDB.getProductById(Long.parseLong(id));
        List<ReviewEntity> reviews = ReviewDB.getReviewsByProductId(Long.parseLong(id));

        request.setAttribute("product", product);
        request.setAttribute("listComment", reviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
