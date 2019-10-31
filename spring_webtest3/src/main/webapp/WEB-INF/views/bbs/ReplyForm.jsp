<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>bbs</title>

</head>
<body>
	<div class="container">
		<h1 class="col-sm-offset-2 col-sm-10">답변</h1>
		<form class="form-horizontal" action="reply" method="post">
			<input type="hidden" name="bbsno" value="${param.bbsno }">
			<input type="hidden" name="grpno" value="${dto.grpno }">
			<input type="hidden" name="indent" value="${dto.indent }">
			<input type="hidden" name="ansnum" value="${dto.ansnum }">
			<input type="hidden" name="col" value="${param.col }">
			<input type="hidden" name="word" value="${param.word }">
			<input type="hidden" name="nowPage" value="${param.nowPage}">
			<div class="form-group">
				<label class="control-label col-sm-2" for="wname">작성자:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="wname" name="wname">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="title">제목:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="title"
						value="${dto.title }" name="title">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="content">내용:</label>
				<div class="col-sm-6">
					<textarea rows="12" cols="6" id="contnet" name="content"
						class="form-control"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">비밀번호:</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter 비밀번호" name="passwd">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<button type="submit" class="btn btn-default">답변</button>
					<button type="reset" class="btn btn-default">취소</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>