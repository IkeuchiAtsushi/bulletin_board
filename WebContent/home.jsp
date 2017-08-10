<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
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
				<a href="posts">新規投稿</a>
				<a href="userManagement">ユーザー管理</a>
				<a href="logout">ログアウト</a>
			</c:if>
		</div>

		<c:if test="${not empty loginUser}">
			<div class="profile">
				<div class="name">

					<h1>
						<c:out value="掲示板" /><br/>
					</h1>
						<br/>
					<h2>
						<c:out value="ようこそ${loginUser.name}さん！" /><br/>
					</h2>
				</div>
			</div>
		</c:if>
<h3>みんなの投稿</h3>
<form action="./">
	<h4>検索</h4>
	カテゴリー<input name=category />の<br />
	<input type="date" name=startDate />から<br />
	<input type="date" name=endDate />まで<br />
	<input type="submit" value="検索" /><br />
</form>
<br/>
<div class="messages">
	<c:forEach items="${posts}" var="post">
		<div class="message">
			<div class="account-name">
				<h3><span class="name"><c:out value="${post.name }"/></span>さんの投稿<br/></h3>
				件名：<span class="subject"><c:out value="${post.subject }"/></span><br/>
				カテゴリー：<span class="category"><c:out value="${post.category }"/></span><br/>
			</div>
			投稿文<div class="text"><c:out value="${post.text }"/></div><br/>
			<div class="date"><fmt:formatDate value="${post.createdAt }"
			pattern="yyyy/MM/dd HH:mm:ss"/><br/>
				所属：<span class="branchId"><c:out value="${post.branchName }"/></span> /
				部署・役職：<span class="departmentId"><c:out value="${post.departmentName }"/></span><br/>
			<br/>
				<form action="postsDelete" method="post">
					<button type="submit" name="id" value="${post.id }">削除</button>
				</form>
			</div>
			<div class="form-area">
				<form action="comment" method="post">
					<input name="postId" type="hidden" id="postId" value ="${post.id}"><br />
					コメント<br/>
					<textarea name="text" rows="5" cols="100" class="tweet-box"></textarea>
					<br/>
					<input type="submit" value="コメント">(500文字まで)
				</form>
				<br/>
					<div class="comment">
						<c:forEach items="${comments}" var="comment">
							<c:if test = "${post.id == comment.postId}">
							<div class="comment">
							<div class="account-name">
							<span class="name"><c:out value="${comment.name }"/></span>さんのコメント<br/>
							<span class="text"><c:out value="${comment.text }"/></span><br/>
							<div class="date"><fmt:formatDate value="${comment.createdAt }"
								pattern="yyyy/MM/dd HH:mm:ss"/><br/></div>
							所属：<span class="branchName"><c:out value="${comment.branchName }"/></span>/
							部署・役職：<span class="departmentName"><c:out value="${comment.departmentName }"/>
							</span><br/>
							</div>
							<br/>
								<form action="commentDelete" method="post">
									<button type="submit" name="id" value="${comment.id }">削除</button>
								</form>
							------------------------------------------------------------------------------
							</div>
							</c:if>
						</c:forEach>
					</div>
			</div>
		</div>
	</c:forEach>
</div>
		<div class="copyright">Copyright(c)Atsushi Ikeuchi></div>
	</div>
</body>
</html>