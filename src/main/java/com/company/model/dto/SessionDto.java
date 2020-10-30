package com.company.model.dto;

import com.company.model.entity.Session;

public class SessionDto {

    public Session session;

    public long film_id;

    public SessionDto(Session session, long film_id) {
        this.session = session;
        this.film_id = film_id;
    }
}
