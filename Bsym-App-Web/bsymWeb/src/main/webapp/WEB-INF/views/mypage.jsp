<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/mypage/mypage.css">
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
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
		<a id="dropdown_btn" class="btn dropdown-button" href="#" data-activates="dropdown" style="background-color: rgb(240, 114, 10);"> <i class="material-icons" style="margin: 0px; font-size: 36px; height: 56px; line-height: 36px;">dehaze</i></a>
      <ul id="dropdown" class="dropdown-content" style="width: 200px">
        <c:choose>
          <c:when test="${empty loginUser.name}">
            <li><a href="/bsym/auth/login" style="font-size:20px">로그인</a></li>
            <li class="divider"></li>
            <li><a href="/bsym/auth/join" style="font-size:17px">회원가입</a></li>
            <li class="divider"></li>
            <li><a href="/bsym/web/myPage" style="font-size:13px">마이페이지</a></li>
          </c:when>
          <c:otherwise>
            <li><a class="nav-link" style="font-size:15px">${loginUser.name}</a></li>
            <li class="divider"></li>
            <li><a class="nav-link" href="/logout" style="font-size:17px">로그아웃</a></li>
            <li class="divider"></li>
            <li><a href="/bsym/web/myPage" style="font-size:13px">마이페이지</a></li>
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
			<a
				href="/bsym/web/bloglistPage/${blogInfo.division}?broadcastName=${blogInfo.broadcastName}"
				class="brand-logo center"> <img src="/image/cook/main_logo.png"></a>
		</div>
	</nav>
	<div class="cook_main">
		<div class="bs_basket_container">
			<div class="basket_menu">
				<ul id="tabs-swipe-demo" class="tabs" style="height: 60px">
					<li class="tab col s4"><a class="active" href="#swipe-1">좋아요 글</a></li>
					<li class="tab col s4"><a class="active" href="#swipe-2">공유한 글</a></li>
				</ul>
			</div>
			<div id="swipe-1" class="cook_list">
				<c:forEach var="item" items="${mypage.like}">
					<div class="cook_board">
						<div class="cook_board_image">
							<a href="/bsym/web/blogDetail/${item.bsymBoard.id}"> <img src="/image/sumnail/${item.bsymBoard.id}.jpg"></a>
						</div>
						<div class="cook_board_title_box">${item.bsymBoard.title}<br /></div>
						<div class="cook_board_function_box">
							<div class="cook_board_function">
								<div class="cook_board_function_like" onclick="unlikes(${loginUser.id},${item.bsymBoard.id})"><i class="material-icons" style="cursor: pointer">favorite</i></div>
								<span class="cook_board_function_font">&nbsp;${item.bsymBoard.likeCount}</span>
							</div>
							<div class="cook_board_function">
								<i class="material-icons">visibility</i> <span
									class="cook_board_function_font">&nbsp;${item.bsymBoard.readCount}</span>
							</div>
							<div class="cook_board_function" onclick="Shares(${loginUser.id}, ${item.bsymBoard.id},'${item.bsymBoard.link}')">
								<i class="material-icons" style="cursor: pointer">timeline</i>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="core_skip_top"><a href="#" id="btnSkipTop" title="상단으로 이동">상단으로 이동</a></div> <!-- 아마 이거일겁니다. -->
			</div>
			<div id="swipe-2" class="cook_list">
				<c:forEach var="item" items="${mypage.share}">
					<div class="cook_board">
					
						<div class="cook_board_image">
							<a href="/bsym/web/blogDetail/${item.bsymBoard.id}"> <img src="/image/sumnail/${item.bsymBoard.id}.jpg"></a>
						</div>
						<div class="cook_board_title_box">${item.bsymBoard.title}<br />
						</div>
						<div class="cook_board_function_box">
							<div class="cook_board_function">
							</div>
							<div class="cook_board_function" onclick="shereDel(${loginUser.id},${item.bsymBoard.id})">
								<i class="material-icons" style="cursor: pointer">delete_forever</i>
							</div>
							<div class="cook_board_function" onclick="copyToClipboard('${item.bsymBoard.link}')">
								<i class="material-icons" style="cursor: pointer">timeline</i>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="core_skip_top"><a href="#" id="btnSkipTop" title="상단으로 이동" >상단으로 이동</a></div> <!-- 아마 이거일겁니다. -->
			</div>
		</div>
	</div>
	<script>
    	function unlikes(userId, boardId){
    		var url = '/bsym/unlike/'+boardId
    		fetch(url,{
    			method:"POST"
    		}).then(function(res){
    			console.log(res);
    			return res.text();
    		}).then(function(result){
    			var re = JSON.parse(result);
    			if(re.loginCheck === 'ok'){
    				alert('좋아요가 취소되었습니다.');
    				location.href='/bsym/web/myPage';
       			}
    		}).catch();
    	}
    	function shereDel(userId, boardId){
    		var url = '/bsym/shareDel/'+boardId
    		fetch(url,{
    			method:"POST"
    		}).then(function(res){
    			console.log(res);
    			return res.text();
    		}).then(function(result){
    			if(result === 'ok'){
    				alert('공유한글 삭제하였습니다.');
    				location.href='/bsym/web/myPage';
       			}
    		});
    	}
    </script>
    <script>
    function copyToClipboard(link) {
        var t = document.createElement("textarea");
        document.body.appendChild(t);
        t.value = link;
        t.select();
        document.execCommand('copy');
        document.body.removeChild(t);
        alert("복사가 완료되었습니다.\n"+link);
      }
    function Shares(userId,boardId,link){
        var url = '/bsym/share/'+boardId
        fetch(url,{
        	method:"POST"
        }).then(function(res){
        	console.log(res);
        	return res.text();
        }).then(function(result){
        	 copyToClipboard(link);
        	if(result === 'ok'){
        		alert("공유함에 담겼습니다.")
        		location.href='/bsym/web/myPage';
        	}else if(result === 'overlap'){
				alert("공유함에 담겨있습니다.")
			}else if(result === 'error'){
				alert("공유함에 담으실려면 로그인을 해주세요.")
			}
        })
      }
    </script>
</body>

</html>