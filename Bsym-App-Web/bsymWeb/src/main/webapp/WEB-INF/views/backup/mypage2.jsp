<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>

<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css'>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/mypage/mypage.css">
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
<script>
    $(document).ready(function () {
      $('ul.tabs').tabs({
        swipeable: true,
        responsiveThreshold: 1200
      });
    });
  </script>
</head>

<body>
	<nav>
		<div class="menu_bar_left">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<c:choose>
					<c:when test="${empty sessionScope.userLoginInfo.username}">
						<li class="nav-item"><a class="nav-link"
							href="/bsym/auth/login">로그인</a></li>
						<li><a href="/bsym/auth/join">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link">${sessionScope.userLoginInfo.name}</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/bsym/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li><a href="/bsym/web/myPage/${sessionScope.userLoginInfo.id}">마이페이지</a></li>
			</ul>
		</div>
		<div class="nav-wrapper">
			<a
				href="/bsym/web/bloglistPage/${blogInfo.division}?broadcastName=${blogInfo.broadcastName}"
				class="brand-logo center"> <img src="/image/cook/main_logo.png"></a>
		</div>
		<div class="menu_bar_right">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="방송맛집 페이지">방송맛집</a></li>
			</ul>
		</div>
	</nav>
	<div class="cook_main">
		<div class="bs_basket_container">
			<div class="basket_menu">
				<ul id="tabs-swipe-demo" class="tabs" style="height: 60px">
					<li class="tab col s4"><a class="active" href="#swipe-1">좋아요
							글</a></li>
					<li class="tab col s4"><a class="active" href="#swipe-2">공유한
							글</a></li>
				</ul>
			</div>
			<div id="swipe-1" class="cook_list">
				<c:forEach var="item" items="${mypage.like}">
					<div class="cook_board">
						<div class="cook_board_image">
							<a href="${item.bsymBoard.link}"> <img
								src="${item.bsymBoard.imgPath}"></a>
						</div>
						<div class="cook_board_title_box">
							${item.bsymBoard.title}<br />
						</div>
						<div class="cook_board_function_box">
							<div class="cook_board_function">
								<c:set var="like_check" value="false" />
								<div id="b${item.bsymBoard.id}">
									<c:choose>
										<c:when test="${like_check eq true}">
											<div onclick="likes(false,${item.bsymBoard.id})">
												<i class="material-icons" style="cursor: pointer">favorite</i>
											</div>
										</c:when>
										<c:otherwise>
											<div onclick="likes(true,${item.bsymBoard.id})">
												<i class="material-icons" style="cursor: pointer">favorite_border</i>
											</div>

										</c:otherwise>
									</c:choose>
								</div>
								<span class="cook_board_function_font">&nbsp;${item.bsymBoard.likeCount}</span>
							</div>
							<div class="cook_board_function">
								<i class="material-icons">visibility</i> <span
									class="cook_board_function_font">&nbsp;${item.bsymBoard.readCount}</span>
							</div>
							<div class="cook_board_function">
								<a href="#"><i class="material-icons">timeline</i></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div id="swipe-2" class="cook_list">

				<c:forEach var="item" items="${mypage.like}">
					<div class="cook_board">
						<div class="cook_board_image">
							<a href="${item.bsymBoard.link}"> <img
								src="${item.bsymBoard.imgPath}"></a>
						</div>
						<div class="cook_board_title_box">
							${item.bsymBoard.title}<br />
						</div>
						<div class="cook_board_function_box">
							<div class="cook_board_function">
								<a href="#"><i class="material-icons">timeline</i></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>

</html>