package com.kunlun.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by fk
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class Delivery implements Serializable {

    private static final long serialVersionUID = -3003708737275325374L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户微信code
     */
    private String wxCode;

    /**
     * 收件人
     */
    private String recipients;

    /**
     * 电话
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 客户、买家  BUYER
     * 商家 SELLER
     */
    private String type;

    /**
     * 正常 NORMAL,非正常 UN_NORMAL
     */
    private String status;

    /**
     * 默认地址
     * DEFAULT_ADDRESS    默认地址
     * UN_DEFAULT_ADDRESS 非默认地址
     */
    private String defaultAddress;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", wxCode='" + wxCode + '\'' +
                ", recipients='" + recipients + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", defaultAddress='" + defaultAddress + '\'' +
                '}';
    }
}
