import controller.AuthorController;
import controller.BookController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String optionGeneral;
        String messageGeneral = """
                ....::::::MENU::::::....
                1. Authors menu.
                2. Books menu.
                3. Relation Authors-books menu.
                4. Exit.
                """;
        do {
            optionGeneral = JOptionPane.showInputDialog(null,messageGeneral);
            if (optionGeneral == null) {
                break;
            }
            switch (optionGeneral){
                //AuthorsController
                case "1":
                    AuthorController controller = new AuthorController();
                    String option;
                    String message = """
                ....::::::AUTHORS MENU::::::....
                1. List authors.
                2. Insert author.
                3. Update author.
                4. Delete author.
                5. Get author By name.
                6. Get author By id.
                7. Show all books.
                8. Exit
                """;
                    do {
                        option = JOptionPane.showInputDialog(null, message);
                        if (option == null) {
                            break;
                        }

                        switch (option) {
                            case "1":
                                JOptionPane.showMessageDialog(null,controller.listAuthors());
                                break;
                            case "2":
                                controller.insertAuthor();
                                break;
                            case "3":
                                controller.updateAuthor();
                                break;
                            case "4":
                                controller.deleteAuthor();
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null,controller.findAuthorByName());
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null,controller.findAuthorById());
                                break;
                            case "7":
                                controller.getAllbooks();
                                break;
                            case "8":
                                JOptionPane.showMessageDialog(null, "You are leaving the menu...");
                                break;
                            default:
                                break;
                        }

                    } while (!option.equals("8"));
                    break;
                case "2":
                    //BooksController
                    BookController bookController = new BookController();
                    String optionBook;
                    String messageBook = """
                ....::::::BOOKS MENU::::::....
                1. List Books.
                2. Insert books.
                3. Update books.
                4. Delete books.
                5. Get books By title.
                6. Get books By id.
                7. Exit
                """;
                    do {
                        optionBook = JOptionPane.showInputDialog(null, messageBook);
                        if (optionBook == null) {
                            break;
                        }

                        switch (optionBook) {
                            case "1":
                                JOptionPane.showMessageDialog(null,bookController.listBooks());
                                break;
                            case "2":
                                bookController.insertBook();
                                break;
                            case "3":
                                bookController.updateBook();
                                break;
                            case "4":
                                bookController.deleteBook();
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null,bookController.findBookByTitle());
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null,bookController.findBookById());
                                break;
                            case "7":
                                JOptionPane.showMessageDialog(null, "You are leaving the menu...");
                                break;
                            default:
                                break;
                        }

                    } while (!optionBook.equals("7"));
                    break;
                case "3":
                    System.out.println("Relation...");
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "You are leaving the menu...");
                    break;
            }
        }while (!optionGeneral.equals("4"));
    }
}