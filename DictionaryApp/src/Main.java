
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.db"; // SQLite database file

        try (Connection conn = DriverManager.getConnection(url); Scanner sc = new Scanner(System.in)) {

            if (conn != null) {
                System.out.println("Connected to SQLite!\n");
            }

            while (true) {
                System.out.print("Enter word to search (or type 'exit' to quit): ");
                String inputWord = sc.nextLine().trim();

                if (inputWord.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting dictionary. Goodbye!");
                    break;
                }

                String query = "SELECT * FROM words WHERE LOWER(word) = LOWER(?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, inputWord);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("\nWord: " + rs.getString("word"));
                        System.out.println("Type: " + rs.getString("type"));
                        System.out.println("Definition: " + rs.getString("defn") + "\n");
                    } else {
                        System.out.println("\nWord not found in the dictionary.");
                        System.out.print("Would you like to add it? (yes/no): ");
                        String choice = sc.nextLine().trim();

                        if (choice.equalsIgnoreCase("yes")) {
                            System.out.print("Enter word type (e.g., noun, verb): ");
                            String type = sc.nextLine().trim();

                            System.out.print("Enter definition: ");
                            String defn = sc.nextLine().trim();

                            String insertQuery = "INSERT INTO words (word, type, defn) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                insertStmt.setString(1, inputWord);
                                insertStmt.setString(2, type);
                                insertStmt.setString(3, defn);
                                insertStmt.executeUpdate();

                                System.out.println("\n Word added successfully!\n");
                            } catch (SQLException e) {
                                System.out.println("Error adding word: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Okay, not adding the word.\n");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
