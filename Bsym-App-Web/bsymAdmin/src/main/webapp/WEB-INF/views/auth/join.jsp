<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/join/join.css">
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
</head>

<body>
	<nav>
		<div class="menu_bar_left">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="/admin/auth/login">관리자로그인</a></li>
				<li><a href="/admin/auth/join">관리자회원가입</a></li>
			</ul>
		</div>
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo center"> <img
				src="/image/cook/main_logo.png"></a>
		</div>
	</nav>
	<div class="cook_main">
		<div class="bs_login_container">
			<div class="row">
				<form class="col s12" id="form" name="form" method="POST" action="/admin/auth/joinProcess" onsubmit="return validateJoin()">
					<div class="row">
						<div class="input-field col s9">
							<i class="material-icons prefix">mail_outline</i> 
							<input id="id" name="username" type="email" class="validate" required /> 
							<label for="email">Email</label>
						</div>
						<div class="back_button col s3">
							<button class="waves-effect waves-light btn-large" type="button" name="action" value="중복확인" onclick="validateDuplicateID()" style="margin-top:10px;">중복확인</button>
					</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">keyboard</i> 
							<input id="pw1"name="password" type="password" class="validate" required /> 
							<label for="password">Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">keyboard_hide</i> 
							<input id="pw2" name="password2" type="password" class="validate"required />
							<label for="password2">Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">account_circle</i> <input
								name="name" type="text" class="validate" required /> <label
								for="name">Name</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">home</i> <input name="address"
								type="text" class="validate" required /> <label for="address">Address</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">phone</i> <input
								name="phone" type="tel" class="validate" required /> <label
								for="telephone">Telephone</label>
						</div>
					</div>
					<div class="row" style="height: 84px; text-align: left;" >
						<div class="input-field col s12" style="height: 54px">
							<i class="material-icons prefix">wc</i>
							<div class="radio_button">
								<label style="margin-right: 20px"> <input
									class="with-gap" name="gender" type="radio" checked value="남" />
									<span>남</span>
								</label> <label> <input class="with-gap" name="gender"
									type="radio" value="여" /> <span>여</span>
								</label>
							</div>
						</div>
					</div>
					<div class="join_button">
						<button class="waves-effect waves-light btn-large" type="submit"
							name="action">가입하기</button>
					</div>
					<div class="back_button">
						<a href="javascript:history.back();">
							<button class="waves-effect waves-light btn-large" type="button"
								name="action">돌아가기</button>
						</a>
					</div>
				</form>
			</div>

		</div>
	</div>
	<script>
		let check = 1;
		function loadAjax(name) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					if (this.responseText == "ok") {
						alert("중복된 아이디가 없습니다.");
						check = 0;
					} else {
						alert("중복된 아이디가 있습니다.");
						check = 1;
					}
					this.responseText;
				}
			};
			xhttp.open("GET", "rest?id=" + id, true);
			xhttp.send();
		}
	</script>

	<script>
		function validateDuplicateID() {
			//var id_element = document.querySelector("#name");
			//var id = id_element.value;
			check=0;
			//loadAjax(name);
		}
		function validateJoin() {
			if (check == 1) {
				alert('ID 중복확인을 해주세요.');
				return false;
			}
			var pw1 = document.querySelector("#pwd1");
			var pw2 = document.querySelector("#pwd2");

			console.log(pw1.value);
			console.log(pw2.value);

			if (pw1.value === pw2.value) {
				return true;
			} else {
				pw1.value = "";
				pw2.value = "";
				pw1.focus();
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
	</script>
</body>

</html>