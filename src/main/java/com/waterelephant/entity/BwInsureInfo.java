package com.waterelephant.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @ClassName: BwInsureInfo
 * @Description: TODO(社保基本信息实体类)
 * @author SongYajun
 * @date 2016年8月30日 下午4:54:55
 *
 */
@Table(name = "bw_insure_info")
public class BwInsureInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 编号
    private String realName; // 姓名
    private String idCard; // 身份证号
    private String sex; // 性别
    private String birthday; // 生日
    private String workStartDay;// 开始工作时间
    private String accProp; // 户口性质
    private String accAddr; // 户口地址
    private String degree; // 学历
    private String cellphone; // 手机号
    private String phone; // 座机号
    private String email; // 邮箱
    private String insureCode; // 社保编号
    private String insureCity; // 社保城市
    private String insureStatus; // 社保缴纳状态
    private String insureMonthMoney; // 社保月申报人民币，缴费基数
    private String comName; // 缴费公司名称
    private String comCode; // 缴费公司机构码
    private String nation; // 民族
    private String liveAddr; // 居住地址
    private String livePostcode; // 居住地址邮编
    private String maritalStatus; // 婚姻状况
    private String workerNation; // 工作性质
    private String startInsureDay; // 参保缴费起始日期
    private String formatAccProp; // 格式化后的户口性质
    private String formatDegree; // 格式化后的学历
    private Long orderId; // 工单id
    private Date createTime; // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkStartDay() {
        return workStartDay;
    }

    public void setWorkStartDay(String workStartDay) {
        this.workStartDay = workStartDay;
    }

    public String getAccProp() {
        return accProp;
    }

    public void setAccProp(String accProp) {
        this.accProp = accProp;
    }

    public String getAccAddr() {
        return accAddr;
    }

    public void setAccAddr(String accAddr) {
        this.accAddr = accAddr;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }

    public String getInsureCity() {
        return insureCity;
    }

    public void setInsureCity(String insureCity) {
        this.insureCity = insureCity;
    }

    public String getInsureStatus() {
        return insureStatus;
    }

    public void setInsureStatus(String insureStatus) {
        this.insureStatus = insureStatus;
    }

    public String getInsureMonthMoney() {
        return insureMonthMoney;
    }

    public void setInsureMonthMoney(String insureMonthMoney) {
        this.insureMonthMoney = insureMonthMoney;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLiveAddr() {
        return liveAddr;
    }

    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    public String getLivePostcode() {
        return livePostcode;
    }

    public void setLivePostcode(String livePostcode) {
        this.livePostcode = livePostcode;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWorkerNation() {
        return workerNation;
    }

    public void setWorkerNation(String workerNation) {
        this.workerNation = workerNation;
    }

    public String getStartInsureDay() {
        return startInsureDay;
    }

    public void setStartInsureDay(String startInsureDay) {
        this.startInsureDay = startInsureDay;
    }

    public String getFormatAccProp() {
        return formatAccProp;
    }

    public void setFormatAccProp(String formatAccProp) {
        this.formatAccProp = formatAccProp;
    }

    public String getFormatDegree() {
        return formatDegree;
    }

    public void setFormatDegree(String formatDegree) {
        this.formatDegree = formatDegree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
