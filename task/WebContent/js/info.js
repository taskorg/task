var $jtn = $("#j_task_name");
var $jtm = $("#j_task_mobile");
var $jtmb = $("#j_task_mobile_binding");
var $submit = $("#j_task_save");
var $msg = $("#msg");
$jtn.focus(function(){$(this).removeClass('forgot_table_text').addClass('forgot_table_text_hover');}).blur(function(){$(this).removeClass('forgot_table_text_hover').addClass('forgot_table_text');});
$jtn.check2({
	url:J_task.Config.website+"ucenter/checkname.action",
	msg : $msg
});
$submit.on("click", function(ev) {
	ev.preventDefault();
	removeMessage.call($submit);
	$submit.toggleButton();
	var postData={};
	var $msg= $("#msg");
	preparePostData(postData,".J_task_data");
	$.ajax({
		url : J_task.Config.website+"ucenter/changeinfo.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		data : postData,
		timeout : 3000,
		success : function(result) {
			if (result.msg == "001000") {
				loginSuccess.call(result,null);
			}else if(j_task_notices[result.msg]){
				showMessage.apply($(j_task_notices[result.msg]["ele"]), [j_task_notices[result.msg].t,j_task_notices[result.msg].txt] );
		        $submit.toggleButton();
			}else{
				$msg.html(j_task_notices[000000].txt).slideDown();
				$submit.toggleButton();
			}
		},
		error : function(xhr, ts, et) {
			xhr = null;
			$msg.html(j_task_notices[000001].txt).slideDown();
			$submit.toggleButton({);
		}
	});
});