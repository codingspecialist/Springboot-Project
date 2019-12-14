<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인페이지</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
       		 <a href="#!" class="brand-logo center"><img src="/image/cook/main_logo.png"></a>
            <!--  <a href="/bsym/web/bloglistPage/${blogInfo.division}?broadcastName=${blogInfo.broadcastName}" class="brand-logo center"><img src="/image/cook/main_logo.png"></a>-->
        </div>
    </nav>
    <div class="cook_main">
        <div class="bs_login_container">
            <form class="col s12" action="/auth/loginProc" method="POST">
                <div class="row">
                    <div class="input-field col s12">
                        <input name="username" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input name="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">로그인
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
    </div>
</body>

</html>