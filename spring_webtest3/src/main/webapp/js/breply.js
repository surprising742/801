console.log("*****Reply Module........");
var replyService = (function() {

	function getList(param, callback, error) {
		var bbsno = param.bbsno;
		var sno = param.sno;
		var eno = param.eno;
		// alert(param.bbsno);
		$.getJSON("./reply/list/" + bbsno + "/" + sno + "/" + eno + ".json",
				function(data) {
					// alert(data);
					if (callback) {
						callback(data); // 댓글 목록만 가져오는 경우
						// callback(data.replyCnt, data.list); //댓글 숫자와 목록을 가져오는
						// 경우
					}
				});
		// 2019-10-30
	}
	function getPage(param, callback, error) {

		$.ajax({
			type : 'get',
			url : "./reply/page",
			data : param,
			contentType : "application/text; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	return {
		getList : getList,
		getPage : getPage
	}

})();
