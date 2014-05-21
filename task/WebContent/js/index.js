var load_option = {
		p:1,
		per:1,
		page_id:'page',
		url:J_task.Config.website+"loadtasks.action",
		params:{},
		showlist:function(result){
			var li = new StringBuilder();
			$.each(result,function(k,v){
				li.append("<li>");
				li.append("<div class='list'>");
				li.append("<span class='pic'>");
				li.append("<a href='/task.htm?id=").append(v.id).append("' target='_blank'>");
				li.append("<img style='max-width:155px;max-height:246px' src='").append(v.logo).append("' />");
				li.append("</a>");
				li.append("</span>");
				li.append("<div class='left_right'>");
				li.append("<p class='title'>");
				li.append("<a href='/task.htm?id=").append(v.id).append("' target='_blank'>").append(v.name).append("</a>");
				li.append("</p>");
				li.append("<p class='about'>").append(v.description).append("</p>");
				li.append("<div class='part_bg'>");
				li.append("<div class='jfb_botton'>");
				li.append("<a href='/task.htm?id=").append(v.id).append("' target='_blank'>立即参与</a>");
				li.append("</div>");
				li.append("<p class='font18'>收益播报</p>");
				li.append("<p class='jfb_word'>");
				li.append("<span class='word'>").append(v.award).append("</span> 个集分宝");
				li.append("</p>");
				li.append("</div>");
				li.append("</div>");
				li.append("</div>");
				li.append("</li>");
			});
			$("#tasklist").html(li.toString());
		}
};
(function() {
	load_option.params.cat = 1;
})();
$.ajax({
	url : J_task.Config.website+"taskcount.action",
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
$("#tasklist").children("li").children("div").mouseover(function() {
	var $this = $(this);
	$this.removeClass('list').addClass('list_hover');
}).mouseout(function() {
	var $this = $(this);
	$this.removeClass('list_hover').addClass('list');
});