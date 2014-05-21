package com.abc.task.vo;

import java.util.Date;

import com.abc.task.enums.MCAccount;
import com.abc.task.enums.WealthType;

public class MCAccountLog {
	private int id;
	private int merchantId;
	private int accountId;
	private WealthType wealthType;
	private long wealth;
	private String serialNumber;
	private String subSerialNumber;
	private String remark;
	private MCAccount account;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public WealthType getWealthType() {
		return wealthType;
	}

	public void setWealthType(WealthType wealthType) {
		this.wealthType = wealthType;
	}

	public long getWealth() {
		return wealth;
	}

	public void setWealth(long wealth) {
		this.wealth = wealth;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubSerialNumber() {
		return subSerialNumber;
	}

	public void setSubSerialNumber(String subSerialNumber) {
		this.subSerialNumber = subSerialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MCAccount getAccount() {
		return account;
	}

	public void setAccount(MCAccount account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
