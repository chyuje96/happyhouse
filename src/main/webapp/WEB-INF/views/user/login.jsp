<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Framework Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="/resources/css/styles.css" rel="stylesheet" />
<link href="/resources/css/custom_styles.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginBtn").click(function() {
			if (!$("#luserid").val()) {
				alert("아이디 입력!!!");
				return;
			} else if (!$("#luserpwd").val()) {
				alert("비밀번호 입력!!!");
				return;
			} else {
				$("#loginform").attr("action", "${root}/user/login").submit();
			}
		});
		$("#mvRegisterBtn").click(function() {
			location.href = "${root}/user/login";
		});
	});
</script>
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/">HappyHouse</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">공지사항</a></li>

					<c:if test="${empty user}">
						<div class="text-danger mb-2" id="msg">${msg}</div>
						<li class="nav-item" id="login_button"><a class="nav-link"
							href="/user/login">로그인</a></li>
						<li class="nav-item" id="register_button"><a class="nav-link"
							href="/user/register"">회원가입</a></li>
					</c:if>
					<c:if test="${!empty user}">
						<!-- 로그인 정보가 있는 경우는 로그아웃과 회원정보를 위한 링크를 생성한다. -->
						<li class="nav-item"><a class="nav-link" href="#"
							style="color: lightgreen">안녕하세요 ${user.userId } </a></li>
						<li class="nav-item"><a class="nav-link" href="/user/logout"
							>로그아웃</a></li>
						<c:if test="${user.userId eq 'admin'}">
							<li class="nav-item"><a class="nav-link" href="/user/list"
								style="color: #FF00FF">회원관리</a></li>
						</c:if>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>



	<!-- Page header with logo and tagline-->
	<header class="py-5 bg-light border-bottom mb-4">
		<div class="container">
			<div class="text-center my-5">
				<h1 class="fw-bolder">집값여지도</h1>
				<p class="lead mb-0">다른 동네 최근 아파트 거래 가격이 알고싶니?</p>
			</div>
		</div>
	</header>

	<div class="container">
		<form id="loginform" class="text-left mb-3" method="post" action="">

			<div class="form-group">
				<label for="userId" style="font-weight: bold; color: #6799FF">아이디</label>
				<input type="text" class="form-control" id="luserid" name=userId
					placeholder="" value="${saveid}">
			</div>
			<div class="form-group">
				<label for="userPassword" style="font-weight: bold; color: #6799FF">비밀번호</label>
				<input type="password" class="form-control" id="luserpwd"
					name="userPassword" placeholder="">
			</div>
			<div class="form-group text-center">
				<button type="button" id="loginBtn" class="btn btn-outline-warning"
					style="font-weight: bold; color: black">LOGIN</button>
				<button type="button" id="mvRegisterBtn"
					class="btn btn-outline-primary"
					style="font-weight: bold; color: black">RESET</button>
			</div>
		</form>
	</div>
</body>
</html>