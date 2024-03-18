<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto_p.CartDTO"%>
<%@page import="dto_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<%
int tot = 0;
int no = 0;

String cartTitle="";
String userId="";
for (CartDTO dto : (ArrayList<CartDTO>) request.getAttribute("mainData")) {
    tot += dto.getProdCnt() * dto.getProdPrice();
    cartTitle += dto.getCartTitle()+", ";
    no += dto.getNo();
    userId = dto.getUserId();
}
String select = request.getParameter("select");
Date date = new Date();
long dd = date.getTime();

request.setAttribute("tot", tot);
request.setAttribute("dd", dd/6000);
request.setAttribute("userId", userId);
request.setAttribute("cartTitle", cartTitle);

	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");

	int admin = 1;
	if (sessDto != null) {
	
	    admin = sessDto.getAdmin();
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width:100px;
	}
</style>
</head>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/jquery-3.7.1.min.js"></script>

<script>
IMP.init('imp85324556')

function check() {
	var form = document.info;
	
	if(form.receiver.value == ""){
		info.receiver.focus();
		alert("받는사람 필수입력")
	}else if(form.postcode.value == ""){
		info.postcode.focus();
		alert("우편번호 필수입력")
	}else if(form.addr2.value == ""){
		info.addr2.focus();
		alert("상세주소 필수입력")
	}else if(form.phoneNum.value == ""){
		info.phoneNum.focus();
		alert("휴대전화 필수입력")
	}else{
		requestPay()
	}
}

function requestPay() {
	//if(form.receiver.value.replace(/ /g, '')){
	    IMP.request_pay({
	        pg: "kakaopay",
	        pay_method: "card",
	        merchant_uid: ${dd} ,   // 주문번호
	        name: "${cartTitle }",
	        amount: ${tot },                         // 숫자 타입
	        buyer_name: "${userId }"
	      }, function (rsp) {
	    	  
	    	  if (rsp.success) {
	              location.href="Order?userId=${userId}&orderNum=${dd}"
	              		+"&receiver="+$("input[name='receiver']").val()
	              		+"&postcode="+$("input[name='postcode']").val()
	              		+"&addr="+$("input[name='addr1']").val()+$("input[name='addr2']").val()+$("input[name='addr3']").val()
	              		+"&phoneNum="+$("input[name='phoneNum']").val()
	              		+"&message="+$("input[name='message']").val()
	              		+"&select=${param.select}"
	              		+"&admin=<%=admin%>";
	              console.log(rsp);
	              return true;
	          } else {
	              alert("결제실패!!!")
	          }
	      });
}

function execDaumPostcode() {
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
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
</script>
<body>
<form name="info" method="post" onsubmit="check()">
<!-- <form action="Order" method="post" enctype="multipart/form-data"> -->
<table class="table table-striped-columns" width="668px">
	<tr> 
		<td class="table-dark top" width="150px">받는사람</td>
		<td >
			<input name="receiver" type="text" class="form-control" style="width: 200px"/>
		</td>
	</tr>
	<tr>
	 	<td class="table-dark top" width="150px">우편번호</td>
	 	<td>
	 	<div class="input-group mb-3" style="width: 350px">
	 	<input type="text" id="postcode" name="postcode" placeholder="우편번호" readonly="readonly" class="form-control">
		<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="btn btn-dark">
		</div> 
		</td>
	<tr>
	<tr>
		<td class="table-dark top" width="150px">주소</td>
		<td>
			<input type="text" id="address" name="addr1" placeholder="주소" style="width: 500px" readonly="readonly" class="form-control">
		</td>
	</tr>
	<tr>
		<td class="table-dark top" width="150px"></td>
		<td>
		<div class="input-group" style="width: 600px">
			<input class="form-control" type="text" id="detailAddress" name="addr2" placeholder="상세주소" style="width: 292px">
			<input class="form-control" type="text" id="extraAddress" name="addr3" style="width: 200px" readonly="readonly">
		</div>
		</td>
	</tr>
	<tr>	                                                                                                                                                                             
		<td class="table-dark top" width="150px">휴대전화</td>
			<td><input name="phoneNum" type="text" class="form-control" style="width: 200px"/>
		</td>
	</tr>
	<tr>	
		<td class="table-dark top" width="150px">메시지</td>
		<td>
			<input name="message" type="text" class="form-control"/>
		</td>
	</tr>
</table>
<table class="table table-striped-columns" width="668px">
	<tr>
		<td class="table-dark top" width="50px">번호</td>
		<td class="table-dark top" width="100px">사진</td>
		<td class="table-dark top" width="232px">상품이름</td>
		<td class="table-dark top" width="50px">수량</td> 
		<td class="table-dark top" width="100px">금액</td>
		<td class="table-dark top" width="107px">배송비</td>
	</tr>
	<c:forEach items="${mainData }" var="dto" varStatus="no">
	<tr>
		<td>${no.index+1 }</td>
		<td><img width="100px" src="../fff/${dto.cartFile }"/></td>
		<td>${dto.cartTitle}</td>
		<td>${dto.prodCnt }</td>
		<td>${dto.prodPrice*dto.prodCnt }</td>
		<td>3,000원</td>
	</tr>
	</c:forEach>
	
	<tr>
		<td colspan="4">총금액</td>
		<td colspan="4">${tot }</td>
     </tr>
	<tr>
	<td colspan="6" align="right">
		<!-- <button onclick="requestPay()">주문</button> -->
		<input type="button" onclick="check()" value="주문" class="btn btn-dark">
	</td>
	</tr>
</table>
</form>
</body>
</html>