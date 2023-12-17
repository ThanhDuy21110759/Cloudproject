<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Display</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>

    <script>
        // Turn off defaut submit repeat window
        if ( window.history.replaceState ) {
            window.history.replaceState( null, null, window.location.href );
        }
    </script>
</head>
<body class="bg-gray-200">
    <%@include file="components/navbar.jsp" %>

    <%--Home page--%>
    <div class="py-10 px-20">
        <!-- Banner -->
        <div class="flex items-center h-auto w-full text-white py-10 px-16 rounded-lg
                        bg-gradient-to-r from-blue-500 via-cyan-500 to-pink-500" >
            <!-- Bio -->
            <div class="items-center w-3/5 h-full py-auto">
                <p class="font-bold text-6xl mb-2 uppercase">
                    <span class="text-pink-300">Velocity Racer</span><br/>
                    Shoes
                </p>

                <p class="text-s mb-3 px-3">
                    Crafted with premium leather, these shoes boast a sleek black exterior that exudes sophistication.
                    The durable rubber outsoles ensure a firm grip on any surface.</p>

                <button class="bg-cyan-500 hover:bg-teal-500 text-white font-bold text-xl py-3 px-10 uppercase rounded-full mb-7">
                    Shop now
                </button>

                <!-- icons -->
                <div class="flex space-x-2 px-3">
                    <i class="fa-brands fa-square-facebook fa-2xl"></i>
                    <i class="fa-brands fa-instagram fa-2xl"></i>
                    <i class="fa-brands fa-tiktok fa-2xl"></i>
                </div>
            </div>

            <!-- Img item -->
            <div class="container w-2/5 h-full m-auto py-5 flex justify-end">
                <div class="rounded-full border border-solid border-8 border-blue-500 shadow-lg shadow-sky-300">
                    <div class="border-4 border-transparent">
                        <img
                                class="h-full object-contain bg-blue-400 rounded-full border border-solid border-4 border-white"
                                src="https://drive.google.com/uc?export=view&id=1f1us4qBrNx47gSHLYFPPPK-MHZEd23O5" />
                    </div>
                </div>
            </div>
        </div>
        <!-- Hint product-->
        <%@include file="/components/Recommendation.jsp" %>
    </div>
    <!-- Footer -->
    <%@include file="/components/footer.jsp" %>
</body>
</html>
