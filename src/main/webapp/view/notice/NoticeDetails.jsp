<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		margin: 10 auto;
		padding: 0;
	}
	/* button {
	 float: right;
     padding: 8px 18px;
     background-color: #147814;
     color: white;
    } */
	.border-cell{
		border-top: 1.5px solid black;
    	border-bottom: 1.5px solid black;
    	width:750px;
	}
/* 	.title-cell{
		background-color: lightgray;
	} */
</style>

<%

	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	
	int admin=1;
	if (sessDto != null) {
	
	    admin = sessDto.getAdmin();
	}

%>
<script src="../js/jquery-3.7.1.min.js"></script>
<script> 
function edit(){
		location.href="NoticeEditForm?noticeNum=${dto.noticeNum}&admin=<%=admin%>";
	}
	function deletePost(){
		if(confirm("게시물을 삭제하면 복구할 수 없습니다. 정말로 삭제하시겠습니까?")){
			location.href="NoticeDelete?noticeNum=${dto.noticeNum}&admin=<%=admin%>";
		}
	}
	function list(){
		location.href="NoticeList?admin=<%=admin%>";
	}
</script>
<table class="border-cell table table-striped-columns">
	<tr>
		<td class="title-cell table-dark top">제목</td>
		<td colspan = "5">${dto.noticeTitle}</td>
	</tr>
	<tr class="border-cell">
		<td class="title-cell table-dark top">작성자</td>
		<td>${dto.userId}</td>
		<td class="title-cell table-dark top">작성일</td>
		<td>${dto.noticeDate}</td>
	</tr>
	<tr>
		<td class="title-cell table-dark top">내용</td>
		<td colspan = "5"><textarea class="form-control" disabled> ${dto.noticeContent}</textarea></td>
	</tr>
	<c:if test="${dto.noticeFile!=null}">
	<tr>
		<td class="title-cell">첨부파일</td>
		<td>
		<a href="<c:url value="/FileDown?fName=${dto.noticeFile}"/>">${dto.noticeFile}</a>
		</td>
	</tr>
	</c:if>

     <tr>
		<td colspan="6" align="right">
		<button class="btn btn-dark" onclick="list()">목록</button>
		<c:if test="${param.admin == 0 }">
			<button class="btn btn-dark" onclick="edit()">수정</button>
			<button class="btn btn-danger" onclick="deletePost()">삭제</button>
		</c:if>
		</td>	
	</tr>
    
	<!-- <tr>
	
		<td colspan = "6">
			<button onclick="edit()">수정</button>
			<button onclick="deletePost()">삭제</button>
			<button onclick="list()">목록</button>
		</td>
	</tr> -->
</table>