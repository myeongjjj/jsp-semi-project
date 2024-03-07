<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../js/jquery-3.7.1.min.js"></script>
<%
	MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
	String pw = sessDto.getPw();

%>
<script>
	$(function(){
	    $("#pwChkBtn").click(function(){
	        var pw = $("#pw").val();
	        var pwChk = $("#pwChk").val()
			if($("#nowPw").val() != <%=pw%> ){
				alert("현재비밀번호가 다릅니다.")	
			}else{
				 $("#nowPw").attr("readonly", true)
	        	if(pw == pwChk && $("#nowPw").val() != pwChk ){
		        	alert("확인되었습니다.")
		            $("#pw").attr("readonly", true)
		            $("#pwChk").attr("readonly", true)
		        }else if(pw == pwChk && $("#nowPw").val() == pwChk){
		            alert("현재비밀번호와 같습니다.")
		        }else if(pw != pwChk){
		        	alert("비밀번호가 다릅니다.")
		        }
			}
	        
	    })
	    
	})
	
	function del(){
		if(confirm("정말로 탈퇴하시겠습니까?")){
			location.href = "ProfileDelete"
		}
		
	}

</script>

<h2>회원정보</h2>
<hr/>
<form name="frm" action="ProfileModify" method="post" enctype="multipart/form-data" class="row g-3">
	<div class="col-md-5">
	  <label for="inputEmail4" class="form-label">아이디</label>
	  <input type="email" class="form-control" id="inputEmail4" readonly="readonly" value="${sessDto.userId }">
	</div>
	<div class="col-md-7"></div>
	<div class="col-md-5">
	  <label for="inputPassword4" class="form-label">패스워드</label>
	  <input type="password" class="form-control" id="nowPw">
	</div>
	<div class="col-md-7"></div>
	<div class="col-md-5">
	  <label for="inputPassword4" class="form-label">새 비밀번호</label>
	  <input type="password" class="form-control" id="pw" name="pw">
	</div>
	<div class="col-md-7"></div>
	<div class="col-md-5">
	  <label for="inputPassword4" class="form-label">비밀번호 확인</label>
	  <input type="password" class="form-control" id="pwChk">
	</div>
	<div class="col-md-2 d-flex align-items-end">
	  <label for="inputPassword4" class="form-label"></label>
	  <input type="button" class="btn btn-secondary" id="pwChkBtn" value="비밀번호 확인"/>
	</div>
	<div class="col-8">
	  <label for="inputAddress" class="form-label">주소</label>
	  <input type="text" class="form-control" name="addr1" value="${sessDto.addr }">
	</div>
	<div class="col-8">
	  <label for="inputAddress2" class="form-label">상세주소</label>
	  <input type="text" class="form-control" name="addr2">
	</div>
	<div class="col-6">
	  <label for="inputCity" class="form-label">이메일</label>
	  <input type="text" class="form-control" name="email" readonly="readonly" value="${sessDto.email }">
	</div>
	<div class="col-md-6"></div>
	<div class="col-6">
	  <label for="inputState" class="form-label">전화번호</label>
	  <input type="text" class="form-control" name="tel" readonly="readonly" value="${sessDto.tel }">
	</div>
	<div class="col-12">
		<a href="ProfileDetail" class="btn btn-secondary">주문내역조회</a>
		<button type="submit" class="btn btn-secondary">수정 완료</button>
		<a href="javascript:del()" class="btn btn-secondary">회원 탈퇴</a>
	</div>
	
  </form>


