
import java.sql.*;

public class DatabaseMain_1 {
    
    public static void main(String[] arguments){
        
        try{
            
            String databaseName = "java_database";
            String userStr = "root";
            String passwordStr = "";
            String connectionStr = "jdbc:mysql://localhost:3306/"
                    + databaseName + "?user=" + userStr + "&password="
                    + passwordStr + "&useSSL=false";
            
            Connection connection = DriverManager.getConnection(connectionStr);
            
            Statement statement = connection.createStatement();
            
            /**
             * statement.executeQuery(query);
             * This method is used to execute only SELECT queries and returns
             * an resultSet object.
             */
            
            /**
             * statement.executeUpdate(query);
             * This menthod is used to execute NON-SELECT queries or an SQL
             * statement that returns nothing.
             */
            
            /**
             * statment.execute(query);
             * This method can be used for both NON-SELECT and SELECT queries.
             */
            
            String query ="CREATE TABLE IF NOT EXISTS products("
                    + "id INT PRIMARY KEY,"
                    + "section VARCHAR(50),"
                    + "name VARCHAR(100),"
                    + "price FLOAT,"
                    + "origin_country VARCHAR(50)"
                    + ")";
            
            statement.execute(query); // Empty the table...!
            
            statement.execute("DELETE FROM products");
            
            query = "INSERT INTO products (id, section, name, price, origin_country) "
                    + "VALUES (1, 'deportes', 'camiseta', 25.50, 'USA')";
            statement.execute(query);
            
            query = "INSERT INTO products (id, section, name, price, origin_country) "
                    + "VALUES (2, 'ceramica', 'taza', 2.75, 'Italia')";
            statement.execute(query);
            
            query = "INSERT INTO products (id, section, name, price, origin_country) "
                    + "VALUES (3, 'tecnología', 'computador', 129.99, 'Japon')";
            statement.execute(query);
            
            query = "INSERT INTO products (id, section, name, price, origin_country) "
                    + "VALUES (4, 'deportes', 'balón', 34.99, 'Brazil')";
            statement.execute(query);
            
            query = "INSERT INTO products (id, section, name, price, origin_country) "
                    + "VALUES (5, 'muebles', 'sofá', 100, 'China')";
            statement.execute(query);
            
            /**
             * Prepared queries.
             */
            
            String prepQuery = "SELECT * FROM products WHERE section = ? AND name = ?";
            
            PreparedStatement prepStatement = connection.prepareStatement(prepQuery);
            
            // Setting parameters for prepared query:
            prepStatement.setString(1, "deportes");
            prepStatement.setString(2, "camiseta");
            
            // Executing the prepared query and getting the ResultSet object:
            ResultSet result = prepStatement.executeQuery();
            
            // Printing the result:
            System.out.println("Id:\tSection:\tName:\t\tPrice:\t\tOrigin Country:\n");
            while(result.next()){
                System.out.println(
                    result.getInt(1) + "\t" + 
                    result.getString(2) + "\t" +
                    result.getString(3) + "\t" +
                    result.getFloat(4) + "\t\t" +
                    result.getString(5) + "\n"
                );
            }
            
            // Re-using prepared query:
            prepStatement.setString(1, "ceramica");
            prepStatement.setString(2, "taza");
            result = prepStatement.executeQuery();
            
            // Printing the new result:
            while(result.next()){
                System.out.println(
                    result.getInt(1) + "\t" + 
                    result.getString(2) + "\t" +
                    result.getString(3) + "\t\t" +
                    result.getFloat(4) + "\t\t" +
                    result.getString(5) + "\n"
                );
            }
            
            // Closing the ResultSet object:
            result.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
