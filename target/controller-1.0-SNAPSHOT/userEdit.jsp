<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create your account</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@include file="components/navbar.jsp" %>

<div class="flex min-h-screen items-center justify-center bg-gray-100 p-6">
    <form class="container mx-auto max-w-screen-lg" action="applyUserDataServlet">
        <h2 class="mb-6 text-xl font-semibold text-gray-600">User Form</h2>

        <div class="mb-6 rounded bg-white p-4 px-4 shadow-lg md:p-8">
            <div class="grid grid-cols-1 gap-4 gap-y-2 text-sm lg:grid-cols-3">
                <div class="text-gray-600">
                    <p class="text-lg font-medium">Personal Details</p>
                    <p>Please fill out all the fields.</p>
                </div>

                <div class="lg:col-span-2">
                    <div class="grid grid-cols-1 gap-4 gap-y-2 text-sm md:grid-cols-5">
                        <!-- Account -->
                        <div class="md:col-span-5">
                            <label for="username">User Name</label>
                            <input type="email" name="username" id="username" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${user.username}" placeholder="Your user Name" />
                        </div>

                        <div class="md:col-span-5">
                            <label for="password">Password</label>
                            <input type="text" name="password" id="password" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${user.password}" placeholder="••••••••" />
                        </div>

                        <!-- Personal info -->
                        <div class="md:col-span-5">
                            <label for="customername">Full Name</label>
                            <input type="text" name="customername" id="customername" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${user.customername}" />
                        </div>

                        <div class="md:col-span-5">
                            <label for="customerphonenumber">Phone Number</label>
                            <input type="text" name="customerphonenumber" id="customerphonenumber" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${user.customerphonenumber}" />
                        </div>

                        <div class="md:col-span-5">
                            <label for="customeraddress">Address</label>
                            <input type="text" name="customeraddress" id="customeraddress" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${user.customeraddress}" />
                        </div>

                        <%--when user check "set shop" => edit/create shop detail--%>
                        <c:choose>
                            <c:when test="${user.getRole() == 'SHOP'}">
                                <div class="mb-3 md:col-span-5">
                                    <div class="inline-flex items-center">
                                        <input type="checkbox" name="role" value="shopRole" class="form-checkbox" onclick="toggleShopInfo()" checked/>
                                        <label class="ml-2">Set my role as a Shop Manager</label>
                                    </div>
                                </div>

                                <div id="shopInfo" class="md:col-span-5">
                                    <label for="shopname">Shop Name</label>
                                    <input type="text" name="shopname" id="shopname" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${shop.shopname}"/>

                                    <label for="shoplocation">Shop Location</label>
                                    <input type="text" name="shoplocation" id="shoplocation" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${shop.shoplocation}" />
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="mb-3 md:col-span-5">
                                    <div class="inline-flex items-center">
                                        <input type="checkbox" name="role" value="shopRole" class="form-checkbox" onclick="toggleShopInfo()"/>
                                        <label class="ml-2">Set my role as a Shop Manager</label>
                                    </div>
                                </div>

                                <div id="shopInfo" class="hidden md:col-span-5">
                                    <label for="shopname">Shop Name</label>
                                    <input type="text" name="shopname" id="shopname" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${shop.shopname}"/>

                                    <label for="shoplocation">Shop Location</label>
                                    <input type="text" name="shoplocation" id="shoplocation" class="mt-1 h-10 w-full rounded border bg-gray-50 px-4" value="${shop.shoplocation}" />
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div class="text-right md:col-span-5">
                            <div class="inline-flex items-end">
                                <button class="rounded bg-blue-500 px-4 py-2 font-bold text-white hover:bg-blue-700" type="submit">Submit</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    function toggleShopInfo(){
        var shopInfo = document.getElementById("shopInfo");
        shopInfo.classList.toggle("hidden");
    }
</script>

</body>
</html>
