<%@ page language="java" 
		 contentType="text/html; charset=UTF-8;" 
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!-- Filter Logitics -->			
<div class="shop-search-filter-logictics">                                   
	<div class="shop-filter-group">
		<div class="shop-filter-group__header">
		    <strong>Phân loại</strong>
		</div>
		<div class="shop-filter-group__body">
			<c:forEach items="${listCategories}" var="item">
				<div class="shop-filter-group__body--items">
					<form method="post" action="productServlet">
						<input type="hidden" name="action" value="CategoriesLoad">
						<input type="hidden" name="catName" value="${item}">
						<button type="submit" class="shop-checkbox__control">
							<span class="shop-checkbox__label">${item}</span>
						</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
	<hr>
</div>