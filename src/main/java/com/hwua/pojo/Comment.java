package com.hwua.pojo;


import java.sql.Timestamp;

public class Comment {

  private long id;
  private String reply;
  private String content;
  private String create_Time;
  private String reply_Time;
  private String nick_Name;
  private String state;

  public Comment() {
  }

  public Comment(String reply, String content, String create_Time, String reply_Time, String nick_Name, String state) {
    this.reply = reply;
    this.content = content;
    this.create_Time = create_Time;
    this.reply_Time = reply_Time;
    this.nick_Name = nick_Name;
    this.state = state;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
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

  public String getReply_Time() {
    return reply_Time;
  }

  public void setReply_Time(String reply_Time) {
    this.reply_Time = reply_Time;
  }

  public String getNick_Name() {
    return nick_Name;
  }

  public void setNick_Name(String nick_Name) {
    this.nick_Name = nick_Name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", reply='" + reply + '\'' +
            ", content='" + content + '\'' +
            ", create_Time=" + create_Time +
            ", reply_Time=" + reply_Time +
            ", nick_Name='" + nick_Name + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
