<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원두창고</title>
<style>
	.container~div{
		float: left;
		border: 1px solid;
		margin: 1px;
	}
	.container{
		width:800px;
		height:800px;
		display: flex;
		justify-content: center;
	}
	.box{
		width:800px;
		height:150px;
		text-align : center;
		line-height : 150px;
	}
	.cate{
		margin: 5px;
		width:390px;
		height:200px;
	}
	.order{
		margin: 2px;
		border: 1px solid;
		width: 156px;
		height: 100px;
		text-align : center;
		line-height: 100px;
	}
	
	.outer{
		width: 750px;
		height: 50px;
	}
	.inner{
		width: 100px;
		float: left;
	}
</style>

<%

	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userId = null;
	int admin=1;
	if (sessDto != null) {
	    userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	}

%>
<script>
	function ale(){
		alert("로그인해주세요.")
		location.href="/firstProj/member/LoginForm";
		
		
	}

</script>

</head>
<body>
<h1>고객센터</h1>
<div class="container">
<!-- 	<h2>나의 주문처리 현황</h2> -->
	<!-- <div class="box">
		<div class="order">결제대기</div>
		<div class="order">배송중</div>
		<div class="order">배송완료</div>
		<div class="order">취소/교환/환불</div>
	</div> -->
	<div class="cate"  style="width: 500px">
	<a href="/firstProj/notice/NoticeList?admin=<%=admin %>">
		<img src="../fff/공지3.png" class="img-fluid" alt="...">
		<!-- <div class="cate">공지사항</div> -->
	</a>
	</div>
	<c:choose>
		<c:when test="${sessDto == null }">
		<div class="cate"><a href="javascript:ale()"><img src="../fff/문의하기2.jpg" class="img-fluid" alt="..."></a></div>
		</c:when>
		<c:otherwise>
		<div class="cate"><a href="/firstProj/board/BoardList?admin=<%=admin %>&userId=<%=userId%>">
		<img src="../fff/문의하기2.jpg" class="img-fluid" alt="..."></a></div>
		</c:otherwise>
	</c:choose>
	
	
</div>
</body>
</html>