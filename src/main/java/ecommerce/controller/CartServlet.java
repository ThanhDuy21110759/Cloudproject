package ecommerce.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ecommerce.business.CartEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.business.ProductEntity;
import ecommerce.data.CustomerDB;
import ecommerce.data.ProductDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    String url = "/home.jsp";
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "cart";
        }

        switch (action) {
            case "cart":
                cartDisplay(request, response);
                break;
            default:
                url = "/home.jsp";
                break;
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

    //init
    protected void cartProduct(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listProduct", ProductDB.selectProduct());
        url = "/shopCart.jsp";
    }
    protected void cartDisplay(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        int quantity = 1;
        long id;
        HttpSession session = request.getSession();
        request.setAttribute("user", CustomerDB.loginedCustomer);
        //Session cart
        if (request.getParameter("Id") != null){
            id = Long.parseLong(request.getParameter("Id"));
            String status = request.getParameter("status");
            ProductEntity product = ProductDB.getProductById(id);

            //
            if (product != null){
                if (session.getAttribute("order") == null){
                    CartEntity order = new CartEntity();
                    List<LineitemsEntity> listItems = new ArrayList<>();
                    LineitemsEntity item = new LineitemsEntity();
                    item.setAmount(quantity);
                    item.setProduct(product);
                    listItems.add(item);
                    order.setLineItems(listItems);

                    //Tính tổng
                    session.setAttribute("total", ProductDB.calculateTotal(listItems));
                    session.setAttribute("order", order);
                } else {
                    CartEntity order = (CartEntity) session.getAttribute("order");
                    List<LineitemsEntity> lineitems = order.getLineItems();
                    boolean check = false;
                    List<LineitemsEntity> itemsToRemove = new ArrayList<>();
                    for (LineitemsEntity item: lineitems){
                        if (item.getProduct().getProductid() == product.getProductid()){
                            if (!Objects.equals(status, "False")) {
                                item.setAmount(item.getAmount() + quantity);
                            } else {
                                if (item.getAmount() != 1) {
                                    item.setAmount(item.getAmount() - quantity);
                                } else {
                                    itemsToRemove.add(item);
                                }
                            }
                            check = true;
                        }
                    }
                    lineitems.removeAll(itemsToRemove);
                    if (!check) {
                        LineitemsEntity item = new LineitemsEntity();
                        item.setAmount(quantity);
                        item.setProduct(product);
                        lineitems.add(item);
                    }
                    order.setLineItems(lineitems);

                    //Tính tổng
                    session.setAttribute("total", ProductDB.calculateTotal(lineitems));
                    session.setAttribute("order", order);
                }
                //check remove
                if (request.getParameter("quantity") != null){
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    if (quantity == 0) {
                        id = Long.parseLong(request.getParameter("Id"));
                        CartEntity order = (CartEntity) session.getAttribute("order");
                        List<LineitemsEntity> lineitems = order.getLineItems();
                        long finalId = id;
                        lineitems.removeIf(item -> item.getProduct().getProductid() == finalId);
                        order.setLineItems(lineitems);

                        //Tính tổng
                        session.setAttribute("total", ProductDB.calculateTotal(lineitems));
                        session.setAttribute("order", order);
                    }
                }
            }
        }
        session.setAttribute("userLogin",session.getAttribute(Constants.USER_LOGGED));
        url = "/shopCart.jsp";
    }
}
