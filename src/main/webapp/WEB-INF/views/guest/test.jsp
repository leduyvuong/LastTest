<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<%@include file="../sources/head" %></head>
</head>
<body>
<%@include file="../sources/header" %>
	<h3 style="height: 200px">Vui lòng vào mail để xác nhận đơn hàng</h1>
<!----footer----->
<%@include file="../sources/footer" %>
<!----js for MenuToggle()----->
<script>
        var MenuItems = document.getElementById("MenuItems");
        MenuItems.style.maxHeight = "0px";

        function menuToggle() {
            if (MenuItems.style.maxHeight == "0px") {
                MenuItems.style.maxHeight = "200px";
            } else {
                MenuItems.style.maxHeight = "0px";
            }
        }
</script>	
</body>
</html>