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

<form action="settings" method="post" enctype="multipart/form-data"><br />

	<label for="login_id">ログインID</label>
	<input name="loginId" value="${editUser.loginId}" /><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/> <br />

	<label for="branch_id">支店名</label>
	<input name="branchId" value="${editUser.branchId}" id="branch_id"/> <br />

	<label for="department_id">部署.役職</label>
	<input name="departmentId" value="${editUser.departmentId}" id="department_id"/> <br />

	<input type="submit" value="登録" /> <br />
	<a href="userManagement">戻る</a>

</form>
<div class="copyright">Copyright(c)Satoshi Kimura</div>
</div>
</body>
</html>
