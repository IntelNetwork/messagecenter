package org.smartwork.comm.enums;
/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author Huanghy
 */
public enum MsgBizResultEnum {
	/***
	 * 006-消息中心系统
	 * 功能暂无-表示通用异常
	 * 001-为空判断
	 */
	AD_CODE_UNIQUE("006001001","广告位编码重复","%s编码已经存在"),
	AD_TYPE_NOT_EXISTS("006001002","广告类型异常","%s广告类型不存在"),
	RELEASE_STATE_NOT_EXISTS("006001003","发布状态异常","%s发布状态不存在"),
	RELEASE_OR_CANCELLED("006001004","广告已发布/已取消","%s已发布/已取消"),
	CANCELLED("006001005","广告已取消","%s已取消"),
	MSG_TYPE_NOT_EXISTS("006002001","业务编码不存在","%s业务编码不存在"),
	N_RELEASE_OR_CANCELLED("006002003","广告已发布/已取消","%s已发布/已取消"),
	N_CANCELLED("006002004","广告已取消","%s已取消");

	
	/**错误编码业务系统代码+功能编码+错误代码**/
	private String bizCode;
	/**错误描述****/
	private String bizMessage;
	/**带格式错误描述****/
	private String bizFormateMessage;

	/***
	 * 构造函数:
	 * @param bizCode
	 * @param bizMessage
	 * @param bizFormateMessage
	 */
	MsgBizResultEnum(String bizCode, String bizMessage, String bizFormateMessage){
		this.bizCode = bizCode;
		this.bizMessage = bizMessage;
		this.bizFormateMessage = bizFormateMessage;
	}

	/** 
	 * @return bizCode 
	 */
	public String getBizCode() {
		return bizCode;
	}

	/** 
	 * @param bizCode 要设置的 bizCode 
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/** 
	 * @return bizMessage 
	 */
	public String getBizMessage() {
		return bizMessage;
	}

	/** 
	 * @param bizMessage 要设置的 bizMessage 
	 */
	public void setBizMessage(String bizMessage) {
		this.bizMessage = bizMessage;
	}

	/** 
	 * @return bizFormateMessage 
	 */
	public String getBizFormateMessage() {
		return bizFormateMessage;
	}

	/** 
	 * @param bizFormateMessage 要设置的 bizFormateMessage 
	 */
	public void setBizFormateMessage(String bizFormateMessage) {
		this.bizFormateMessage = bizFormateMessage;
	}
}
