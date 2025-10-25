import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:data.sqlite"; // your SQLite file

        try (Connection conn = DriverManager.getConnection(url);
             Scanner sc = new Scanner(System.in)) {

            if (conn != null) {
                System.out.println("Connected to SQLite!\n");
            }

            System.out.print("Enter word to search: ");
            String inputWord = sc.nextLine();

            String query = "SELECT * FROM entries WHERE word = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, inputWord);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) { // word exists
                    System.out.println("\nWord: " + rs.getString("word"));
                    System.out.println("Type: " + rs.getString("wordtype"));
                    System.out.println("Definition: " + rs.getString("definition"));
                } else {
                    System.out.println("\nWord not found in the dictionary.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
