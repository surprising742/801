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
					<strong class="primary-font">무플 방지 위원회</strong> <small
							class="pull-right text-muted">20XX-XX-XX</small>
					</div>
					<p>부추는 10cm 길이로 썬다.	
						대패삼겹살, 소금, 후추, 참기름, 청주, 다진마늘을 넣고 밑간을 한다.	
						끓는 물에 식초를 넣고 쫄면을 3분간 삶아 찬물로 깨끗이 헹군다.	
						달군 팬에 기름을 두르고 밑간한 대패삼겹살을 노릇하게 굽는다.	
						간장, 설탕, 굴소스를 넣고 고루 섞은 후 쫄면을 넣고 비빈다.	
						부추, 식초, 고춧가루, 설탕, 참기름을 넣고 가볍게 섞는다	
						그릇에 비빔쫄면, 대패삼겹살, 부추무침을 얹어 완성한다. 
					</p>

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
					<h4 class="modal-title" id="myModalLabel">답변 모달</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>내용</label>
						<textarea cols="10" rows="3" class="form-control" name='content'>New Reply!!!!</textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
					<button id='modalRegisterBtn' type="button" class="btn btn-primary">등록</button>
					<button id='modalCloseBtn' type="button" class="btn btn-default">취소</button>
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
		  var bbsno = "${bbsno}";
		  var sno = "${sno}";
		  var eno = "${eno}";
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
		
		
		var modal = $(".modal");
		var modalInputContent = modal.find("textarea[name='content']");
		 
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");

		$("#modalCloseBtn").on("click", function(e){
		 
		     modal.modal('hide');
		});
		 
		 
		$("#addReplyBtn").on("click", function(e){
		  
		  if('${sessionScope.id}'==''){
		   if(confirm("로그인을 해야 댓글을 쓸수 있습니다.")) {
		        var url = "../member/login";
		        url += "?col=${col}";
		        url += "&word=${word}";
		        url += "&nowPage=${nowPage}";
		        url += "&nPage=${nPage}";
		        url += "&bbsno=${bbsno}";
		        url += "&rurl=../bbs/read";
		   location.href = url;
		   
		   }
		  }else{
		 
		  modalInputContent.val("");
		  modal.find("button[id !='modalCloseBtn']").hide();
		  modalRegisterBtn.show();
		  
		  $(".modal").modal("show");
		  }
		}); //end addReplyBtns
		
		modalRegisterBtn.on("click",function(e){
			  
			  if(modalInputContent.val()==''){
			  alert("댓글을 입력하세요")
			  return ;
			  }
			 
			  var reply = {
			        content: modalInputContent.val(),
			        id:'<c:out value="${sessionScope.id}"/>',
			        bbsno:'<c:out value="${bbsno}"/>'
			      };
			  //alert(reply.content);
			  replyService.add(reply, function(result){
			    
			    alert(result);
			    
			    modal.find("input").val("");
			    modal.modal("hide");
			    
			    //showList(1);
			    showList();
			    
			  }); //end add
			  
			}); //end modalRegisterBtn.on

	//댓글 조회 클릭 이벤트 처리 
 $(".chat").on("click", "li", function(e){
   
   var rnum = $(this).data("rnum");
   
		
		//alert(rnum);
		
		replyService.get(rnum,function(reply){
		modalInputContent.val(reply.content);
		modal.data("rnum",reply.rnum);
		
		modal.find("button[id !='modalCloseBtn']").hide();
		
		if('${sessionScope.id}'==reply.id){
			
			modalModBtn.show();
			modalRemoveBtn.show();
		}
			$(".modal").modal("show");
		});
		
	});
	
 
 modalModBtn.on("click", function(e){
   
     var reply = {
    		 rnum		:modal.data("rnum"),
    		 content	: modalInputContent.val()};
     //alert(reply.rnum);
     replyService.update(reply, function(result){
           
       alert(result);
       modal.modal("hide");
       showList();
       
     });
     
   });//modify 
  
  
   modalRemoveBtn.on("click", function (e){
     
     var rnum = modal.data("rnum");
     
     replyService.remove(rnum, function(result){
           
         alert(result);
         modal.modal("hide");
         showList();
         
     });
     
   });//remove
	
			
	}); //end $(document).ready
	</script>
</body>
</html>