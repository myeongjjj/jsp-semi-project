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
<!--  method="post" enctype="multipart/form-data"> -->
<form action="ProductModifyReg">
	<table class="table table-striped">
		 <input type="hidden" name="prodNum" value="${dto.prodNum}">
		<tr>
			<td class="table-dark">상품명</td>
			<td ><input class="form-control" type="text" name="prodTitle" value="${dto.prodTitle }" style="width:100%;"/></td>
		</tr>
		<tr>
			<td class="table-dark">카테고리</td>
			<td ><select class="form-select" name="prodCate" style="width:100%;">
					<option value="블랜드">블랜드</option>
					<option value="싱글오리진">싱글오리진</option>
					<option value="디카페인">디카페인</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="table-dark">상품 대표사진</td>
			<td><input class="form-control" type="file" name="prodFile" value="${dto.prodFile }" /></td>
		</tr>
		<tr>
			<td class="table-dark">상품 금액</td>
			<td><input class="form-control" type="text" name="prodPrice" value="${dto.prodPrice }"  style="width:100%;"/></td>
		</tr>
		<tr>
			<td class="table-dark">상품 상세정보</td>
			<td><textarea class="form-control" name="prodInfo" cols="100" rows="5">${dto.prodInfo }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input class="btn btn-dark" type="submit" value="수정완료"/></td>
		</tr>
		
	</table>


</form>

</body>
</html>