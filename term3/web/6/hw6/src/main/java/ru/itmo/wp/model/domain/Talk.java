package ru.itmo.wp.model.domain;

import java.util.Date;

public class Talk {

    private long id;
    private String sourceId;
    private String targetId;
    private String text;
    private Date creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getText() {
        return text;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
