<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<title>독거 어르신과 함께</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      var size = '<c:out value="${fn:length(oldman.tahData)}"/>';
      var tableData = Array(Array('시간', '습도', '온도'));
      <c:forEach var="item" items="${oldman.tahData}">
    	  tableData.push(Array("${item.createDate}".split('T')[1], ${item.humi}, ${item.temp}))
      </c:forEach> 
      function drawChart() {
          var data = google.visualization.arrayToDataTable(tableData);
        var options = {
          title: '${oldman.user.name}어르신 온습도 그래프',
          vAxis: {title: '값'},
          hAxis: {title: '시간'},
          curveType: 'function',
          legend: { position: 'top', textStyle: {fontSize: 10} },
          height: 800,
          width: 2370
        };
        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options);
      }
    </script>
<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="/home" class="w3-bar-item w3-button"><b>독거 어르신</b> 과 함께</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="#projects" class="w3-bar-item w3-button">함께하기</a>
      <a href="#!" class="w3-bar-item w3-button" onclick="document.getElementById('id01').style.display='block'">등록</a>
      <a href="#contact" class="w3-bar-item w3-button">로그인</a>
    </div>
  </div>
</div>

<!-- Header -->

<header class="w3-display-container w3-content w3-wide" style="max-width:1000px;" id="home">
  <div class="w3-container w3-padding-32">
  	</br>
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">카메라영상</h3>
  </div>
  <img class="w3-image" src="${oldman.user.img_ipaddress}" alt="카메라를 점검해주세요." width="1000" height="800">
</header>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">
  <!-- Project Section -->
  <div class="w3-container w3-padding-32" id="projects">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">상세정보</h3>
  </div>
  <table class="w3-table w3-striped w3-bordered w3-border">
  <thead class="w3-teal"><th style="width:30%">항목</th><th>정보</th></thead>
  <tr><td>성함</td><td>${oldman.user.name}</td></tr>
  <tr><td>나이</td><td>${oldman.user.age}</td></tr>
  <tr><td>전화번호</td><td>${oldman.user.tel}</td></tr>
  <tr><td>주소</td><td>${oldman.user.address_si}</td></tr>
  <tr><td>모델명</td><td>${oldman.user.ipaddress}</td></tr>
  <tr><td>날짜</td><td>${oldman.createDate}</td></tr>
  <tr><td style="width:30%">온도 & 습도</td>
  	<c:choose>
		<c:when test="${!empty oldman.tahData}">
			<c:set var="size" value="${fn:length(oldman.tahData)-1}" />
		  	<td>${oldman.tahData.get(size).temp}℃  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${oldman.tahData.get(size).humi}% <a href="#!" class="w3-right" onclick="document.getElementById('tahgrapt').style.display='block'">온습도그래프</a></td>
		</c:when>
		<c:otherwise>
			<td>온습도센서 연결이 필요합니다.</td>
		</c:otherwise>
	</c:choose>
  </tr>
  <tr><td>가스누출유무</td>
  	<c:choose>
  		<c:when test="${oldman.gasDetect.gasCheck eq true}">
  			<td>위험 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 누출시간 - ${oldman.gasDetect.checkDate} <a href="#!" class="w3-right" onclick="document.getElementById('gasdetect').style.display='block'">가스누출확인</a></td>
  		</c:when>
  		<c:otherwise>
  			<td>양호 <a href="#!" class="w3-right" onclick="document.getElementById('gasdetect').style.display='block'">가스누출확인</a></td>
  		</c:otherwise>
  	</c:choose>
  </tr>
  <tr><td>외출여부</td>
	<c:choose>
		<c:when test="${!empty oldman.humanDetect}">
			<c:set var="size" value="${fn:length(oldman.humanDetect)-1}" />
			<c:choose>
		  		<c:when test="${oldman.humanDetect.get(size).outCheck eq 'out'}">
		  			<td>외출중 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 외출시간 - ${oldman.humanDetect.get(size).outDate} <a href="#!" class="w3-right" onclick="document.getElementById('outlist').style.display='block'">외출리스트</a></td>
		  		</c:when>
		  		<c:otherwise>
		  			<td>복귀함 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 복귀시간 - ${oldman.humanDetect.get(size).comebackDate} <a href="#!" class="w3-right" onclick="document.getElementById('outlist').style.display='block'">외출리스트</a></td>
		  		</c:otherwise>
		  	</c:choose>
		</c:when>
		<c:otherwise>
			<td>외출센서 연결이 필요합니다.</td>
		</c:otherwise>
	</c:choose>
  </tr>
  </table>
  <div id="outlist" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
	<h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">${oldman.user.name}어르신 외출리스트</h3>
      <div class="w3-center"><br>
        <span onclick="document.getElementById('outlist').style.display='none'" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
      </div>
      <table class="w3-table-all">
      	<tr>
	      <th>외출여부</th>
	      <th>외출시간</th>
	      <th>복귀시간</th>
	    </tr>
	    <c:forEach var="item" items="${oldman.humanDetect}">
	    	<tr>
		      <td>${item.outCheck}</td>
		      <c:choose>
		      	<c:when test="${item.outCheck eq 'come'}">
			      	<td>${item.outDate}</td>
			      	<td>${item.comebackDate}</td>
		      	</c:when>
		      	<c:otherwise>
			      <td>${item.outDate}</td>
			      <td>외출 중이십니다.</td>
		      	</c:otherwise>
		      </c:choose>
		    </tr>
      </c:forEach>
	  </table>
    </div>
  </div>
  <div id="tahgrapt" class="w3-modal" style="width:100%">
  	<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="width:100%">
  		<div class="w3-center"><br>
  			<span onclick="document.getElementById('tahgrapt').style.display='none'" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
  		</div><br>
  		<div id="curve_chart"></div>
  	</div>
  </div>
  <div id="gasdetect" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
	<h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">${oldman.user.name}어르신 가스누출확인</h3>
      <div class="w3-center"><br>
        <span onclick="document.getElementById('gasdetect').style.display='none'" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
      </div>
      <table class="w3-table-all">
      	<tr>
	      <th>누출여부</th>
	      <th>누출시간</th>
	    </tr>
	    <tr>
		  <c:choose>
		     	<c:when test="${oldman.gasDetect.gasCheck eq true}">
		     		<td>위험</td>
		      	<td>${oldman.gasDetect.checkDate}</td>
		     	</c:when>
		     	<c:otherwise>
		      <td>양호</td>
		      <td>-</td>
		     	</c:otherwise>
		  </c:choose>
		 </tr>
	  </table>
    </div>
  </div>
<!-- End page content -->
</div>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>독거 어르신과 <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-text-green">함께</a></p>
</footer>

</body>
</html>
