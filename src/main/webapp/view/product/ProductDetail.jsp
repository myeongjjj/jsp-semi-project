<%@page import="dto_p.MemberDTO"%>
<%@page import="dto_p.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html>
<%
	
	MemberDTO sessDto = (MemberDTO) session.getAttribute("sessDto");
	String userId = null;
	int admin = 1;
	if (sessDto != null) {
	    userId = sessDto.getUserId();
	    admin = sessDto.getAdmin();
	}

%>
<html>
<style>

	.box1{
		height:600px;
	}
	.box2{
		height:1000px;
	}
	.box3{
		height:200px;
		/* background-color:#0ff; */
	}
	.box1>div>img{
		width:400px;
		height:400px;
		margin:25px;
		float:left;
		margin-left:100px;
	}
	
	.content{
		width:400px;
		height:300px;
		margin:15px;
		float:right;
		margin-right:100px;
	}
	hr~div>div{
		width:50%;
		margin:15px 0px;
		float:left;
	}
	button{
		width:150px;
		height:50px;
		float:left;
		margin:20px;
		background-color:#fff;
		border-radius: 30px;
	}
	.box2>h1{
		text-align:center;
	}

</style>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>

	function del(){
	
		if(confirm("삭제하시겠습니까?")){
			location.href = "ProductDelete?prodNum=${dto.prodNum}"
			}
	
	}
	var userId = '<%=userId %>';
	$(function(){
		
		$(".btn1").click(function(){
			//alert("눌림")
			if(userId != 'null'){
			var chk = confirm("장바구니로 이동하시겠습니까?");
			 if(chk){
				
				location.href="/firstProj/cart/CartReg?userId="+userId+"&prodCnt="+$("select[name='prodCnt']").val()
					+"&prodPrice=${dto.prodPrice }"
					+"&cartFile=${dto.prodFile }"
					+"&cartTitle=${dto.prodTitle }"
					+"&option1="+$("select[name='option1']").val()
					+"&option2="+$("select[name='option2']").val()
					+"&prodNum=${dto.prodNum }"
					+"&chk=true"
					+"&admin=<%=admin %>";
				 
			 }else{
				 
				 location.href="/firstProj/cart/CartReg?userId="+userId+"&prodCnt="+$("select[name='prodCnt']").val()
						+"&prodPrice=${dto.prodPrice }"
						+"&cartFile=${dto.prodFile }"
						+"&cartTitle=${dto.prodTitle }"
						+"&option1="+$("select[name='option1']").val()
						+"&option2="+$("select[name='option2']").val()
						+"&prodNum=${dto.prodNum }"
						+"&chk=false"
						+"&admin=<%=admin %>";

			}
		}else{
			alert("로그인해주세요.");
			location.href="/firstProj/member/LoginForm";
		
		}
			
		})
		
		$(".btn2").click(function(){
			//alert("눌림")
			if(userId != 'null'){
				 
				 location.href="/firstProj/cart/CartReg?userId="+userId+"&prodCnt="+$("select[name='prodCnt']").val()
						+"&prodPrice=${dto.prodPrice }"
						+"&cartFile=${dto.prodFile }"
						+"&cartTitle=${dto.prodTitle }"
						+"&option1="+$("select[name='option1']").val()
						+"&option2="+$("select[name='option2']").val()
						+"&prodNum=${dto.prodNum }"
						+"&chk=true"
						+"&admin=<%=admin %>";

			}else{
				alert("로그인해주세요.");
				location.href="/firstProj/member/LoginForm";
				
			}
		})
		
	})
	

</script>
<head>
<meta charset="UTF-8">
</head>
<body>
<div  class="wrapper">

	 <c:if test="${param.admin == 0 }">
            <div>
				<a href="ProductModify?prodNum=${param.prodNum }" class="btn btn-dark">수정</a>
				<a href="javascript:del()" class="btn btn-danger">삭제</a>
			</div>
        </c:if>
	
	<div class="box1">
		<div>
			<c:choose>
			<c:when test="${not fn:startsWith(dto.prodFile, 'http')}">
			<img src="../fff/${dto.prodFile }">
			</c:when>
			<c:otherwise>
				<img src="${dto.prodFile }">
			</c:otherwise>
			</c:choose>
		</div>
		<div class="content mb-3">
			<div><h1>${dto.prodTitle }</h1></div>
			<hr/>
			<div>
				<div>식품의 유형</div>
				<div>원두 커피</div>
			</div>
			<div>
				<div>배송비</div>
				<div>3,000원</div>
			</div>
			<div>
				<div>판매가</div>
				<div>${dto.prodPrice }원</div>
			</div>
			<hr/>
			<div>
			<div>용량 선택</div>
				<div>
					 <select name="option1" class="form-select">
		                 <option value="200g">200g</option>
		                 <option value="1kg(+10,000원)">1kg(+10,000원)</option>
           			 </select>
				</div>
			</div>
			<div>
			<div>분쇄 선택</div>
				<div>
					 <select name="option2" class="form-select">
		                 <option value="원두상태">원두상태</option>
		                 <option value="프렌치 프레스 분쇄">프렌치 프레스 분쇄</option>
		                 <option value="드립 및 커피메이커 분쇄">드립 및 커피메이커 분쇄</option>
		                 <option value="더치 분쇄">더치 분쇄</option>
		                 <option value="모카포트 분쇄">모카포트 분쇄</option>
		                 <option value="에스프레소 분쇄">에스프레소 분쇄</option>
           			 </select>
				</div>
			</div>
			<div>
			<div>수량</div>
				<div>
					 <select name="prodCnt" class="form-select" style="width: 100px;">
		                 <option value="1">1개</option>
		                 <option value="2">2개</option>
		                 <option value="3">3개</option>
		                 <option value="4">4개</option>
		                 <option value="5">5개</option>
           			 </select>
				</div>
			</div>
			<hr/>
			<div>
				<button  class="btn1" >장바구니</button>
			</div>
			<div>
				<button  class="btn2">구매하기</button>
			</div>
		</div>
	</div>
	<hr/>
	<div class="box2">
		<h1>상품상세정보</h1>
		<div style="margin: 50px 300px 0px 300px; font-size: 21px;">
		${dto.prodInfo }
		</div>
	</div>
	<div class="box3">
		<h1>한줄평</h1>
				<table class="table table-striped-columns" style="overflow-y: scroll;">
				<c:forEach items="${reviewData }" var="reDto" varStatus="no">
					<tr>
						<td>${reDto.userId }</td>
						<td>${reDto.reviewTitle }</td>
				<c:choose>
					<c:when test="${reDto.reviewStar == 1 }">
						<td>★</td>
					</c:when>
					<c:when test="${reDto.reviewStar == 2 }">
						<td>★★</td>
					</c:when>
					<c:when test="${reDto.reviewStar == 3 }">
						<td>★★★</td>
					</c:when>
					<c:when test="${reDto.reviewStar == 4 }">
						<td>★★★★</td>
					</c:when>
					<c:when test="${reDto.reviewStar == 5 }">
						<td>★★★★★</td>
					</c:when>
				</c:choose>			
					</tr>
					</c:forEach>
				</table>
		<div style="width: 1150px; margin: auto;" align="right">
			<c:if test="${sessDto != null }">
			<a class="btn btn-dark" href="ReviewWrite?prodTitle=${dto.prodTitle }&prodNum=${dto.prodNum }&admin=<%=admin %>">리뷰쓰기</a>
			</c:if>	
		</div>
		</div>
		
	</div>
</div>

</body>
</html>