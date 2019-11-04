<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="util" uri="/ELFunctions"%>
<!DOCTYPE html>
<html>
<head>
<title>bbs</title>

<script type="text/javascript">
	function read(bbsno) {

		var url = "read";
		url += "?bbsno=" + bbsno;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;

	}
</script>

</head>
<body>
	<div class="container">
		<h1>게시판 목록</h1>

		<form class="form-inline" action="./list">
			<div class="form-group">
				<select class="form-control" name="col">

					<option value="wname" <c:if test="${col=='wname' }">selected</c:if>>성명</option>
					<option value="title" <c:if test="${col=='title' }">selected</c:if>>제목
					</option>
					<option value="content"
						<c:if test="${col=='content' }">selected</c:if>>내용</option>
					<option value="title_content"
						<c:if test="${col=='title_content' }">selected</c:if>>제목+내용</option>
					<option value="total" <c:if test="${col=='total' }">selected</c:if>>전체</option>

				</select>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Enter 검색어"
					name="word" value="${word}">
			</div>
			<button class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span>검색
			</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='create'">
				<span class="glyphicon glyphicon-plus"></span>등록
			</button>
		</form>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>grpno</th>
					<th>indent</th>
					<th>ansnum</th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="6">등록된 글이 없습니다.</td>
					</c:when>

					<c:otherwise>
						<c:forEach var="dto" items="${list }">
							<tr>
								<td>${dto.bbsno}</td>
								<td><c:forEach begin="1" end="${dto.indent }">
						&nbsp;&nbsp;
					</c:forEach> <c:if test="${dto.indent > 0 }">

										<img src='${pageContext.request.contextPath}/images/re.jpg'>

									</c:if> 
									<c:set var="rcount" value="${util:rcount(dto.bbsno,rmapper) }" />
									<a href="javascript:read('${dto.bbsno}')">${dto.title}</a> 
									<c:if test="${rcount>0 }">
										<span class="badge">${rcount}</span>
									</c:if> 
									<c:if test="${util:newImg(dto.wdate) }">
										<img src="${pageContext.request.contextPath}/images/new.gif">
									</c:if></td>
								<td>${dto.wname}</td>
								<td>${dto.grpno}</td>
								<td>${dto.indent}</td>
								<td>${dto.ansnum}</td>
							</tr>


						</c:forEach>

					</c:otherwise>
				</c:choose>
			</tbody>

		</table>
		<div>${paging }</div>
	</div>
</body>
</html>