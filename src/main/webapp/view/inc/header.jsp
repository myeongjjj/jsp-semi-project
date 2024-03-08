<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>

</head>
<%
	
	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userName = "";
	String userId = "";
	int admin = 1;
	int status = 1;
	if (sessDto != null) {
	    userName = sessDto.getUserName();
	    userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	    status = sessDto.getStatus();
	}

%>
<script>
	function ale(){
		alert("로그인해주세요.")
		location.href="/firstProj/member/LoginForm";
		
		
	}

</script>
<body>
<c:choose>
	<c:when test="${sessDto !=null}">
		<div id="header"><%=userName %>님 반갑습니다 | <a href="/firstProj/logout/LogOut?userId=<%=userId %>">로그아웃</a> | <a href="/firstProj/notice/NoticeMain?admin=<%=admin %>">고객센터</a></div>
	</c:when>
	<c:otherwise>
	<div id="header"><a href="/firstProj/member/MemberForm">회원가입</a> | <a href="/firstProj/member/LoginForm">로그인</a> | <a href="/firstProj/notice/NoticeMain?admin=<%=admin %>">고객센터</a></div>
	
	</c:otherwise>

</c:choose>

<div id="logo">
	<div id="void"><a href="/firstProj/main/Main?admin=<%=admin %>"><img src="<c:url value="/view/inc/img/main_logo.png"/>" alt=""></a></div>
	<ul id="logo_list">
	</ul>
	<div id="void">
	 	<c:if test="${param.admin == 0 }">
			<a href="/firstProj/admin/Admin?admin=<%=admin %>">
				<svg xmlns="http://www.w3.org/2000/svg" width="50px" height="44px" fill="currentColor" class="bi bi-window" viewBox="0 0 16 16">
					<path d="M2.5 4a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1zm2-.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm1 .5a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1z"/>
					<path d="M2 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2zm13 2v2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1zM2 14a1 1 0 0 1-1-1V6h14v7a1 1 0 0 1-1 1H2z"/>
				</svg><br>
				관리자
			</a>
	    </c:if>
		<c:if test="${sessDto !=null && param.admin == 1}">
			<a href="/firstProj/cart/Cart?userId=<%=userId %>&admin=<%=admin %>" style="margin-left: 20px; margin-right: 20px;">
				<svg xmlns="http://www.w3.org/2000/svg" width="64px" height="44px" fill="currentColor" class="bi bi-basket" viewBox="0 0 16 16">
				  <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z"/>
				</svg><br>
				장바구니
			</a>
			<a href="/firstProj/myPage/MyPageMain?admin=<%=admin %>">
				<svg xmlns="http://www.w3.org/2000/svg" width="82px" height="44px" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
					<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
					<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
				</svg><br>
			마이페이지
			</a>
		</c:if>
	</div>
</div>
<div id="category" class="navbar bg-body-tertiary" style="background-color: #e3f2fd;">
	<ul id="cate_list">
		<li id="cli"><a href="/firstProj/product/ProductList?prodCate=블랜드&admin=<%=admin %>">블랜드</a></li>
	    <li id="cli"><a href="/firstProj/product/ProductList?prodCate=싱글오리진&admin=<%=admin %>">싱글 오리진</a></li>
	    <li id="cli"><a href="/firstProj/product/ProductList?prodCate=디카페인&admin=<%=admin %>">디카페인</a></li>
	</ul>
</div>
</body>
</html>

