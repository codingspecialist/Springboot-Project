<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>
    <link rel="stylesheet" href="/css/imagehover.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/home/home.css">
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
</head>

<body>
    <div class="container">
        <div class="bscook_image ">
            <figure class="imghvr-slide-right">
                <img src="/image/main/bscook_image.jpg" height="775px" width="100%">
                <figcaption>
                    
                    <a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙">	<!-- 요리페이지 -->
                        <img src="/image/main/bs_mjyr.png"  width="80%"><br /><br />
                    </a>
                </figcaption>
            </figure>
        </div>
        <div class="bsrestaurant_image">
            <figure class="imghvr-slide-left">
                <img src="/image/main/bsrestaurant_image.jpg" height="775px" width="100%">
                <figcaption>
                    
                    <a href="/bsym/web/bloglistPage/res?broadcastName=미식클럽">	<!-- 맛집페이지 -->
                        <img src="/image/main/bs_mjmj.png"width="80%"><br /><br />
                    </a>
                </figcaption>
            </figure>
        </div>
    </div>
    <div class="footer">
        <div class="bscook_message">
            <img src="/image/main/bscook_mj.png"width="80%" alt="X">
        </div>
        <div class="main_image">
            <a href="/bsym/web/home"><img src="/image/main/main_logo.png"></a>
        </div>
        <div class="bsrestaurant_message">
            <img src="/image/main/bsrestaurant_image_mj.png"width="80%" alt="X">
        </div>
    </div>
</body>

</html>