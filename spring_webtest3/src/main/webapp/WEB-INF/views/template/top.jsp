<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<c:choose>
	<c:when test="${not empty sessionScope.id && sessionScope.grade=='A' }">
		<c:set var="str">관리자 페이지입니다</c:set>
	</c:when>
	<c:when test="${not empty sessionScope.id && sessionScope.grade!='A' }">
		<c:set var="str">안녕하세요 ${sessionScope.id }님</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="str">기본 페이지 입니다 ^-----------^</c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<title>memo</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	</script>
  <style type="text/css">
  #grade {
    color :orange;
  }
  </style>	
	
</head>
<body>
	<!-- 상단 메뉴 -->
	<div class="container">
		<div class="page-header row">
			<div class="col-sm-4">
				<a href="${root }"><img src="${root }/images/야타.jpg" class="img-responsive img-thumbnail"
					alt="Cinque Terre"></a>
			</div>
			<div class="col-sm-8">
				<a href="${root }"><h1>HOMEPAGE</h1></a>
				
    			<p id="grade">${str }</p> 
   				
			</div>
		</div>

		<ul class="nav nav-tabs">
			<li class="active"><a href="${root }">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">메모<span class="glyphicon glyphicon-arrow-down"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${root }/memo/list">메모 목록</a></li>
					<li><a href="${root }/memo/create">메모 생성</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">게시판<span class="glyphicon glyphicon-arrow-down"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${root }/bbs/list">게시판 목록</a></li>
					<li><a href="${root }/bbs/create">게시판 생성</a></li>
				</ul></li>
<%-- 			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">board_file<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${root }/board/list">board_file 목록</a></li>
				</ul></li>
 --%>			<c:choose>
				<c:when test="${empty sessionScope.id }">
					<li><a href="${root }/member/agree">회원가입</a></li>
					<li><a href="${root }/member/login"><span class="glyphicon glyphicon-log-in"></span>로그인</a></li>
					
				
				</c:when>
				<c:otherwise>
					<li><a href="${root }/member/read"><span class="glyphicon glyphicon-user"></span>나의정보</a></li>
					<li><a href="${root }/member/logout"><span class="glyphicon glyphicon-log-out"></span>로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty sessionScope.id && sessionScope.grade=='A' }">
				<li><a href="${root }/admin/list">회원 목록</a></li>
			</c:if>			
		</ul>
	</div>
</body>
</html>
