package ecommerce.controller;

import java.io.*;
import java.util.*;
import java.util.List;

import ecommerce.business.ProductEntity;
import ecommerce.data.*;
import ecommerce.util.StatisticUtil;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String url = "/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setAttribute("listLocations", ShopDB.getUniqueLocations());
        request.setAttribute("listCategories", CategoryDB.getAllCategoryNames());


        //Set URL vs Default Action equals "display"
        if (action == null) {
            action = "display";
            url = "/index.jsp";
        }

        switch (action) {
            case "display":
                displayProduct(request, response);
                break;
            case "displayASC":
                displayIncreasing(request, response);
                break;
            case "displayDESC":
                displayDescreasing(request, response);
                break;
            case "ProductPopularity":
                displayPopulation(request, response);
                break;
            case "NewestProducts":
                displayNewest(request,response);
                break;
            case "BestSelling":
                displayBestselling(request, response);
                break;
            case "ProductsRating":
                displayRating(request, response);
                break;
            case "ProductLocation":
                displayLocation(request, response);
                break;
            case "CategoriesLoad":
                categoriesLoad(request, response);
                break;
            case "searchProduct":
                searchProduct(request, response);
                break;
            case "hintProduct":
                hintProduct(request, response);
                break;
            case "delProduct":
                delProduct(request, response);
                break;
            case "infoProduct":
                infoProduct(request, response);
                break;
            default:
                url = "/index.jsp";
                break;
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    //Khởi tạo hàm cho servlet
    protected void displayProduct(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        boolean[] chidInit = new boolean[Objects.requireNonNull(ShopDB.getUniqueLocations()).size() + 1];
        chidInit[0] = true;
        request.setAttribute("chid", chidInit);
        request.setAttribute("listProduct", ProductDB.selectProduct());
        url = "/product.jsp";
    }

    protected void displayIncreasing(HttpServletRequest request,
                                     HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listProduct", ProductDB.selectProductASC());
        url = "/product.jsp";
    }

    protected void displayDescreasing(HttpServletRequest request,
                                      HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listProduct", ProductDB.selectProductDESC());
        url = "/product.jsp";
    }

    protected void displayPopulation(HttpServletRequest request,
                                     HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listProduct", ProductDB.getProductPopularity());
        url = "/product.jsp";
    }

    protected void displayNewest(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listProduct", ProductDB.getNewestProducts());
        url = "/product.jsp";
    }

    protected void displayBestselling(HttpServletRequest request,
                                      HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listProduct", ProductDB.getBestSellingProducts());
        url = "/product.jsp";
    }

    protected void displayRating(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        String rating = request.getParameter("danhgia");
        request.setAttribute("listProduct",
                                    ProductDB.getProductsRating(Integer.parseInt(rating)));
        url = "/product.jsp";
    }
    protected void categoriesLoad(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        String Name = request.getParameter("catName");
        request.setAttribute("listProduct",
                                    ProductDB.getProductsByCategoryName(Name));
        url = "/product.jsp";
    }

    protected void displayLocation(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException {

        String[] selectedLocations = request.getParameterValues("cidd");
        if (selectedLocations == null || selectedLocations[0].equals("0")) {
            boolean[] chidInit1 = new boolean[ShopDB.getUniqueLocations().size() + 1];
            chidInit1[0] = true;
            request.setAttribute("chid", chidInit1);
            request.setAttribute("listProduct", ProductDB.selectProduct());
        } else {
            boolean[] chid = new boolean[ShopDB.getUniqueLocations().size() + 1];
            List<ProductEntity> resList = new ArrayList<>();
            for (int i = 0; i < ShopDB.getUniqueLocations().size(); i++) {
                for (String selectedLocation : selectedLocations) {
                    if (ShopDB.getUniqueLocations().get(i).equals(selectedLocation)) {
                        chid[i + 1] = true;
                        List<ProductEntity> products = ShopDB.getProductsByShop(ShopDB.getIdFromLocation(selectedLocation));
                        resList.addAll(products);
                        break;
                    }
                }
            }
            request.setAttribute("chid", chid);
            request.setAttribute("listProduct", resList);
        }
        url = "/product.jsp";
    }
    protected void searchProduct(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException {
        String str = request.getParameter("searchTxt");

        request.setAttribute("listProduct",
                StatisticUtil.getSearchProducts(str));
        url = "/product.jsp";
    }
    protected void hintProduct(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {
        if (CustomerDB.loginedCustomer == null
                || !CustomerDB.isLogin){
            request.setAttribute("listProduct",
                    ProductDB.getList10Popular());
        } else {
            request.setAttribute("listProduct",
                    ProductDB.getListHint(CustomerDB.loginedCustomer));
        }
        url = "/home.jsp";
    }
    protected void delProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("Id");

        //Delete product by ID
        if (BillDB.checkBill(Long.parseLong(id))){
            ProductDB.delete(
                    ProductDB.getProductById(Long.parseLong(id)));
        }

        url = "/profileServlet?action=userProfile";
    }
    protected void infoProduct(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {
        String Id = request.getParameter("Id");

        //truyen data
        ProductEntity product = ProductDB.getProductById(Long.parseLong(Id));
        request.setAttribute("item", product);

        url = "/shopComponents/editProduct.jsp";
    }
}