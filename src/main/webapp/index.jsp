<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>

    <script>
        window.onload = (event) => {
            document.getElementById("loadingPage").submit();
        };
    </script>
</head>
<body>
    <p>Index.jsp</p>
    <form id="loadingPage" type="hidden" action="productServlet" method="post">
        <input type="hidden" name="action" value="hintProduct">
    </form>
</body>
</html>