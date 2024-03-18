<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		margin: 10 auto;
		padding: 0;
	}
/* 	.submit {
	 float: right;
     padding: 8px 18px;
     background-color: #147814;
     color: white;
    } */
    .border-cell{
    	width:750px;
    }
	.border-cell > td{
		width: 20%;
	}
/* 	.title-cell{
		background-color: lightgray;
	} */
</style>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>
	function answer(){
		location.href="BoardAnswerReg?perNum=${dto.perNum}"
	}
	
</script>
<table class = "table table-striped-columns">
	
	<tr>
		<td class="table-dark">제목</td>
		<td colspan = "6">${dto.perTitle}</td>
	</tr>
	<tr>
		<td class="table-dark">작성자</td>
		<td>${dto.userId}</td>
		<td class="table-dark">작성일</td>
		<td>${dto.perDate}</td>
		<td class="table-dark">답변여부</td>
		<td>${dto.perAnswer}</td>
	</tr>
	<tr>
		<td class="table-dark">내용</td>
		<td colspan = "6"><textarea class="form-control" disabled>${dto.perContent}</textarea></td>
	</tr>
	<c:if test="${dto.perFile!=null}">
	<tr>
		<td class="table-dark">첨부파일</td>
		<td>
		<a href="<c:url value="/FileDown?fName=${dto.perFile}"/>">${dto.perFile}</a>
		</td>
	</tr>
	</c:if>
</table>
<form action="BoardAnswerReg">
<br/><br/><br/>
<table class = "table table-striped-columns">
	<input type="hidden" name="perNum" value="${dto.perNum}">
	<tr>
		<td class="table-dark">제목</td>
		<td colspan = "6">${dto.perTitle}</td>
	</tr>
	<tr>
		<td class="table-dark">작성자</td>
		<td>커피창고</td>
		<td class="table-dark">작성일</td>
		<td>${dto.perDate}</td>
		<td class="table-dark">답변여부</td>
		<td>
			<select class="form-select" name="perAnswer" style="width: 200px">
				<option value="답변완료">답변완료</option>
				<option value="답변전">답변전</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="table-dark">내용</td>
		<td colspan = "6" >
		<textarea class="form-control" name="answer" rows="5" cols="100"></textarea>
		</td>
	</tr>

	
	<tr>
		<td colspan = "6" align="right">
				<input class="btn btn-dark"  type="submit" class="submit" value="등록"/>
		</td>
	</tr>
</table>
</form>