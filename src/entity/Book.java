package entity;

public class Book{
    private int id;
    private String title;
    private int year;
    private double price;
    private int idAuthor;

    public Book(){}
    public Book(int id, String title, int year, double price, int idAuthor) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.idAuthor = idAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", idAuthor=" + idAuthor +
                '}';
    }
}
