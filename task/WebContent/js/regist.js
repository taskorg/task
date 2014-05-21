(function($) {
	var $email =$("#j_task_email");
	var $name =$("#j_task_name");
	var $pwd =$("#j_task_pwd");
	var $rpwd =$("#j_task_pwd_re");
	var $submit = $("#j_task_submit");
	var postData = {};
	var $msg= $("#j_task_msg");
	$(".J_data:visible").focus(function(){$(this).removeClass('forgot_table_text').addClass('forgot_table_text_hover');}).blur(function(){$(this).removeClass('forgot_table_text_hover').addClass('forgot_table_text');});
	$submit.on("click", function(ev) {
		ev.preventDefault();
		submitHandler();
	});
	function submitHandler() {
		if (blankValidation()) {
			removeMessage.call($submit);
			$submit.toggleButton();
			preparePostData();
			$.ajax({
				url : J_task.Config.website+"regist.action",
				type : "POST",
				dataType : "JSON",
				jsonp : "jsoncallback",
				data : postData,
				success : function(result) {
					if (result.msg == "001000") {
						registSuccess.call(result,null);
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
					$submit.toggleButton();
				}
			});
		}
	};
	function registSuccess () {
		"username".setCookie(this.name, 14, J_task.Config.domain, "/");
		"userid".setCookie(this.id, 14, J_task.Config.domain, "/");
        var go_url = $("#go_url").val();
        go_url = J_task.Config.website+"registsuccess.action?go_url=" + encodeURIComponent(go_url);
        window.location.href = go_url;
        setTimeout(function() {
            $msg.html('<p class="success">注册成功！如果长时间没有响应，请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>').slideDown();
        }, 3000);
    }
	function preparePostData() 
    {
        $(".J_data:visible").each(function() 
        {
            var $this = $(this);
            postData[$this.attr("name")] = trim($this.val());
        });
        postData.regurl = eu(window.location.href);
        postData.refurl = eu(document.referrer);
        postData.cookie_age = 86400;
    }
	function blankValidation() {
		var nameVal = trim($name.val());
		var emailVal = trim($email.val());
		var pwdVal = trim($pwd.val());
		var rpwdVal = trim($rpwd.val());
		if (/^\d+$/g.test(nameVal)){
			showMessage.call($name,
					j_task_notices[103002].t,
					j_task_notices[103002].txt);
            return false;
        }
		if(!J_task.regValid(J_task.regs.uname,nameVal)){
			showMessage.call($name,
					j_task_notices[103005].t,
					j_task_notices[103005].txt);
            return false;
		}
		if(pwdVal.length < 8 || pwdVal.length>25){
			showMessage.call($pwd,j_task_notices[100002].t,j_task_notices[100002].txt);
            return false;
		}
		if (J_task.regValid(J_task.regs.painNumber, pwdVal)){
			showMessage.call($pwd,j_task_notices[100003].t,j_task_notices[100003].txt);
            return false;
        }
        if (J_task.regValid(J_task.regs.painChararter, pwdVal)){
        	showMessage.call($pwd,j_task_notices[100004].t,j_task_notices[100004].txt);
            return false;
        }
        if (J_task.regValid(J_task.regs.sameChararter, pwdVal)){
        	showMessage.call($pwd,j_task_notices[100005].t,j_task_notices[100005].txt);
            return false;
        }
        if (emailVal == pwdVal || emailVal.split("@")[0] == pwdVal){
            $rpwd.val('');
            $pwd.val('');
            showMessage.call($pwd,j_task_notices[100006].t,j_task_notices[100006].txt);
            return false;
        }
        if (nameVal == pwdVal){
        	 $rpwd.val('');
             $pwd.val('');
            showMessage.call($pwd,j_task_notices[100009].t,j_task_notices[100009].txt);
            return false;
        }
        if (!J_task.regValid(J_task.regs.email, emailVal)) {
			showMessage.call($email,j_task_notices[104002].t,j_task_notices[104002].txt);
			return false;
		}
        if (pwdVal.length > 0 && rpwdVal != pwdVal){
            showMessage.call($rpwd,j_task_notices[100009].t,j_task_notices[100009].txt);
            return false;
        }
		return true;
	};
	$email.css({
		"ime-mode" : "disabled"
	}).on("focusout",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (!val) {
			removeMessage.call($this);
			return false;
		}
		if (!J_task.regValid(J_task.regs.email, val)) {
			showMessage.call($this,j_task_notices[104002].t,j_task_notices[104002].txt);
			return false;
		}
		$email.check2({
			url:J_task.Config.website+"/ucenter/checkemail.action",
			msg:$("#J_msg")
		});
	});
	$pwd.css({
		"ime-mode" : "disabled"
	}).on("focusin",function() {
		var $this = $(this);
		var val = trim($this.val());
		removeMessage.call($this);
		if (!val) {
			showMessage.call($this,j_task_notices[100001].t,j_task_notices[100001].txt);
			return false;
		}
	}).on("focusout",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (!val) {
			removeMessage.call($this);
			return false;
		}
		if(val.length < 8 || val.length>25){
			showMessage.call($this,j_task_notices[100002].t,j_task_notices[100002].txt);
            return false;
		}
		if (J_task.regValid(J_task.regs.painNumber, val)){
			showMessage.call($this,j_task_notices[100003].t,j_task_notices[100003].txt);
            return false;
        }
        if (J_task.regValid(J_task.regs.painChararter, val)){
        	showMessage.call($this,j_task_notices[100004].t,j_task_notices[100004].txt);
            return false;
        }
        if (J_task.regValid(J_task.regs.sameChararter, val)){
        	showMessage.call($this,j_task_notices[100005].t,j_task_notices[100005].txt);
            return false;
        }
        if ($email.val() == val || $email.val().split("@")[0] == val){
            $rpwd.val('');
            $pwd.val('');
            showMessage.call($this,j_task_notices[100006].t,j_task_notices[100006].txt);
            return false;
        }
        if ($name.val() == val){
        	 $rpwd.val('');
             $pwd.val('');
            showMessage.call($this,j_task_notices[100009].t,j_task_notices[100009].txt);
            return false;
        }
	});
	$rpwd.css({
		"ime-mode" : "disabled"
	}).on("focusin",function() {
		var $this = $(this);
		removeMessage.call($this);
	}).on("focusout",function() {
		var $this = $(this);
		var val = trim($this.val());
		if ($pwd.val().length > 0 && val != $pwd.val()){
            showMessage.call($this,j_task_notices[100009].t,j_task_notices[100009].txt);
            return false;
        }
	});
	$name.css({
		"ime-mode" : "disabled"
	}).on("focusin",function() {
		var $this = $(this);
		var val = trim($this.val());
		removeMessage.call($this);
		if (!val) {
			showMessage.call($this,
					j_task_notices[103004].t,
					j_task_notices[103004].txt);
			return false;
		}
	}).on("focusout",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (!val) {
			removeMessage.call($this);
			return false;
		}
		if (/^\d+$/g.test(val)){
			showMessage.call($this,
					j_task_notices[103002].t,
					j_task_notices[103002].txt);
            return false;
        }
		if(!J_task.regValid(J_task.regs.uname,val)){
			showMessage.call($this,
					j_task_notices[103005].t,
					j_task_notices[103005].txt);
            return false;
		}
		$name.check3({
			url:J_task.Config.website+"/ucenter/checkname.action",
			success:function(){
			},
			error:function(){
			},
			msg:$("#J_msg")
		});
	});
	$('#J_verify_code').checkverify();
	$name .focus();
})(jQuery);