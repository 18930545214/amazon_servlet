package com.hwua.pojo;


public class Category {

  private long id;
  private String name;
  private long parent_Id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getParent_Id() {
    return parent_Id;
  }

  public void setParent_Id(long parent_Id) {
    this.parent_Id = parent_Id;
  }

  @Override
  public String toString() {
    return "Category{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", parent_Id=" + parent_Id +
            '}';
  }
}
