package model;

import java.time.*;

public class Film {
    private Long filmId;
    private String filmName;
    private LocalDate filmDate;
    private LocalTime filmTime;
    private Double filmCost;

    public Film(){

    }

    public Film(Long filmId, String filmName, LocalDate filmDate, LocalTime filmTime, Double filmCost) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmDate = filmDate;
        this.filmTime = filmTime;
        this.filmCost = filmCost;
    }

    public void setCost(Double filmCost) {
        this.filmCost = filmCost;
    }

    public Double getCost() {
        return filmCost;
    }

    public void setFilmDate(LocalDate filmDate) {
        this.filmDate = filmDate;
    }

    public void setFilmTime(LocalTime filmTime) {
        this.filmTime = filmTime;
    }

    public LocalDate getFilmDate() {
        return filmDate;
    }

    public LocalTime getFilmTime() {
        return filmTime;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Long getFilmId() {
        return filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    @Override
    public String toString() {
       // return System.out.format("%32s%10d%16s", filmId, filmName, filmDate).toString();
        return  "filmId: " + filmId + " | " +
                "filmName: '" + filmName + '\'' + " | " +
                "filmDate: " + filmDate + " | " +
                "filmTime: " + filmTime + " | " +
                "filmCost: " + filmCost + " | ";
    }
}
