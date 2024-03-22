package controller;
import entity.Author;
import entity.Book;
import model.BookModel;

import javax.swing.*;

public class BookController {
    private BookModel bookModel;

    public BookController(){
        bookModel = new BookModel();
    }

    public String listBooks() {
        StringBuilder list = new StringBuilder();
        list.append("......:::::::Books List:::::::......");
        if (!bookModel.findAll().isEmpty()) {
            for (Object objBook : bookModel.findAll()) {
                Book book = (Book) objBook;
                list.append("\nId: ").append(book.getId()).append("\nTitle: ").append(book.getTitle())
                        .append("\nYear: ").append(book.getYear())
                        .append("\nPrice: ").append(book.getPrice())
                        .append("\nIdAuthor: ").append(book.getIdAuthor());
            }
            return list.toString();
        }
        return list.append("\nThere are no books in this list").toString();
    }
    public void updateBook() {
        StringBuilder message = new StringBuilder();
        message.append(listBooks());
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, message.append("\n\nEnter id to update").toString()));
            if (bookModel.findById(id) != null) {
                Book book = (Book) bookModel.findById(id);
                String title = JOptionPane.showInputDialog(null, "Enter new book title", book.getTitle());
                int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new book year", book.getYear()));
                double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new book price", book.getPrice()));
                int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new id author for the book", book.getIdAuthor()));
                book.setTitle(title);
                book.setYear(year);
                book.setPrice(price);
                book.setIdAuthor(idAuthor);
                if (bookModel.update(book)) {
                    JOptionPane.showMessageDialog(null, "Book updated Successfully\n" + book);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update the book");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book doesn't exist in the database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void deleteBook() {
        StringBuilder message = new StringBuilder();
        message.append(listBooks());
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, message.append("\n\nEnter id to delete").toString()));
            if (bookModel.delete(id)) {
                JOptionPane.showMessageDialog(null, "Book deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Book doesn't exist in the database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void insertBook() {
        try{
            String title = JOptionPane.showInputDialog(null, "Enter the book title");
            int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the book year"));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the book price"));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new id author for the book"));
            Book book = new Book();
            book.setTitle(title);
            book.setYear(year);
            book.setPrice(price);
            book.setIdAuthor(idAuthor);
            JOptionPane.showMessageDialog(null, bookModel.insert(book).toString());
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Book findBookById() {
        Book book = null;
        try {
            int toSearch = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id to search"));
            if (bookModel.findById(toSearch) != null) {
                book = (Book) bookModel.findById(toSearch);
            } else {
                JOptionPane.showMessageDialog(null, "Book doesn't exist in database");
            }
        } catch (Exception e) {
            System.out.println("findBookById: no valid number\n" + e.getMessage());
        }

        return book;
    }

    public String findBookByName() {
        StringBuilder list = new StringBuilder();
        list.append("......:::::::Books List:::::::......");

        String toSearch = JOptionPane.showInputDialog(null, "Enter the name to search");
        if (!bookModel.findByName(toSearch).isEmpty()) {
            for (Object objBook : bookModel.findByName(toSearch)) {
                Book book = (Book) objBook;
                list.append("\nId: ").append(book.getId()).append("\nTitle: ").append(book.getTitle())
                        .append("\nYear: ").append(book.getYear())
                        .append("\nPrice: ").append(book.getPrice())
                        .append("\nAuthor id: ").append(book.getIdAuthor());
            }
            return list.toString();
        }

        return list.append("\nThere are no books in this list").toString();
    }

}
