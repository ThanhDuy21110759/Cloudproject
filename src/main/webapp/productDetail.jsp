<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="components/navbar.jsp" %>
    <div class="flex max-h-[600px] min-h-[300px] items-stretch px-[10%]">
        <div class="flex w-6/12 flex-col items-center p-4">
            <div class="mb-1 flex h-full w-5/6 items-center overflow-hidden rounded-lg border">
                <img class="h-full w-full object-cover"
                     src="${pageContext.request.contextPath}/upload/${product.getProductimg()}.jpg"/>
            </div>
        </div>

        <div class="w-6/12 overflow-scroll px-10 py-5 text-left leading-8">
            <h1 class="text-3xl font-bold">${product.getProducttittle()}</h1>
            <p class="text-lg italic">$${product.getProductprice()}</p>
            <a href="<%=request.getContextPath()%>/cartServlet?Id=<%= request.getParameter("Id")%>">
                <button class="my-5 w-full rounded-lg bg-blue-500 px-4 py-2 font-bold text-white hover:bg-blue-700">
                    Buy now
                </button>
            </a>

            <div class="mb-5">
                <h3 class="text-xl font-bold">Item description</h3>
                <hr class="border-[1px] border-gray-400" />
                <p class="px-1 py-2 text-sm">${product.getDescription()}</p>
            </div>

            <div class="mb-5">
                <h3 class="text-xl font-bold">Item Details</h3>
                <hr class="border-[1px] border-gray-400" />
                <div class="px-0">
                    <div class="grid grid-cols-2">
                        <h4 class="font-bold">Catergory</h4>
                        <p class="px-2">${product.getCategoryName()}</p>
                    </div>
                    <div class="grid grid-cols-2">
                        <h4 class="font-bold">Standard shipping cost</h4>
                        <p class="px-2">$10.00</p>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <h3 class="mb-2 text-xl font-bold">Seller</h3>

                <div class="flex items-center gap-4 rounded-md border bg-blue-200 px-3 py-2">
                    <img src="https://images.unsplash.com/photo-1438761681033-6461ffad8d80?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NzEyNjZ8MHwxfHNlYXJjaHwyfHxhdmF0YXJ8ZW58MHwwfHx8MTY5MTg0NzYxMHww&ixlib=rb-4.0.3&q=80&w=1080" class="h-16 w-16 rounded-full object-cover object-center" />
                    <div class="w-fit">
                        <h1 class="font-bold">${product.getShop().getShopname()}</h1>
                    </div>
                </div>
            </div>

            <div>
                <h3 class="mb-2 text-xl font-bold">Comment</h3>
                <c:forEach var = "i" items="${listComment}" >
                    <div class="mb-3 flex items-start gap-4 rounded-md border px-3 py-2">
                        <img src="https://images.unsplash.com/photo-1438761681033-6461ffad8d80?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NzEyNjZ8MHwxfHNlYXJjaHwyfHxhdmF0YXJ8ZW58MHwwfHx8MTY5MTg0NzYxMHww&ixlib=rb-4.0.3&q=80&w=1080" class="h-16 w-16 rounded-full object-cover object-center" />
                        <div class="w-fit">
                            <h1 class="font-bold">${i.getCusNamebyId()}</h1>
                            <div class="flex p-2">
                                <c:forEach begin="1" end="${i.getRating()}" varStatus="loop">
                                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#f59e0b">
                                        <!-- SVG path here -->
                                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                    </svg>
                                </c:forEach>
                                <!-- Unfilled stars -->
                                <c:forEach begin="${i.getRating()+1}" end="5" varStatus="loop">
                                    <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                                        <!-- SVG path here -->
                                        <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                    </svg>
                                </c:forEach>
                            </div>
                            <div class="flex items-center gap-4 rounded-md border bg-blue-200 px-3 py-2">
                                <p>${i.getComment()}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <section class="bg-white py-8 antialiased">
                    <div class="mx-auto max-w-2xl">
                        <h3 class="mb-2 text-xl font-bold">Your Comment</h3>

                        <form class="mb-6" action="reviewServlet" method="get">
                            <input type="hidden" name="Id" value="<%= request.getParameter("Id")%>"/>
                            <%--Start rating--%>
                            <div class="stars flex mb-2 px-2">
                                <input class="rateValue" name="userRating" type="hidden" value="" />
                                <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#000000">
                                    <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                </svg>
                                <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#000000">
                                    <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                </svg>
                                <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#000000">
                                    <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                </svg>
                                <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#000000">
                                    <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                </svg>
                                <svg height="15" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="#000000">
                                    <path d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"></path>
                                </svg>
                            </div>
                            <div class="mb-4 rounded-lg rounded-t-lg border border-gray-200 bg-white px-4 py-2">
                                <label for="comment" class="sr-only"></label>
                                <textarea id="comment" rows="6"
                                          class="w-full border-0 px-0 text-sm text-gray-900 focus:outline-none focus:ring-0"
                                          placeholder="Write a comment..."
                                          name="comment" value=""></textarea>

                            </div>
                            <% if (CustomerDB.isLogin) { %>
                                <button type="submit" class="focus:ring-primary-200 hover:bg-primary-800 inline-flex items-center rounded-lg bg-blue-700 px-4 py-2.5 text-center text-xs font-medium text-white focus:ring-4">Post comment</button>
                            <% } else { %>
                            <button type="submit" class="focus:ring-primary-200 hover:bg-primary-800 inline-flex items-center rounded-lg bg-gray-400 px-4 py-2.5 text-center text-xs font-medium text-white focus:ring-4" disabled>Post comment</button>
                            <% } %>

                        </form>
                    </div>
                </section>
                <script>
                    const stars = document.querySelectorAll(".stars svg");
                    const ratingValue = document.querySelector(".rateValue");

                    stars.forEach((star, index1) =>{
                        star.addEventListener("click", ()=>{
                            stars.forEach((star, index2) =>{
                                if(index1 >= index2){
                                    star.setAttribute("fill", "#f59e0b");
                                    ratingValue.setAttribute("value", (index1+1).toString());
                                }else{
                                    star.setAttribute("fill", "#000000");
                                }
                            })
                        })
                    })
                </script>
            </div>

        </div>
    </div>
</body>
</html>
