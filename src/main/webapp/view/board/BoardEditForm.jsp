<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<style>
	*{
		margin: 0;
		padding: 0;
	}
</style>
<script>
	function cancel(){
		alert("취소되었습니다.")
		location.href="BoardList"
	}
	function deleteFile(){
		if(confirm("파일을 삭제하시겠습니까? \n삭제된 파일은 복구할 수 없습니다.")){
			var forms = document.forms;
			forms.action = "DeletePost";
			forms.submit()
		}
	}
	
</script>

<form name="forms" action="BoardEditHandler" method="post" enctype="multipart/form-data">
	<input type="hidden" name="perNum" value="${dto.perNum }" readonly="readonly"/>
		<table class="table table-striped-columns" >
		<tr>
			<td class="table-dark">카테고리/제목</td>
			<td class="input-group mb-3">
				<select class="form-select" name="boardCate" value="${dto.boardCate}" style="width: 100px;">
					<option value="상품">상품</option>
				  	<option value="주문/결제">주문/결제</option>
				  	<option value="취소/교환/환불">취소/교환/환불</option>
				  	<option value="기타">기타</option>
				</select>	
				<input class="form-control" type="text" name="perTitle" value="${dto.perTitle}" style="width: 600px;"/>
			</td>
		</tr>
		<tr>
			<td class="table-dark">내용</td>
			<td>
				<textarea class="form-control" cols="30" rows="10" name="perContent">${dto.perContent}</textarea>
			</td>
		</tr>
		<tr>
			<td class="table-dark">첨부파일</td>
			<td>
			<c:choose>
				<c:when test="${dto.perFile!=null}">
					<input class="form-control" type="text" name="perFile" value="${dto.perFile }" readonly="readonly"/>
					<input class="btn btn-danger" type="button" onclick="deleteFile()" value="파일삭제" />
				</c:when>
				<c:otherwise>
					<input class="form-control" type="file" name = "perFile"/>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	<div align="right">
		<input class="btn btn-dark" type="submit" value="수정"/>
		<input class="btn btn-dark" type="button" value="취소" onclick="cancel()"/>
	</div>
</form>