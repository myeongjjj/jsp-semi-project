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
<head>
<meta charset="UTF-8">
<style>
	
	.big{
		display:flex;
		width:100%;
		justify-content: space-evenly;
	}
/* 	.big>div{
		width:150px;
		height:250px;
		border:1px solid #000;
		margin-top : 20px;
		display:block;

	}
	.img>img{
		width:130px;
		height:150px;
		margin-top : 10px;
		margin-left : 10px;
	} */
	.cate{
		height:100px;
		text-align: center;
		line-height: 100px;
	}
	
	

</style>
<script src="../js/jquery-3.7.1.min.js"></script>

<script>
	$(function(){
		$(".col").click(function(){
			/* alert("눌림") */
			var prodNum = $(this).data("prodnum");
			location.href="ProductDetail?prodNum=" + prodNum + "&admin="+<%=admin %>;
			
		})
		
		$(".button").click(function(){
			/* alert("눌림")  */
			
			location.href="ProductWrite"
			
		})
		
	})


</script>
</head>
<body>
<div class="wrapper">
	<h2 class="cate">${param.prodCate }</h2>
	
	<div>
	 	<c:if test="${param.admin == 0 }">
            <div>
                <input type="button" class="button btn btn-dark" value="상품추가" />
            </div>
        </c:if>
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