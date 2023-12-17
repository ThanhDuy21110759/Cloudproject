package ecommerce.controller;

import ecommerce.business.ProductEntity;
import ecommerce.business.ReviewEntity;
import ecommerce.data.CustomerDB;
import ecommerce.data.ProductDB;
import ecommerce.data.ReviewDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewServlet")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{
        String url = "/productDetail.jsp";
        String id = request.getParameter("Id");
        String rating = request.getParameter("userRating");
        String comment = request.getParameter("comment");

        // add new data
        // phải login mới cho post commment
        if(CustomerDB.loginedCustomer != null){
            ReviewEntity review = new ReviewEntity();
            review.setComment(comment);
            review.setRating(Integer.parseInt(rating));
            review.setCustomerId(CustomerDB.loginedCustomer.getCustomerid());
            review.setProductId(Long.parseLong(id));

            ReviewDB.insert(review);
        }

        // load data
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