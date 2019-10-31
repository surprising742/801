<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>댓글보기</title>
<meta charset="utf-8">

<script type="text/javascript">
	function listb() {

		var url = "list";
		url += "?col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";

		location.href = url;
	}

	function updateb(bbsno) {

		var url = "update";

		url += "?bbsno=" + bbsno;
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		location.href = url;

	}

	function replyb(bbsno) {

		var url = "reply";
		url += "?bbsno=" + bbsno;
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		location.href = url;

	}

	function deleteb(bbsno) {

		var url = "delete";
		url += "?bbsno=" + bbsno;
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		location.href = url;

	}
</script>

</head>
<body>



	<div class="container">
		<h1>조회</h1>

		<div class="panel panel-default">
			<div class="panel-heading">작성자</div>
			<div class="panel-body">${dto.wname }</div>

			<div class="panel-heading">제목</div>
			<div class="panel-body">${dto.title }</div>

			<div class="panel-heading">내용</div>
			<div class="panel-body">${dto.content }</div>

			<div class="panel-heading">조회수</div>
			<div class="panel-body">${dto.viewcnt }</div>

			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.wdate }</div>

		</div>

		<button class="btn btn-default" onclick="location.href='./create'">등록</button>
		<button class="btn btn-default" onclick="updateb('${param.bbsno}')">수정</button>
		<button class="btn btn-default" onclick="deleteb('${param.bbsno}')">삭제</button>
		<button class="btn btn-default" onclick="listb()">목록</button>
		<button class="btn btn-default" onclick="replyb('${param.bbsno}')">답변</button>
		<br><br>
		<div class='row'>
			<div class="col-lg-12">
				<div class="panel panel-default">
				
				
		<div class="panel-heading">
			<i class="fa fa-comments fa-fw"></i> 댓글
			<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
				Reply</button>
		</div>
					
					
	<!-- panel-heading -->
	<div class="panel-body">
		<ul class="chat list-group">
			<li class="left clearfix" data-rno="12">
				<div>
					<div class="header">
						<strong class="primary-font">user1</strong> <small
							class="pull-right text-muted">2019-05-12</small>
					</div>
					<p>Good job!</p>

				</div>
			</li>
		</ul>
		<!-- ./ end ul -->
	</div>
	
	
					<div class="panel-footer"></div>
				</div>
				<!-- /.panel -->
			</div>
			<!-- col-lg-12 end -->
		</div>
		<!-- row end -->

	</div>
	<!-- container div-->



	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>내용</label>
						<textarea cols="10" rows="3" class="form-control" name='content'>New Reply!!!!</textarea>
					</div>
					<div class="form-group">
						<label>아이디</label> <input class="form-control" name='id'
							value='${sessionScope.id}'>
					</div>
					<div class="form-group">
						<label>등록날짜</label> <input class="form-control" name='regdate'
							value='2018-01-01 13:13'>
					</div>

				</div>
				<div class="modal-footer">
					<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
					<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
					<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript" src="${pageContext.request.contextPath }/js/breply.js"></script>
	<script>
	$(document).ready(function () {
		  var bbsno = '<c:out value="${bbsno}"/>';
		  var sno = '<c:out value="${sno}"/>';
		  var eno = '<c:out value="${eno}"/>';
		  var replyUL = $(".chat");
		  
		  showList();
		function showList(){
			replyService.getList({bbsno:bbsno,sno:sno,eno:eno},function(list){
				var str="";
				
				if(list == null ||list.length ==0){
					return;
				}
				 for (var i = 0, len = list.length || 0; i < len; i++) {
				       str +="<li class='list-group-item' data-rnum='"+list[i].rnum+"'>";
				       str +="<div><div class='header'><strong class='primary-font'>"+list[i].id+"</strong>"; 
				       str +="<small class='pull-right text-muted'>"+list[i].regdate+"</small></div>";
				       str +=replaceAll(list[i].content,'\n','<br>')+"</div></li>";
				     }
				 replyUL.html(str);
			     
			     showReplyPage();
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			});//end function
			
		}//end showlist
		
		
		function replaceAll(str,searchStr,replaceStr){
			return str.split(searchStr).join(replaceStr);
		}
		// 2019-10-30 수요일
		var nPage = '<c:out value="${nPage}"/>';
		var nowPage = '<c:out value="${nowPage}"/>';
		var colx = '<c:out value="${col}"/>';
		var wordx = '<c:out value="${word}"/>';
		var replyPageFooter = $(".panel-footer");
		var param = "nPage="+nPage;
		param += "&nowPage="+nowPage;
		param += "&bbsno="+bbsno;
		param += "&col="+colx;
		param += "&word="+wordx;
		 
		function showReplyPage(){
		 
		replyService.getPage(param, function(paging) {
		 
		  console.log(paging);
		 
		    var str ="<div><small class='text-muted'>"+paging+"</small></div>";
		    replyPageFooter.html(str);
		  
		});
		}//end showReplyPage
		
	}); //end $(document).ready
	</script>
</body>
</html>