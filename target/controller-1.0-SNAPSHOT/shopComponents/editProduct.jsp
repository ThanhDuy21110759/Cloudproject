<%@ page language="java"
         contentType="text/html; charset=UTF-8;"
         pageEncoding="UTF-8"%>
<script src="https://cdn.tailwindcss.com"></script>
<%@include file="/components/navbar.jsp" %>

<form action="editProductServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="updateProduct" />
    <input type="hidden" name="Id" value="${item.getProductid()}">
    <div class="flex min-h-screen items-center justify-center bg-gray-100 p-6" >
        <div class="container mx-auto max-w-screen-lg">
            <div>
                <h2 class="mb-6 text-xl font-semibold text-gray-600">Product Form</h2>

                <div class="mb-6 rounded bg-white p-4 px-4 shadow-lg md:p-8" style="display: flex; flex-direction: row;">
                    <div class="text-gray-600" style="flex: 0.4;">
                        <p class="text-lg font-medium">Product Details</p>
                        <p>Please fill out all the fields.</p>
                    </div>
                    <div class="grid gap-4 mb-4 grid-cols-2" style="flex: 0.6;">
                        <div class="col-span-2">
                            <label for="name" class="block mb-2 text-sm font-medium text-gray-900">Name</label>
                            <input type="text" name="name" id="name"
                                   class="bg-white border text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-500 dark:placeholder-gray-400 dark:text-gray dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                   placeholder="Type product name" value="${item.getProducttittle()}" required="">
                        </div>

                        <div class="col-span-2">
                            <label for="price" class="block mb-2 text-sm font-medium text-gray-900">Price</label>
                            <input type="number" name="price" id="price"
                                   class="bg-white border text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-500 dark:placeholder-gray-400 dark:text-gray dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                   placeholder="$2999" value="${item.getProductprice()}" required="">
                        </div>

                        <div class="col-span-2 ">
                            <label for="category" class="block mb-2 text-sm font-medium text-gray-900">Category</label>
                            <select id="category" name="category"
                                    class="bg-white border text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-500 dark:placeholder-gray-400 dark:text-gray dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                    >
                                <option value="1" ${item.getCategoryName() == "Phone" ? 'selected' : ''}>Phone</option>
                                <option value="2" ${item.getCategoryName() == "Clothes" ? 'selected' : ''}>Clothes</option>
                                <option value="3" ${item.getCategoryName() == "Laptop" ? 'selected' : ''}>Laptop</option>
                            </select>
                        </div>

                        <div class="col-span-2">
                            <label for="description" class="block mb-2 text-sm font-medium text-gray-900">Product Description</label>
                            <textarea id="description" name="description" rows="4"
                                      class="bg-white border text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-500 dark:placeholder-gray-400 dark:text-gray dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                      placeholder="Write product description here">${item.getDescription()}</textarea>
                        </div>

                        <div class="col-span-2">
                            <label for="image" class="block mb-2 text-sm font-medium text-gray-900">Product Image</label>
                            <input type="file" id="image" name="image" accept="image/*"
                                   class="bg-white border text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-500 dark:placeholder-gray-400 dark:text-gray dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                   value="${item.getProductimg()}"><br>
                            <c:if test="${item.getProductimg() != null}">
                                <img src="upload/${item.getProductimg()}.jpg">
                            </c:if>

                            <div class="col-span-2 flex justify-end">
                                <button type="submit" class="text-white inline-flex items-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>


