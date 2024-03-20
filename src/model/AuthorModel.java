package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

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
        try{
            PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,author.getName());
            ps.setString(2, author.getNationality());
            int rows = ps.executeUpdate();
            if (rows > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    author.setId(rs.getInt(1));
                    System.out.println("Insert: Author inserted successfully");
                }
            }
        }catch (SQLException e){
            System.out.println("Insert: Error in database\n"+e.getMessage());
        }
        return author;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "DELETE FROM author where idauthor=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            if(ps.executeUpdate()>0){
                System.out.println("Delete: Author deleted successfully");
                isDelete = true;
            }
            ConfigDB.closeConnection();
        } catch (SQLException e) {
            System.out.println("Delete: Error in database\n"+e.getMessage());
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
        return null;
    }

    @Override
    public Object findByName(String name) {
        return null;
    }
}
