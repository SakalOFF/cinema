package com.company.model.entity;

import java.util.Date;
import java.util.List;

public class Session {

    private long id;
    private Film film;
    private int dayId;
    private Date startTime;
    private List<Seat> occupiedSeats;
    private int seatsCounter;

    public Session(Film film, int dayId, Date startTime) {
        this.film = film;
        this.dayId = dayId;
        this.startTime = startTime;
    }

    public Session(long id, int seatsCounter, Date startTime, int dayId) {
        this.id = id;
        this.seatsCounter = seatsCounter;
        this.startTime = startTime;
        this.dayId = dayId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<Seat> getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(List<Seat> occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public int getSeatsCounter() {
        return seatsCounter;
    }

    public void setSeatsCounter(int seatsCounter) {
        this.seatsCounter = seatsCounter;
    }
}
