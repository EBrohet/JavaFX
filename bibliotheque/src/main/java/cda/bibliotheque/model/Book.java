package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private LocalDate release_date;
    private boolean isAvailable;
    private List<Author> authors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();

    public Book() {
    }

    public Book(int id, String title, Date release_date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.release_date = release_date.toLocalDate();
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, LocalDate release_date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, LocalDate release_date, boolean isAvailable, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(String title, LocalDate release_date, boolean isAvailable, List<Author> authors, List<Genre> genres) {
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
        this.authors = authors;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return release_date;
    }

    public Date getReleaseDate_Date() {
        return Date.valueOf(release_date);
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate release_date) {
        this.release_date = release_date;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toStringAuthors() {
        String authorString = "";
        for(Author a : authors) {
            authorString = authorString + a.toString() + "\n";
        }
        return authorString;
    }

    public String toStringGenres() {
        String genreString = "";
        for(Genre g : genres) {
            genreString = genreString + g.toString() + "\n";
        }
        return genreString;
    }

    public void setAuthors(List<Author> authors){
        this.authors = authors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return title;
    }
}