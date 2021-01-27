<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	
   <%@include file="../sources/head" %>
</head>
<body>
<!------------header------------------>
     <%@include file="../sources/header" %>
<!----Login page----->
<div class="account-page">
    <div class="container">
        <div class="row">
            <div class="col-2">
                <img src='<c:url value="/resources/img/frontend/new-banner.png" ></c:url>' alt="" width="100%" height="100%">
            </div>
            <div class="col-2">
                <div class="form-container">
                    <div class="form-btn">
                        <span onclick="login()">Register</span>
                        <span onclick="register()">Login</span>
                        <hr id="Indicator">
                    </div>
                    <p>${err }</p>
                    <form id="RegForm" method="post" action="register">
                    	<h4 style="color: red;">${errR}</h4>
                        <input name = "userReg" type="text" placeholder="Username " pattern="[A-Za-Z0-9]{6}" title="Ít nhất 6 ký tự">
                        <input name = "pass" type="password" placeholder="Password " pattern="[A-Za-Z0-9]{6}" min="6" title="Trên 6 ký tự" >
                        <input name = "full_name" type="text" placeholder="Full Name " required="required">
                        <input name="email" type="email" placeholder="Email " pattern="[a-zA-Z0-9]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" required="required">  
                        <input name = "address" type="text" placeholder="Address " required="required">
                        <input name = "phone" type="tel" placeholder="0123456789" pattern="[0-9]{10}" required="required">                         
                        <button type="submit" class="btn ">Register</button>
                    </form>
                    <form id="LoginForm" action="loginCheck" method="post">
                    	<h4 style="color: red;">${err}</h4>
                        <input name="user" type="text" placeholder="Username ">
                        <input name="pass" type="password" placeholder="Password ">
                        <input type="checkbox" class="checkBox">Remember Session</input>
                        <button type="submit" class="btn ">Login</button>                        
                        <a href="forgetP">Forgot Password</a>
                        <a class="btn"  href="https://www.facebook.com/dialog/oauth?client_id=923149301514261&redirect_uri=http://localhost:8080/com.jsp.shopaoquan/login-facebook">
							Login with Facebook
						</a>
	                    <a class="btn"  href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/com.jsp.shopaoquan/login-google&response_type=code
	    							&client_id=894453835091-ngnssakc3q9n2dnj6qv1h950o1m7d21k.apps.googleusercontent.com&approval_prompt=force">
	    						Login with Gmail
	    				</a> 
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
</div>

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
<!----js for toggle Form----->
<script>
    var LoginForm = document.getElementById("LoginForm");
    var RegForm = document.getElementById("RegForm");
    var Indicator = document.getElementById("Indicator");

    function login() {
        
        RegForm.style.transform = "translateX(300px)";
        LoginForm.style.transform = "translateX(300px)";
        Indicator.style.transform = "translateX(0px)";
    }

    function register() {
        RegForm.style.transform = "translateX(0px)";
        LoginForm.style.transform = "translateX(0px)";
        Indicator.style.transform = "translateX(100px)";
    }
</script>
</body>
</html>