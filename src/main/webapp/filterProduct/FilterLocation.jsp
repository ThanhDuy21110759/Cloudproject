<%@ page language="java" 
		 contentType="text/html; charset=UTF-8;" 
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSP file for FilterLocations -->
<div class="shop-search-filter-location">                                      
	<div class="shop-filter-group">
		<div class="shop-filter-group__header">
			<strong>Nơi Bán</strong>
		</div>
		<div class="shop-filter-group__body">	                    
			<div class="shop-filter-group__body--items">
				<div class="shop-checkbox__control">					
					<form id="f1" action="productServlet" method="post">	
						<c:set var="cat" value="${requestScope.listLocations}"/> 
						<c:set var="chid" value="${requestScope.chid}"/>
						<input type="hidden" name="action" value="ProductLocation">					
						<div style="margin: 10px 0px">
							<input type="checkbox" 
							   id="c0" name="cidd"
							   ${chid[0]?"checked":""}
							   value="${0}" 
							   onclick="setCheck(this)"/> All
						</div>
						<c:forEach begin="0" end="${cat.size()-1}" var="i">
							<div style="margin: 10px 0px">
								<input type="checkbox"
									   id="cm" name="cidd"
									${chid[i+1]?"checked":""}
									   value="${cat[i]}"
									   onclick="setCheck(this)"/>
								<span class="shop-checkbox__label">${cat[i]}</span>
							</div>
						</c:forEach>							   			            	   						
					</form>                          						        
				</div>                        
			</div>
		</div>
	</div>
	<hr/>
</div>