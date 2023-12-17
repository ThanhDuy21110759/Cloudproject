<%@ page language="java"
		 contentType="text/html; charset=UTF-8;"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Filter Rating -->
<div class="shop-search-filter-rating">
	<div class="shop-filter-group">
		<div class="shop-filter-group__header">
		    <strong>Đánh giá</strong>
		</div>
		<div class="shop-filter-group__body">
			<% for(int stars = 5; stars >= 1; stars--) { %>
			    <div class="shop-filter-group__body--items">
			    	<form id="myForm<%= stars %>" action="productServlet" method="post">
				    	<input type="hidden" name="action" value="ProductsRating">
				    	<input type="hidden" name="danhgia" value="<%= stars %>">
				    	<div class="shop-checkbox__control">
					        <a onclick="document.getElementById('myForm<%= stars %>').submit()" style="cursor: pointer;">
				                <% for(int i = 1; i <= stars; i++) { %>
				                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#f59e0b">
				                        <!-- SVG path here -->
				                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
				                    </svg>
				                <% } %>
				                <!-- Unfilled stars -->
				                <% for(int i = stars + 1; i <= 5; i++) { %>
				                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
				                        <!-- SVG path here -->
				                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
				                    </svg>
				                <% } %>
				                <span class="shop-checkbox__label"><%= stars %> sao</span>
					        </a>
				        </div>
			    	</form>
			    </div>
			<% } %>
		</div>
	</div>
	<hr>
</div>
