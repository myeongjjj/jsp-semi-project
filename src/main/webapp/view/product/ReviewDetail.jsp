<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-striped">
	<tr class="table-dark">
		<td>리뷰번호</td>
		<td>상품번호</td>
		<td>아이디</td>
		<td>리뷰</td>
		<td>별점</td>
		<td></td>
	</tr>

	<c:forEach items="${reDto }" var="reDto" varStatus="no">
	<tr>
		<td style="width: 100px">${no.index+1 }</td>
		<td style="width: 100px">${reDto.prodNum }</td>
		<td style="width: 150px">${reDto.userId }</td>
		<td style="width: 250px">${reDto.reviewTitle }</td>
		<c:choose>
			<c:when test="${reDto.reviewStar == 1 }">
				<td style="width: 150px">★</td>
			</c:when>
			<c:when test="${reDto.reviewStar == 2 }">
				<td style="width: 150px">★★</td>
			</c:when>
			<c:when test="${reDto.reviewStar == 3 }">
				<td style="width: 150px">★★★</td>
			</c:when>
			<c:when test="${reDto.reviewStar == 4 }">
				<td style="width: 150px">★★★★</td>
			</c:when>
			<c:when test="${reDto.reviewStar == 5 }">
				<td style="width: 150px">★★★★★</td>
			</c:when>
		</c:choose>
		<td style="width: 100px">
			<a class="btn btn-danger" style="width: 100px" href="ReviewDelete?reviewNum=${reDto.reviewNum }&userId=${reDto.userId }">삭제</a>
		</td>	
			</tr>
		</c:forEach>

	
</table>
</body>
</html>