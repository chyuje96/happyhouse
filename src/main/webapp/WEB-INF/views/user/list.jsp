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
		$.ajax({
			url:'${root}/admin/user',  
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(users) {
				makeList(users);
			},
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500: function() {
					alert("서버 에러 발생");
					// location.href = "/error/e500";
				},
				404: function() {
					alert("페이지 에러 발생");
					// location.href = "/error/e404";
				}
			}	
		});
		
		$("#registerBtn").click(function() {
			let registerinfo = JSON.stringify({
				"userId" : $("#userId").val(), 
				"userPassword" : $("#userPassword").val(), 
				"userName" : $("#userName").val(),
				"userAddress" : $("#userAddress").val(),
				"userPhoneNumber" : $("#userPhoneNumber").val()
			   });
			$.ajax({
				url:'${root}/admin/user',  
				type:'POST',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data: registerinfo,
				success:function(users) {
					$("#userId").val(), 
					$("#userPassword").val(), 
					$("#userName").val(),
					$("#userAddress").val(),
					$("#userPhoneNumber").val(),
					$("#userRegModal").modal("hide");
					makeList(users);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
		});
		
		$(document).on("dblclick", "tr.view", function() {
			let vid = $(this).attr("data-id");
			$.ajax({
				url:'${root}/admin/user/' + vid,  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				success:function(user) {
					$("#vid").text(user.userId);
					$("#vname").text(user.userName);
					$("#vaddress").text(user.userAddress);
					$("#vphonenumber").text(user.userPhoneNumber);
					$("#userViewModal").modal();
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}				
			});			
		});
		
		$(document).on("click", ".modiBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			$("#view_" + mid).css("display", "none");
			$("#mview_" + mid).css("display", "");
		});

		$(document).on("click", ".modifyBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			let modifyinfo = JSON.stringify({
						"userId" : mid, 
						"userPassword" : $("#userPassword" + mid).val(),
						"userAddress" : $("#userAddress" + mid).val(),
						"userPhoneNumber" : $("#userPhoneNumber" + mid).val()
					
					   });
			$.ajax({
				url:'${root}/admin/user',  
				type:'PUT',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data: modifyinfo,
				success:function(users) {
					makeList(users);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
			$("#view_" + mid).css("display", "");
			$("#mview_" + mid).css("display", "none");
		});
		
		$(document).on("click", ".cancelBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			$("#view_" + mid).css("display", "");
			$("#mview_" + mid).css("display", "none");
		});
		
		$(document).on("click", ".delBtn", function() {
			if(confirm("정말 삭제하시겠어요?")) {
				let delid = $(this).parents("tr").attr("data-id");
				$.ajax({
					url:'${root}/admin/user/' + delid,  
					type:'DELETE',
					contentType:'application/json;charset=utf-8',
					dataType:'json',
					success:function(users) {
						makeList(users);
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				});
			}
		});

	});
	
	function makeList(users) {
		$("#userlist").empty();
		$(users).each(function(index, user) {
		
			let str = `
			<tr id="view_${'${user.userId}'}" class="view" data-id="${'${user.userId}'}">
				<td>${'${user.userId}'}</td>
				<td>${'${user.userPassword}'}</td>
				<td>${'${user.userName}'}</td>
				<td>${'${user.userAddress}'}</td>
				<td>${'${user.userPhoneNumber}'}</td>
				<td>
					<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
					<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
				</td>
			</tr>
			<tr id="mview_${'${user.userId}'}" data-id="${'${user.userId}'}" style="display: none;">
				<td>${'${user.userId}'}</td>
				<td><input type="text" name="userPassword" id="userPassword${'${user.userId}'}" value="${'${user.userPassword}'}"></td>
				<td><input type="text" name="userName" id="userName${'${user.userId}'}" value="${'${user.userName}'}"></td>
				<td><input type="text" name="userAddress" id="userAddress${'${user.userId}'}" value="${'${user.userAddress}'}"></td>
				<td><input type="text" name="userPhoneNumber" id="userPhoneNumber${'${user.userId}'}" value="${'${user.userPhoneNumber}'}"></td>
				<td>
					<button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
					<button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
				</td>
			</tr>
			`;
			$("#userlist").append(str);
		});//each
	}

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

	<div class="container text-center mt-3">
		<div class="col-lg-12 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<p>회원목록</p>
			</h2>
			
			<!-- 검색 input
			<div class="search-User">
				<input id="userSearch" type="text" placeholder="검색어 입력">
				<img id="searchIcon" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" >
			</div>  
			-->
			
			<div class="mb-3 text-right" id="register-User">
				<button type="button" class="modiBtn btn btn-outline-primary"
					data-toggle="modal" data-target="#userRegModal">등록</button>
			</div>
			<table class="table table-hover">
				<colgroup>
					<col width="120">
					<col width="120">
					<col width="120">
					<col width="170">
					<col width="120">
					<col width="130">
				</colgroup>
				<thead>
					<tr>
						<th class="text-center">아이디</th>
						<th class="text-center">비밀번호</th>
						<th class="text-center">이름</th>
						<th class="text-center">주소</th>
						<th class="text-center">연락처</th>
						<th class="text-center">수정/삭제</th>
					</tr>
				</thead>
				<tbody id="userlist"></tbody>
			</table>
		</div>
	</div>

	<!-- 회원 등록 모달 -->
	<div class="modal" id="userRegModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form id="memberform" method="post" action="">

						<div class="form-group" align="left">
							<label for="">아이디</label> <input type="text" class="form-control"
								id="userId" name="userId" placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <input type="password"
								class="form-control" id="userPassword" name="userPassword"
								placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="userName">이름</label> <input type="text"
								class="form-control" id="userName" name="userName"
								placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="userAddress">이메일</label><br> <input type="text"
								class="form-control" id="userAddress" name="userAddress"
								placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="userPhoneNumber">이메일</label><br> <input
								type="text" class="form-control" id="userPhoneNumber"
								name="userPhoneNumber" placeholder="">
						</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-primary" id="registerBtn">회원가입</button>
							<button type="reset" class="btn btn-warning">초기화</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 회원 정보 모달 -->
	<div class="modal" id="userViewModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원정보</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<table class="table table-bordered">
						<colgroup>
							<col width="120">
							<col width="*">
							<col width="120">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th class="text-center">ID</th>
								<td class="text-left" id="vid"></td>
								<th class="text-center">회원명</th>
								<td class="text-left" id="vname"></td>
							</tr>
							<tr>
								<th class="text-center">주소</th>
								<td class="text-left" id="vaddress"></td>
								<th class="text-center">연락처</th>
								<td class="text-left" id="vphonenumber"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

</body>
</html>