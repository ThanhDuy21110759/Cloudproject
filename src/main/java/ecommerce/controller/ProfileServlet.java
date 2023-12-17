package ecommerce.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import ecommerce.business.*;
import ecommerce.data.*;
import ecommerce.util.StatisticUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/userProfile.jsp";
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if (action == null) {
            action = "getAllLines"; // Đặt hành động mặc định là "getAllLines"
        }

        // data
        CustomerEntity customer = CustomerDB.loginedCustomer;
        ShopEntity shop = ShopDB.getShopByManagerID(customer.getCustomerid());

        //set init data cho page
        request.setAttribute("dsProduct",
                LineitmesDB.getLineItemsByUserId(CustomerDB.loginedCustomer.getCustomerid()));

        if (shop != null){
            List<ProductEntity> listProduct = ShopDB.getProductsByShop(shop.getShopid());

            //Danh sách sản phẩm
            request.setAttribute("listProduct", listProduct);

            //Danh sách bill INQUEUE
            request.setAttribute("listLine", BillDB.getLineItemsInQueue(listProduct));
        }

        //truyen data (shop; user)
        request.setAttribute("shop", shop);
        request.setAttribute("user", customer);

        //get action
        switch (action) {
            case "userProfile":
                if (shop != null){
                    List<ProductEntity> listProduct = ShopDB.getProductsByShop(shop.getShopid());

                    //Danh sách sản phẩm
                    request.setAttribute("listProduct", listProduct);

                    //Danh sách bill INQUEUE
                    request.setAttribute("listLine", BillDB.getLineItemsInQueue(listProduct));

                    request.setAttribute("dsProduct",
                            LineitmesDB.getLineItemsByUserId(CustomerDB.loginedCustomer.getCustomerid()));
                }

                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "1";
                break;
            case "confirmBill":
                String id = request.getParameter("lineId");

                //set status line
                LineitemsEntity line =
                        LineitmesDB.getLineItemById(Long.parseLong(id));
                line.setStatus("ACCEPT");
                LineitmesDB.update(line);

                //set status all bill
                BillDB.updateBillStatus();

                url = "/profileServlet?action=userProfile";
                break;
            case "shopStats":

                //import code here
                request.setAttribute("dsprod",
                        StatisticUtil.getBestSeller(
                                ShopDB.getProductsByShop(shop.getShopid())));

                //Truyen data thong ke doanh thu theo ngay
                Map<String, Double> revenueByDay =
                        StatisticUtil.calculateRevenue(ShopDB.getProductsByShop(shop.getShopid()));
                request.setAttribute("revenueByDay", revenueByDay);

                //Truyen data thong ke doanh thu theo thang
                Map<String, Double> revenueByMonth =
                        StatisticUtil.calculateRevenueByMonth(ShopDB.getProductsByShop(shop.getShopid()));
                request.setAttribute("revenueByMonth", revenueByMonth);

                url = "/shopComponents/statsShop.jsp";
                break;
            case "getAllLines":
                request.setAttribute("dsProduct",
                        LineitmesDB.getLineItemsByUserId(CustomerDB.loginedCustomer.getCustomerid()));

                request.setAttribute("statusBtn", "disabled");

                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "1";
                break;
            case "getWaitLines":
                request.setAttribute("dsProduct",
                        LineitmesDB.getLineItemsNull(
                                CustomerDB.loginedCustomer.getCustomerid()));

                request.setAttribute("statusBtn", "remove");

                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "2";
                break;
            case "getAcceptLines":
                request.setAttribute("dsProduct",
                        LineitmesDB.getLineItemsACCEPT(
                                CustomerDB.loginedCustomer.getCustomerid()));

                request.setAttribute("statusBtn", "accept");
                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "3";
                break;
            case "getDoneLines":
                request.setAttribute("dsProduct",
                        LineitmesDB.getLineItemsDONE(
                                CustomerDB.loginedCustomer.getCustomerid()));

                request.setAttribute("statusBtn", "disabled");
                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "4";
                break;
            case "getCancelLines":
                request.setAttribute("dsProduct",
                        LineitmesDB.getLineItemsCANCEL(
                                CustomerDB.loginedCustomer.getCustomerid()));

                request.setAttribute("statusBtn", "disabled");

                url = "/userProfile.jsp";

                LineitmesDB.pagePosition = "5";
                break;
            case "acceptLine":
                String lineId = request.getParameter("lineId");
                LineitemsEntity item =
                        LineitmesDB.getLineItemById(Long.parseLong(lineId));
                item.setStatus("DONE");

                //Up-to-date item
                LineitmesDB.update(item);

                url = "/profileServlet?action=getAcceptLines";
                break;
            case "removeLine":
                String lineId1 = request.getParameter("lineId");
                LineitemsEntity item1 =
                        LineitmesDB.getLineItemById(Long.parseLong(lineId1));
                item1.setStatus("CANCEL");

                //Up-to-date item
                LineitmesDB.update(item1);

                url = "/profileServlet?action=getCancelLines";
                break;
            default:
                // Xử lý cho hành động không xác định
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
}
