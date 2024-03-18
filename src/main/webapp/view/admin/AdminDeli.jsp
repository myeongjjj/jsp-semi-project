<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<form name="frm" action="AdminDeliReg" method="post" enctype="multipart/form-data">
<table class="table table-striped-columns" style="overflow-y: scroll;" >
	<tr class="table-dark top">
		<td width="100px">주문번호</td>
		<td width="100px">아이디</td>
		<td width="250px">상품정보</td>
		<td width="150px">배송상태</td>
		<td width="150px">운송장</td>
		<td width="200px">취소/교환/반품</td>
	</tr>
	<c:forEach items="${adDeliData}" var="dto" varStatus="no">
	<tr>
		<td><input class="form-control" type="text" name="orderNum" value="${dto.orderNum }" readonly /></td>
		<td>${dto.userId }</td>
		<td>
		<c:forEach items="${deliProdData}" var="dto2" varStatus="no">
			${no.index + 1}. ${dto2.prodTitle }<br>
		</c:forEach>
		</td>
		<td>
		
		<select name="deliveryStatus" id="deliveryStatus" class="form-select">
		<c:forTokens var="deSt" items="결제완료,배송중,배송완료" delims=",">
 			<c:choose>
				<c:when test="${deSt==dto.deliveryStatus }">
					<option value="${deSt }" selected >${deSt }</option>
				</c:when>
				<c:otherwise>
					<option value="${deSt }">${deSt }</option>
				</c:otherwise>
			</c:choose>
		</c:forTokens>
		</select>
		</td>
		<td><input class="form-control" type="text" name="wayBill" value="${dto.wayBill }" /></td>
		<td>
		<select name="orderStatus" id="orderStatus" class="form-select">
		<c:forTokens var="orSt" items=" ,취소,반품,환불" delims=",">
 			<c:choose>
				<c:when test="${orSt==dto.orderStatus }">
					<option value="${orSt }" selected >${orSt }</option>
				</c:when>
				<c:when test="${orSt==' ' }">
					<option value="" selected ></option>
				</c:when>
				<c:otherwise>
					<option value="${orSt }">${orSt }</option>
				</c:otherwise>
			</c:choose>
		</c:forTokens>
		</select>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6" align="right">
			<input class="btn btn-dark" type="submit" value="수정" />
			<a class="btn btn-outline-danger" href="Admin?admin=0">취소</a>
		</td>
	</tr>
</table>
</form>