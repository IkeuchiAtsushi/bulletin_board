<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${loginUser.loginId}の設定</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script>
var set=0;
function double() {
if(set==0){ set=1; } else {
alert("只今処理中です。\nそのままお待ちください。");
return false; }}
</script>
</head>
<body>
<h1>社内掲示板</h1>
<div class="main-contents">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<br/><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
<form action="settings" method="post"onSubmit="return double()" ><br />

	<input name="id" type="hidden" id="id" value ="${editUser.id}"><br />

	<label for="login_id">ログインID</label>
	<input name="loginId" value="${editUser.loginId}" maxlength="20" />半角英数字6文字以上～20文字以下<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password" maxlength="20" />記号も含む全ての半角文字6文字以上～20文字以下<br />

	<label for="passwordConfirm">パスワード確認</label>
	<input name="passwordConfirm" type="password" id="passwordConfirm"><br/>

	<label for="name">アカウント名</label>
	<input name="name" value="${editUser.name}" id="name" maxlength="10" />10文字以下<br />

	<c:if test="${loginUser.id != editUser.id }" >
		<label for="branch_id">支店名</label>
		<select name=branchId>
			<c:forEach items="${branches}" var="branch">
				<option value="${branch.id}" <c:if test="${branch.id==editUser.branchId }">
					selected</c:if>>${branch.name}</option>
			</c:forEach>
		</select>
		<br/>
		<label for="department_id">部署・役職</label>
		<select name="departmentId">
			<c:forEach items="${departments}" var="department">
				<option value="${department.id}" <c:if test="${department.id==editUser.departmentId }">
					selected</c:if>>${department.name}</option>
			</c:forEach>
		</select>
		<br/>
	</c:if>
		<c:if test="${loginUser.id == editUser.id }">
			<input type=hidden name="branchId" value="${1}">
			<input type=hidden name="departmentId" value="${1}">
		</c:if>
	<input class="square_btn" type="submit" value="登録" /> <br />
	<a href="userManagement" >戻る</a>
</form>
</div>
</body>
</html>
