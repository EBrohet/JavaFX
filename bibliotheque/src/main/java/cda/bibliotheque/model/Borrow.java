package cda.bibliotheque.model;

import java.time.LocalDate;
import java.sql.Date;

public class Borrow {
    private Client client;
    private Book book;
    private LocalDate endDate;
    private LocalDate startDate;
    private boolean isDone;

    public Borrow() {
    }

    public Borrow(Client client, Book book, LocalDate endDate, LocalDate startDate, boolean isDone) {
        this.client = client;
        this.book = book;
        this.endDate = endDate;
        this.startDate = startDate;
        this.isDone = isDone;
    }

    public Borrow(Client client, Book book, Date endDate, Date startDate, boolean isDone) {
        this.client = client;
        this.book = book;
        this.endDate = endDate.toLocalDate();
        this.startDate = startDate.toLocalDate();
        this.isDone = isDone;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Date getEndDate_Date() {
        return Date.valueOf(endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Date getStartDate_Date() {
        return Date.valueOf(startDate);
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
