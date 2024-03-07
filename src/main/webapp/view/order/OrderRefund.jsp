<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto_p.MemberDTO"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<% String orderStatus = request.getParameter("orderStatus"); %>
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
		<a href="OrderRefund?orderStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary active">취소/환불 내역</a>
		<a href="OrderHistory?start=&end=&admin=<%=admin %>" class="btn btn-secondary">과거주문내역</a>
	</div>
</div>
<br/>
<form action="" class="row g-3 form-control-sm">
		<div class="col-auto">
			<select name="orderStatus" aria-label="Small select example" style="width:200px;">
				<option value="">전체보기</option>
				<option value="취소">취소</option>
				<option value="환불">환불</option>
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
 기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 주문처리완료 후 36개월 이내의 주문내역을 조회하실 수 있습니다.<br/>
 완료 후 36개월 이상 경과한 주문은 [과거주문내역]에서 확인할 수 있습니다.<br/>
 주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.<br/>
 취소/교환/반품 신청은 주문 완료일 기준 30일까지 가능합니다.<br/></font>
</div>
<hr style="margin:0; padding:0;" />
<h4>취소/반품/교환 내역</h4>
<hr style="margin:0; padding:0;" />
<h6><B> ▶ <%=orderStatus %>  </B></h6>
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

