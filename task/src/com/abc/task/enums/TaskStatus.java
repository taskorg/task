package com.abc.task.enums;

public enum TaskStatus {
	ONLIINE("在线"), OFFLNIE("下线"), MAX_COST("最大消耗"), RECEIVE_DATA_END("不再接收奖励数据");

	private final String message;

	private TaskStatus(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
