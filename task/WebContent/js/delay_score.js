	$.ajax({
		url : J_task.Config.website+"ucenter/delayscore.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		timeout : 3000,
		success : function(result) {
			$("#j_task_score").html(result);
		},
		error : function(xhr, ts, et) {
			xhr = null;
		}
	});
	var uc_account_status = {'UNCHECK':'待审核','VALID':'{0}m后','INVALID':'无效'};
	var wealtype = {'EXCHARGE':'充值','TASK':'任务'};
	
	var load_option = {
			p:1,
			per:20,
			page_id:'page',
			url : J_task.Config.website+"ucenter/delayscorelog.action",
			params:{},
			showlist:function(result){
				if(result.length==0){
					$("#j_task_scorelogs").append("没有待返积分");
					return;
				}
				$("#j_task_scorelogs").find(".jifen_table_info").hide();
				$.each(result,function(k,v){
					var create = v.create_time;
					var now = new Date();
					var delay_hours = v.delay_hours;
					var minis=Math.floor(delay_hours*60-(now.getTime()-create)/(60*1000));
					var $tr = $("<tr bgcolor='#fbfcf3' class='jifen_table_info'></tr>");
					$tr.append("<td height='36' align='center'>"+new Date(v.create_time).format("yyyy-MM-dd hh:mm:ss")+"</td>");
					$tr.append("<td class='color_hs' align='center'>"+v.wealth+"</td>");
					$tr.append("<td align='center'>"+wealtype[v.wealth_type]+"</td>");
					$tr.append("<td align='center'>"+v.remark+"</td>");
					$tr.append("<td align='center'>"+uc_account_status[v.status].format(minis)+"</td>");
					$tr.hide();
					$("#j_task_scorelogs").append($tr);
					$tr.fadeIn(k*200);
				});
			}
	};
	$.ajax({
		url : J_task.Config.website+"ucenter/delayscorelogcount.action",
		type : "POST",
		dataType : "JSON",
		jsonp : "jsoncallback",
		async:false,
		data : load_option.params,
		success : function(result) {
			load_option.t = result;
		},
		error : function(xhr, ts, et) {
			xhr = null;
			load_option.t = 0;
		}
	});
	load({data:load_option});