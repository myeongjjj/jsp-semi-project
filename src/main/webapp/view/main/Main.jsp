<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<%
	
	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userId = null;
	int admin = 1;
	if (sessDto != null) {
	    userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	  
	}

%>
<html>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		$(".col").click(function(){
			/* alert("눌림") */
			var prodNum = $(this).data("prodnum");
			location.href="/firstProj/product/ProductDetail?prodNum=" + prodNum + "&admin="+<%=admin %>;
				
		})
	})
</script>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<c:url value="/view/main/img/main(1).jpg"/>" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block " style="color: #000; right: 55%; bottom: 3.25rem;">
        <h5>설렘가득! 달콤함이 가득한</h5>
        <h2>커피창고2월 이벤트</h2>
        <p>다양한 이벤트를 만나보세요.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/view/main/img/main(2).jpg"/>" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block" style="right: 55%; bottom: 3.25rem;">
        <h5>신규 사업자 가입 기념</h5>
        <h2>웰컴 혜택 증정 이벤트</h2>
        <p>첫 구매/7만원 이상 구매 시</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/view/main/img/main(3).jpg"/>" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block" style="color: #000; right: 55%; bottom: 3.25rem;">
        <h5>이달의 원두</h5>
        <h2>콜롬비아 수프리모 40%</h2>
        <p>#적포도 #초콜렛향 #달콤한시럽</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
<div>
<div id="category" class="navbar bg-body-tertiary" style="height: 100px" >
	<h2>이 상품 어때요? ></h2>
</div>
		<div class="row row-cols-1 row-cols-md-4 g-4" >
			<c:forEach items="${mainData}" var="dto" varStatus="no" begin="0" end="4">
				<div class="col" data-prodnum="${dto.prodNum}">
					<div class="card h-100">
						<c:choose>
						<c:when test="${not fn:startsWith(dto.prodFile, 'http')}">
							<img src="../fff/${dto.prodFile }" class="card-img-top">
						</c:when>
						<c:otherwise>
							<img src="${dto.prodFile }" class="card-img-top">
						</c:otherwise>
						</c:choose>
						<div class="card-body">
							<h5 class="card-title">${dto.prodTitle }</h5>
							<p class="card-text">${dto.prodPrice }</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
   </div>
</body>
</html>