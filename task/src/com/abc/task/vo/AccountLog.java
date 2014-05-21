package com.abc.task.vo;

import java.util.Date;

import com.abc.task.enums.MCAccount;
import com.abc.task.enums.UCAccount;
import com.abc.task.enums.UCAccountLogStatus;
import com.abc.task.enums.WealthType;

public class AccountLog {
	private int memberId;
	private String memberName;
	private int merchantId;
	private String merchantName;
	private int mcAccountId;
	private WealthType wealthType;
	private UCAccount ucAccount;
	private MCAccount mcAccount;
	private int ucWealth;
	private int mcWealth;
	private String serialNumber;
	private String subSerialNumber;
	private Date createTime;
	private String remark;
	private UCAccountLogStatus status;
	private int delayHours;
	private String operator;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public int getMcAccountId() {
		return mcAccountId;
	}
	public void setMcAccountId(int mcAccountId) {
		this.mcAccountId = mcAccountId;
	}
	public WealthType getWealthType() {
		return wealthType;
	}
	public void setWealthType(WealthType wealthType) {
		this.wealthType = wealthType;
	}
	public UCAccount getUcAccount() {
		return ucAccount;
	}
	public void setUcAccount(UCAccount ucAccount) {
		this.ucAccount = ucAccount;
	}
	public MCAccount getMcAccount() {
		return mcAccount;
	}
	public void setMcAccount(MCAccount mcAccount) {
		this.mcAccount = mcAccount;
	}
	public int getUcWealth() {
		return ucWealth;
	}
	public void setUcWealth(int ucWealth) {
		this.ucWealth = ucWealth;
	}
	public int getMcWealth() {
		return mcWealth;
	}
	public void setMcWealth(int mcWealth) {
		this.mcWealth = mcWealth;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public UCAccountLogStatus getStatus() {
		return status;
	}
	public void setStatus(UCAccountLogStatus status) {
		this.status = status;
	}
	public int getDelayHours() {
		return delayHours;
	}
	public void setDelayHours(int delayHours) {
		this.delayHours = delayHours;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
