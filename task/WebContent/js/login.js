	var selectors = {
		emailSelector : "#J_login_email",
		passwordSelector : "#J_login_password",
		codeSelector : "#J_verify_code",
		submitSelector : "#J_login_submit",
		postDataSelector : ".J_login_data",
		loginMsgSelector : "#msg_login"
	};
	var notices = {
			email_empty :{t:"notice",txt:"请输入注册邮箱"},email_error :{t:"error",txt:"请输入正确的邮箱"},
			pwd_empty : {t:"notice",txt:"请输入密码"},
			code_empty : {t:"notice",txt:"请输入验证码"},code_error : {t:"error",txt:"验证码错误"},
			login_error : {t:"error",txt:"邮箱或密码错误"},
			system_error : {t:"error",txt:"系统错误"}
	};
	var $email = $(selectors.emailSelector);
	var $pwd = $(selectors.passwordSelector);
	var $code = $(selectors.codeSelector);
	var $submit = $(selectors.submitSelector);
	var $msg= $(selectors.loginMsgSelector);
	var openStaticTips = true;
	var noticeStatusString = "notice";
	var codeTimeid;
	var toggleSubmit= true;
	var postData = {};
	var eu = encodeURIComponent;
	function showMessage (status, text, getfocus) {
		this.next(".J_loginer").remove().end().after("<div class='login_{0} font12 J_loginer'>{1}</div>".format(status, text));
		if (getfocus) {
			this.focus();
		}
		return false;
	};
	function removeMessage() {
		this.next(".J_loginer").remove();
	};
	function regValid(reg, txt) {
		reg.lastIndex = 0;
		return reg.test(txt);
	};
	$(".J_login_data:visible").focus(function() {
		var $this = $(this);
		$this.removeClass('login_table_text').addClass('login_table_text_hover');
	}).blur(function() {
		var $this = $(this);
		$this.removeClass('login_table_text_hover').addClass('login_table_text');
	});
	$submit.on("click", function(ev) {
		ev.preventDefault();
		submitHandler();
	});
	function submitHandler() {
		if (blankValidation()) {
			removeMessage.call($submit);
			toggleButton();
			preparePostData();
			$.ajax({
				url : J_task.Config.website+"loginin.action",
				type : "POST",
				dataType : "JSON",
				jsonp : "jsoncallback",
				data : postData,
				success : function(result) {
					if (result.msg == "login_ok") {
						loginSuccess.call(result,null);
					} else if(result.msg == "login_error"){
						$msg.html('<p class="error">输入的密码与账号不匹配，<a href="' + J_task.Config.website + 'password" class="green forget-psw" target="_blank">忘记密码了？</a></p>').slideDown();
						identifycode();
						toggleButton();
					} else if(result.msg == "system_error"){
						$msg.html('<p class="error">系统错误，请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>').slideDown();
						identifycode();
						toggleButton();
					}
				},
				error : function(xhr, ts, et) {
					xhr = null;
					$msg.html('<p class="error">服务器异常，请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>').slideDown();
					identifycode();
					toggleButton();
				}
			});
		}
	};
	function loginSuccess () {
		"username".setCookie(this.name, 14, J_task.Config.domain, "/");
		"userid".setCookie(this.id, 14, J_task.Config.domain, "/");
        var go_url = $("#go_url").val();
        go_url = J_task.Config.website+"loginsuccess.action?go_url=" + encodeURIComponent(go_url);
        window.location.href = go_url;
        setTimeout(function() {
            $msg.html('<p class="success">登录成功！如果长时间没有响应，请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>').slideDown();
        }, 3000);
    }
	function preparePostData() 
    {
        $(selectors.postDataSelector).each(function() 
        {
            var $this = $(this);
            postData[$this.attr("name")] = trim($this.val());
        });
        postData.regurl = eu(window.location.href);
        postData.refurl = eu(document.referrer);
        postData.cookie_age = 86400;
    }
	function blankValidation() {
		var emailVal = trim($email.val());
		var pwdVal = trim($pwd.val());
		var codeVal = trim($code.val());
		if (!regValid(J_task.regs.email, emailVal)) {
			showMessage.call($email,notices.email_error.t,notices.email_error.txt,true);
			return false;
		}
		if (pwdVal=="") {
			showMessage.call($pwd,notices.pwd_empty.t,notices.pwd_empty.txt,true);
			return false;
		}
		if (codeVal=="") {
			$msg.html(notices.code_empty.txt);
			return false;
		}
		return true;
	};
	$email.css({
		"ime-mode" : "disabled"
	}).on("focusin",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (openStaticTips && !val) {
			showMessage.call($this,
					notices.email_empty.t,
					notices.email_empty.txt);
		}
	}).on("focusout",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (!val) {
			removeMessage.call($this);
			return;
		}
		if (!regValid(J_task.regs.email, val)) {
			showMessage.call($this,notices.email_error.t,notices.email_error.txt);
			return false;
		}else{
			removeMessage.call($this);
		}
	});
	$pwd.css({
		"ime-mode" : "disabled"
	}).on("focusin",function() {
		var $this = $(this);
		var val = trim($this.val());
		if (openStaticTips && !val) {
			showMessage.call($this,
					notices.pwd_empty.t,
					notices.pwd_empty.txt);
		}
	}).on("focusout",function() {
		var $this = $(this);
		removeMessage.call($this);
	});
	
	$(function() {
	    logerrtimes = "LOGERRTIMES".getCookie();
	    $('#J_verify_code').checkverify({success : function(){
	    	if(regValid(J_task.regs.email, trim($("#J_login_email").val())) && trim($("#J_login_password").val())!=""){
	    		submitHandler();
	    	}
	    }});
	});
	(function($) {
	    var defaults = {text: "处理中...",id: "J_toggle",getOriginalClass: false,size: "btn-m"};
	    $.fn.toggleButton = function(options) {
	        var settings = $.extend(true, {}, defaults, options);
	        var id = settings.id;
	        return this.each(function() {
	            var $this = $(this);
	            if ($("#{0}".format(id)).length == 0) {
	                $this.after("<span id='{0}' style='display:none;' class='{1}'>{2}</span>".format(id, settings.getOriginalClass ? $this.attr("class") : "btn {0} btn-waiting".format(settings.size), settings.text));
	            }
	            exchange($this, $("#{0}".format(id)));
	        });
	        function exchange($btn, $spoofBtn) {
	            $btn.is(":visible") ? $btn.hide() : $btn.css("display", "inline-block");
	            $spoofBtn.is(":visible") ? $spoofBtn.hide() : $spoofBtn.css("display", "inline-block");
	        }
	    };
	    $.fn.toggleButton.defaults = defaults;
	})(jQuery);
	function toggleButton() {
		if (!toggleSubmit) {
			return;
		}
		$submit.toggleButton({
			text : "登录中...",
			id : "J_loginer_toggle"
		});
	}
