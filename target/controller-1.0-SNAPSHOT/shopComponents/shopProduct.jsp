<!-- List product -->
<br>
<b><h1 class="text-3xl text-gray-900 text-center mb-4">PRODUCT MANAGEMENT</h1></b>
<div class="flex justify-end mb-2">
    <button type="button"
            class="px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            id="myBtn">+ Add new product</button>
</div>
<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">
                    Product name
                </th>
                <th scope="col" class="px-6 py-3">
                    Category
                </th>
                <th scope="col" class="px-6 py-3">
                    Selling
                </th>
                <th scope="col" class="px-6 py-3">
                    Rating
                </th>
                <th scope="col" class="px-6 py-3">
                    Price
                </th>
                <th scope="col" class="px-6 py-3">
                    Edit
                </th>
                <th scope="col" class="px-6 py-3">
                    Remove
                </th>
            </tr>
        </thead>
        <c:forEach items="${listProduct}" var="item">
            <tbody>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        ${item.getProducttittle()}
                </th>
                <td class="px-6 py-4">
                        ${item.getCategoryName()}
                </td>
                <td class="px-6 py-4">
                        ${item.getProducttotalselling()}
                </td>
                <td class="px-6 py-4">
                        ${item.getProductavgrating()}
                </td>
                <td class="px-6 py-4">
                    $${item.getProductprice()}
                </td>
                <td class="px-6 py-4">
                    <a href="${pageContext.request.contextPath}/productServlet?action=infoProduct&Id=${item.getProductid()}" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Edit</a>
                </td>
                <td class="px-6 py-4">
                    <a href="${pageContext.request.contextPath}/productServlet?action=delProduct&Id=${item.getProductid()}"
                       class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Remove</a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div><br>

<!--List confirm bill-->
<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
            <th scope="col" class="px-6 py-3">
                BillID
            </th>
            <th scope="col" class="px-6 py-3">
                ProductInfo
            </th>
            <th scope="col" class="px-6 py-3">
                Amount
            </th>
            <th scope="col" class="px-6 py-3">
                Confirm
            </th>
        </tr>
        </thead>
        <c:forEach items="${listLine}" var="i">
            <tbody>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        ${i.getBillId()}
                </th>
                <td class="px-6 py-4">
                        ${i.getProduct().getProducttittle()}
                </td>
                <td class="px-6 py-4">
                        ${i.getAmount()}
                </td>
                <td class="px-6 py-4">
                    <a href="${pageContext.request.contextPath}/profileServlet?action=confirmBill&lineId=${i.getId()}" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Accept</a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div><br>
<%@include file="/shopComponents/addProduct.jsp" %>






