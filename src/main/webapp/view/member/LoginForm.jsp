<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form action="LoginReg"  style=" display: flex; justify-content: center; padding:100px;">
		<table class="table" style="width: 300px;">
			<tr>
				<td class="table-dark">id</td>
				<td>
					<input class="form-control" type="text" name="userId" style="width: 200px;"/>
				</td>
			</tr>
			<tr>
				<td class="table-dark">암호</td>
				<td>
					<input class="form-control" type="password" name="pw" style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn btn-dark" type="submit" value="로그인" />
				</td>
			</tr>
		
		</table>
			

 </form>
</body>
</html>