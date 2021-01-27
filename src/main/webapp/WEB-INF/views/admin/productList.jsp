<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="resourcesAdmin/inc/header.html" %>
<%@include file="resourcesAdmin/inc/sidebar.html" %>
</head>
<body>
	<div class="grid_10">
    <div class="box round first grid">
        <h2>Post List</h2>
        <div class="block">
        
            <table class="data display datatable" id="example">
			
				<tr>
					<th>Name Product</th>
					<th>Price</th>
					<th>Orignal Price</th>
					<th>Image</th>
					<th>Action</th>
				</tr>
			
			<c:forEach var="prd" items="${list }" >
				<tr class="odd gradeX">
					
					<td>${prd.getName_Prd() }</td>
					<td>${prd.getPrice_Prd() }</td>
					<td>${prd.getOriginal_price() }</td>
					<c:forEach var="type" items="${type }" >
						<c:if test="${ prd.getId_type()==type.getId_prd_type()}">
							<td>${type.getName_prd_type() }</td>
							
						</c:if>
					</c:forEach>
					<td  class="center"><img style="width: 50px;height: 50px" src='<c:url value="/resources/img/products/${prd.getImg_Prd() }" ></c:url>' alt=""></td>
					
					<td><a href="edit?id=${prd.getName_Prd() }">Edit</a> || <a href="productDelete?id=${prd.getName_Prd() }">Delete</a></td>
					
				</tr>
			</c:forEach>
			
		</table>

       </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        setupLeftMenu();
        $('.datatable').dataTable();
		setSidebarHeight();
    });
</script>
<%@include file="resourcesAdmin/inc/footer.html" %>	
</body>
</html>