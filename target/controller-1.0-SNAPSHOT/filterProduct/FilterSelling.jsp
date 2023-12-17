<%@ page language="java" 
		 contentType="text/html; charset=UTF-8;" 
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Filter Selling -->
<form id="ProductPopularity" action="productServlet" method="post">
    <input type="hidden" name="action" value="ProductPopularity">
    <div class="shop-sort-by-options__option shop-sort-by-options__option--selected" 
	  	 style="background-color: #42adf5;"
		 onclick="document.getElementById('ProductPopularity').submit()">
	  	 Phổ biến
	</div>
</form>

<form id="NewestProducts" action="productServlet" method="post">
    <input type="hidden" name="action" value="NewestProducts">
    <div class="shop-sort-by-options__option"
	 onclick="document.getElementById('NewestProducts').submit()">
	 Mới nhất</div>
</form>


<form id="BestSelling" action="productServlet" method="post">
    <input type="hidden" name="action" value="BestSelling">
    <div class="shop-sort-by-options__option"
	 onclick="document.getElementById('BestSelling').submit()">
	 Bán chạy</div>
</form>
		 