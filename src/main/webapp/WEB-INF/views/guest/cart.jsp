<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../sources/head" %>
</head>
<body>
<!------------header------------------>
<%@include file="../sources/header" %>
<div class="small-container cart-page">
		<h2>${thank }</h2>
        <c:if test="${listOr.size() > 0 }">
        <table>
            <tr>
                <th>Products</th>
                <th>Quanlity</th>
                <th>Subtotal</th>
            </tr>
            
            <c:forEach var = "cart" items="${listOr }">
            <tr>
                <td>
                    <div class="cart-info">
                        <img src='<c:url value="/resources/img/products/${cart.getImage_prd() }" ></c:url>' alt="">
                        <div>
                        
                            <p >${cart.getName_prd() }</p>
                            
                            <small>${cart.formatVND(cart.getPrice()) } </small>
                            <br>
                            <a href="cart/delete?id=${cart.getId_order_detail() }">Remove</a>
                        </div>
                    </div>
                </td>
                <td><p id="quantity" >${cart.getQuantity() }</p></td>
                <td><p id = "total">${cart.formatVND(cart.getTotal()) } </p></td>
            </tr>
            </c:forEach>
        </table>
        <div class="total-price">
            <table>
                <tr>
                    <td>Subtotal</td>
                    <td>${listOr.get(0).formatVND(subTotal) } </td>
                </tr>
                <tr>
                    <td colspan="2"><a href="infor" class="btn" >Mua</a></td>
                </tr>
            </table>
        </div>
        </c:if>
        <c:if test="${listOr.size() == 0 }">
        	<h1 style="height: 200px;">Chưa có mặt hàng nào đã đặt!</h1>
        </c:if>
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

</body>
</html>