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
				<a href="posts">新規投稿</a>
				<a href="logout">ログアウト</a>
				<a href="userManagement">ユーザー管理</a>
			</c:if>
		</div>

		<c:if test="${not empty loginUser}">
			<div class="profile">
				<div class="name">
					<h1>
						<c:out value="ようこそ${loginUser.name}さん！" /><br/>
					</h1>
				</div>
			</div>
		</c:if>
<h3>みんなの投稿</h3>

<div class="messages">
	<c:forEach items="${Posts}" var="post">
		<div class="message">
			<div class="account-name">
				<span class="name"><c:out value="${post.name }"/></span>さんの投稿<br/>
				件名：<span class="subject"><c:out value="${post.subject }"/></span><br/>
				カテゴリー：<span class="category"><c:out value="${post.category }"/></span><br/>
			</div>
			投稿文：<div class="text"><c:out value="${post.text }"/></div><br/>
			<div class="date"><fmt:formatDate value="${post.createdAt }"
			pattern="yyyy/MM/dd HH:mm:ss"/><br/>
				所属：<span class="branchId"><c:out value="${post.branchName }"/></span> /
				部署・役職：<span class="departmentId"><c:out value="${post.departmentName }"/></span><br/>
			<br/>
			</div>
		</div>
	</c:forEach>
</div>
		<div class="copyright">Copyright(c)Atsushi Ikeuchi></div>
	</div>
</body>
</html>