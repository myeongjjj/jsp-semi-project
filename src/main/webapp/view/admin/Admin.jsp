<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<style>
	.scr{
		border: 1px solid #fff;
		width: 1200px;
		height: 250px;
		overflow-y: scroll;
	}
	.bot{
		position: sticky; 
		bottom: 0;
	}
	.top{
		position: sticky; 
		top: 0;
	}
</style>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>

</script>
<meta charset="UTF-8">
<title>관리자</title>
</head>
<body>
<h1>들어온 주문</h1>
<div class="scr">
	<table class="table table-striped-columns table-hover">
		<tr class="table-dark top">
			<td width="100px">주문번호</td>
			<td width="100px">주문날짜</td>
			<td width="100px">유저</td>
			<td width="250px">상품정보</td>
			<td width="150px">배송상태</td>
			<td width="150px">운송장</td>
			<td width="200px">취소/반품/환불 여부</td>
		</tr>
		<c:forEach items="${orderData}" var="dto" varStatus="no">
		<tr>
			<td>${dto.orderNum }</td>
			<td>${dto.orderDate }</td>
			<td>${dto.userId }</td>
			<td><a href="AdminDeli?admin=0&orderNum=${dto.orderNum }">${dto.prodTitle }</a></td>
			<td>${dto.deliveryStatus }</td>
			<td>${dto.wayBill }</td>
			<td>${dto.orderStatus }</td>
		</tr>
		</c:forEach>
		<tr>
		</tr>
	</table>
</div>
<h1>매출</h1>
<form action="">
<input type="month" name="start"> ~ <input type="month" name="end">
<input class="btn btn-dark" type="submit" value="조회">
<div class="scr">
<table class="table table-striped-columns">
	<tr class="table-dark top">
		<td width="150px">주문일자</td>
		<td width="100px">주문번호</td>
		<td width="550px">상품정보</td>
		<td width="100px">수량</td>
		<td width="150px">금액</td>
		<td width="150px">총액</td>
	</tr>
	<c:forEach items="${mainData}" var="dto" varStatus="no">
	<tr>
		<td>${dto.orderDate }</td>
		<td>${dto.orderNum }</td>
		<td>${dto.prodTitle }
		${dto.prodCate }</td>
		<td>${dto.orderCnt }</td>
		<td>${dto.prodPrice }</td>
		<td>${dto.tot }</td>
	</tr>
	</c:forEach>
	<tr>
	<!-- <td colspann="6"><br/><br/></td> -->
	</tr>
	<tr class="bot">
		<td colspan="5" align="left">통계</td>
		<td>${tData}</td>
	</tr>
</table>
</div>
</form>
</body>
</html>