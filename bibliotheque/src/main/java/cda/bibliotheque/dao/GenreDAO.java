package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Genre;

public class GenreDAO {
    private Connection connection;

    public GenreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Genre> getAllGenre(){
        String sql  = "SELECT id, label FROM gender";
        List<Genre> genres = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rSet = stmt.executeQuery(sql)) {
            while(rSet.next()){
                genres.add(new Genre(
                    rSet.getInt("id"),
                    rSet.getString("label")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur de la récupération dans getAllGenre -> " + e.getMessage());
            
        }
        return genres;
    }

    public void addGenre(Genre genre) {
        String sql = "INSERT INTO gender(label) VALUES (?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, genre.getLabel());
            pstmt.executeUpdate();
            System.out.println("Ajout du genre effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout dans addGenre -> " + e.getMessage());
        }
    }

    public void updateGenre(Genre genre){
        String sql = "UPDATE gender SET label = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, genre.getLabel());
            pstmt.setInt(2, genre.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Genre mis à jour");
            }else{
                System.out.println("Genre n'existe pas");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification dans updateGenre -> " + e.getMessage());
        }
    }

    public void deleteGenre(int id){
        String sql = "DELETE FROM gender WHERE id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                System.out.println("Genre supprimé avec succès");
            } else {
                System.out.println("Genre n'existe pas");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression dans deleteGenre -> " + e.getMessage());
        }
    }

    
}
