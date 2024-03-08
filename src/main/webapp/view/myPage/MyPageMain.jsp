<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원두창고</title>
<%
	
	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userId = "";
	int admin = 1;
	if (sessDto != null) {
	 	userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	}

%>
<body>
<h3><B>마이페이지</B></h3>
<hr/>
<div class="row g-4" style="width:1000px; margin:auto;">
  <div class="card" style="width: 10rem; margin:auto;">
  	<img src="../fff/beforePay.png" class="card-img-top" alt="...">
  	<div class="card-body">
    	<h5 class="card-title">결제대기</h5>
    	<p class="card-text">현재 결제 대기중인 상품 정보</p>
    	<a href="/firstProj/order/OrderDetail?deliveryStatus=결제대기&start=&end=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
  	</div>
	</div>
  <div class="card" style="width: 10rem; margin:auto;">
  	<img src="../fff/deliverying.png" class="card-img-top" alt="...">
  	<div class="card-body">
    	<h5 class="card-title">배송중</h5>
    	<p class="card-text">현재 배송중인 상품 정보</p>
    	<a href="/firstProj/order/OrderDetail?deliveryStatus=배송중&start=&end=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
  	</div>
	</div>
  <div class="card" style="width: 10rem; margin:auto;">
  	<img src="../fff/delivery.png" class="card-img-top" alt="...">
  	<div class="card-body">
    	<h5 class="card-title">배송완료</h5>
    	<p class="card-text">배송 완료된 상품 정보</p>
    	<a href="/firstProj/order/OrderDetail?deliveryStatus=배송완료&start=&end=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
  	</div>
	</div>
  <div class="card" style="width: 10rem; margin:auto;">
  	<img src="../fff/refund.png" class="card-img-top" alt="...">
  	<div class="card-body">
    	<h5 class="card-title">취소/교환/환불</h5>
    	<p class="card-text">취소/환불 관련 정보</p>
    	<a href="/firstProj/order/OrderRefund?orderStatus=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
  	</div>
	</div>
  
</div>

<div class="row row-cols-1 row-cols-md-2 g-4" style="width:1000px; margin:auto;">
  <div class="col">
    <div class="card">
      <img src="../fff/orderDetail.png" class="card-img-top" alt="..." style="width:300px; margin:20px auto;">
      <div class="card-body">
        <h5 class="card-title">주문 내역</h5>
        <p class="card-text">주문 내역, 취소/환불 내역, 과거 주문 내역을 조회할 수 있습니다</p>
        <a href="/firstProj/order/OrderDetail?deliveryStatus=&start=&end=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
      </div>
    </div>
  </div>
  <div class="col" >
    <div class="card">
      <img  src="../fff/profile.png" class="card-img-top" alt="..." style="width:300px; margin:20px auto;">
      <div class="card-body">
        <h5 class="card-title">회원 정보</h5>
        <p class="card-text">회원 정보 조회 및 비밀번호를 변경할 수 있습니다</p>
        <a href="/firstProj/profile/ProfileDetail?admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img src="../fff/board.png" class="card-img-top" alt="..." style="width:300px; margin:20px auto;">
      <div class="card-body">
        <h5 class="card-title">1:1 상담</h5>
        <p class="card-text">나의 1:1 상담 내역을 조회할 수 있습니다</p>
        <a href="/firstProj/board/BoardList?admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card">
      <img src="../fff/review.png" class="card-img-top" alt="..." style="width:300px; margin:20px auto;">
      <div class="card-body">
        <h5 class="card-title">리뷰 관리</h5>
        <p class="card-text">내가 쓴 리뷰들을 조회할 수 있습니다</p>
        <a href="/firstProj/product/ReviewDetail?userId=&admin=<%=admin %>" class="btn btn-secondary">바로가기</a>
      </div>
    </div>
  </div>
</div>

</body>
</html>