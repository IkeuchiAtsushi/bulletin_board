<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<script type="text/javascript">
function check(){
	if(window.confirm('削除してよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理
		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
var set=0;
function double() {
if(set==0){ set=1; } else {
alert("只今処理中です。\nそのままお待ちください。");
return false; }}


</script>
<script type="text/javascript">
function CountDownLength( idn, str, mnum ) {
   document.getElementById(idn).innerHTML = "あと" + (mnum - str.length) + "文字";
}
</script>
</head>
<body>
<div class="main-contents">
	<div class="header">
		<c:if test="${not empty loginUser }">
			<a href="posts">新規投稿</a>
				<c:if test="${loginUser.departmentId == 1 }">
					<a href="userManagement">ユーザー管理</a>
				</c:if>
			<a href="logout" onclick="return confirm('ログアウトします')">ログアウト </a>
		</c:if>
	</div>
		<c:if test="${not empty loginUser}">
			<div class="profile">
				<div class="name"><br/>
					<h1>社内掲示板</h1>
					<h2><c:out value="ようこそ${loginUser.name}さん！" /><br/></h2>
				</div>
			</div>
		</c:if>
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
	<h3>みんなの投稿</h3>
		<form action="./">
			<h4>カテゴリーで検索</h4>
				<select name=category size="1">
					<option value="">全て表示</option>
					<c:forEach items="${categories }" var="category">
						<option value="${category.category}" <c:if test="${category.category==selectCategory }">
								selected</c:if>>${category.category}</option>
					</c:forEach>
				</select>
				<br/>
			<h4>日にちで検索</h4>
			<input type="date" name=startDate value="${startDate}"/>から<br />
			<input type="date" name=endDate value="${endDate}"/>まで<br />
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
				投稿文
				<c:forEach var="text" items="${fn:split(post.text, '
							')}">
    					<div><c:out value="${text}"/></div>
				</c:forEach>
				<br/>
				<div class="date"><fmt:formatDate value="${post.createdAt }"
						pattern="yyyy/MM/dd HH:mm:ss"/><br/>
				所属：<span class="branchId"><c:out value="${post.branchName }"/></span> /
				部署・役職：<span class="departmentId"><c:out value="${post.departmentName }"/></span><br/>
				<br/>
				<c:if test = "${loginUser.departmentId==2 || loginUser.id == post.userId ||
										 loginUser.branchId == post.branchId && loginUser.departmentId == 3 }">
					<form action="postsDelete" method="post"  onSubmit="return check()">
						<button type="submit" name="id" value="${post.id }">削除</button>
					</form>
				</c:if>
			</div>
			<div class="form-area">
				<form action="comment" method="post" onSubmit="return double()" >
					<input name="postId" type="hidden" id="postId" value ="${post.id}"><br />
					コメント(500文字以下)<br/>

					<textarea name="text" rows="5" cols="100" maxlength="500" class="tweet-box"
							 onkeyup="CountDownLength( '${post.id }' , value , 500 );"></textarea>
						<p id=${post.id } ></p>

					<br/>
					<input type="submit" value="コメント">
				</form>
				<br/>
					<div class="comment">
						<c:forEach items="${comments}" var="comment">
							<c:if test = "${post.id == comment.postId}">
							<div class="comment">
							<div class="account-name">
							<span class="name"><c:out value="${comment.name }"/></span>さんのコメント<br/>
								<c:forEach var="text" items="${fn:split(comment.text, '
											')}">
    								<div><c:out value="${text}"/></div>
								</c:forEach>
							<br/>
							<div class="date"><fmt:formatDate value="${comment.createdAt }"
								pattern="yyyy/MM/dd HH:mm:ss"/><br/></div>
							所属：<span class="branchName"><c:out value="${comment.branchName }"/></span>/
							部署・役職：<span class="departmentName"><c:out value="${comment.departmentName }"/>
							</span><br/>
							</div>
							<br/>
								<c:if test = "${loginUser.departmentId==2 || loginUser.id == comment.userId ||
										 loginUser.branchId == comment.branchId && loginUser.departmentId == 3 }">
									<form action="commentDelete" method="post"  onSubmit="return check()">
										<button type="submit" name="id" value="${comment.id }">削除</button>
									</form>
								</c:if>
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