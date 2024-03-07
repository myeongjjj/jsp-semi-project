<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<style>
        *{
            margin:0;
            padding:0;
        }
        ul{
            width:1200px;
            height: 1000px;
            padding: 0px;
        }
        a{
            text-decoration-line: none;
            color: #000;
        }
         .tr>td{
        	padding:30px;
        }
        #li_logo{
            list-style: none;
            float: left;
            width: 400px;
            height: 60px;
            line-height: 60px;
            text-align: center;
        }
        #cli{
            list-style: none;
            float: left;
            width: 150px;
            height: 64px;
            line-height: 54px;
            text-align: center;
        }
        #header{
            width:1100px;
            height: 44px;
            float: right;
            text-align: right;
            font-size: 16px;
            margin:10px 50px;
        }
        #category{
            width:1200px;
            height: 64px;
            display:flex;
            justify-content: center;
        }
        #logo{
            width:1200px;
            height: 100px;
            display:flex;
        }
        #cate_list{
            height: 64px;
            display:flex;
            justify-content: center;
        }
        #logo_list{
            width:600px;
            height: 44px;
            display:flex;
        }
        #void{
            width:300px;
            height: 44px;
            margin-left:40px;
            display:flex;
            /* justify-content: center; */
        }
        #li_t{
            list-style: none;
            float: left;
            width: 400px;
            height: 50px;
            line-height: 50px;
            text-align: center;
        }
       
        #banner{
            width:1200px;
            height: 500px;
            position: relative;
            overflow: hidden;
        }
        #long{
            width: 3600px;
            height: 500px;
            left: 0px;
            position: absolute;
            transition-duration: 2.0s;
        }
        #bn{
            width: 1200px;
            height: 500px;
            display: block;
            float: left;
        }
       
</style>
<title>원두쓰리</title>
</head>
<body>
<table>
	<tr class="tr">
		<td><jsp:include page="inc/header.jsp"/></td>
	</tr>
	<tr class="tr">
		<td width="800px"><jsp:include page="${mainUrl }"/></td>
	</tr>
	<tr class="tr">
		<td><jsp:include page="inc/footer.jsp"/></td>
	</tr>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>

