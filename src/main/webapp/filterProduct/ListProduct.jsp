<%@ page language="java"
		 contentType="text/html; charset=UTF-8;"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Display List of Product -->			
<div class="block-product-list-filter">			
	<div class="product-list-filter">
		<c:choose>
			<c:when test="${empty listProduct}">
				Không có sản phẩm nào
			</c:when>
			<c:otherwise>
				<c:forEach items="${listProduct}" var="item">
			    	<div class="product-info-container">
				       <div class="product-info">
				            <a href="${pageContext.request.contextPath}/productDetailServlet?Id=${item.getProductid()}" class="product__link">
								<img src="${pageContext.request.contextPath}/upload/${item.getProductimg()}.jpg" alt="img">
				            </a>
				            <div class="product__name">
				                <strong>
				                	<h3>${item.getProducttittle()}</h3>
				                </strong>
				            </div>
				            <div class="product-box-price">
				                <div class="box-info__box-price">
				                    <div class="product__price--show">$${item.getProductprice()}</div>
				                </div>
				            </div>
				       </div>
				        <div class="product__box-rating">
				        	<div class="icon_star">
					            <c:forEach begin="1" end="${item.getProductavgrating()}" varStatus="loop">
				                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#f59e0b">
				                        <!-- SVG path here -->
				                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
				                    </svg>								                
					            </c:forEach>
					            <!-- Unfilled stars -->
					            <c:forEach begin="${item.getProductavgrating()+1}" end="5" varStatus="loop">
				                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
				                        <!-- SVG path here -->
				                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
				                    </svg>							                
					            </c:forEach>
				            </div>
				        </div>
				        <div class="btn-wish-lst">
				            <div class="btn-wish-lst-text">
				                <span> Đã bán ${item.getProducttotalselling()}</<span>
				            </div>
				            <div class="btn-wish-lst-icon">               
				                <div class="wishListBtn">
				                    <span class="like"><i class='bx bx-heart'></i></span>
				                </div>
				            </div>
				        </div>
				    </div>
				</c:forEach>
			</c:otherwise>
		</c:choose>			
	</div>	
</div>