<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
  <title>관리자페이지</title>
  <style>
    * {
      margin: 0;
      padding: 0;
    }

    header {
      padding: 30px;
      width: 100%;
      height: 100px;
      display: grid;
      grid-template-columns: 1fr 1fr;
    }

    .header_logout {
      text-align: center;
    }

    .crawling_btn {
      text-align: left;
    }

    .container {
      display: grid;
      justify-content: center;
      text-align: center;
    }

    .cook_list {
      margin: 0;
      padding: 0;
      display: grid;
      grid-template-columns: repeat(4, 1fr);

    }

    .cook_board {
      margin-left: 25px;
      margin-right: 25px;
      margin-bottom: 30px;
      padding: 0;
      width: 250px;
      height: 300px;
    }

    .cook_board_image {
      margin: 0;
      padding: 0;
      width: 250px;
      height: 250px;
    }

    .cook_board_image img {
      margin: 0;
      padding: 0;
      width: 250px;
      height: 250px;
    }

    .cook_board_function_box {
      margin: 0;
      padding: 0;
      width: 250px;
    }

    #delete_btn {
      margin: 0;
      padding: 0;
      width: 100px;
      text-align: center;
    }
  </style>
</head>

<body>
	<!-- oldmanJoin -->
	<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
  
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
      </div>
      <form class="w3-container" method="post" enctype="multipart/form-data" id="fileUploadForm">
        <div class="w3-section">
			<div style="width:49%; display:Inline-block; margin-right:1%;">
          	<label><b>제목</b></label>
          	<input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="제목" name="title" required>
          	</div></br>
          <label><b>내용</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="내용" name="content" required>
          </br>
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit" id="btnSubmit">보내기</button>
        </div>
      </form>
    </div>
  </div>
  <header>
    <div class="header_logout">
      관리자님 환영합니다.<a class="btn btn-light" href="/logout">로그아웃</a>
    </div>
    <div class="crawling_btn">
      <button type="button" class="btn btn-secondary" onclick="crawl('All')">All 크롤링</button>
      <button type="button" class="btn btn-success" onclick="crawl('New')">NEW 크롤링</button>
      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">프로그램 버튼</button>
      <div class="w3-right w3-hide-small">
      <a href="#!" class="w3-bar-item w3-button" onclick="document.getElementById('id01').style.display='block'">푸시메시지 보내기</a>
      </div>
      <%
		String[] castName = {"스페인하숙", "수미네반찬", "삼시세끼", "알토란", "만물상", "집밥백선생", "요리비결", "윤식당", "냉부해", "커피프렌즈",
				"모두의주방","미식클럽","수요미식회","3대천왕","밥블레스유","식신로드","맛있는녀셕들","외식하는날","골목식당","전참시","생방송투데이"};
	%>
      <div class="dropdown-menu">
      	<div class="dropdown">
		<c:set var="castName" value="${castName}" />
		<%
			String var = (String) pageContext.getAttribute("castName");
		%>
		<%
			int i = 0, check = 0;
			for (i = check; i < castName.length; i++) {
		%>
			<a class="dropdown-item" href="/admin/page?broadcastName=<%=castName[i]%>"><%=castName[i]%></a>
		<%
			}
		%>
		<%
			for (i = 0; i < check; i++) {
		%>
			<a class="dropdown-item" href="/admin/page?broadcastName=<%=castName[i]%>"><%=castName[i]%></a>
		<%
			}
		%>
      </div>
    </div>
  </header>
  <div class="container">
    <h1>${blogs[0].broadcastName}</h1>
    <div class="cook_list">
    <c:forEach var="item" items="${blogs}">
      <div class="cook_board">
        <div class="cook_board_image">
        	<a href="/admin/detail/${item.id}"><img src="/image/sumnail/${item.id}.jpg"></a>
        </div>
        <div class="cook_board_title_box">${item.title}<br /></div>
        <div class="cook_board_function_box">
            <a id="delete_btn" type="button" class="btn btn-info" onclick="del(${item.id},'${item.broadcastName }' )" href="#">삭제</a>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
</body>
<script type="text/javascript">
	$("#btnSubmit").click(function (event) {
	 
    //preventDefault 는 기본으로 정의된 이벤트를 작동하지 못하게 하는 메서드이다. submit을 막음
    event.preventDefault();
    // Get form
    var form = $('#fileUploadForm')[0];
    // Create an FormData object 
    var data = new FormData(form);
   // disabled the submit button
    $("#btnSubmit").prop("disabled", true);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/send",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            alert("complete");
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            alert("fail");
        }
    });

});

</script>
<script type="text/javascript">
	function del(id, name){
		let url ="/admin/del/"+id;
		fetch(url,{
			method:"post",
		}).then(function(res) {
			return res.text();
		}).then(function(rs) {
			if(rs === "ok"){
				alert('삭제되었습니다.');
				location.href='/admin/page?broadcastName='+name;
			}
		})
	}
	function crawl(div){
		let url = '/admin/blog/Save/'+div
		LoadingWithMask('/image/loading/Spinner.gif');
		fetch(url,{
			method:"post",
		}).then(function(res){
			return res.text();
		}).then(function(result){
			if(result === 'ok'){
				alert("네이버 블로그 크롤링이 완료 되었습니다.")
				closeLoadingWithMask();
				location.href='/admin/page';
			}
		})
		
	}

</script>
<script>
	function LoadingWithMask(lodingImg) {
	    //화면의 높이와 너비를 구합니다.
	    var maskHeight = $(document).height();
	    var maskWidth  = window.document.body.clientWidth;
	     
	    //화면에 출력할 마스크를 설정해줍니다.
	    var mask       = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
	    var loadingImg = '';
	      
	    loadingImg += "<div id='loadingImg'>";
	    loadingImg += " <img src='"+lodingImg+"' style='position: relative; display: block; margin: 0px auto;'/>";
	    loadingImg += "</div>"; 
	  
	    //화면에 레이어 추가
	    $('body')
	        .append(mask)
	        .append(loadingImg)
	        
	    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
	    $('#mask').css({
	            'width' : maskWidth
	            , 'height': maskHeight
	            , 'opacity' : '0.3'
	    });
	  
	    //마스크 표시
	    $('#mask').show();  
	  
	    //로딩중 이미지 표시
	    $('#loadingImg').show();
	}
	function closeLoadingWithMask() {
	    $('#mask, #loadingImg').hide();
	    $('#mask, #loadingImg').remove(); 
	}
</script>

</html>