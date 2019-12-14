<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방송맛집 페이지</title>
<script type="text/JavaScript" src="/js/jquery.js"></script>
<script src="/js/jquery.bxslider.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/css/style.css"/>
<link rel="stylesheet" href="/css/cook_home/cook_home.css"/>
<link rel="shortcut icon" href="/image/main/favicon-16x16.ico">
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

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
            <li><a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙" style="font-size:17px">방송요리</a></li>
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
			<a href="/bsym/web/bloglistPage/res?broadcastName=미식클럽" class="brand-logo center">	<!-- 맛집페이지 새로고침 -->
			<img src="/image/cook/main_logo.png"></a>
		</div>
		<div class="menu_bar_right">
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="/bsym/web/bloglistPage/cook?broadcastName=스페인하숙">방송요리</a></li>
			</ul>
		</div>
	</nav>
	<% String[] castName={"미식클럽","수요미식회","3대천왕","밥블레스유","식신로드","맛있는녀석들","외식하는날","골목식당","전참시","생방송투데이"}; %>
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
		<a class="carousel-item"><%=castName[i]%><img src="/image/res/subimg<%=i+1%>.png " onclick="broacSelect('<%=castName[i]%>')"></a>
	<% }%>
	<%	for(i=0; i<check; i++){ %>
		<a class="carousel-item"><%=castName[i]%><img src="/image/res/subimg<%=i+1%>.png " onclick="broacSelect('<%=castName[i]%>')"></a>
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
		      <li class="btns on"><a href="#!" onclick="sortpage('postdate','${blogs.content[0].broadcastName}');">최신순</a></li>
		      <li class="btns"><a href="#!" onclick="sortpage('readCount','${blogs.content[0].broadcastName}');" class="new">조회수순</a></li>
		      <li class="btns"><a href="#!" onclick="sortpage('likeCount','${blogs.content[0].broadcastName}');">좋아요순</a></li>
		</ul>
		<div class="cook_list">
			<c:forEach var="item" items="${blogs.content}" varStatus="status"> <%-- begin="0" end="7">--%>
				<div class="cook_board">
					<div class="cook_board_image">
						<a href="/bsym/web/blogDetail/${item.id}"><img src="/image/sumnail/${item.id}.jpg"></a>
					</div>
					<div class="cook_board_title_box">
						${item.title}<br />
					</div>
					<div class="cook_board_function_box">
						<div id="b${item.id}" class="cook_board_function">
							<c:choose>
								<c:when test="${item.likeCheck eq true}">
									<div class="cook_board_function_like" onclick="likes(false,${item.id})"><i class="material-icons" style="cursor: pointer">favorite</i></div>
							    </c:when>
							    <c:otherwise>
							    	<div class="cook_board_function_like" onclick="likes(true,${item.id})"><i class="material-icons" style="cursor:pointer">favorite_border</i></div>
							    
							    </c:otherwise>
							</c:choose>
							<span class="cook_board_function_font">&nbsp;${item.likeCount}</span>
						</div>
						<div class="cook_board_function">
							<i class="material-icons">visibility</i>
							<span class="cook_board_function_font">&nbsp;${item.readCount}</span>
						</div>
						<div class="cook_board_function" onclick="Shares(${item.id},'${item.link}')"><i class="material-icons" style="cursor:pointer">timeline</i></div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="cook_board_Paging">
			<ul id="pageList" class="pagination">
			<c:forEach var="i" begin="0" end="${blogs.totalPages-1}">
				<c:choose>
					<c:when test="${i == 0}">
						<li class="pagecheck active"><a href="#!" onclick="paging(${i},'${blogs.content[0].broadcastName}')">${i+1}</a></li>
					</c:when>
					<c:otherwise>
						<li class="pagecheck waves-effect"><a href="#!" onclick="paging(${i},'${blogs.content[0].broadcastName}')">${i+1}</a></li>
					</c:otherwise>
				</c:choose> 
			</c:forEach>
			  </ul>
		 </div>
	</div>
	<script type="text/javascript">
	function broacSelect(broadcastName){
          $.ajax({
            url:"/bsym/web/listSearch/res",
            type:"post",
            
            headers : { // Http header
                  "Content-Type" : "application/json",
            
                },
            dataType : 'text',
            data : JSON.stringify({ // 보낼 데이터
                  "broadcastName" : broadcastName
                 
                }),
                
                success : function(result) { // 결과 성공
                  var re = JSON.parse(result);
                var page = re.totalPages

                  	addDiv(re.content);
                	addPage(re.content[0].broadcastName,page);
                	pageEventSet()
                },
                error : function(request, status, error) { // 결과 에러
              alert("못불러옴");
                }
            });
      }
        function addDiv(relist){
    	  $(".cook_list").empty();
			 for (var i in relist) {    
		          var newDiv = document.createElement("div");
		          newDiv.className="cook_board";
		          console.log(relist[i].id);
		          var inbox = "<div class='cook_board_image'><a href='/bsym/web/blogDetail/"+relist[i].id+"'><img src='/image/sumnail/"+relist[i].id+".jpg'></a></div>"
		          inbox = inbox + "<div class='cook_board_title_box'>"+relist[i].title+"</br></div>"
		          inbox = inbox + "<div class='cook_board_function_box'><div id='b"+relist[i].id+"' class='cook_board_function'>"
		          //여기서 likeCheck 확인해서 버튼다르게 설정해야함
		          if(relist[i].likeCheck=="true"){
		        	  inbox = inbox + "<div class='cook_board_function_like' onclick='likes(false,"+relist[i].id+")'><i class='material-icons' style='cursor:pointer'>favorite</i></div>"
		          }else if(relist[i].likeCheck=="false"){
		        	  inbox = inbox + "<div class='cook_board_function_like' onclick='likes(true,"+relist[i].id+")'><i class='material-icons' style='cursor:pointer'>favorite_border</i></div>"
		          }
		          inbox = inbox + "<span class='cook_board_function_font'>&nbsp;"+relist[i].likeCount+"</span>"
		          inbox = inbox + "</div><div class='cook_board_function'><i class='material-icons'>visibility</i><span class='cook_board_function_font'>&nbsp;"+relist[i].readCount+"</span></div><div class='cook_board_function' onclick='Shares("+relist[i].id+",&#39;"+relist[i].link+"&#39;)'><i class='material-icons' style='cursor:pointer'>timeline</i></div></div>"
		          newDiv.innerHTML = inbox;
		          $(".cook_list").append(newDiv);
		       } 
    }
      function addPage(broadcastName,page){
    	  let page_el = document.querySelector('.cook_board_Paging');
  		var pagebox = "<ul id='pageList' class='pagination'>"
  		for(var j=0;j<page;j++){
  			console.log(j);
  			if(j==0){
  				pagebox = pagebox + "<li class='pagecheck active'><a href='#!' onclick='paging("+j+",&#39;"+broadcastName+"&#39;)'>"+(j+1)+"</a></li>"
  			}else{
  				pagebox = pagebox+"<li class='pagecheck waves-effect'><a href='#!' onclick='paging("+j+",&#39;"+broadcastName+"&#39;)'>"+(j+1)+"</a></li>"
  			}
  		}
  		pagebox = pagebox+"</ul>"
  		//console.log(pagebox);
  		page_el.innerHTML = pagebox;
      }
    </script>
    <script>
    function paging(page,broadcastName){
    	 // var header = document.getElementsByClassName("pagination");
            $.ajax({
                url:"/bsym/web/listSearch/cook?page="+page,
                type:"post",
                
                headers : { // Http header
                      "Content-Type" : "application/json",
                
                    },
                dataType : 'text',
                data : JSON.stringify({ // 보낼 데이터
                      "broadcastName" : broadcastName
                      //alert('${blogInfo.broadcastName}');
                    }),
                    
                    success : function(result) { // 결과 성공
                      var re = JSON.parse(result);
                    var page = re.totalPages
                      	addDiv(re.content);
                    	//btnActive(page);
                    },
                    error : function(request, status, error) { // 결과 에러
                  alert("못불러옴");
                    }
                });
       	
    }
    </script>
    <script>
	function sortpage(sort,broadcastName){
		$.ajax({
            url:"/bsym/web/listSearch/cook?sort="+sort+",desc",
            type:"post",
            
            headers : { // Http header
                  "Content-Type" : "application/json",
            
                },
            dataType : 'text',
            data : JSON.stringify({ // 보낼 데이터
                  "broadcastName" : broadcastName
                  //alert('${blogInfo.broadcastName}');
                }),
                
                success : function(result) { // 결과 성공
                  var re = JSON.parse(result);
                var page = re.totalPages
                  	addDiv(re.content);
                	//btnActive(page);
                },
                error : function(request, status, error) { // 결과 에러
              alert("못불러옴");
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
      }
    function Shares(boardId,link){
        var url = '/bsym/share/'+boardId
        fetch(url,{
        	method:"POST"
        }).then(function(res){
        	console.log(res);
        	return res.text();
        }).then(function(result){
        	 copyToClipboard(link);
    	     alert("복사가 완료되었습니다.\n"+link);
        	if(result === 'ok'){
        		alert("공유함에 담겼습니다.")
        	}else if(result === 'overlap'){
				alert("공유함에 담겨있습니다.")
			}else if(result === 'error'){
				alert("공유함에 담으실려면 로그인을 해주세요.")
			}
        })
      }
    </script>
    <script> //정렬 버튼 이벤트리스너 정의
    var header = document.getElementById("sub_menu");
    var btns = header.getElementsByClassName("btns");
    for (var i = 0; i < btns.length; i++) {
      btns[i].addEventListener("click", function() {
        var current = document.getElementsByClassName("on");
        current[0].className = current[0].className.replace("on", "");
        this.className += " on";
      });
    }
  	</script>
	<script>//페이징 버튼 이벤트리스너 정의
		var header = document.getElementById("pageList");
		var btns = header.getElementsByClassName("pagecheck");
		for (var i = 0; i < btns.length; i++) {
		  btns[i].addEventListener("click", function() {
		  var current = header.getElementsByClassName("active");
		  current[0].className = current[0].className.replace(" active"," waves-effect");
		  this.className = this.className.replace(" waves-effect"," active");
		  });
		}
	</script>
  	<script>//페이징 버튼 이벤트리스너 정의
  	function pageEventSet(){
		var header = document.getElementById("pageList");
		var btns = header.getElementsByClassName("pagecheck");
		for (var i = 0; i < btns.length; i++) {
		  btns[i].addEventListener("click", function() {
		  var current = header.getElementsByClassName("active");
		  current[0].className = current[0].className.replace(" active"," waves-effect");
		  this.className = this.className.replace(" waves-effect"," active");
		  });
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
    				var re = JSON.parse(result);
    				if(re.loginCheck === 'ok'){
    					let like_el = document.querySelector('#b'+id);
    					like_el.innerHTML = "<div class='cook_board_function_like' onclick='likes(false,"+id+")'><i class='material-icons' style='cursor:pointer'>favorite</i></div><span class='cook_board_function_font'>&nbsp;"+re.bsymBoard.likeCount+"</span>";
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
    				var re = JSON.parse(result);
    				if(re.loginCheck === 'ok'){
    					let like_el = document.querySelector('#b'+id);
    					like_el.innerHTML = "<div class='cook_board_function_like' onclick='likes(true,"+id+")'><i class='material-icons' style='cursor:pointer'>favorite_border</i></div><span class='cook_board_function_font'>&nbsp;"+re.bsymBoard.likeCount+"</span>";
    				}else{
    					alert("로그인을 해주세요.")
    				}
    			}).catch();
    		}
    	}
    </script>
</body>
</html>