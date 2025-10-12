package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Borrow;
import cda.bibliotheque.model.Client;

public class BorrowDAO {
    private Connection connection;

    public BorrowDAO(){
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Borrow> getAllBorrow(){
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT book_id, client_id, end_date, start_date, isDone, title, release_date, isAvailable, firstName, lastName, email FROM borrow ";
        sql = sql + "INNER JOIN books ON books.id = book_id ";
        sql = sql + "INNER JOIN client ON client.id = client_id";
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Client client = new Client(
                    rs.getInt("client_id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email")
                );
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getDate("release_date"),
                    rs.getBoolean("isAvailable")
                );

                borrows.add(new Borrow(
                    client,
                    book,
                    rs.getDate("end_date").toLocalDate(),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getBoolean("isDone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur de la récupération dans getAllBorrow -> " + e.getMessage());
        }
        return borrows;
    }

    public void addBorrow(Borrow borrow){
        String sql = "INSERT INTO client(book_id, client_id, endDate, startDate, isDone) VALUES (?,?,?,?,?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(2, borrow.getClient().getId());
            pstmt.setDate(3, borrow.getEndDate_Date());
            pstmt.setDate(4, borrow.getStartDate_Date());
            pstmt.setBoolean(5, borrow.getIsDone());
            pstmt.executeUpdate();
            System.out.println("Ajout de l'emprunt effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout dans addBorrow -> " + e.getMessage());
        }
    }

    public void updateBorrow(Borrow borrow){
        String sql = "UPDATE borrow SET book_id = ?, client_id = ?, endDate = ?, startDate = ?, isDone = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(2, borrow.getClient().getId());
            pstmt.setDate(3, borrow.getEndDate_Date());
            pstmt.setDate(4, borrow.getStartDate_Date());
            pstmt.setBoolean(5, borrow.getIsDone());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Emprunt mis à jour");
            }else{
                System.out.println("Emprunt n'existe pas");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification dans updateBorrow -> " + e.getMessage());
        }
    }

    public void deleteBorrow(Borrow borrow){
        String sql = "DELETE FROM borrow WHERE book_id = ? AND client_id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(1, borrow.getClient().getId());
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                System.out.println("Emprunt supprimé avec succès");
            } else {
                System.out.println("Emprunt n'existe pas");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression dans deleteBorrow -> " + e.getMessage());
        }
    }
}
