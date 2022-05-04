package me.proch240970.projektPC2T.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Double checked singleton class (singleton means that only one instance of this class could be
 * created) getting database connection.
 * 
 * @author Pavel Šeda (154208)
 *
 */
public class DBConnection {

  private static volatile Connection dbConnection;

  private DBConnection() {}

  /**
   * Metoda, která vytváøí "jedináèka" èili maximálnì jednu instanci pøipojení k databázi.
   * 
   * POZN: Není vhodné vytváøet mnoho instancí pøipojení k databázi, aby se databáze nezahltila
   * 
   * @return vrací instanci, pøipojení k databázi
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
   * metoda uzavírající spojení s databází POZN. vhodné volat tuto metodu až po vykonání všech
   * dotazù nad databází
   */
  public static void closeConnection() {
    try {
      dbConnection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
