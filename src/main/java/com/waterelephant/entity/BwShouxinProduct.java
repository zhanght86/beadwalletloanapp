package com.waterelephant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 
 */
@Table(name = "bw_shouxin_product")
public class BwShouxinProduct implements Serializable {
    /**
     * 主键
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 现金分期/商品分期
     */
    private String appTerm;

    private Date applyTime;

    /**
     * 参见《信审系统api文档附录》“申请环节”:授信额度环节 0, 商品分期环节1,  提现环节 2。
     */
    private String applyState;

    /**
     * 银行卡号
     */
    private String bankCardNo;

    /**
     * 银行行号
     */
    private String bankCode;

    /**
     * 渠道信息
     */
    private String channelInfo;

    /**
     * 业务类型
     */
    private String codeBus;

    /**
     * 证件号码
     */
    private String idCard;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String nameCustc;

    /**
     * 合作方申请单号
     */
    private String noBusb;

    /**
     * 订单信息相关
     */
    private String orderInfo;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 是否再次借款
     */
    private String reLoan;

    /**
     * 北京返回审核结果
     */
    private String returnResult;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private String otherOne;

    private String otherTwo;

    /**
     * 设备信息
     */
    private byte[] device;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAppTerm() {
        return appTerm;
    }

    public void setAppTerm(String appTerm) {
        this.appTerm = appTerm;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(String channelInfo) {
        this.channelInfo = channelInfo;
    }

    public String getCodeBus() {
        return codeBus;
    }

    public void setCodeBus(String codeBus) {
        this.codeBus = codeBus;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNameCustc() {
        return nameCustc;
    }

    public void setNameCustc(String nameCustc) {
        this.nameCustc = nameCustc;
    }

    public String getNoBusb() {
        return noBusb;
    }

    public void setNoBusb(String noBusb) {
        this.noBusb = noBusb;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getReLoan() {
        return reLoan;
    }

    public void setReLoan(String reLoan) {
        this.reLoan = reLoan;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOtherOne() {
        return otherOne;
    }

    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }

    public String getOtherTwo() {
        return otherTwo;
    }

    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }

    public byte[] getDevice() {
        return device;
    }

    public void setDevice(byte[] device) {
        this.device = device;
    }
}