<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script><!-- 제이쿼리 사이트 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<h1>회원가입 폼</h1>
<form action="MemberWrite" onsubmit="return pwChek();">
	<table class="table table-striped-columns">
		<tr>
			<td class="table-dark">아이디</td>
			<td>
			<div class="input-group mb-3" style="width: 340px">
				<input class="form-control" type="text" name="userId" id="userId"/>
				<input class="btn btn-dark" type="button" id="idChk" value="중복확인"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="table-dark">비밀번호</td>
			<td><input class="form-control" type="password" name="pw" id="pw" style="width: 250px"/></td>
		</tr>
		<tr>
			<td class="table-dark">비밀번호 확인</td>
			<td><input class="form-control" type="password" name="pwChk" id="pw2" style="width: 250px"/></td>
		</tr>
		<tr>
			<td class="table-dark">이름</td>
			<td><input class="form-control" type="text" name="userName" style="width: 250px"/></td>
		</tr>
		<tr>
			<td class="table-dark">성별</td>
			<td>
			<input class="form-check-input" type="radio" name="gender" value="남">남
			<input class="form-check-input" type="radio" name="gender" value="여">여
			</td>
		</tr>
		<tr>
			<td class="table-dark">전화번호</td>
			<td><input class="form-control" type="text" name="tel" id = "tel" placeholder="010-0000-0000" style="width: 300px"/></td>
		</tr>
		<tr>
			<td class="table-dark">이메일</td>
			<td>
			<div class="input-group mb-3" style="width: 450px">
			<input class="form-control" type="text" name="email" placeholder="영문+숫자" id="writeEmail" />
			<span class="input-group-text">@</span>
			<select class="form-select" name="email" id="selectEmail">
			    <option value="선택">선택</option>
			    <option value="naver.com">naver.com</option>
			    <option value="gmail.com">gmail.com</option>
			    <option value="daum.com">daum.com</option>
			    <option value="green.com">green.com</option>
			</select>
			</div>		    
		    </td>
		</tr>
		<tr>
			<td class="table-dark">우편번호</td>
			<td>
				<div class="input-group mb-3" style="width: 350px">
					<input class="form-control" type="text" id="sample6_postcode" name="postNum" placeholder="우편번호">
					<input class="btn btn-dark" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
			</td>
			<tr>
			  	<td rowspan="2" class="table-dark">주소</td>
			  <td>
			    <input class="form-control" type="text" id="sample6_address" name="addr" placeholder="주소" style="width: 600px">
			  </td>
			</tr>
			<tr>
			  <td>
			  <div class="input-group" style="width: 600px">
			    <input class="form-control" type="text" id="sample6_detailAddress" name="addr" placeholder="상세주소" style="width: 400px">
			    <input class="form-control" type="text" id="sample6_extraAddress"  name="addr" placeholder="참고항목" style="width: 200px">
			   </div>
			  </td>
			</tr>
		<tr>
			<td colspan="2" align="center">
			<input class="btn btn-dark" type="submit" value="회원가입"/>
			<a href="/firstProj/main/Main">메인으로</a>
			</td>
		</tr>
	</table>
</form>
<script>
	function pwChek() {
	    var pw = document.getElementById("pw").value;
	    var pw2 = document.getElementById("pw2").value;
	
	    if (pw !== pw2) {
	        alert("비밀번호가 일치하지 않습니다.");
	        return false;
	    }
	    return true;
	}
	$("#idChk").click(function () {
 		var userId = $("#userId").val();
       // 단일 전송
	 	$.ajax({
	          url: "../ajax/IdCheck",
	          type: "post",
	          data: {userId: userId},
	          success: function(result) {
 	               if(result=="true"){
	               	alert("이미 사용 중인 아이디입니다.")
	               }else{
	               	alert("사용 가능한 아이디입니다.")
	               }   
	           },
	           error: function (e) {
	               alert("오류 발생");
	           } 
	   	})
 	})
 	//주소 api
 function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
  /*   //일반 유효성 검사
    //아이디 //test ->usernamWWWe
     function idTest() {  
    	var userId = document.getElementById("userId").value;
        var idPw = /^[A-Za-z]{4,20}$/;
        if (!idPw.test(userId)) {
            alert("아이디 - 대소문자 포함 4글자 이상 20자 이하로 작성해주세요.");
            return false;
        }else{
        	return true;
        }
    }
    //비밀번호 test ->userName1111@@@@
     function pwTest() {
   	    var pw = document.getElementById("pw").value;
        //최소 8자, 하나의 이상의 대소문자 및 하나의 숫자, 하나의 특수문자
        var pwCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
        if (!pwCheck.test(pw)) {
            alert("비밀번호 - 최소 8자, 대소문자 및 특수문자, 숫자를 포함해서 작성해주세요.");
            return false;
        }else{
        	return true;
        }
    }
     //성별
     function genderTest() {
         var gender = document.getElementsByName("gender");
		for (var i = 0; i < gender.length; i++) {
			if(gender[i].value.equals("man")|| gender[i].value.equals("girl")){
				if(gender[i].value==null){
					alert("성별을 선택해주세요.");
		            return false;
				}
			}
		}
     }
    //이메일
    function emailTest() {
        var writeEmail = document.getElementById("writeEmail").value;
        var selectEmail = document.getElementById("selectEmail").value;
        var email = writeEmail+"@"+selectEmail;        
        var emailPw = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

        if (!emailPw.test(email)) {
            alert("올바르지 않은 이메일 형식입니다.");
            return false;
        }else{
        	return true;
        }
    }
    //전화번호
    function telTest() {
        var tel = document.getElementById("tel").value;
    	var telPw =  /^\d{3}-\d{3,4}-\d{4}$/;
		if(!telPw.test(tel)){
			alert("올바르지 않은 전화번호 형식입니다.");
		}
     } */
</script>
</body>
</html>