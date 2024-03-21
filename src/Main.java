import controller.AuthorController;

public class Main {
    public static void main(String[] args) {
        AuthorController controller = new AuthorController();
//        System.out.println(controller.listAuthors());
//        controller.deleteAuthor();
//        controller.insertAuthor();
//        System.out.println(controller.findAuthorById());
        System.out.println(controller.findAuthorByName());
    }
}