package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
public class FeelingPlaceRequest {
    @NotBlank(message = "Topic must be not empty")
    private String topic;
    @NotBlank(message = "Content must be not empty")
    private String content;
    @NotBlank(message = "User name must be not empty")
    private String userUid;
    @NotBlank(message = "Place must be not empty")
    private String placeUid;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getPlaceUid() {
        return placeUid;
    }

    public void setPlaceUid(String placeUid) {
        this.placeUid = placeUid;
    }
}
