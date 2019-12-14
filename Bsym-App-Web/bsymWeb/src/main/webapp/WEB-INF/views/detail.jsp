<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글페이지</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/css/style.css"/>
<link rel="stylesheet" href="/css/board/board.css"/>
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>
</head>

<body>
    <nav>
        <div class="menu_bar_left">
		<a id="dropdown_btn" class="btn dropdown-button" href="#" data-activates="dropdown" style="background-color: rgb(240, 114, 10);"> <i class="material-icons" style="margin: 0px; font-size: 36px; height: 56px; line-height: 36px;">dehaze</i></a>
      <ul id="dropdown" class="dropdown-content" style="width: 200px">
        <c:choose>
          <c:when test="${empty loginUser.name}">
            <li><a href="/bsym/auth/login" style="font-size:20px">로그인</a></li>
            <li class="divider"></li>
            <li><a href="/bsym/auth/join" style="font-size:17px">회원가입</a></li>
          </c:when>
          <c:otherwise>
            <li><a class="nav-link" style="font-size:15px">${loginUser.name}</a></li>
            <li class="divider"></li>
            <li><a class="nav-link" href="/logout" style="font-size:17px">로그아웃</a></li>
          </c:otherwise>
        </c:choose>
                    <li class="divider"></li>
            <li><a href="/bsym/web/myPage" style="font-size:13px">마이페이지</a></li>
            <li class="divider"></li>
            	<c:choose>
					<c:when test="${blog.division eq 'cook'}">
						<li><a href="/bsym/web/bloglistPage/res?broadcastName=미식클럽" style="font-size:17px">방송맛집</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙" style="font-size:17px">방송요리</a></li>
					</c:otherwise>
				</c:choose>
      </ul>
            <ul id="nav-mobile" class="left hide-on-med-and-down">
               <c:choose>
					<c:when test="${empty loginUser.name}">
						<li class="nav-item">
							<a class="nav-link" href="/bsym/auth/login">로그인</a>
						</li>
						<li><a href="/bsym/auth/join">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" >${loginUser.name}</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li><a href="/bsym/web/myPage">마이페이지</a></li>
            </ul>
        </div>
        <div class="nav-wrapper">
            <a href="/bsym/web/bloglistPage/${blog.division}?broadcastName=${blog.broadcastName}" class="brand-logo center"><img src="/image/cook/main_logo.png"></a>
        </div>
        <div class="menu_bar_right">
            <ul id="nav-mobile" class="left hide-on-med-and-down">
            	<c:choose>
					<c:when test="${blog.division eq 'cook'}">
						<li><a href="/bsym/web/bloglistPage/res?broadcastName=미식클럽">방송맛집</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙">방송요리</a></li>
					</c:otherwise>
				</c:choose>
            </ul>
        </div>
    </nav>
    <div class="cook_main">
       <iframe src="${blog.link}"></iframe>
    </div>
</body>

</html>