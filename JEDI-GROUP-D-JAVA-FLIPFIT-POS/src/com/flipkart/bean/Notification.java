package com.flipkart.bean;

public class Notification {
    private Integer notificationId;
    private String content;
    private String bookingStatus;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", content='" + content + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}
