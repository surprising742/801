<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>아이디 중복 확인</title>
<meta charset="utf-8">
<script type="text/javascript">


function use() {
	opener.document.aa.id.value = ${id};
	self.close();
}
</script>
</head>
<body>
	<div class="container">
		<br> <br>
		<div class="well well-lg col-sm-offset-1 col-sm-4">
			<br> 입력된 이름 =${param.mname}<br> <br> 입력된 이메일
			=${param.email}<br> <br> <br>
			<p>
				<c:choose>
					<c:when test="${not empty id }">
					아이디는 = ${id }
					<button class='btn btn-default' onclick='use()'>사용</button>
					</c:when>
					<c:otherwise>
						아이디를 찾을 수 없음<br>
						<br>
					</c:otherwise>
				</c:choose>
		</div>
		<div class="col-sm-offset-2 col-sm-8">
			<button class="btn btn-default" onclick="window.close()">닫기</button>
		</div>
	</div>
</body>
</html>