import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookStoreApp {

    private static final BookDao bookDao = new BookDaoImpl(JDBConnection.getConnection());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("""
                    Welcome to my Bookstore
                    Please enter a number
                    1 - Add a new book
                    2 - Update a book
                    3 - Delete a Book
                    4 - Display a Book
                    5 - Display all books
                    6 - Exit the program
                    """);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    updateBook(sc);
                    break;
                case 3:
                    deleteBook(sc);
                    break;
                case 4:
                    getBookById(sc);
                    break;
                case 5:
                    displayBooks(sc);
                    break;
                case 6:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice !=5);
    }

    private static void getBookById(Scanner sc) {
        System.out.println("Please enter a book id");
        int bookId = sc.nextInt();
        Book book = bookDao.getBookById(bookId);
        if(book == null){
            System.out.println("Book not found");
        }
        System.out.println(book);
    }

    private static void deleteBook(Scanner sc) {
        System.out.println("Please enter a book id");
        int bookId = sc.nextInt();
        bookDao.deleteBook(bookId);
        System.out.println("Book deleted successfully");
    }

    private static void displayBooks(Scanner sc) {
        for (Book book : bookDao.getAllBooks()){
            System.out.println(book);
        }
    }

    private static void updateBook(Scanner sc) {
        System.out.println("Please enter a book id to update");
        int bookId = sc.nextInt();
        sc.nextLine();
        Book book = bookDao.getBookById(bookId);
        if(book == null){
            System.out.println("Book not found");
        } else {
            System.out.println("Please enter a new title or press enter to skip ");
            String title = sc.nextLine();
            System.out.println("Please enter a new author or press enter to skip ");
            String author = sc.nextLine();
            System.out.println("Please enter a new genre or press enter to skip ");
            String genre = sc.nextLine();
            System.out.println("Please enter a new price or press 0 to skip ");
            double priceStr = sc.nextDouble();
            book.setTitle(title.isEmpty() ? book.getTitle() : title);

            book.setAuthor(author.isEmpty() ? book.getAuthor() : author);
            book.setGenre(genre.isEmpty() ? book.getGenre() : genre);
            book.setPrice(priceStr == 0 ? book.getPrice() : priceStr);

            bookDao.updateBook(book);
            System.out.println("Book updated successfully");
        }
    }

    private static void addBook(Scanner sc) {
        System.out.println("please enter a book title");
        String title = sc.nextLine();
        System.out.println("Please enter an author");
        String author = sc.nextLine();
        System.out.println("Please enter a genre");
        String genre = sc.nextLine();
        System.out.println("Please enter a book price");
        double price = sc.nextDouble();
        Book book = new Book(title,author,genre,price);
        bookDao.addBook(book);
        System.out.println("Book added successfully");
    }

}
