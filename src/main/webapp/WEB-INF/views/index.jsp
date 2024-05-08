<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>HappyHouse</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="resources/css/styles.css" rel="stylesheet" />
<link href="resources/css/custom_styles.css" rel="stylesheet" />
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
						<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
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
	<!-- Page content-->
	<!-- 추가한 deal container-->
	<div class="container deal-container">
		<div class="row">
			<form action="">
				<select id="si-select" class="form-select location-select">
					<option>광역시 / 도</option>
				</select> <select id="goo-select" class="form-select location-select">
					<option>시 / 구 / 군</option>
				</select> <select id="dong-select" class="form-select location-select">
					<option>읍 / 면 / 동</option>
				</select>
			</form>
		</div>

		<div class="row">
			<div class="col-2 deal-box">
				<!-- 
              api 호출이 초과됐을 때 호출용 코드
              <div class="deal-content">
              <a class="deal-content-title" href="javascript:void(0);" onClick="getAptDeal(this);">다솜빌</a>
              <p>100,000</p>
              <p>2022.03.05</p>
              </div> -->
			</div>
			<div id="googleMap" class="col map-box"></div>
		</div>
	</div>



	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- 추가한 JS-->
	<script src="resources/js/getData.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAbYVt33lOy68gqAkzzcfQCKZcgWbD17yw&callback=myMap&libraries=&v=weekly"
		async></script>
</body>
</html>
