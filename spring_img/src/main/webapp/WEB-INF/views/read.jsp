<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.curImg {
	margin-right: 0;
	border-style: solid;
	border-width: 3px;
	border-color: red;
}
</style>
<script type="text/javascript">
	function readGo(imgno) {
		var url = "./read";
		url += "?imgno=" + imgno;
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";

		location.href = url;
	}
</script>

</head>
<body>
	<div class="container">
		<h2 class="col-sm-offset-2 col-sm-10">이미지 조회</h2>
		<table class="table table-bordered ">
			<tr style="text-align: center;">
				<td colspan="4" style="text-align: center;"><img
					src="${root }/storage/${dto.filename}" width="450px" height="350px"
					class="img=thumbnail"></td>
			</tr>
			<tr>
				<th width="20%" style="text-align: center;">제목</th>
				<th width="20%" style="text-align: center;">${dto.title}</th>
			</tr>
			<tr>
				<th style="text-align: center;">내용</th>
				<th style="text-align: center;">${dto.content }</th>
			</tr>
			<tr>
				<th style="text-align: center;">성명</th>
				<th style="text-align: center;">${dto.wname}</th>
			</tr>
		</table>

		<table class="table table-bordered">
			<tr>
				<c:forEach var="i" begin="0" end="4">
					<c:choose>
						<c:when test="${empty files[i] }">
							<td><img src="${root }/storage/default.jpg"
								class="img-rounded" width="200px" height="150px"></td>
						</c:when>
						<c:otherwise>


							<c:choose>
								<c:when test="${dto.imgno == noArr[i] }">
									<td><a href="javascript:readGo('${noArr[i]}')"> <img
											class="img-thumbnail curImg"
											src="${root}/storage/${files[i]}" width="200px"
											height="150px">
									</a></td>
								</c:when>


								<c:otherwise>
									<td><a href="javascript:readGo('${noArr[i]}')"> <img
											class="img-rounded" src="${root}/storage/${files[i]}"
											width="200px" height="150px">
									</a></td>
								</c:otherwise>

							</c:choose>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</tr>
		</table>
		<div style="text-align:center; ">
			<button class="btn btn-default" onclick="">목록</button>
			<button class="btn btn-default" onclick="">등록</button>
			<button class="btn btn-default" onclick="">수정</button>
			<button class="btn btn-default" onclick="">삭제</button>
		</div>
		
	</div>

</body>
</html>