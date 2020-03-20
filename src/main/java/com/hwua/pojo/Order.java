package com.hwua.pojo;


import java.util.List;

public class Order {

  private long id;
  private long uid;
  private String uName;
  private String uAddress;
  private String create_Time;
  private double money;
  private long status;
  private long type;
  private List<Product> products;
  private List<OrderDetail> orderDetails;

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public Order() {
  }

  public Order(long uid, String uName, String uAddress, String create_Time, double money, long status, long type) {
    this.uid = uid;
    this.uName = uName;
    this.uAddress = uAddress;
    this.create_Time = create_Time;
    this.money = money;
    this.status = status;
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getuName() {
    return uName;
  }

  public void setuName(String uName) {
    this.uName = uName;
  }

  public String getuAddress() {
    return uAddress;
  }

  public void setuAddress(String uAddress) {
    this.uAddress = uAddress;
  }

  public String getCreate_Time() {
    return create_Time;
  }

  public void setCreate_Time(String create_Time) {
    this.create_Time = create_Time;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", uid=" + uid +
            ", uName='" + uName + '\'' +
            ", uAddress='" + uAddress + '\'' +
            ", create_Time='" + create_Time + '\'' +
            ", money=" + money +
            ", status=" + status +
            ", type=" + type +
            '}';
  }
}
