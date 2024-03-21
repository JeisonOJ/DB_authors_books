import controller.AuthorController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
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
                7. Exit
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
                    JOptionPane.showMessageDialog(null, "You are leaving the menu...");
                    break;
                default:
                    break;
            }

        } while (!option.equals("7"));
    }
}