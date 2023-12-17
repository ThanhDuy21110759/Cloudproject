<%@ page import="ecommerce.data.LineitmesDB" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<div class="bg-gray-50 p-2">
    <div class="mb-2 grid grid-cols-5 gap-2 bg-white shadow-sm text-center">
        <form method="post" action="profileServlet"
            id="categoris-1" class="categoris p-2">
            <input type="hidden" name="action" value="getAllLines">
            <button type="submit"
                    onclick="changePage(1)">Tất cả</button>
        </form>

        <form method="post" action="profileServlet"
              id="categoris-2" class="categoris p-2">
            <input type="hidden" name="action" value="getWaitLines">
            <button type="submit"
                    onclick="changePage(2)">Chờ xác nhận</button>
        </form>

        <form method="post" action="profileServlet"
              id="categoris-3" class="categoris p-2">
            <input type="hidden" name="action" value="getAcceptLines">
            <button type="submit"
                    onclick="changePage(3)">Giao hàng</button>
        </form>

        <form method="post" action="profileServlet"
              id="categoris-4" class="categoris p-2">
            <input type="hidden" name="action" value="getDoneLines">
            <button type="submit"
                    onclick="changePage(4)">Hoàn thành</button>
        </form>

        <form method="post" action="profileServlet"
              id="categoris-5" class="categoris p-2">
            <input type="hidden" name="action" value="getCancelLines">
            <button type="submit"
                    onclick="changePage(5)">Hủy đơn</button>
        </form>
    </div>

    <div class="mb-2">
        <div class="relative">
            <div class="pointer-events-none absolute inset-y-0 start-0 flex items-center ps-3">
                <svg class="h-4 w-4 text-gray-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                          stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"></path>
                </svg>
            </div>
            <input type="search" id="search"
                   class="block w-full rounded-sm bg-gray-100 p-4 ps-10 text-sm text-gray-900" placeholder="Tìm cái gì đó đi bạn :3" />
            <button type="submit"
                    class="absolute bottom-2.5 end-2.5 rounded-lg bg-blue-400 px-4 py-2 text-sm font-medium text-white">Tìm kiếm</button>
        </div>
    </div>

    <div class="flex shadow-md my-2">
        <div class="w-full bg-white px-8 py-8 " style="max-height: 500px; overflow-y: auto;">
            <c:forEach items="${dsProduct}" var="item">
                <div class="flex items-center hover:bg-gray-100 px-6 py-5">
                    <!-- product img -->
                    <div class="flex w-full"> <!-- product -->
                        <div class="w-20">
                            <img class="h-24" src="${pageContext.request.contextPath}/upload/${item.getProduct().getProductid()}.jpg" alt="product-img">
                        </div>
                        <div class="flex flex-col justify-between ml-4 flex-grow">
                            <span class="font-bold text-sm">${item.getProduct().getProducttittle()}</span>
                            <span class="text-red-500 text-xs">${item.getProduct().getCategoryName()}</span>
                            <c:choose>
                                <c:when test="${statusBtn == 'remove'}">
                                    <a href="${pageContext.request.contextPath}/profileServlet?action=removeLine&lineId=${item.getId()}">
                                        <button class="font-semibold hover:bg-blue-700 text-white text-xs bg-blue-400 rounded-lg mr-44 py-1 p-2">Remove</button>
                                    </a>
                                </c:when>
                                <c:when test="${statusBtn == 'accept'}">
                                    <a href="${pageContext.request.contextPath}/profileServlet?action=acceptLine&lineId=${item.getId()}">
                                        <button class="font-semibold hover:bg-blue-700 text-white text-xs bg-blue-400 rounded-lg mr-44 py-1 p-2">Accept</button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#">
                                        <button class="font-semibold text-white text-xs bg-gray-400 rounded-lg mr-44 py-1 p-2" hidden>Hidden</button>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <!-- Price -->
                    <span class="text-end w-full font-semibold text-sm">Mã đơn hàng: ${item.getBillId()}</span>
                    <span class="text-end w-full font-semibold text-sm">Thành tiền: ${item.getProduct().getProductprice() * item.getAmount()}</span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script>
    window.onload = (event) => {
        changePage(<%=LineitmesDB.pagePosition%>);
    };

    function changePage(id){
        const category = document.getElementById('categoris-' + id);

        const categoris = document.getElementsByClassName('categoris');
        for (let i = 0; i < categoris.length; i++) {
            categoris[i].classList.remove('text-indigo-500');
            categoris[i].classList.remove('border-b-4');
            categoris[i].classList.remove('border-indigo-500');
        }

        category.classList += ' text-indigo-500 border-b-4 border-indigo-500';
    }

</script>
