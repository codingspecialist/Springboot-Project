<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>독거 어르신과 함께</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	var sel_file;

	$(document).ready(function() {
		$('#input_img').on("change", handleImgFileSelect);
	});

	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$('#img').attr('src', e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
</script>

<style>
.img_wrap {
	width: 500px;
	margin-top: 20px;
	display : grid;
	justify-content : center;
}

.img_wrap img {
	max-width: 100%;
}
</style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="#home" class="w3-bar-item w3-button"><b>독거 어르신</b> 과 함께</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="#projects" class="w3-bar-item w3-button">함께하기</a>
      <a href="#!" class="w3-bar-item w3-button" onclick="document.getElementById('id01').style.display='block'">등록</a>
    </div>
  </div>
</div>

<!-- Header -->
<header class="w3-display-container w3-content w3-wide" style="max-width:800px;" id="home">
  <img class="w3-image" src="/images/home/header.jpg" alt="Architecture" width="800" height="400">
  <div class="w3-display-middle w3-margin-top w3-center">
    <h1 class="w3-xxlarge w3-text-white"><span class="w3-padding w3-black w3-opacity-min"><b>독거 어르신</b></span></h1>
  </div>
</header>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">
	<!-- oldmanJoin -->
	<div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
  
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
      </div>
      <form class="w3-container" action="/joinProc" method="post"
				enctype="multipart/form-data">
        <div class="w3-section">
			<div>
				<input id="input_img" type="file" name="file"
					placeholder="사진파일">
			</div>
			<div style="display:grid; justify-content: center">
				<div class="img_wrap">
						<img id="img" />
				</div>
			</div>
			<div style="width:49%; display:Inline-block; margin-right:1%;">
          	<label><b>어르신 성함</b></label>
          	<input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="성함" name="name" required>
          	</div>
          	<div style="width:49%; display:Inline-block;">
          	<label><b>연세</b></label>
          	<input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="연세" name="age" required>
          	</div></br>
          <label><b>성별</b></label>
          	<div style="height: 34px; text-align: left;" >
				<div class="radio_button">
					<label style="margin-right: 20px">
					<input name="gender" type="radio" checked value="남" /><span>남</span></label>
					<label> <input class="with-gap" name="gender" type="radio" value="여" /> <span>여</span></label>
				</div>
			</div>
          <label><b>전화번호 ('-'없이 적어주세요.)</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="전화번호" name="tel" required>
          <label><b>주소</b></label></br>
          <input class="w3-input w3-border w3-margin-bottom" style="width:32%; display:Inline-block;" type="text" placeholder="시" name="address_si" required>
          <input class="w3-input w3-border w3-margin-bottom" style="width:33%; display:Inline-block;" type="text" placeholder="구" name="address_gu" required>
          <input class="w3-input w3-border w3-margin-bottom" style="width:33%; display:Inline-block;" type="text" placeholder="동" name="address_dong" required>
          </br>
          <label><b>상세 주소</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="상세주소" name="address_detail" required>
          <label><b>모델 시리얼넘버</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="시리얼 넘버" name="ipaddress" required>
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">등록</button>
        </div>
      </form>
    </div>
  </div>
  <!-- Project Section -->
  <div class="w3-container w3-padding-32" id="projects">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">가스누출위험</h3>
  </div>
  <div class="w3-row-padding">
  <c:forEach var="item" items="${gaswarnings}" varStatus="status">

    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-display-container">
        <div class="w3-display-topleft w3-black w3-padding">${item.user.name} 어르신</div>
        <a href="/detail/${item.user.id}"><img src="/images/photoId/${item.user.id}.jpg" alt="House" style="width:100%"></a>
      </div>
    </div>
  </c:forEach>
  </div>
    <div class="w3-col l3 m6 w3-margin-bottom" style="width:100%">
      <p><button class="w3-button w3-light-grey" style="width:100%">더보기</button></p>
    </div>
      <!-- Project Section -->
  <div class="w3-container w3-padding-32" id="projects">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">온도관리요망</h3>
  </div>
  <div class="w3-row-padding">
  <c:forEach var="item" items="${tempwarnings}" varStatus="status">

    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-display-container">
        <div class="w3-display-topleft w3-black w3-padding">${item.user.name} 어르신</div>
        <a href="/detail/${item.user.id}"><img src="/images/photoId/${item.user.id}.jpg" alt="House" style="width:100%"></a>
      </div>
    </div>
  </c:forEach>
  </div>
    <div class="w3-col l3 m6 w3-margin-bottom" style="width:100%">
      <p><button class="w3-button w3-light-grey" style="width:100%">더보기</button></p>
    </div>
  <!-- Project Section -->
  <div class="w3-container w3-padding-32" id="projects">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">함께하기</h3>
  </div>
  <div class="w3-row-padding">
  <c:forEach var="item" items="${oldmans}" varStatus="status">

    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-display-container">
        <div class="w3-display-topleft w3-black w3-padding">${item.user.name} 어르신</div>
        <a href="/detail/${item.user.id}"><img src="/images/photoId/${item.user.id}.jpg" alt="House" style="width:100%"></a>
      </div>
    </div>
  </c:forEach>
  </div>
    <div class="w3-col l3 m6 w3-margin-bottom" style="width:100%">
      <p><button class="w3-button w3-light-grey" style="width:100%">더보기</button></p>
    </div>
  <!-- About Section -->
  <div class="w3-container w3-padding-32" id="about">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">인사말</h3>
    <h4><strong>홀로사는 어르신의 건강하고 행복한 노후</strong>를 위해 노력 하겠습니다.</br></br></br>
		안녕하십니까?</h4> </br>
	<p>	
		21세기에 사회학적으로 나타난 가장 두드러진 변화 중 하나는 노인인구의 증가로 인한 사회구조의 변화라고 할 수 있습니다.</br>
		노인 인구의 지속적인 증가, 핵가족화 등과 같이 사회구조가 복잡해지면서 국가나 가족이 부양하지 못하는 홀몸 노인이 늘어나면서, 최근 들어 홀몸 노인에 대한 관심이 사회적으로 중요한 이슈가 되고 있습니다.
		</br></br>
		부산시 또한 65세 이상인 고령가구가 2012년 26.3만가구로 전체가구 중 20.6%를 달성하였고, 고령가구 비율이 점차 증가하면서 2022년 처음으로 30%대 진입(31.1%)하고 2029년에 40%대에 진입(40.4%)할 것으로 보여집니다. 독거노인가구도 전국평균보다 높고 해마다 그 수치가 증가하여 2035년에는 50.8%에 도달할 것으로 예상됩니다.
		</br></br>
		독거노인은 가족 및 이웃들과의 사회적 교류가 단절되기 싶고, 현대사회의 병리현상의 상징인
		고독사의 위험에 노출되기 싶습니다. 독거노인이 사회구성원으로서 존중받을 수 있는 사회를 
		만들기 위해 정부, 사회복지기관, 기업이 함께 협력하는 사회적 안전망을 구축하고, 부산시민들이 
		이 의미 있는 일에 마음을 잇고 사랑을 이어갈 수 있도록 노력하겠습니다.
    </p>
  </div>

<!-- End page content -->
</div>


<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>독거 어르신과 <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-text-green">함께</a></p>
</footer>

</body>
</html>
