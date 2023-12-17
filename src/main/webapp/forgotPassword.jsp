<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>
</head>
<body>
    <section class="bg-gray-50 dark:bg-gray-900">
        <div class="mx-auto flex flex-col items-center justify-center px-6 py-8 md:h-screen lg:py-0">
            <a href="#" class="mb-6 flex items-center text-2xl font-semibold text-gray-900 dark:text-white">
                <img class="mr-2 h-8 w-8" src="https://drive.google.com/uc?export=view&id=1W5YSNi0VnfxXZalgZMJln6TTJQk1ZPbh" alt="logo" />
                ShopSavvy
            </a>
            <div class="w-full rounded-lg bg-white shadow dark:border dark:border-gray-700 dark:bg-gray-800 sm:max-w-md md:mt-0 xl:p-0">
                <div class="space-y-4 p-6 sm:p-8 md:space-y-6">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 dark:text-white md:text-2xl">Forgot your password?</h1>

                    <form method="post" action="EmailSendingServlet">
                        <input type="hidden" name="action" value="forgotPassword">
                        <div>
                            <label for="userName" class="mb-2 block text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                            <input type="email" name="userName" id="userName" placeholder="Your Email"
                                   class="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500 sm:text-sm" required/>

                        </div>
                        <div>
                            <p class="text-red-400">${msg}</p>
                        </div><br>
                        <button type="submit"
                                class="bg-blue-600 hover:bg-primary-700 focus:ring-primary-300 dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800 w-full rounded-lg px-5 py-2.5 text-center text-sm font-medium text-white focus:outline-none focus:ring-4"
                        >Reset password</button><br><br>
                        <a href="${pageContext.request.contextPath}/login.jsp" class="text-gray-500 text-sm font-medium hover:underline">  Back to Login</a>
                    </form>
                </div>
            </div>
        </div>
    </section>

</body>
</html>
