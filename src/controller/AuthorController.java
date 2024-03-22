package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {

    private AuthorModel authorModel;

    public AuthorController() {
        authorModel = new AuthorModel();
    }

    public String listAuthors() {
        StringBuilder list = new StringBuilder();
        list.append("......:::::::Authors List:::::::......");
        if (!authorModel.findAll().isEmpty()) {
            for (Object objAuthor : authorModel.findAll()) {
                Author author = (Author) objAuthor;
                list.append("\nId: ").append(author.getId()).append("\nName: ").append(author.getName()).append("\nNationality: ").append(author.getNationality());

            }
            return list.toString();
        }
        return list.append("\nThere are no authors in this list").toString();

    }

    public void updateAuthor() {
        StringBuilder message = new StringBuilder();
        message.append(listAuthors());
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, message.append("\n\nEnter id to update").toString()));
            if (authorModel.findById(id) != null) {
                Author author = (Author) authorModel.findById(id);
                String name = JOptionPane.showInputDialog(null, "Enter new author name", author.getName());
                String nationality = JOptionPane.showInputDialog(null, "Enter new author nationality", author.getNationality());
                author.setName(name);
                author.setNationality(nationality);
                if (authorModel.update(author)) {
                    JOptionPane.showMessageDialog(null, "Author updated Successfully\n" + author);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update the author");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Author doesn't exist in the database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void deleteAuthor() {
        StringBuilder message = new StringBuilder();
        message.append(listAuthors());
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, message.append("\n\nEnter id to delete").toString()));
            if (authorModel.delete(id)) {
                JOptionPane.showMessageDialog(null, "Author deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Author doesn't exist in the database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void insertAuthor() {
        String name = JOptionPane.showInputDialog(null, "Enter the author name");
        String nationality = JOptionPane.showInputDialog(null, "Enter the author nationality");
        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);
        JOptionPane.showMessageDialog(null, authorModel.insert(author).toString());
    }

    public Author findAuthorById() {
        Author author = null;
        try {
            int toSearch = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id to search"));
            if (authorModel.findById(toSearch) != null) {
                author = (Author) authorModel.findById(toSearch);
            } else {
                JOptionPane.showMessageDialog(null, "Author doesn't exist in database");
            }
        } catch (Exception e) {
            System.out.println("FindAuthorById: no valid number\n" + e.getMessage());
        }

        return author;
    }

    public String findAuthorByName() {
        StringBuilder list = new StringBuilder();
        list.append("......:::::::Authors List:::::::......");

        String toSearch = JOptionPane.showInputDialog(null, "Enter the name to search");
        if (!authorModel.findByName(toSearch).isEmpty()) {
            for (Object objAuthor : authorModel.findByName(toSearch)) {
                Author author = (Author) objAuthor;
                list.append("\nId: ").append(author.getId()).append("\nName: ").append(author.getName()).append("\nNationality: ").append(author.getNationality());

            }
            return list.toString();
        }

        return list.append("\nThere are no authors in this list").toString();
    }

    public String getAllbooks(){
        try {
            int toSearch = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id to search"));
            List<Object> objectList = authorModel.findAllBooks(toSearch);
            if (!objectList.isEmpty()) {
                for (Object object: objectList){
                    Book book = (Book) object;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Author doesn't exist in database");
            }
        } catch (Exception e) {
            System.out.println("getAllbooks: no valid number\n" + e.getMessage());
        }

    }

}
