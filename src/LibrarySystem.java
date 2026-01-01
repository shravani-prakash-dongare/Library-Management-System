import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();

                    library.addBook(new Book(isbn, title, author, genre));
                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.nextLine();

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
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    for (Book book : library.searchBooks(keyword)) {
                        book.displayInfo();
                    }
                    break;

                case 6:
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextLine();
                    System.out.print("Enter Book ISBN: ");
                    isbn = scanner.nextLine();

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookByIsbn(isbn);

                    if (member != null && book != null && member.borrowBook(book)) {
                        System.out.println("✅ Book borrowed successfully!");
                        System.out.println("Member: " + member.getName());
                        System.out.println("Book: " + book.getTitle());
                    } else {
                        System.out.println("❌ Borrowing failed!");
                    }
                    break;

                case 7:
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextLine();
                    System.out.print("Enter Book ISBN: ");
                    isbn = scanner.nextLine();

                    member = library.findMemberById(memberId);
                    book = library.findBookByIsbn(isbn);

                    if (member != null && book != null && member.returnBook(book)) {
                        System.out.println("✅ Book returned successfully!");
                    } else {
                        System.out.println("❌ Return failed!");
                    }
                    break;

                case 8:
                    System.out.println("Thank you for using the Library System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
