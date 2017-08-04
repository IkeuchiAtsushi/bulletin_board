<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
</head>
<body>
	<div class="main-contents">

		<div class="header">
			<c:if test="${not empty loginUser }">
				<a href="./">ホーム</a>
				<a href="settings">設定</a>
				<a href="newPosts">新規投稿</a>
				<a href="logout">ログアウト</a>
				<a href="userManagement">ユーザー管理</a>
			</c:if>
		</div>

		<c:if test="${not empty loginUser}">
			<div class="profile">
				<div class="name">
					<h2>
						<c:out value="${loginUser.name}" />
					</h2>
				</div>
				<div class="login_id">
					@
					<c:out value="${loginUser.loginId}" />
				</div>
			</div>
		</c:if>

		<%--
<div class="messages">
	<c:forEach items="${messages}" var="message">
		<div class="message">
			<div class="account-name">
				<span class="account"><c:out value="${message.login_id }"/>
				</span>
				<span class="name"><c:out value="${message.name }"/>
				</span>
			</div>
			<div class="text"><c:out value="${message.text }"/></div>
		</div>
	</c:forEach>
</div>
--%>
		<div class="copyright">Copyright(c)Atsushi Ikeuchi></div>
	</div>
</body>
</html>