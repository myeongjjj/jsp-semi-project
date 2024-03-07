<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto_p.MemberDTO"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
		<a href="OrderDetail?deliveryStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary" aria-current="page">주문내역조회</a>
		<a href="OrderRefund?orderStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary">취소/환불내역</a>
		<a href="OrderHistory?start=&end=&admin=<%=admin %>" class="btn btn-secondary active">과거주문내역</a>
	</div>
</div>
<br/>
<br/>
<form action="" class="row g-3 form-control-sm">
		<div class="col-auto">
			<input type="date" name="start"> ~ <input type="date" name="end">
		</div>
		<div class="col-auto">
			<input type="submit" value="조회"/>
		</div>
</form>
<hr style="margin:0; padding:0;" />
<div>
<font size="1" color="fff">주문처리완료 후 36개월 이내의 최근 주문건은 주문내역조회 탭에서 확인할 수 있습니다.<br/>
상품구매금액은 주문 당시의 판매가와 옵션추가금액의 합(부가세 포함)에 수량이 반영된 값입니다.<br/></font>
</div>
<hr style="margin:0; padding:0;" />
<h4>과거 주문 내역</h4>
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
      <th scope="col">취소/환불</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
  	<c:forEach items="${mainData}" var="dto" varStatus="no">
	<tr>
		<th scope="row">${dto.orderNum }</th>
		<td>${dto.orderDateStr }</td>
		<td><img style="width:100px;" src="../fff/${dto.prodFile }"/></td>
		<td>${dto.prodTitle }</td>
		<td>${dto.orderCnt }</td>
		<td>${dto.prodPrice }</td>
		<td>${dto.deliveryStatus }</td>
		<td>${dto.orderStatus }</td>
	</tr>
	</c:forEach>
</table>

