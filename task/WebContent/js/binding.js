	$.ajax({
		url : J_task.Config.website+"ucenter/bindplats.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		timeout : 3000,
		success : function(result) {
			$.each(result,function(k,v){
				J_task.log(v);
				v = v.toLocaleLowerCase();
				$("#j_task_no_bind_"+v).hide();
				$("#j_task_bind_"+v).show();
			});
		},
		error : function(xhr, ts, et) {
			xhr = null;
		}
	});
	
	function unbind(plat){
		if(!confirm("确定要取消绑定吗?")){
			return false;
		}
		$.ajax({
			url : J_task.Config.website+plat+"unbind.action",
			type : "POST",
			dataType : "JSON",
			jsonp : "jsoncallback",
			timeout : 3000,
			success : function(result) {
				J_task.log("#j_task_bind_"+plat);
				if(result.msg=="001000"){
					$("#j_task_no_bind_"+plat).show();
					$("#j_task_bind_"+plat).hide();
				}else{
					showMessage.call($("#j_task_bind_"+plat),j_task_notices[106001].t,j_task_notices[106001].txt);
				}
			},
			error : function(xhr, ts, et) {
				xhr = null;
			}
		});
	}