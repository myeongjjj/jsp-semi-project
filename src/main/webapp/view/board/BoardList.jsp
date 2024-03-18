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
	String userId = null;
	int admin=1;
	if (sessDto != null) {
	    userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	}

%>
<h2>1:1 문의</h2>
<form action="BoardList" method="post" style="display: flex; justify-content: center;">
    <div class="input-group mb-3" style="width: 500px">
    	<select class="form-select" name = "searchCate" style="width: 130px" >
  			<option>카테고리</option>
		  	<option>제목</option>
    	</select>
        <input class="form-control" type="text" placeholder = "검색어를 입력해주세요." name="search" style="width: 300px"/>
        <input class="btn btn-outline-secondary" type="submit" value="검색" style="width: 70px">
    </div>
</form>
<table class="listTable table table-striped-columns">
	<tr class="table-dark top">
		<td>번호</td>
		<td>카테고리</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>답변</td>
	</tr>
	<c:choose>
		<c:when test="${boardDataList != null}">
			<c:forEach items="${boardDataList}" var = "dtoList" varStatus="row">
			<tr>
				<td>${page.start+row.index+1}</td>
				<td>${dtoList.boardCate}</td>
				<td><a href="PostDetails?perNum=${dtoList.perNum}&nowPage=${page.nowPage}&admin=<%=admin%>&perAnswer=${dtoList.perAnswer}">${dtoList.perTitle}</a></td>
				<td>${dtoList.userId}</td>
				<td>${dtoList.perDate}</td>
				<td>${dtoList.perAnswer}</td>
			</tr>
			</c:forEach>
		</c:when>
		 <c:otherwise>
			<c:forEach items="${searchResult}" var = "dtoList" varStatus="row">
		<tr>
			<td>${page.start+row.index+1}</td>
			<td>${dtoList.boardCate}</td>
			<td><a href="PostDetails?perNum=${dtoList.perNum}&admin=<%=admin%>">${dtoList.perTitle}</a></td>
			<td>${dtoList.userId}</td>
			<td>${dtoList.perDate}</td>
			<td>${dtoList.perAnswer}</td>
		</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
<%-- 	<tr>
		<td colspan="6" align="center">
		<c:if test="${page.startPage>1}">
			<a href="BoardList?nowPage=${page.startPage-1 }">◀</a>
			</c:if>
			<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
			<c:choose>
				<c:when test="${page.nowPage==i}">
					<div class="pageNext">${i }</div>
					<input id="in" style="display: none" value="${i }"/>
				</c:when>
				<c:otherwise>
					<a href="BoardList?nowPage=${i }" style="text-decoration: none; margin:20px"> ${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${page.endPage < page.totalPage }">
			<a href="BoardList?nowPage=${page.endPage+1}">▶</a>
		</c:if>	
		</td>
	</tr> --%>
	
	<tr>
		<td colspan="6" >
			  <ul class="pagination" style="height: 40px; display: flex; justify-content: center;">
			  <c:choose>
			  <c:when test="${page.startPage>1}">
			    <li class="page-item">
			      <a class="page-link" href="BoardList?nowPage=${page.startPage-1 }" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			   </c:when>
			   <c:otherwise>
			    <li class="page-item disabled">
			      <a class="page-link" href="BoardList?nowPage=${page.startPage-1 }" aria-label="Previous">
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
				    		<li class="page-item"><a class="page-link" href="BoardList?nowPage=${i }">${i }</a></li>
				    	</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
				<c:when test="${page.endPage < page.totalPage }">
			    <li class="page-item">
			      <a class="page-link" href="BoardList?nowPage=${page.endPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    </c:when>
			    <c:otherwise>
			    <li class="page-item disabled">
			      <a class="page-link" href="BoardList?nowPage=${page.endPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    </c:otherwise>
			    </c:choose>
			  </ul>
		</td>
	</tr>
	
	<c:if test="${sessDto != null && param.admin == '1' }">
    <tr>
    	<%-- <c:if test="${param.admin == '1' }"> --%>
		<td colspan="6" align="right">
		<button onclick="button()" class="btn btn-dark">글쓰기</button>
		</td>
		<%-- </c:if> --%>
	</tr>
    </c:if>

</table>
<script>
	function button() {
		location.href = "WritePost";
	}
/* 	paging();
	   function paging(e) {
		   
		   //if(e.this.value() != null || e.this.value() != undefined) alert(e.this.value());
		  var html = "";
	      html = "<a id='startBtn' onClick='paging()'> < </a>"
	      for(var i = 1; i <= 5; i++) {
	         html += "<a href='BoardList?nowPage=" + (i) + "' style='text-decoration: none; id='" + (i) + "'>" + ( i ) + "</a>";
	    	  //html += "<a href='BoardList?nowPage=1 style='text-decoration: none; id='1'>1</a>";
				
	      }
	      html += "<a id='endBtn' onClick='paging()'> > </a>"
	      var tt = document.getElementById("aa");
	      
	      tt.innerHTML = html;
	      
	      alert(tt.innerHTML);
	      //.innerHTML = html;
	   } */
</script>