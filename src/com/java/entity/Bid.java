package com.java.entity;

import java.util.Date;

public class Bid {
    private Integer id;

    private String title;

    private Date date;

    private String bidname;

    private String bidaddress;

    private String bidamount;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBidname() {
        return bidname;
    }

    public void setBidname(String bidname) {
        this.bidname = bidname == null ? null : bidname.trim();
    }

    public String getBidaddress() {
        return bidaddress;
    }

    public void setBidaddress(String bidaddress) {
        this.bidaddress = bidaddress == null ? null : bidaddress.trim();
    }

    public String getBidamount() {
        return bidamount;
    }

    public void setBidamount(String bidamount) {
        this.bidamount = bidamount == null ? null : bidamount.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}