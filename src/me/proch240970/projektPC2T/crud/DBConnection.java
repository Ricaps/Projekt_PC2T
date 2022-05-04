package me.proch240970.projektPC2T.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Double checked singleton class (singleton means that only one instance of this class could be
 * created) getting database connection.
 * 
 * @author Pavel �eda (154208)
 *
 */
public class DBConnection {

  private static volatile Connection dbConnection;

  private DBConnection() {}

  /**
   * Metoda, kter� vytv��� "jedin��ka" �ili maxim�ln� jednu instanci p�ipojen� k datab�zi.
   * 
   * POZN: Nen� vhodn� vytv��et mnoho instanc� p�ipojen� k datab�zi, aby se datab�ze nezahltila
   * 
   * @return vrac� instanci, p�ipojen� k datab�zi
   */
  public static Connection getDBConnection() {
    if (dbConnection == null) {
      synchronized (DBConnection.class) {
        if (dbConnection == null) {
          try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:db/students.db");
          } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // log it
          }
        }
      }
    }
    return dbConnection;
  }
  
  public static void createTable() {
	  String createTable = "CREATE TABLE IF NOT EXISTS Students ("
	  		+ "ID INT NOT NULL,"
	  		+ "FirstName VARCHAR (255) NOT NULL"
	  		+ "LastName VARCHAR (255) NOT NULL,"
	  		+ "BirthDay VARCHAR (100),"
	  		+ "Specialization INT,"
	  		+ "Marks VARCHAR (255))";
  }

  /**
   * metoda uzav�raj�c� spojen� s datab�z� POZN. vhodn� volat tuto metodu a� po vykon�n� v�ech
   * dotaz� nad datab�z�
   */
  public static void closeConnection() {
    try {
      dbConnection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
