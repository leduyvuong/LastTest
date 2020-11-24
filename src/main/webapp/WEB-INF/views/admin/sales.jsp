<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<%@include file="resourcesAdmin/inc/header.html" %>
<%@include file="resourcesAdmin/inc/sidebar.html" %>
</head>
<body>
	<div class="grid_10">
		
		<div class="box round first grid">
			<div class="block">
				<c:if test="${list.size() > 0 }">
				<table class="data display datatable" style="align-items: center;" id="example">
						<tr>
							<th>Day</th>
							<th>Description</th>
							<th>Total</th>					
						</tr>
						<c:forEach var="list" items="${list }" >
						<tr>
							<td>${list.getDate_of_sale() }</td>
							<td>${list.getDescription() }</td>
							<td>${list.formatVND(list.getTotal()) }</td>
						</tr>
						</c:forEach>
						<c:if test="${sub != 0 }">
							<tr style="border-top: 2px solid ;">	
									<td></td>
									<td style="color: red">Sub Total</td>									
									<td style="color: red">${list.get(0).formatVND(sub)}</td>
							</tr>
						</c:if>
				</table>
				
				</c:if>
				<c:if test="${result.size() > 0 }">
					<table class="data display datatable" style="align-items: right ;" id="example">
						<tr>
							<th>Month</th>
							<th>Description</th>
							<th>Status</th>					
						</tr>
						<c:forEach var="m" begin="1" end ="12" >
						<tr>
							<td><a href="saleM?month=${m}">${m }</a></td>
							<td>${ord.formatVND(result.get(m)) }</td>
							
							
							<c:if test="${result.get(m) < n }">
									<td style="color: red;">${ord.formatVND(result.get(m) - n) } </td>
							</c:if>
							<c:if test="${result.get(m) > n }">
									<td style="color: green;">+${ord.formatVND(result.get(m) - n) } </td>
							</c:if>
							<c:if test="${result.get(m) == n }">
									<td >---------- </td>
							</c:if>
							<c:set var ="n" value="${result.get(m)}"></c:set>
						</tr>
						</c:forEach>
						
				</table>
				</c:if>
			</div>
		</div>
	</div>
<%@include file="resourcesAdmin/inc/footer.html" %>	
</body>
</html>