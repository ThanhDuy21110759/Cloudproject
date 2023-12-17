package ecommerce.controller;

import ecommerce.business.CustomerEntity;
import ecommerce.business.InventoryEntity;
import ecommerce.business.ProductEntity;
import ecommerce.business.ShopEntity;
import ecommerce.data.CustomerDB;
import ecommerce.data.InventoryDB;
import ecommerce.data.ProductDB;
import ecommerce.data.ShopDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "EditProductServlet")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/home.jsp";
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if(action == null) action = (String) session.getAttribute("action");
        String productId = request.getParameter("Id");

        CustomerEntity customer = CustomerDB.loginedCustomer;
        ShopEntity shop = ShopDB.getShopByManagerID(customer.getCustomerid());


        url = "/profileServlet?action=userProfile";

        // data from jsp
        String producttittle = request.getParameter("name");
        String productprice = request.getParameter("price");
        String description = request.getParameter("description");
        String categoryName = request.getParameter("category");
        String inventory = request.getParameter("amount");

        CustomerEntity user = CustomerDB.loginedCustomer;


        //
        Part filePart = request.getPart("image");
        String applicationPath = request.getServletContext().getRealPath("/upload");



        // get curent date
        Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());

        // action
        if(action.equals("addProduct")){
            ProductEntity newPro = new ProductEntity();
            InventoryEntity newInvent = new InventoryEntity();

            newPro.setProducttittle(producttittle);
            newPro.setProductprice(Double.parseDouble(productprice));
            newPro.setDescription(description);
            String cat = new String();
            switch (categoryName){
                case "1":
                    cat = "Phone";
                    break;
                case "2":
                    cat = "Clothes";
                    break;
                case "3":
                    cat = "Laptop";
                    break;
            }
            newPro.setCategory(ProductDB.getCategoryByName(cat));
            newPro.setUpdatedate(currTimestamp);
            newPro.setShop(ShopDB.getShopByManagerID(user.getCustomerid()));
            newPro.setProducttotalselling(0);
            newPro.setProductavgrating(2);
            ProductDB.insert(newPro);

            //
            addImg(filePart, applicationPath);
            newPro.setProductimg(ProductDB.lastId().toString());
            ProductDB.update(newPro);

            //add new inventory
            newInvent.setProduct(ProductDB.lastId());
            newInvent.setAmount(Integer.parseInt(inventory));
            InventoryDB.insert(newInvent);
        }

        if(action.equals("updateProduct")){
            ProductEntity existPro = ProductDB.getProductById(Long.parseLong(productId));

            existPro.setProducttittle(producttittle);
            existPro.setProductprice(Double.parseDouble(productprice));
            existPro.setDescription(description);
            String cat = new String();
            switch (categoryName){
                case "1":
                    cat = "Phone";
                    break;
                case "2":
                    cat = "Clothes";
                    break;
                case "3":
                    cat = "Laptop";
                    break;
            }
            existPro.setCategory(ProductDB.getCategoryByName(cat));
            existPro.setUpdatedate(currTimestamp);
            existPro.setShop(ShopDB.getShopByManagerID(user.getCustomerid()));
//            if (fileName != null && !fileName.isEmpty()) {
//                existPro.setProductimg(realPath);
//            }

            ProductDB.update(existPro);
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

    protected void addImg(Part filePart, String applicationPath){

        // the upload img
        String fileName = null;
        try {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            File imageDir = new File(applicationPath);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }

            File imageFile = new File(imageDir, fileName);
            filePart.write(imageFile.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
