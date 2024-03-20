package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {

    private AuthorModel authorModel;

    public AuthorController() {
        authorModel = new AuthorModel();
    }

    public String listAuthors(){
        StringBuilder list = new StringBuilder();
        list.append("\n...::Authors List::...");
        if (!authorModel.findAll().isEmpty()){
            for (Object objAuthor: authorModel.findAll()){
                Author author = (Author) objAuthor;
                list.append("\nId: ").append(author.getId()).append("\nName: ").append(author.getName()).append("\nNationality: ").append(author.getNationality());

            }
            return list.toString();
        }
        return list.append("\nThere are no authors in this list").toString();

    }

    public void deleteAuthor(){
        StringBuilder message = new StringBuilder();
        message.append(listAuthors());
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,message.append("\n\nEnter id to delete").toString()));
        if (authorModel.delete(id)){
            JOptionPane.showMessageDialog(null,"Author deleted Successfully");
        }else {
            JOptionPane.showMessageDialog(null,"Author doesn't exist in the database");
        }
    }

    public void insertAuthor(){
        String name = JOptionPane.showInputDialog(null,"Enter the author name");
        String nationality = JOptionPane.showInputDialog(null,"Enter the author nationality");
        Author author = new Author();
        author.setName(name);
        author.setName(nationality);
        JOptionPane.showMessageDialog(null,authorModel.insert(author).toString());
    }

}
