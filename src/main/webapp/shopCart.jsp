<%@ page import="ecommerce.business.LineitemsEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Cart</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="components/navbar.jsp" %>

    <div class="container mx-auto mt-10">
        <div class="flex shadow-md my-10">
            <div class="w-3/4 bg-white px-10 py-10">
                <div class="flex justify-between border-b pb-8">
                    <h1 class="font-semibold text-2xl">Shopping Cart</h1>
                    <h2 class="font-semibold text-2xl">${order.getLineItems().size()} Items</h2>
                </div>
                <div class="flex mt-10 mb-5">
                    <h3 class="font-semibold text-gray-600 text-xs uppercase w-2/5">Product Details</h3>
                    <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 ">Quantity</h3>
                    <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 ">Price</h3>
                    <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 ">Total</h3>
                </div>

                <!-- 1 cart product -->
                <c:forEach items="${order.getLineItems()}" var = "item">
                    <div class="flex items-center hover:bg-gray-100 -mx-8 px-6 py-5">
                        <!-- product img -->
                        <div class="flex w-2/5"> <!-- product -->
                            <div class="w-20">
                                <img class="h-24" src="upload/${item.getProduct().getProductimg()}.jpg" alt="product-img">
                            </div>
                            <div class="flex flex-col justify-between ml-4 flex-grow">
                                <span class="font-bold text-sm">${item.getProduct().getProducttittle()}</span>
                                <span class="text-red-500 text-xs">${item.getProduct().getCategoryName()}</span>
                                <a href="${pageContext.request.contextPath}/cartServlet?Id=${item.getProduct().getProductid()}&quantity=0">
                                    <button class="font-semibold hover:bg-blue-700 text-white text-xs bg-blue-400 rounded-lg mr-44 py-1 p-2">Remove</button>
                                </a>
                            </div>
                        </div>
                        <div class="flex justify-center w-1/5">
                            <a href="${pageContext.request.contextPath}/cartServlet?Id=${item.getProduct().getProductid()}&status=False"
                               style="display: flex; justify-content: center; align-items: center;">
                                <button>
                                    <svg class="fill-current text-gray-600 w-3" viewBox="0 0 448 512">
                                        <path d="M416 208H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h384c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z"/>
                                    </svg>
                                </button>
                            </a>

                            <input class="mx-2 border text-center w-8" type="text" id="numberCount${item.getProduct().getProductid()}" value="${item.getAmount()}">

                            <a href="${pageContext.request.contextPath}/cartServlet?Id=${item.getProduct().getProductid()}&status=True"
                               style="display: flex; justify-content: center; align-items: center;">
                                <button>
                                    <svg class="fill-current text-gray-600 w-3" viewBox="0 0 448 512">
                                        <path d="M416 208H272V64c0-17.67-14.33-32-32-32h-32c-17.67 0-32 14.33-32 32v144H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h144v144c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32V304h144c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z"/>
                                    </svg>
                                </button>
                            </a>
                        </div>
                        <!-- Price -->
                        <span class="text-center w-1/5 font-semibold text-sm">${item.getProduct().getProductprice()}</span>
                        <span class="text-center w-1/5 font-semibold text-sm">${item.getProduct().getProductprice() * item.getAmount()}</span>
                    </div>
                </c:forEach>
            </div>

            <div id="summary" class="w-1/4 px-8 py-10">
                <h1 class="font-semibold text-2xl border-b pb-8">Order Summary</h1>
                <div class="flex justify-between mt-10 mb-5">
                    <span class="font-semibold text-sm uppercase">${order.getLineItems().size()} Items</span>
                </div>
                <div>
                    <label class="font-medium inline-block mb-3 text-sm uppercase">Shipping</label>
                    <select class="block p-2 text-gray-600 w-full text-sm">
                        <option>Standard shipping - $10.00</option>
                    </select>
                </div>
                <div class="border-t mt-8">
                    <form method="post" action="EmailSendingServlet">
                        <!-- Send value -->
                        <input type="hidden" name="action" value="confirmBill">
                        <input type="hidden" name="userLogin" value="${userLogin}">

                        <div class="flex font-semibold justify-between py-6 text-sm uppercase">
                            <span>Total cost</span>
                            <span>$${total}</span>
                        </div>
                        <!-- Check Out -->
                        <div>
                            <p class="text-red-400">${msg}</p>
                        </div><br>
                        <c:choose>
                            <c:when test="${empty order.getLineItems()}">
                                <button type="button"
                                        id="myBtn"
                                        class="bg-indigo-500 font-semibold rounded-lg hover:bg-indigo-600 py-3 text-sm text-white uppercase w-full disabled:bg-gray-500" disabled
                                >Checkout</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button"
                                        id="myBtn"
                                        class="bg-indigo-500 font-semibold rounded-lg hover:bg-indigo-600 py-3 text-sm text-white uppercase w-full"
                                >Checkout</button>
                            </c:otherwise>
                        </c:choose>
                        <!-- Pop-up user-->
                        <%@include file="/shopComponents/confirmUser.jsp" %>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
