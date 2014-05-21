	$.ajax({
		url : J_task.Config.website+"ucenter/score.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		timeout : 3000,
		success : function(result) {
			$("#j_task_score").html(result.s1);
			$("#j_task_score_1").html(result.s1);
		},
		error : function(xhr, ts, et) {
			xhr = null;
		}
	});