<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방송요리 페이지</title>
<script type="text/JavaScript" src="/js/jquery.js"></script>
<script src="/js/jquery.bxslider.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css"/>
<link rel="stylesheet" href="/css/cook_home/cook_home.css"/>
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
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
							<a class="nav-link" href="/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li><a href="/bsym/web/myPage/${sessionScope.userLoginInfo.id}">마이페이지</a></li>
			</ul>
		</div>
		<div class="nav-wrapper">
			<a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙" class="brand-logo center">	<!-- 요리페이지 새로고침 -->
			<img src="/image/cook/main_logo.png"></a>
		</div>
		<div class="menu_bar_right">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="/bsym/web/bloglistPage/res?broadcastName=미식클럽">방송맛집</a></li>
			</ul>
		</div>
	</nav>
	<% String[] castName={"스페인하숙","수미네반찬","삼시세끼","알토란","만물상","집밥백선생","요리비결","윤식당","냉부해","커피프렌즈","모두의주방"}; %>
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
		<a class="carousel-item"><%=castName[i]%><img src="/image/cook/subimg<%=i+1%>.png "></a>
	<% }%>
	<%	for(i=0; i<check; i++){ %>
		<a class="carousel-item"><%=castName[i]%><img src="/image/cook/subimg<%=i+1%>.png "></a>
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
			<c:forEach var="item" items="${blogs}"> <%-- begin="0" end="7">--%>
				<div class="cook_board">
					<div class="cook_board_image">
						<a href="/bsym/web/blogDetail/${item.id}"><img src="/image/sumnail/${item.id}.jpg"></a>
					</div>
					<div class="cook_board_title_box">
						${item.title}</br>
					</div>
					<div class="cook_board_function_box">
						<div class="cook_board_function">
						<c:set var="like_check" value="false" />
						<div id="b${item.id}">
						<c:choose>
							<c:when test="${like_check eq true}">
								<button class="material-icons" onclick="likes(false,${item.id})">favorite</button>
						    </c:when>
						    <c:otherwise>
						    	<button class="material-icons" onclick="likes(true,${item.id})">favorite_border</button>
						    
						    </c:otherwise>
						</c:choose>
						</div>
						<span class="cook_board_function_font">&nbsp;${item.likeCount}</span>
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
    $(document).on('click', '.remove', function () {
      $(this).parent('div').remove();
  });
      $('.carousel').click(function() {
        $('.cook_board:last').before('<div class="cook_board"><div class="cook_board_image"><a href="/cook/board/${item.id}"><img src="${item.imglink}"></a></div><div class="cook_board_function_box"><div class="cook_board_function"><a href="#"><i class="material-icons">favorite_border</i></a><span class="cook_board_function_font">&nbsp;${item.likeCount}</span></div><div class="cook_board_function"><i class="material-icons">visibility</i><span class="cook_board_function_font">&nbsp;${item.readCount}</span></div><div class="cook_board_function"><a href="#"><i class="material-icons">timeline</i></a></div></div></div>');
      });
      


      $( ".carousel" ).on( "click", "a", function() {
        var yori = $(this).text();
          var cookboard = $('.cook_board');   
          cookboard.detach();
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
                  addDiv(re);
                },
                error : function(request, status, error) { // 결과 에러
              alert("못불러옴");
                }
            });
       
      })
        function addDiv(re){
		
		for (var i in re) {
		                    
		          var newDiv = document.createElement("Div");
		          newDiv.className = "cook_board";
		          console.log(re[i].id);
		          var inbox = "<div class='cook_board_image'><a href='/bsym/web/blogDetail/"+re[i].id+"'><img src='/image/sumnail/"+re[i].id+".jpg'></a></div>"
		          inbox = inbox + "<div class='cook_board_title_box'>"+re[i].title+"</br></div>"
		          inbox = inbox + "<div class='cook_board_function_box'><div class='cook_board_function'><div id='"+re[i].id+"'>"
		          inbox = inbox + "<button class='material-icons' onclick='likes(true,"+re[i].id+")'>favorite_border</button>"
		          inbox = inbox + "</div><span class='cook_board_function_font'>&nbsp;"+re[i].likeCount+"</span>"
		          inbox = inbox + "</div><div class='cook_board_function'><i class='material-icons'>visibility</i><span class='cook_board_function_font'>&nbsp;"+re[i].readCount+"</span></div><div class='cook_board_function'><a href='#'><i class='material-icons'>timeline</i></a></div></div>"
		          newDiv.innerHTML = inbox;
		          cook_list.append(newDiv);
		          
		          
		       } 
    }
    </script>
    <script>
    	function likes(check, id){
    		//true -> like
    		//false ->unlike
    		if(check){
    			var url = '/bsym/like/'+id
    			fetch(url,{
    				method:"POST"
    			}).then(function(res){
    				console.log(res);
    				return res.text();
    			}).then(function(result){
    				if(result === 'ok'){
    					let like_el = document.querySelector('#b'+id);
    					like_el.innerHTML = "<button class='material-icons' onclick='likes(false,"+id+")'>favorite</button>";
    				}else{
    					alert("로그인을 해주세요.")
    				}
    			}).catch();
    		}else{
    			var url = '/bsym/unlike/'+id
    			fetch(url,{
    				method:"POST"
    			}).then(function(res){
    				console.log(res);
    				return res.text();
    			}).then(function(result){
    				if(result === 'ok'){
    					let like_el = document.querySelector('#b'+id);
    					like_el.innerHTML = "<button class='material-icons' onclick='likes(true,"+id+")'>favorite_border</button>";
    				}else{
    					alert("로그인을 해주세요.")
    				}
    			}).catch();
    		}
    	}
    </script>
 <!--   <script type="text/javascript">
    var cook_list = document.querySelector('.cook_board_function');
    $(document).on('click', '.remove', function () {
      $(this).parent('div').remove();
  });
      $('.LikeCheck').click(function() {
        $('.cook_board_function:last').before('<div class="cook_board_function"><div class="LikeCheck"><a href="#"><i class="material-icons">favorite_border</i></a></div><span class="cook_board_function_font">&nbsp;${item.likeCount}</span></div><div class="cook_board_function"><i class="material-icons">visibility</i><span class="cook_board_function_font">&nbsp;${item.readCount}</span></div><div class="cook_board_function"><a href="#"><i class="material-icons">timeline</i></a></div></div></div>');
      });
      
   


      $( ".LikeCheck" ).on( "click", "a", function() {
    	let userid = ${sessionScope.userLoginInfo.id};
    	let blog= ${item};
        let check = $(this).val();
          var likeCheck = $('.cook_board_function');   
          likeCheck.detach();
          $.ajax({
            url:"/bsym/like",
            type:"post",
            
            headers : { // Http header
                  "Content-Type" : "application/json",
            
                },
            dataType : 'text',
            data : JSON.stringify({ // 보낼 데이터
                  "LikeCheck" : check,
                  "rockerId" : userid,
                  "bsymBoard" : blog
            
                 
                }),
                
                success : function(result) { // 결과 성공
                    
                  var re = JSON.parse(result);
                  //addDiv(re);
                },
                error : function(request, status, error) { // 결과 에러
              alert("못불러옴");
                }
            });
       
      })
        function addDiv(re){
		
		for (var i in re) {
		                    
		          var newDiv = document.createElement("Div");
		          newDiv.className = "cook_board";
		          console.log(re[i].id);
		          var inbox = "<div class='cook_board_image'><a href='/bsym/web/blogDetail/"+re[i].id+"'><img src='/image/sumnail/"+re[i].id+".jpg'></a></div>"
		          inbox = inbox + "<div class='cook_board_title_box'>"+re[i].title+"</br></div>"
		          inbox = inbox + "<div class='cook_board_function_box'><div class='cook_board_function'><a href='#'><i class='material-icons'>favorite_border</i></a><span class='cook_board_function_font'>&nbsp;"+re[i].likeCount+"</span>"
		          inbox = inbox + "</div><div class='cook_board_function'><i class='material-icons'>visibility</i><span class='cook_board_function_font'>&nbsp;"+re[i].readCount+"</span></div><div class='cook_board_function'><a href='#'><i class='material-icons'>timeline</i></a></div></div>"
		          newDiv.innerHTML = inbox;
		          cook_list.append(newDiv);
		          
		          
		       }
			return false;   
    }
    </script>-->
</body>
</html>