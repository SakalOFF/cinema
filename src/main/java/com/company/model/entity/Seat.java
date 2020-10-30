package com.company.model.entity;

public class Seat {

    private long id;
    private int number;
    private long userId;
    private long sessionId;

    public Seat(long id, int number, long userId, long sessionId) {
        this.id = id;
        this.number = number;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
