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
	<a>${i }</a>
	<c:if test="${ i == 0}">
		<a style="text-align: center;"> Nhập tên tài khoản</a>
		<form method="get" action="getName" >
		
		<input name = "userName" type="text" style="width: 150px;height: 30px;margin: 10px 0;padding: 0 10px;border: 1px solid #ccc;">
		<input  type="submit" >
		
		</form>
	</c:if>
	<c:if test="${i == 1 }">
		<a>Please enter your code in mail ${mail}</a>
		<form action="cofirm" method="get">
			<a style="color: red;">${err }</a>
			<input name = "code" type="number" placeholder="Your code">
			<input type="submit">
		</form>
	</c:if>
	<c:if test="${i == 2 }">
		<form action="changeP" method="post">
			<a>Enter new password</a>
			<input id="newPass" name="newPass" type="password" placeholder="New Password" onkeyup="check();">
			<a>Confirm your password</a>
			<input id="CPass" name="CPass" type="password" placeholder="Cofirm Password" onkeyup="check();">
			<span id="mess"></span>
			<input type="submit" id ="submit" disabled />
		</form>
	</c:if>
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
        function check() {
			if (document.getElementById("newPass").value == document.getElementById("CPass").value)	{
				
				document.getElementById("mess").style.color = "green";
				document.getElementById("mess").innerHTML = "matching";
				document.getElementById("submit").disabled = false;

			} else {
				document.getElementById("mess").style.color = "red";
				document.getElementById("mess").innerHTML = "not matching";
				document.getElementById("submit").disabled = true;
			}
			

       	}
</script>	
</body>
</html>