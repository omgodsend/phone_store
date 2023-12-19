import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int bookId);

    Book getBookById(int bookId);

    List<Book> getAllBooks();
}
