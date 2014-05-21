var j_task_notices = {
		//ok
		001000 : {t:"notice",txt:"操作成功"},
		//j_task_sys
		000000 : {t:"error",txt:'<p class="error">未知错误,请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>',ele:"#j_task_sys"},
		000001 : {t:"error",txt:'<p class="error">服务器异常,请<a class="green" href="javascript:void(0);" onclick="window.parent.location.reload();">刷新页面</a>或<a href="javascript:void(0);" class="green contact-us">联系客服</a></p>',ele:"#j_task_sys"},
		//j_task_pwd
		100001 : {t:"tip",txt:"密码必须为8-25位非纯数字或字母!",ele:"#j_task_pwd"},
		100002 : {t:"error",txt:"密码必须为8-25位!",ele:"#j_task_pwd"},
		100003 : {t:"error",txt:"密码不能为纯数字!",ele:"#j_task_pwd"},
		100004 : {t:"error",txt:"密码不能为纯字符!",ele:"#j_task_pwd"},
		100005 : {t:"error",txt:"您的密码过于简单，请不要使用相同字符!",ele:"#j_task_pwd"},
		100006 : {t:"error",txt:"输入的密码与邮箱一致，请您重新输入密码!",ele:"#j_task_pwd"},
		100007 : {t:"error",txt:"密码为空,请输入密码!",ele:"#j_task_pwd"},
		100008 : {t:"error",txt:"不能输入非法字符!",ele:"#j_task_pwd"},
		100009 : {t:"error",txt:"您输入的密码与用户名一致，请您重新输入密码!",ele:"#j_task_pwd"},
		
		//j_task_pwd_check
		101001 : {t:"error",txt:"密码输入错误!",ele:"#j_task_pwd_check"},
		//j_task_pwd_re
		102001 : {t:"error",txt:"密码不一致!",ele:"#j_task_pwd_re"},
		//j_task_name
		103001 : {t:"error",txt:"用户名已存在!",ele:"#j_task_name"},
		103002 : {t:"error",txt:"用户名不能为纯数字!",ele:"#j_task_name"},
		103003 : {t:"error",txt:"用户名不能为空!",ele:"#j_task_name"},
		103004 : {t:"tip",txt:"不能为纯数字,不能包含符号除下划线外!",ele:"#j_task_name"},
		103005 : {t:"error",txt:"用户名长度为3—25个字符，只允许中英文、数字及下划线!",ele:"#j_task_name"},
		//j_task_email
		104001 : {t:"error",txt:"邮箱已存在!",ele:"#j_task_email"},
		104002 : {t:"error",txt:"邮箱格式不正确!",ele:"#j_task_email"},
		104003 : {t:"error",txt:"邮箱不能为空!",ele:"#j_task_email"},
		//j_task_mobile
		105001 : {t:"error",txt:"手机已存在!",ele:"#j_task_mobile"},
		105002 : {t:"error",txt:"手机格式不正确!",ele:"#j_task_mobile"},
		//j_task_QQ,j_task_SINA,j_task_ALIPAY
		106001 : {t:"error",txt:"您还没有设置过密码,请<a href='/ucenter/pwd.htm'>设置</a>密码后,再解除绑定!"}
};