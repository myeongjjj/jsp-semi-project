<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<form action="ReviewWriteReg?prodNum=${param.prodNum }">  
	<table border="">
	 <input type="hidden" name="prodNum" value="${param.prodNum}"/>
		<tr>
			<td>작성자</td>
			<td>${sessDto.userName }</td>
		</tr>
		<tr>
			<td>상품</td>
			<td>${param.prodTitle }</td>
		</tr>
		<tr>
			<td>한줄평</td>
			<td><input type="text" name="reviewTitle"/></td>
		</tr>
		<tr>
			<td>평점</td>
			<td><select name="reviewStar">
				<option value="1">★</option>
				<option value="2">★★</option>
				<option value="3">★★★</option>
				<option value="4">★★★★</option>
				<option value="5">★★★★★</option>
			</select></td>
		</tr>
		<!-- <tr>
			<td>리뷰내용</td>
			<td><textarea name="reviewContent" cols="100" rows="5"></textarea></td>
		</tr> -->
		<tr>
			<td colspan="2">
				<input type="submit" value="등록"/>
			</td>
		</tr>
	
	</table>

</form>

</body>
</html>