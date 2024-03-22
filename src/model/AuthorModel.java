package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Author author = (Author) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO author (name, nationality) values (?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getNationality());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    author.setId(rs.getInt(1));
                    System.out.println("Insert: Author inserted successfully");
                }
            }
        } catch (SQLException e) {
            System.out.println("Insert: Error in database\n" + e.getMessage());
        }
        return author;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdated = false;
        Author author = (Author)object;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE author SET name = ?, nationality = ? WHERE idauthor = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,author.getName());
            ps.setString(2,author.getNationality());
            ps.setInt(3,author.getId());
            if (ps.executeUpdate()>0){
                System.out.println("Update: Author Updated successfully");
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
        String sql = "DELETE FROM author where idauthor=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                System.out.println("Delete: Author deleted successfully");
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
        List<Object> authors = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM author;";
        try {
            System.out.println("FindAll: Getting all of the database");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                authors.add(new Author(rs.getInt("idauthor"), rs.getString("name"), rs.getString("nationality")));
            }
            ConfigDB.closeConnection();
        } catch (SQLException error) {
            System.out.println("FindAll: Error in database\n" + error);
        }
        return authors;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * from author where idauthor=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //if for single result
            if (rs.next()) {
                return new Author(rs.getInt("idauthor"), rs.getString("name"), rs.getString("nationality"));
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
        List<Object> authorsFound = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * from author where name like ?;";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                authorsFound.add(new Author(rs.getInt("idauthor"), rs.getString("name"), rs.getString("nationality")));
            }
        } catch (SQLException e) {
            System.out.println("FindByName: Error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return authorsFound;
    }

    public List<Object> findAllBooks(int id) {
        List<Object> booksFound = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "select * from author inner join book where author.idauthor = book.idauthor and author.idauthor = ?;";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                booksFound.add(new Book(rs.getInt("idbook"),rs.getString("title"), rs.getInt("year"),rs.getDouble("price"),rs.getInt("idauthor")));
            }
        } catch (SQLException e) {
            System.out.println("findAllBooks: Error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return booksFound;
    }

}
