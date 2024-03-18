<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		margin: 10 auto;
		padding: 0;
	}
/* 	button {
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
<script>
	function edit(){
		location.href="BoardEditForm?perNum=${dto.perNum}"
	}
	function answer(){
		location.href="BoardAnswerForm?perNum=${dto.perNum}"
	}
	function deletePost(){//delete는 javascript의 예약어
		if(confirm("게시물을 삭제하면 복구할 수 없습니다. 정말로 삭제하시겠습니까?")){
			location.href="DeletePost?perNum=${dto.perNum}"
		}
	}
	function list(){
		location.href="BoardList"
	}
</script>
<table class="border-cell table table-striped-columns">
	<tr>
		<td class="table-dark top">제목</td>
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
		<td class="table-dark top">내용</td>
		<td colspan = "6"><textarea class="form-control" disabled>${dto.perContent}</textarea></td>
	</tr>
	<c:if test="${dto.perFile!=null}">
	<tr>
		<td class="title-cell">첨부파일</td>
		<td>
		<a href="<c:url value="/FileDown?fName=${dto.perFile}"/>">${dto.perFile}</a>
		</td>
	</tr>
	</c:if>
	</table>
	<br/><br/><br/>
	<table class="border-cell table table-striped-columns">
		<c:if test="${dto.perAnswer =='답변완료' }">
			<tr class="next-cell">
				<td colspan="2"><h2>답변</h2></td>
			</tr>
			<tr>
				<td class="table-dark top">작성자</td>
				<td>커피창고</td>
			</tr>
			<tr>
				<td class="table-dark top">내용</td>
				<td colspan = "6"><textarea class="form-control" disabled>${dto.answer	}</textarea></td>
			</tr>
		</c:if>
	<tr>
		<td colspan = "6" align="right">
		<c:choose>
			<c:when test="${param.admin == 0 }">
				<button class="btn btn-dark" onclick="answer()">답변</button>
			</c:when>
			<c:otherwise>
				<c:if test="${dto.perAnswer == '답변전' }">
				<button class="btn btn-dark" onclick="edit()">수정</button>
				</c:if>
				<!-- <button onclick="edit()">수정</button> -->
			</c:otherwise>
		</c:choose>
				<button class="btn btn-dark" onclick="list()">목록</button>
				<button class="btn btn-danger" onclick="deletePost()">삭제</button>
		</td>
	</tr>
</table>