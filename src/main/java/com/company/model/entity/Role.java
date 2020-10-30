package com.company.model.entity;

public enum Role {
    USER(1), ADMIN(2);

    private int id;

    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
