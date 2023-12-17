<%@ page import="ecommerce.data.CustomerDB" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="bg-gray-900 text-white">
    <div class="m-auto flex max-w-screen-xl flex-wrap items-center justify-between py-2">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/productServlet?action=hintProduct" class="flex items-center">
            <img src="https://drive.google.com/uc?export=view&id=1W5YSNi0VnfxXZalgZMJln6TTJQk1ZPbh" class="mr-3 h-8" alt="Logo" />
            <span class="self-center whitespace-nowrap text-2xl font-semibold text-white">ShopSavvy</span>
        </a>

        <!-- responsive navbar icon-->
        <button type="button" class="inline-flex h-10 w-10 items-center justify-center rounded-lg p-2 text-sm text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 md:hidden" aria-controls="navbar-dropdown" aria-expanded="false" onclick="toggleDropwdown('navbar-dropdown')">
            <svg class="h-5 w-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h15M1 7h15M1 13h15" />
            </svg>
        </button>

        <!-- Links -->
        <div class="w-full leading-9 text-white md:block md:w-auto" id="navbar-dropdown">
            <ul class="flex flex-col items-center bg-gray-900 p-2 font-medium text-white md:mt-0 md:flex-row md:space-x-8 md:border-0 md:p-0">
                <!-- All products -->
                <li>
                    <form id="loadingPage" type="hidden" action="productServlet" method="post" class="my-auto">
                        <input type="hidden" name="action" value="display" />
                        <input type="submit" value="All Products"
                               class="flex cursor-pointer text-white hover:text-blue-700" />
                    </form>
                </li>

                <!-- Search bar -->
                <li>
                    <div class="relative mx-auto max-[600px]:hidden p-2 text-gray-600">
                        <form method="get" action="productServlet" class="flex items-center justify-center mb-0">
                            <input type="hidden" name="action" value="searchProduct">
                            <input class="h-10 rounded-lg border-2 border-gray-300 bg-white px-5 pr-16 text-sm focus:outline-none" type="search" name="searchTxt" placeholder="Search" />
                            <button type="submit" class="absolute right-0 top-0 mr-4 mt-5">
                                <svg class="h-4 w-4 fill-current text-gray-600" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 56.966 56.966;" xml:space="preserve" width="512px" height="512px">
                                        <path d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z" />
                                    </svg>
                            </button>
                        </form>
                    </div>
                </li>

                <!-- Login icons, toggle when login success -->
                <c:choose>
                    <c:when test="<%=CustomerDB.isLogin%>">
                        <!-- Login success -->
                        <li class="flex justify-between gap-10">
                            <!-- Logout -->
                            <form id="" type="hidden" action="loginServlet" method="post" class="my-auto">
                                <input type="hidden" name="action" value="logout" />
                                <input type="submit" value="Logout"
                                       class="cursor-pointer text-white hover:text-blue-700" />
                            </form>

                            <!-- user profile -->
                            <form type="hidden" action="profileServlet" method="post"
                                  class="cursor-pointer text-white hover:text-blue-700 p-3 my-auto">
                                <input type="hidden" name="action" value="userProfile"/>
                                <input type="hidden" name="userName" value="${user.username}" />
                                <button type="submit" class="hover:border-blue-700">
                                    <i class="fa-solid fa-user max-[600px]:hidden"></i>
                                    <p class="md:hidden cursor-pointer text-white hover:text-blue-700">Your Profile</p>
                                </button>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- Not login -->
                        <li>
                            <a class="rounded-lg bg-blue-500 cursor-pointer items-center px-10 py-3 font-bold text-white hover:bg-blue-700"
                               href="${pageContext.request.contextPath}/login.jsp"> Login </a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <!-- Cart icon -->
                <li>
                    <form id="cartIconForm" type="hidden" action="cartServlet" method="post"
                          class="h-10 w-10 my-auto items-center justify-center text-sm text-gray-500  focus:outline-none focus:ring-2 focus:ring-gray-200 max-[600px]:hidden">
                        <input type="hidden" name="action" value="cart" />
                        <button type="submit"
                                class="h-10 w-10 items-center justify-center text-sm text-gray-500  focus:outline-none focus:ring-2 focus:ring-gray-200 max-[600px]:hidden">
                            <i class="fa-solid fa-cart-shopping text-2xl text-blue-500 hover:text-white"></i>
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script>
    function toggleDropwdown(id){
        let dropDownBtn = document.querySelector('#' + id);
        dropDownBtn.classList.toggle("hidden");
    }

    function changeColor(id){
        let e = document.querySelector("#" + id);
        e.classList.add("text-blue-700");
    }

    document.getElementById("cartIconForm").onclick = function() {
        document.getElementById("yourFormId").submit();
    }

</script>