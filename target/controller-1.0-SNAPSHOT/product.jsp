<!-- Sửa file này all ** -->
<%@ page language="java"
         contentType="text/html; charset=UTF-8;"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link href="styles/main.css" rel="stylesheet" type="text/css">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>

    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>

    <script type="text/javascript">
        //Filter Locations
        function setCheck(obj) {
            let i;
            var checkboxes = document.getElementsByName('cidd');
            if (obj.id==='c0' && obj.checked===true) {
                for (i = 1; i < checkboxes.length; i++) {
                    checkboxes[i].checked = false;
                }
            } else {
                for (i = 1; i < checkboxes.length; i++) {
                    if (checkboxes[i].checked===true) {
                        checkboxes[0].checked=false;
                        break;
                    }
                }
            }
            document.getElementById('f1').submit();
        }
    </script>
</head>
<body>
    <%@include file="/components/navbar.jsp" %>
    <div class="container">
        <div class="shop-search-filter">
            <a class="shop-search-filter__header" href="#">
                <box-icon name='filter-alt' ></box-icon>
                <strong><p>Bộ lọc tìm kiếm</p></strong>
            </a>
            <%@include file="/filterProduct/FilterLocation.jsp" %>
            <%@include file="/filterProduct/FilterCate.jsp" %>
            <%@include file="/filterProduct/FilterRating.jsp" %>
        </div>
        <div class="flex-container">
            <!-- filter bar -->
            <div class="block-shop-sort-bar">
                <div class="shop-sort-bar">
                    <span class="shop-sort-bar__label">Sắp xếp theo</span>
                    <div class="shop-sort-by-options">
                        <%@include file="/filterProduct/FilterSelling.jsp" %>
                        <%@include file="/filterProduct/FilterPrice.jsp" %>
                    </div>
                </div>
            </div>
            <br>
            <%@include file="/filterProduct/ListProduct.jsp" %>
        </div>
    </div>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</body>
</html>
