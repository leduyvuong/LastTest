<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="../sources/head" %>
</head>
<body>

	<!------------header------------------>
    <%@include file="../sources/header" %>

    <!------------feature categories------------------>
    
    <div class="small-container">
        <div class="row row-2">
        	<c:if test="${errS != null}">
            <h2>${errS}</h2>
            </c:if>
            <form  action="search" method="post"  class="search-bar">
	            <input type="text" name = "word" placeholder="Search....">
	           
	            <div class="search"></div>
            </form>
            
            <select name="" id="">
                <option value=""><a>Defaut Shorting</a></option>
                <option value=""><a href="">Short by price</a></option>
                <option value="">Short by popularity</option>
                <option value="">Short by rating</option>
                <option value="">Short by sale</option>
                <option value="">${session.getName_customer() }</option>
            </select>
        </div>
        
        <div class="row">
        <c:forEach var ="a" items="${list }">
        
            <div class="col-4">
                <a href="detail?id=${a.getName_Prd()}">
                    <img src='<c:url value="/resources/img/products/${a.getImg_Prd() }" ></c:url>' alt="">
                    <h4>${a.getName_Prd() }</h4>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half"></i>
                    </div>
                    
                    <p>${a.formatVND() } </p>
                </a>
            </div>
             
           </c:forEach>
           </div>
       
    <div class="page-btn">
    <c:if test="${max>1 }">    	
    	<c:forEach var="i" begin="0" end="${max-1}" >
	        <a href="products?id=${id}&page=${i}">${i}</a>
        </c:forEach>
        <span>&#8594</span>
       
      </c:if>  
    </div>
    
    </div>
    
    <!------------feature categories------------------>

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