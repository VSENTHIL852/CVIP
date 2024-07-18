import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private List<Book> books;
    private List<User> users;
    private List<Transaction> transactions;
    private int nextBookId;
    private int nextUserId;
    private int nextTransactionId;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        transactions = new ArrayList<>();
        nextBookId = 1;
        nextUserId = 1;
        nextTransactionId = 1;
    }

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();

        librarySystem.addBook("The Hobbit", "J.R.R. Tolkien");
        librarySystem.addBook("To Kill a Mockingbird", "Harper Lee");

        librarySystem.registerUser("Alice");
        librarySystem.registerUser("Bob");

        librarySystem.borrowBook(1, 1); // Alice borrows The Hobbit
        librarySystem.borrowBook(2, 2); // Bob borrows To Kill a Mockingbird

        librarySystem.returnBook(1); // Alice returns The Hobbit

        librarySystem.printBooks();
        librarySystem.printUsers();
    }

    public void addBook(String title, String author) {
        Book book = new Book(nextBookId++, title, author);
        books.add(book);
    }

    public void registerUser(String name) {
        User user = new User(nextUserId++, name);
        users.add(user);
    }

    public void borrowBook(int userId, int bookId) {
        Transaction transaction = new Transaction(nextTransactionId++, userId, bookId);
        transactions.add(transaction);

        Book book = findBook(bookId);
        if (book != null) {
            book.available = false;
        }
    }

    public void returnBook(int transactionId) {
        Transaction transaction = findTransaction(transactionId);
        if (transaction != null) {
            transaction.returned = true;

            Book book = findBook(transaction.bookId);
            if (book != null) {
                book.available = true;
            }
        }
    }

    public Book findBook(int bookId) {
        for (Book book : books) {
            if (book.bookId == bookId) {
                return book;
            }
        }
        return null;
    }

    public Transaction findTransaction(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.transactionId == transactionId) {
                return transaction;
            }
        }
        return null;
    }

    public void printBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println("- " + book.title + " by " + book.author + " (Available: " + (book.available ? "Yes" : "No") + ")");
        }
    }

    public void printUsers() {
        System.out.println("Registered users:");
        for (User user : users) {
            System.out.println("- " + user.name);
        }
    }
}

class Book {
    public int bookId;
    public String title;
    public String author;
    public boolean available;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }
}

class User {
    public int userId;
    public String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Transaction {
    public int transactionId;
    public int userId;
    public int bookId;
    public boolean returned;

    public Transaction(int transactionId, int userId, int bookId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.returned = false;
    }
}
