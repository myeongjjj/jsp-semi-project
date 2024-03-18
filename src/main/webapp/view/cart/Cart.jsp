<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto_p.CartDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<%
    int tot = 0;
    for (CartDTO dto : (ArrayList<CartDTO>) request.getAttribute("mainData")) {
        tot += dto.getProdCnt() * dto.getProdPrice();
    }
    
    request.setAttribute("tot", tot);
%>
<html>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>
function cart() {
    var form = document.frm;
    var select = [];
    var chk = form.elements["chk"];
    
    for (var i = 0; i < chk.length; i++) {
        if (chk[i].checked) {
        	select.push(chk[i].value);
        }
    }
    
   
    if (select.length == 0) {
        location.href = "Pay?select=" + chk.value;
    }
    else if(select.length > 0){
    	
    	location.href = "Pay?select=" + select.join(",");
    }else  {
        alert("최소한 한 개의 상품을 선택해야 합니다.");
        return;
    }
    
   
}


$(function(){
	$("#checkall").click(function(){       
		if($("#checkall").prop("checked")){
			$("input[name=chk]").prop("checked",true); 
		}else{
			$("input[name=chk]").prop("checked",false);        
		}    
	})
})
</script>
<head>
<meta charset="UTF-8">
</head>
<style>

</style>
<script>
 function del(no){
	 
	 if(confirm("정말 삭제하시겠습니까?")){
		 location.href = "CartDelete?no="+no;
	 }
	 
 }

</script>

<body>
<h1>장바구니</h1>
<form action="Order" name="frm" method="post" enctype="multipart/form-data"><!-- Order -->
	<table class="table table-striped-columns">

		<tr class="table-dark top">
			<td><input type="checkbox" class="form-check-input" id="checkall" name=""/></td>
			<td>번호</td>
			<td width="100px">사진</td>
			<td>상품이름</td>
			<td>수량</td>
			<td>금액</td>
			<td>배송비</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${mainData }" var="dto" varStatus="no">
		<tr>
			<td><input class="form-check-input" type="checkbox" name="chk" value="${dto.no}"/></td>
			<td>${no.index+1 }</td>
			<td><img style="width:100px;" src="../fff/${dto.cartFile }"/></td>
			<td>${dto.cartTitle}</td>
			<td>${dto.prodCnt }</td>
			<td>${dto.prodPrice*dto.prodCnt }</td>
			<td>3,000원</td>
			<td><a href="#" class="btn btn-danger" onclick="del(${dto.no })">삭제</a></td>
		</tr>
		</c:forEach>
		<tr>
            <td colspan="4">총금액</td>
            <td colspan="4">${tot }</td>
        </tr>
		<tr >
			<td colspan="8" align="right">
				<!-- <input type="submit" value="주문하기"/> -->
				    <a href="#" onclick="cart()" class="btn btn-dark">주문하기</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>