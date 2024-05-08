<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

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
	  $(document).ready(function () {
      	var isId = false;
      	$("#userId").keyup(function () {
      		var ckid = $("#userId").val();
      		if(ckid.length < 6 || ckid.length > 16) {
      			$("#idresult").text("아이디는 6자이상 16자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
      			isId = false;
      		} else {
	                $.ajax({
	                	url: '${root}/user/idcheck',
	                	data: {'ckid': ckid},
	                  	type: 'GET',
	                  	dataType: 'json',
	                  	success: function (response) {
	                  		console.log(response);
	                    	var cnt = response.idcount;
	                    	if(cnt == 0) {
	                    		$("#idresult").text(ckid + "는 사용가능합니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
	                    		isId = true;
	                    	} else {
	                    		$("#idresult").text(ckid + "는 사용할 수 없습니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
	                    		isId = false;
	                    	}
	                  	}, 
	                  	error: function(request, status, error) {
	                  		console.log("status : " + request.status + "\tmsg : " + error);
	                  	}
					});
      		}
			}); 
        	
        	// 회원가입
            $("#registerBtn").click(function () {
                if (!$("#userName").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!isId) {
                    alert("아이디 확인!!!");
                    return;
                } else if (!$("#userPassword").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if ($("#userPassword").val() != $("#pwdcheck").val()) {
                    alert("비밀번호 확인!!!");
                    return;
                } else {
        			alert("회원가입 성공!!");
                    $("#memberform").attr("action", "${root}/user/register").submit();
                }
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
		<form id="memberform" class="text-left mb-3" method="post" action="">
			<input type="hidden" id="email" name="email">
			<div class="form-group">
				<label for="userId" style="color: #6799FF; font-weight: bold">아이디</label>
				<input type="text" class="form-control" id="userId" name="userId"
					placeholder="아이디를 입력하세요.">
				<div id="idresult" class="mt-1"></div>
			</div>
			<div class="form-group">
				<label for="userPassword" style="color: #6799FF; font-weight: bold">비밀번호</label>
				<input type="password" class="form-control" id="userPassword"
					name="userPassword" placeholder="비밀번호를 입력하세요.">
			</div>
			<div class="form-group">
				<label for="pwdcheck" style="color: #6799FF; font-weight: bold">비밀번호
					재입력</label> <input type="password" class="form-control" id="pwdcheck"
					name="pwdcheck" placeholder="비밀번호를 다시 입력하세요.">
			</div>
			<div class="form-group">
				<label for="userName" style="color: #6799FF; font-weight: bold">이름</label>
				<input type="text" class="form-control" id="userName"
					name="userName" placeholder="이름을 입력하세요.">
			</div>
			<div class="form-group">
				<label for="userAddress" style="color: #6799FF; font-weight: bold">주소</label>
				<input type="text" class="form-control" id="userAddress"
					name="userAddress" placeholder="주소를 입력하세요.">
			</div>
			<div class="form-group">
				<label for="userPhoneNumber" style="color: #6799FF; font-weight: bold">연락처</label>
				<input type="text" class="form-control" id="userPhoneNumber"
					name="userPhoneNumber" placeholder="연락처를 입력하세요.">
			</div>
			<div class="form-group text-center">
				<button type="button" id="registerBtn"
					class="btn btn-outline-primary"
					style="font-weight: bold; color: black">REGISTER</button>
				<button type="reset" class="btn btn-outline-danger"
					style="font-weight: bold; color: black">RESET</button>
			</div>
		</form>
	</div>
</body>
</html>