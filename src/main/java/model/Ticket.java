package model;

public class Ticket {
    private Long id;
    private Long userId;
    private String filmName;
    private Long filmId;
    private Long rowNumber;
    private Long colNumber;
    private Double cost;
    private boolean isPurchased;
    public Ticket(){

    }

    public Ticket(Long id, Long userId, String filmName, Long filmId, Long rowNumber, Long colNumber, Double cost, boolean isPurchased) {
        this.id = id;
        this.userId = userId;
        this.filmName = filmName;
        this.filmId = filmId;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.cost = cost;
        this.isPurchased = isPurchased;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setColNumber(Long colNumber) {
        this.colNumber = colNumber;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public Long getColNumber() {
        return colNumber;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFilmName() {
        return filmName;
    }

    public Double getCost() {
        return cost;
    }

    public boolean getIsPurchased() {
        return isPurchased;
    }

    @Override
    public String toString() {
        return "ticketId: " + id + " | " +
                "userId: " + userId + " | " +
                "filmName: '" + filmName + '\'' + " | " +
                "filmId: " + filmId + " | " +
                "rowNumber: " + rowNumber + " | " +
                "colNumber: " + colNumber + " | " +
                "cost: " + cost + " | " +
                "isPurchased: " + isPurchased + " | ";
    }
}
