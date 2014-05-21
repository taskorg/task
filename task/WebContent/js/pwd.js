var $opwd = $("#j_task_pwd_check");
var $npwd = $("#j_task_pwd");
var $pwdcheck = $("#check_tag");
var $rpwd = $("#j_task_pwd_re");
var $submit = $("#j_task_save");
$(".J_changepwd").focus(function(){$(this).removeClass('forgot_table_text').addClass('forgot_table_text_hover');}).blur(function(){$(this).removeClass('forgot_table_text_hover').addClass('forgot_table_text');});
$opwd.check2({
	url:J_task.Config.website+"ucenter/checkpwd.action",
	success:function(){
		$pwdcheck.val("true");
	},
	error:function(){
		$pwdcheck.val("false");
	}
});
$npwd.css({
	"ime-mode" : "disabled"
}).on("focusin",function() {
	var $this = $(this);
	var val = trim($this.val());
	if (!val) {
		showMessage.call($this,j_task_notices[100001].t,j_task_notices[100001].txt);
	}
}).on("focusout",function() {
	var $this = $(this);
	var val = trim($this.val());
	if (!val) {
		removeMessage.call($this);
		return;
	}else{
		if (val.length < 8 || val.length > 25){
	       return showMessage.call($this, j_task_notices[100002].t,j_task_notices[100002].txt);
	    }
		if (J_task.regValid(J_task.regs.painNumber, val)) {
			return showMessage.call($this,j_task_notices[100003].t,j_task_notices[100003].txt);
		}
		if (J_task.regValid(J_task.regs.painChararter, val)) {
			showMessage.call($this,j_task_notices[[100004]].t,j_task_notices[100004].txt);
			return false;
		}
		if (J_task.regValid(J_task.regs.sameChararter, val)) {
			showMessage.call($this,j_task_notices[100005].t,j_task_notices[100005].txt);
			return false;
		}
		if (J_task.regValid(J_task.regs.invalidpassword, val)) {
			showMessage.call($this,j_task_notices[100008].t,j_task_notices[100008].txt);
			return false;
		}
		removeMessage.call($this);
	}
});
$rpwd.css({
	"ime-mode" : "disabled"
}).on("focusout",function() {
	var $this = $(this);
	var npwd = trim($npwd.val());
	var val= trim($this.val());
	if (!val) {
		removeMessage.call($this);
		return;
	}else if(npwd != val){
		$this.focus();
		showMessage.apply($this, [j_task_notices[102001].t,j_task_notices[102001].txt] );
        return false;
	}
	removeMessage.call($this);
});
$submit.on("click", function(ev) {
	ev.preventDefault();
	var opwd = trim($opwd.val());
	var npwd = trim($npwd.val());
	var rpwd= trim($rpwd.val());
	var pwdcheck= trim($pwdcheck.val());
//	if(opwd.length ==0 || pwdcheck=="false"){
//		$opwd.focus();
//        return false;
//	}
	if(npwd.length==0){
		return showMessage.apply($npwd, [j_task_notices[100007].t,j_task_notices[100007].txt] );
	}
	if(npwd != rpwd){
		return showMessage.apply($npwd, [j_task_notices[100009].t,j_task_notices[100009].txt] );
	}
    if (npwd.length < 8 || npwd.length > 25){
    	return showMessage.apply($npwd, [j_task_notices[100002].t,j_task_notices[100002].txt] );
    }
    if (J_task.regValid(J_task.regs.invalidpassword, npwd)){
    	return showMessage.apply($npwd, [j_task_notices[100008].t,j_task_notices[100008].txt] );
    }
	removeMessage.call($submit);
	$submit.toggleButton({
		text : "保存中...",
		id : "J_submit_toggle"
	});
	var postData={};
	var $msg= $("#msg");
	preparePostData(postData,".J_pwd_data");
	$.ajax({
		url : J_task.Config.website+"ucenter/changepwd.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		data : postData,
		timeout : 3000,
		success : function(result) {
			J_task.log(j_task_notices[result.msg]);
			if (result.msg == "001000") {
				loginSuccess.call(result,null);
			}else if(j_task_notices[result.msg]){
				showMessage.apply($(j_task_notices[result.msg]["ele"]), [j_task_notices[result.msg].t,j_task_notices[result.msg].txt] );
		        $submit.toggleButton({
		    		text : "保存中...",
		    		id : "J_submit_toggle"
		    	});
			}else{
				$msg.html(j_task_notices[000000].txt).slideDown();
				$submit.toggleButton({
					text : "保存中...",
					id : "J_submit_toggle"
				});
			}
		},
		error : function(xhr, ts, et) {
			xhr = null;
			$msg.html(j_task_notices[000001].txt).slideDown();
			$submit.toggleButton({
				text : "保存中...",
				id : "J_submit_toggle"
			});
		}
	});
});