<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!--  method="post" enctype="multipart/form-data"> -->
<form action="ProductWriteReg" method="post" enctype="multipart/form-data">
	<table class="table table-striped">
		<tr>
			<td class="table-dark">상품명</td>
			<td ><input class="form-control" type="text" name="prodTitle" style="width:100%;"/></td>
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
			<td><input class="form-control" type="file" name="prodFile"/></td>
		</tr>
		<tr>
			<td class="table-dark">상품 금액</td>
			<td><input class="form-control" type="text" name="prodPrice" style="width:100%;"/></td>
		</tr>
		<tr>
			<td class="table-dark">상품 상세정보</td>
			<td><textarea class="form-control" name="prodInfo" cols="100" rows="5"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input class="btn btn-dark" type="submit" value="등록하기"/></td>
		</tr>
		
	</table>


</form>