import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class Book {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String category;
    private boolean borrowed;
    private int borrowingPeriod;
    private String borrowingDate;

    public Book(String name, String category) {
        this.id = idCounter++;
        this.name = name;
        this.category = category;
        this.borrowed = false;
       this.borrowingPeriod = 0;
        this.borrowingDate = "";
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getCategory() {return category;}

    public boolean isBorrowed() {return borrowed;}

    public int getBorrowingPeriod() {return borrowingPeriod;}

    public String getBorrowingDate() {return borrowingDate;}

    public void borrow(int period) {
        this.borrowed = true;
        this.borrowingPeriod = period;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        this.borrowingDate = dateFormat.format(date);
    }

    public void returnBook() {
        this.borrowed = false;
        this.borrowingPeriod = 0;
        this.borrowingDate = "";
    }
}

class LibrarySystem {

    private int capacity;
    private List<Book> books;

    public LibrarySystem(int capacity) {
        this.capacity = capacity;
        this.books = new ArrayList<Book>();
    }

    public void addBook(String name, String category) {
        if (books.size() == capacity) {
            System.out.println("The library is full and cannot accept more books.");
            return;
        }
        Book book = new Book(name, category);
        books.add(book);
        System.out.printf("The book has been added to the library with id = %d%n",book.getId());
    }

    public boolean removeBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isBorrowed()) {
                    System.out.println("The book is currently borrowed and cannot be removed.");
                  return false;
                } else {
                    books.remove(book);
                    System.out.println("The book has been removed from the library.");
                  return true;
                }
            }
        }
        System.out.println("No book found with the given id.");
      return false;
    }

    public boolean borrowBook(int id, int period) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isBorrowed()) 
                    System.out.println("The book is currently borrowed and cannot be borrowed again.");
                 else{
                    book.borrow(period);
                    System.out.println("The book has been borrowed. Please return it within " + period + " days.");
                   return true;
                }
                return false;
            }
        }
        System.out.println("No book found with the given id.");
       return false;
    }
  public boolean isid(int id){
    for (Book book : books) 
            if (book.getId() == id) 
              return true;
    return false;
    
  }

    public boolean returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println("The book has been returned.");
                  return true;
                } else {
                    System.out.println("The book is not currently borrowed.");
                  return false;
                }
            }
        }
        System.out.println("No book found with the given id.");
      return false;
    }

    public String getStatus() {
        int biologyCount = 0;
        int mathsCount = 0;
        int historyCount = 0;
        int chemistryCount = 0;
        int politicsCount = 0;
        int borrowedCount = 0;
        String status = "Id\tName\t\tCategory\tBorrowed\tBorrowing Period\tBorrowing Date\n";
        for (Book book : books) {
            status += book.getId() + "\t" + book.getName() + "\t\t" + book.getCategory() + "\t\t";
            if (book.isBorrowed()) {
                status += "   Yes\t   " + book.getBorrowingPeriod() + "\t\t" + book.getBorrowingDate();
                borrowedCount++;
            } else {
                status += "   No   ";
                if (book.getCategory().equals("biology")) 
                        biologyCount++;
                else if(book.getCategory().equals("maths"))
                        mathsCount++;
                else if(book.getCategory().equals("history"))
                        historyCount++;
                else if(book.getCategory().equals("chemistry"))
                        chemistryCount++;
                else if(book.getCategory().equals("politics"))
                        politicsCount++;
            }
            status += "\n";
        }
        status += "Number of books by category: \n";
        status += "Biology: " + biologyCount + "\n";
        status += "Maths: " + mathsCount + "\n";
        status += "History: " + historyCount + "\n";
        status += "Chemistry: " + chemistryCount + "\n";
        status += "Politics: " + politicsCount + "\n";
        status += "Total number of borrowed books: " + borrowedCount + "\n";
        return status;
    }

}



public class Main extends Application {

    private LibrarySystem library;
    private GridPane gridPane;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create the library system with a capacity of 100
        library = new LibrarySystem(100);

        // Create the user interface
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Create the title label
        Label title = new Label("Library System");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        root.setTop(title);

        // Create the grid pane for the input fields
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Create the book name text field
        TextField bookName = new TextField();
        bookName.setPromptText("Book Name");
        bookName.setMaxWidth(200);
        gridPane.add(bookName, 0, 0);

        // Create the category combo box
        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll("Biology", "Maths", "History", "Chemistry", "Politics");
        category.setPromptText("Category");
        gridPane.add(category, 1, 0);

        // Create the buttons
        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            String name = bookName.getText();
            String cat = category.getValue();
            if (name.isEmpty() || cat == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Please enter a book name and select a category.");
                alert.showAndWait();
            } else {
                library.addBook(name, cat.toLowerCase());
                statusLabel.setText(library.getStatus());
                bookName.clear();
                category.getSelectionModel().clearSelection();
            }
        });
        gridPane.add(addButton, 2, 0);

        Button removeButton = new Button("Remove Book");
        removeButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Remove Book");
            dialog.setHeaderText("Enter the ID of the book to remove:");
            dialog.showAndWait().ifPresent(id -> {
                try {
                    int bookId = Integer.parseInt(id);
                   boolean  isremoved = library.removeBook(bookId);
                    statusLabel.setText(library.getStatus());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  if (isremoved == true)
                  {
                    alert.setHeaderText("Success");
                    alert.setContentText("The book is borrowed");
                  }
                  else
                  {
                    alert.setHeaderText("Error");
                    alert.setContentText("The book not in the library");
                  }

                    alert.showAndWait();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a valid book ID.");
                    alert.showAndWait();
                }
            });
        });
        gridPane.add(removeButton, 3, 0);

        Button borrowButton = new Button("Borrow Book");
        borrowButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Borrow Book");
            dialog.setHeaderText("Enter the ID of the book to borrow:");
            dialog.showAndWait().ifPresent(id -> {
                try {
                    int bookId = Integer.parseInt(id);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  
                    if(!library.isid(bookId)) {
                      alert.setHeaderText("This ID is not in the library");
                      alert.setContentText("Cant found this id");
                      alert.showAndWait();
                    }
                  else{
                    TextInputDialog periodDialog = new TextInputDialog();
                    periodDialog.setTitle("Borrow Book");
                    periodDialog.setHeaderText("Enter the borrowing period in days:");
                    periodDialog.showAndWait().ifPresent(period -> {
                        try {
                            int borrowPeriod = Integer.parseInt(period);
                             if(library.borrowBook(bookId, borrowPeriod)){                                       statusLabel.setText(library.getStatus());
                            alert.setHeaderText("Success");
                            alert.setContentText("The book has been borrowed. Please return it within " + borrowPeriod + "days.");
                                                                          alert.showAndWait();
                             }
                          else {
                            statusLabel.setText(library.getStatus());
     //                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("ERROR");
                            alert.setContentText("the book is already borrowed");
                            alert.showAndWait();
                          }
                        } catch (NumberFormatException ex) {
                            alert.setHeaderText("Error");
                            alert.setContentText("Please enter a valid borrowing period.");
                            alert.showAndWait();
                        }
                    });
                  }
                } 
                catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a valid book ID.");
                    alert.showAndWait();
                }
            });
        });
        gridPane.add(borrowButton, 4, 0);

        Button returnButton = new Button("Return Book");
        returnButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Return Book");
            dialog.setHeaderText("Enter the ID of the book to return:");
            dialog.showAndWait().ifPresent(id -> {
                try {
                    int bookId = Integer.parseInt(id);
                  if(!library.isid(bookId)) {
                      alert.setHeaderText("This ID is not in the library");
                      alert.setContentText("Cant found this id");
                      alert.showAndWait();
                    }
                  else{
                    if(library.returnBook(bookId)){
                    statusLabel.setText(library.getStatus());

                    alert.setHeaderText("Success");
                    alert.setContentText("The book has been returned to the library.");
                    alert.showAndWait();
                    }
                    else{
                      alert.setHeaderText("ERROR");
                    alert.setContentText("The book already in the library");
                    alert.showAndWait();
                    }
                } 
                }
                catch (NumberFormatException ex) {
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a valid book ID.");
                    alert.showAndWait();
                }
                        });
        });
        gridPane.add(returnButton, 5, 0);

      Button viewStatus = new Button("View Status");

      viewStatus.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("View Status");
                try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The library status");
                    alert.setContentText(library.getStatus());
                    alert.showAndWait();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a valid book ID.");
                    alert.showAndWait();
                }
        });
        gridPane.add(viewStatus, 6, 0);


        // Add the grid pane to the center of the root pane
        root.setCenter(gridPane);

        // Create the status label
        statusLabel = new Label(library.getStatus());
        statusLabel.setFont(new Font("Arial", 14));
        statusLabel.setAlignment(Pos.CENTER);
        root.setBottom(statusLabel);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}