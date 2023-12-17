<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>
</head>
<body>
    <%
        String uName = "", pass = "", remember = "";
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cook: cookies){
                if(cook.getName().equals("cookUname")){
                    uName = cook.getValue();
                }else if(cook.getName().equals("cookPass")){
                    pass = cook.getValue();
                }else if (cook.getName().equals("cookRemember")){
                    remember = cook.getValue();
                }
            }
        }
    %>

    <section class="bg-gray-50 dark:bg-gray-900">
        <div class="mx-auto flex flex-col items-center justify-center px-6 py-8 md:h-screen lg:py-0">
            <a href="${pageContext.request.contextPath}/productServlet?action=hintProduct" class="mb-6 flex items-center text-2xl font-semibold text-gray-900 dark:text-white">
                <img class="mr-2 h-8 w-8" src="https://drive.google.com/uc?export=view&id=1W5YSNi0VnfxXZalgZMJln6TTJQk1ZPbh" alt="logo" />
                ShopSavvy
            </a>
            <div class="w-full rounded-lg bg-white shadow dark:border dark:border-gray-700 dark:bg-gray-800 sm:max-w-md md:mt-0 xl:p-0">
                <div class="space-y-4 p-6 sm:p-8 md:space-y-6">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 dark:text-white md:text-2xl">Sign in to your account</h1>
                    <form class="space-y-4 md:space-y-6" action="loginServlet">
                        <input type="hidden" name="action" value="login" />
                        <div>
                            <label for="userName" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">User name</label>
                            <input type="text" name="userName" id="userName" value="<%=uName%>" placeholder="YourUserName"
                                   class="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500 sm:text-sm" />
                        </div>
                        <div>
                            <label for="password" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">Password</label>
                            <input type="password" name="password" id="password" value="<%=pass%>" placeholder="••••••••"
                                   class="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500 sm:text-sm" required="" />
                        </div>

                        <div>
                            <p class="text-red-400">${message}</p>
                        </div>

                        <div class="flex items-center justify-between">
                            <div class="flex items-start">
                                <div class="flex h-5 items-center">
                                    <input type="checkbox" name="remember" value="stayRemember"
                                           <%= remember.equals("stayRemember") ? "checked='/checked'" : "" %>
                                           class="focus:ring-3 focus:ring-primary-300 dark:focus:ring-primary-600 h-4 w-4 rounded border border-gray-300 bg-gray-50 dark:border-gray-600 dark:bg-gray-700 dark:ring-offset-gray-800" />
                                </div>
                                <div class="ml-3 text-sm">
                                    <label class="text-gray-500 dark:text-gray-300">Remember me</label>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/forgotPassword.jsp" class="text-gray-500 text-sm font-medium hover:underline">Forgot password?</a>
                        </div>
                        <button type="submit"
                                class="bg-blue-600 hover:bg-primary-700 focus:ring-primary-300 dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800 w-full rounded-lg px-5 py-2.5 text-center text-sm font-medium text-white focus:outline-none focus:ring-4"
                        >Sign in</button>
                        <p class="text-sm font-light text-gray-500 dark:text-gray-400">Don’t have an account yet? <a href="${pageContext.request.contextPath}/editUserServlet?action=INSERT" class="text-primary-600 dark:text-primary-500 font-medium hover:underline">Sign up</a></p>
                    </form>
                </div>
            </div>
        </div>
    </section>

</body>
</html>
