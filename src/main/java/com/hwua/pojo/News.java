package com.hwua.pojo;


public class News {

  private long id;
  private String title;
  private String content;
  private String create_Time;

  public News() {
  }

  public News(long id, String title, String content, String create_Time) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.create_Time = create_Time;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreate_Time() {
    return create_Time;
  }

  public void setCreate_Time(String create_Time) {
    this.create_Time = create_Time;
  }

  @Override
  public String toString() {
    return "News{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", create_Time='" + create_Time + '\'' +
            '}';
  }
}
