package model;

import database.CRUD;
import database.ConfigDB;
import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Book book = (Book) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO book (title, year,price,idauthor) values (?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getYear());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getIdAuthor());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                    System.out.println("Insert: Book inserted successfully");
                }
            }
        } catch (SQLException e) {
            System.out.println("Insert: Error in database\n" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return book;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdated = false;
        Book book = (Book) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE book SET title = ?, year = ?, price = ?, idauthor = ? WHERE idbook = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,book.getTitle());
            ps.setInt(2,book.getYear());
            ps.setDouble(3,book.getPrice());
            ps.setInt(4,book.getIdAuthor());
            ps.setInt(5,book.getId());
            if (ps.executeUpdate()>0){
                System.out.println("Update: Book Updated successfully");
                isUpdated = true;
            }
        }catch (SQLException e){
            System.out.println("Update: Error in database\n"+e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "DELETE FROM book where idbook=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                System.out.println("Delete: Book deleted successfully");
                isDelete = true;
            }
            ConfigDB.closeConnection();
        } catch (SQLException e) {
            System.out.println("Delete: Error in database\n" + e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        List<Object> books = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM book;";
        try {
            System.out.println("FindAll: Getting all of the database");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(rs.getInt("idbook"),rs.getString("title"), rs.getInt("year"),rs.getDouble("price"),rs.getInt("idauthor")));
            }
            ConfigDB.closeConnection();
        } catch (SQLException error) {
            System.out.println("FindAll: Error in database\n" + error);
        }
        return books;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * from book where idbook=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //if for single result
            if (rs.next()) {
                return new Book(rs.getInt("idbook"),rs.getString("title"), rs.getInt("year"),rs.getDouble("price"),rs.getInt("idauthor"));
            }
        } catch (SQLException e) {
            System.out.println("FindById: Error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return null;
    }

    @Override
    public List<Object> findByName(String name) {
        List<Object> booksFound = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * from book where title like ?;";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                booksFound.add(new Book(rs.getInt("idbook"),rs.getString("title"), rs.getInt("year"),rs.getDouble("price"),rs.getInt("idauthor")));
            }
        } catch (SQLException e) {
            System.out.println("FindByName: Error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return booksFound;
    }
}
