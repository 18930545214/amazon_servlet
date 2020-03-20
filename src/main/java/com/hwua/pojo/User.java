package com.hwua.pojo;

public class User {
    private Long id;
    private String uName;
    private String pwd;
    private String sex;
    private String birthday;
    private String idCard;
    private String email;
    private String mobile;
    private String address;
    private Integer uType;

    public User(String uName, String pwd) {
        this.uName = uName;
        this.pwd = pwd;
    }

    public User(Long id, String uName, String pwd, String sex, String birthday, String idCard, String email, String mobile, String address, Integer uType) {
        this.id = id;
        this.uName = uName;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
        this.idCard = idCard;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.uType = uType;
    }

    public User(String uName, String pwd, String sex, String birthday, String idCard, String email, String mobile, String address, Integer uType) {
        this.uName = uName;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
        this.idCard = idCard;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.uType = uType;
    }
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUType() {
        return uType;
    }

    public void setUType(Integer uType) {
        this.uType = uType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uName='" + uName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", uType=" + uType +
                '}';
    }
}
