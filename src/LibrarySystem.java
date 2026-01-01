import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample data (optional – helps testing)
        library.addBook(new Book("978-0-262-03384-8", "Introduction to Algorithms", "Thomas Cormen", "Computer Science"));
        library.addBook(new Book("978-0-13-468599-1", "Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("978-1-56619-909-4", "Clean Code", "Robert Martin", "Programming"));

        Member sampleMember = new Member("M001", "Alice Johnson", "alice@email.com");
        library.addMember(sampleMember);

        int choice;

        do {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Register New Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Search Books");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.println("\n=== ADD NEW BOOK ===");
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    library.addBook(new Book(isbn, title, author, genre));
                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:
                    System.out.println("\n=== REGISTER NEW MEMBER ===");
                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();

                    library.addMember(new Member(memberId, name, contact));
                    System.out.println("✅ Member registered successfully!");
                    break;

                case 3:
                    library.displayAllBooks();
                    break;

                case 4:
                    library.displayAvailableBooks();
                    break;

                case 5:
                    System.out.println("\n=== SEARCH BOOKS ===");
                    System.out.print("Enter search keyword: ");
                    String keyword = sc.nextLine();

                    ArrayList<Book> results = library.searchBooks(keyword);

                    if (results.isEmpty()) {
                        System.out.println("No books found!");
                    } else {
                        System.out.println("\nSearch Results:");
                        for (Book book : results) {
                            book.displayInfo();
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n=== BORROW BOOK ===");
                    System.out.print("Enter Member ID: ");
                    String borrowMemberId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String borrowIsbn = sc.nextLine();

                    Member member = library.findMemberById(borrowMemberId);
                    Book bookToBorrow = library.findBookByIsbn(borrowIsbn);

                    if (member == null) {
                        System.out.println("❌ Member not found!");
                    } else if (bookToBorrow == null) {
                        System.out.println("❌ Book not found!");
                    } else if (member.borrowBook(bookToBorrow)) {
                        System.out.println("✅ Book borrowed successfully!");
                        System.out.println("Member: " + member.getName());
                        System.out.println("Book: " + bookToBorrow.getTitle());
                    } else {
                        System.out.println("❌ Book is not available!");
                    }
                    break;

                case 7:
                    System.out.println("\n=== RETURN BOOK ===");
                    System.out.print("Enter Member ID: ");
                    String returnMemberId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String returnIsbn = sc.nextLine();

                    Member returnMember = library.findMemberById(returnMemberId);
                    Book bookToReturn = library.findBookByIsbn(returnIsbn);

                    if (returnMember == null) {
                        System.out.println("❌ Member not found!");
                    } else if (bookToReturn == null) {
                        System.out.println("❌ Book not found!");
                    } else if (returnMember.returnBook(bookToReturn)) {
                        System.out.println("✅ Book returned successfully!");
                        System.out.println("Book: " + bookToReturn.getTitle());
                    } else {
                        System.out.println("❌ This member did not borrow that book!");
                    }
                    break;

                case 8:
                    System.out.println("👋 Exiting Library System. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }

        } while (choice != 8);

        sc.close();
    }
}
