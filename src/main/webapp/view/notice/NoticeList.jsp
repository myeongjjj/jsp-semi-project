<%@page import="dto_p.MemberDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		margin: 0;
		padding: 0;
	}
	.listTable{
		width: 750px;
		margin: 10px auto;
		text-align: center;
	}
	.writeButton{
		display: inline-block;
		padding: 8px 18px;
     	background-color: #147814;
    	color: white;
	}
	.searchContainer{
		float:right;
		margin: 10px auto;
	}
	h2{
		display: flex;
        justify-content: center;
        margin: 30px auto;
        align-items: center;
	}
	.pageNext{
		display:inline-block;
		color: white;
		background-color: #147814;
		width: 20px;
    	padding: 10px; 
	}
</style>

<%

	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	
	int admin=1;
	if (sessDto != null) {
	
	    admin = sessDto.getAdmin();
	}

%>
<h2>공지사항</h2>
<form action="NoticeList" method="post" style="display: flex; justify-content: center;">
    <div class="input-group mb-3" style="width: 400px">
        <input class="form-control" type="text" placeholder = "제목을 검색하세요." name="search"/>
        <input class="btn btn-outline-secondary" type="submit" value="검색">
    </div>
</form>
<table class="listTable table table-striped-columns">
	<tr class="table-dark top">
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	<c:choose>
		<c:when test="${NoticeDataList != null}">
			<c:forEach items="${NoticeDataList}" var = "dtoList" varStatus="row">
			<tr>
				<td>${page.start + row.index+1}</td>
				<td><a href="NoticeDetails?noticeNum=${dtoList.noticeNum}&admin=<%=admin%>">${dtoList.noticeTitle}</a></td>
				<td>${dtoList.userId}</td>
				<td>${dtoList.noticeDate}</td>
			</tr>
			</c:forEach>
		</c:when>
		 <c:otherwise>
			<c:forEach items="${searchResult}" var = "dtoList" varStatus="row">
		<tr>
			<td>${row.index+1}</td>
			<td><a href="NoticeDetails?noticeNum=${dtoList.noticeNum}&admin=<%=admin%>">${dtoList.noticeTitle}</a></td>
			<td>${dtoList.userId}</td>
			<td>${dtoList.noticeDate}</td>
		</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
<%-- 	<tr>
		<td colspan="4" align="center">
			<c:if test="${page.startPage>1}">
				<a href="NoticeList?nowPage=${page.startPage-1 }">◀이전</a>
			</c:if>
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
				<c:choose>
					<c:when test="${page.nowPage==i}">
						<div class="pageNext">${i }</div>
					</c:when>
					<c:otherwise>
						<a href="NoticeList?nowPage=${i }" style="text-decoration: none; margin:20px"> ${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage }">
				<a href="NoticeList?nowPage=${page.endPage+1}">다음▶</a>
			</c:if>	
		</td>
	</tr> --%>
	<tr>
		<td colspan="4" >
			  <ul class="pagination" style="height: 40px; display: flex; justify-content: center;">
			  <c:choose>
			  <c:when test="${page.startPage>1}">
			    <li class="page-item">
			      <a class="page-link" href="NoticeList?nowPage=${page.startPage-1 }" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			   </c:when>
			   <c:otherwise>
			    <li class="page-item disabled">
			      <a class="page-link" href="NoticeList?nowPage=${page.startPage-1 }" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			   </c:otherwise>
			   </c:choose>
				<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
					<c:choose>
						<c:when test="${page.nowPage==i}">
			    			<li class="page-item active" aria-current="page"><span class="page-link">${i }</span></li>
			   			</c:when>
				    	<c:otherwise>
				    		<li class="page-item"><a class="page-link" href="NoticeList?nowPage=${i }">${i }</a></li>
				    	</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
				<c:when test="${page.endPage < page.totalPage }">
			    <li class="page-item">
			      <a class="page-link" href="NoticeList?nowPage=${page.endPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    </c:when>
			    <c:otherwise>
			    <li class="page-item disabled">
			      <a class="page-link" href="NoticeList?nowPage=${page.endPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    </c:otherwise>
			    </c:choose>
			  </ul>
		</td>
	</tr>
	<c:if test="${param.admin == 0 }">
     <tr>
		<td colspan="6" align="right">
		<button onclick="button()" class="btn btn-dark">글쓰기</button>
	</tr>
    </c:if>
	
</table>
<script>
	function button() {
		location.href = "NoticeWritePost";
	}
</script>