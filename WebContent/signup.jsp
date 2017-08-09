<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css"rel="stylesheet"type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"> <br />
	<label for="login_id">ログインID</label>
	<input name="login_id" id="login_id"/>（忘れないでね）<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> (こっちもね) <br />

	<label for="name">名前</label>
	<input name="name" id="name" value="${user.name}"/> <br />

	<label for="branch_id">支店名</label>
	<input name="branch_id" id="branch_id"/> <br />

	<label for="department_id">部署・役職</label>
	<input name="department_id" id="department_id"/> <br />

	<input type="submit" value="登録" /> <br />
	<a href="userManagement">戻る</a>
</form>
<div class="copyright">Copyright(c)Atsushi Ikeuchi</div>
</div>
</body>
</html>