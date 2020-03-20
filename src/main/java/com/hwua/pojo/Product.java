package com.hwua.pojo;


public class Product {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private Long stock;
  private Long major_Id;
  private Long minor_Id;
  private String img_Source;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public Long getMajor_Id() {
    return major_Id;
  }

  public void setMajor_Id(Long major_Id) {
    this.major_Id = major_Id;
  }

  public Long getMinor_Id() {
    return minor_Id;
  }

  public void setMinor_Id(Long minor_Id) {
    this.minor_Id = minor_Id;
  }

  public String getImg_Source() {
    return img_Source;
  }

  public void setImg_Source(String img_Source) {
    this.img_Source = img_Source;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", stock=" + stock +
            ", major_Id=" + major_Id +
            ", minor_Id=" + minor_Id +
            ", img_Source='" + img_Source + '\'' +
            '}';
  }
}
