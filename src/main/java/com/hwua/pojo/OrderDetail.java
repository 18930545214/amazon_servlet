package com.hwua.pojo;


public class OrderDetail {

  private long id;
  private long oid;
  private long pid;
  private long quantity;
  private double money;


  public OrderDetail(long oid, long pid, long quantity, double money) {
    this.oid = oid;
    this.pid = pid;
    this.quantity = quantity;
    this.money = money;
  }

  public OrderDetail() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOid() {
    return oid;
  }

  public void setOid(long oid) {
    this.oid = oid;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

}
