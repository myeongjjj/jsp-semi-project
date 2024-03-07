<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto_p.MemberDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<% String deliveryStatus = request.getParameter("deliveryStatus"); %>

<script>
function orderCancel(orderNum){
	if(confirm("주문을 취소하시겠습니까?")){
		location.href = "OrderCancel?orderNum="+orderNum
	}
}

function orderClear(orderNum){
	if(confirm("환불 요청하시겠습니까?")){
		location.href = "OrderClear?orderNum="+orderNum
	}
}

function orderConfirm(orderNum){
	if(confirm("구매 확정하시겠습니까?\n확정 시 환불이 불가합니다.")){
		location.href = "OrderConfirm?orderNum="+orderNum
	}
}

</script>
<%
	
	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userId = "";
	int admin = 1;
	if (sessDto != null) {
	 	userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	}

%>

<br/>
<div class="d-flex justify-content-end">홈 > 마이쇼핑 > 주문조회 </div>
<div class="d-flex justify-content-center"><h3><B>주문 조회</B></h3></div>
<br/>
<div class="d-flex justify-content-evenly">
	<div class="btn-group">
		<a href="OrderDetail?deliveryStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary active" aria-current="page">주문내역조회</a>
		<a href="OrderRefund?orderStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary">취소/환불 내역</a>
		<a href="OrderHistory?start=&end=&admin=<%=admin %>" class="btn btn-secondary">과거주문내역</a>
	</div>
</div>
<br/>
	<form action="" class="row g-3 form-control-sm">
		<div class="col-auto">
			<select name="deliveryStatus" aria-label="Small select example" style="width:200px;">
				<option value=""></option>
				<option value="결제대기">결제대기</option>
				<option value="결제완료">결제완료</option>
				<option value="배송중">배송중</option>
				<option value="배송완료">배송완료</option>
				<option value="구매확정">구매확정</option>
			</select>
		</div>
		<div class="col-auto">
			<input type="date" name="start"> ~ <input type="date" name="end">
		</div>
		<div class="col-auto">
			<input type="submit" value="조회"/>
		</div>
	  </form>

<hr style="margin:0; padding:0;" />
<div>
<font size="1" color="fff">
 가장 최근 주문 순으로 주문내역이 조회됩니다. <br/>
 상세한 과거주문내역은 [과거주문내역]에서 확인할 수 있습니다.<br/>
 취소 신청은 배송 시작 전까지 가능하고 환불 신청은 배송완료 후 가능합니다.<br/>
 구매확정 후에는 취소/환불 모두 불가능합니다.<br/></font>
</div>
<hr style="margin:0; padding:0;" />
<h4>주문 상품 정보</h4>
<hr style="margin:0; padding:0;" />
<h6><B> ▶ <%=deliveryStatus %>  </B></h6>
<hr style="margin:0; padding:0;" />
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">주문번호</th>
      <th scope="col">주문일자</th>
      <th scope="col">이미지</th>
      <th scope="col">상품정보</th>
      <th scope="col">수량</th>
      <th scope="col">상품구매금액</th>
      <th scope="col">주문처리상태</th>
      <th scope="col">취소/교환/반품</th>
      <th scope="col">요청</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
  	<c:forEach items="${mainData}" var="dto" varStatus="no">
	<tr>
		<th scope="row">${dto.orderNum }</th>
		<td>${dto.orderDateStr }</td>
		<td><img style="width:100px;" src="../fff/${dto.prodFile }"/></td>
		<td><a href="/firstProj/product/ProductDetail?prodNum=${dto.prodNum}&admin=1">${dto.prodTitle }</a></td>
		<td>${dto.orderCnt }</td>
		<td>${dto.totalPrice }</td>
		<td>${dto.deliveryStatus }</td>
		<td>${dto.orderStatus }</td>
		<c:choose>
         <c:when test="${dto.deliveryStatus=='결제완료' && dto.orderStatus == '' }">
               <td><a href="javascript:orderCancel(${dto.orderNum })">취소 요청</a></td>
         </c:when>
         <c:when test="${dto.deliveryStatus=='배송완료' && dto.orderStatus == '' }">
               <td>
               		<a href="javascript:orderClear(${dto.orderNum })">환불 요청</a><br/>
               		<a href="javascript:orderConfirm(${dto.orderNum })">구매 확정</a>
               </td>
         </c:when>
         
         <c:otherwise>
               <td>--</td>   
         </c:otherwise>
      </c:choose>
		
	</tr>
	</c:forEach>
    
</table>