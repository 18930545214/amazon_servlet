package com.hwua.pojo;


public class ShopCart {

  private long id;
  private long pid;
  private long pum;
  private long uid;

  public ShopCart(long pid, long pum, long uid) {
    this.pid = pid;
    this.pum = pum;
    this.uid = uid;
  }

  public ShopCart() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

  public long getPum() {
    return pum;
  }

  public void setPum(long pum) {
    this.pum = pum;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  @Override
  public String toString() {
    return "ShopCart{" +
            "id=" + id +
            ", pid=" + pid +
            ", pum=" + pum +
            ", uid=" + uid +
            '}';
  }
}
