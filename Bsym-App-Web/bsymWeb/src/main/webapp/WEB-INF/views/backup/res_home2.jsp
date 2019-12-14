<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방송요리 페이지</title>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.bxslider.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css"/>
<link rel="stylesheet" href="/css/cook_home/cook_home.css"/>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<script>
        document.addEventListener('DOMContentLoaded', function () {
            var elems = document.querySelectorAll('.carousel');
            var instances = M.Carousel.init(elems, options);
        });

        // Or with jQuery

        $(document).ready(function () {
            $('.carousel').carousel();
        });
    </script>

</head>

<body>
	<nav>
		<div class="menu_bar_left">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li>${sessionScope.userLoginInfo.id}</li>
				<li>${sessionScope.userLoginInfo.username}</li>
				<c:choose>
					<c:when test="${empty sessionScope.userLoginInfo.username}">
						<li class="nav-item">
							<a class="nav-link" href="/bsym/auth/login">로그인</a>
						</li>
						<li><a href="/bsym/auth/join">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" >${sessionScope.userLoginInfo.name}</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="member?cmd=memberLogout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li><a href="/bsym/web/myPage/${sessionScope.userLoginInfo.id}">마이페이지</a></li>
			</ul>
		</div>
		<div class="nav-wrapper">
			<a href="/bsym/web/bloglistAll/res?broadcastName=미식클럽" class="brand-logo center">	<!-- 요리페이지 새로고침 -->
			<img src="/image/res/main_logo.png"></a>
		</div>
		<div class="menu_bar_right">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="/bsym/web/bloglistAll/cook?broadcastName=스페인하숙">방송요리</a></li>
			</ul>
		</div>
	</nav>
	<% String[] castName={"미식클럽","수요미식회","3대천왕","밥블레스유","식신로드","맛있는녀셕들","외식하는날","골목식당","전참시","생방송투데이"}; %>
	<div class="carousel">
	<c:set var="castName" value="${castName}" />
	<%	String var = (String)pageContext.getAttribute("castName");%>
	<%	int i=0,check=0;
		for(check=0; check<castName.length; check++){
			if(var.equals(castName[check])){
				i=check;
				break;
			}
		}%>
	<%	for(i=check; i<castName.length; i++){ %>
		<a class="carousel-item" href="?broadcastName=<%=castName[i]%>" id="<%=castName[i]%>"><%=castName[i]%><img src="/image/res/subimg<%=i+1%>.png "></a> 
	<% }%>
	<%	for(i=0; i<check; i++){ %>
		<a class="carousel-item" href="?broadcastName=<%=castName[i]%>" id="<%=castName[i]%>"><%=castName[i]%><img src="/image/res/subimg<%=i+1%>.png "></a> 
	<% }%>
	</div>
	<div class="cook_main">
		<div class="cook_list_menu">
			<h2 class="line_box">
				<span class="line_font"> <strong class="bold">Content</strong>
				</span>
			</h2>
		</div>
		<ul id="sub_menu">
			<li class="btns on"><a href="#" >최신순</a></li>
			<li class="btns"><a href="#" class="new">조회수순</a></li>
			<li class="btns"><a href="#">좋아요순</a></li>
		</ul>

		<div class="cook_list">
			<c:forEach var="item" items="${blogs}" begin="0" end="7">
				<div class="cook_board">
					<div class="cook_board_image">
						<a href="/bsym/web/blogDetail/${item.id}"><img src="/image/sumnail/${item.id}.jpg"></a>
					</div>
					<div class="cook_board_function_box">
						<div class="cook_board_function">
						<a href="#"><i class="material-icons">favorite_border</i></a>
						<span class="cook_board_function_font">&nbsp;${item.likeCount}</span>
						<%-- <c:set var="like_check" value="false" />

							<c:forEach var="like" items="${image.likes}">
								<c:if test="${like.user.id eq user.id}">	
									<c:set var="like_check" value="true" />
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${like_check eq true}">

									<div class="c__1 c__1${image.id}">
										<a href="#" onclick="like(${image.id},${fn:length(image.likes)}, -1)" height="30px"><i class="material-icons">star</i></a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="c__1 c__1${image.id}">
										<a href="#" onclick="like(${image.id},${fn:length(image.likes)}, 1)" height="30px"><i class="material-icons">star_border</i></a>
									</div>		
								</c:otherwise>
							</c:choose> --%>
						</div>
						<div class="cook_board_function">
							<i class="material-icons">visibility</i>
							<span class="cook_board_function_font">&nbsp;${item.readCount}</span>
						</div>
						<div class="cook_board_function">
							<a href="#"><i class="material-icons">timeline</i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
	
      var cook_list = document.querySelector('.cook_list');

      cook_list.empty(); 
      $( ".carousel" ).on( "click", "a", function() {
        var yori = $(this).text();
        alert(yori)       
          $.ajax({
            url:"/bsym/web/listSearch/cook",
            type:"post",
            
            headers : { // Http header
                  "Content-Type" : "application/json",
            
                },
            dataType : 'text',
            data : JSON.stringify({ // 보낼 데이터
                  "broadcastName" : yori
                }),
                
                success : function(result) { // 결과 성공
                    
                  var re = JSON.parse(result);
                  alert(re);
                  addDiv();
                },
                error : function(request, status, error) { // 결과 에러
              alert("못불러옴");
                }
            });
       
            
        function addDiv(){
          
          var newDiv = document.createElement("Div");
          newDiv.className = "cook_board";
          
          <c:forEach var="item" items="${boardList}" begin="5" end="8"> 
            var inbox = "<div class='cook_board_image'><a href='/cook/board/${item.id}'><img src='${item.imglink}'></a></div>"
            inbox = inbox + "<div class='cook_board_function_box'><div class='cook_board_function'><a href='#'><i class='material-icons'>favorite_border</i></a><span class='cook_board_function_font'>&nbsp;${item.likeCount}</span>"
          inbox = inbox + "</div><div class='cook_board_function'><i class='material-icons'>visibility</i><span class='cook_board_function_font'>&nbsp;${item.readCount}</span></div><div class='cook_board_function'><a href='#'><i class='material-icons'>timeline</i></a></div></div>"
            newDiv.innerHTML = inbox;
            cook_list.append(newDiv);
          </c:forEach>
          
        }
       })
      
    </script>
    
  
    <script>
            var header = document.getElementById("sub_menu");
            var btns = header.getElementsByClassName("btns");
            for (var i = 0; i < btns.length; i++) {
              btns[i].addEventListener("click", function() {
              var current = document.getElementsByClassName("on");
              current[0].className = current[0].className.replace(" on", "");
              this.className += " on";
              });
            }
            
	</script>
</body>
</html>