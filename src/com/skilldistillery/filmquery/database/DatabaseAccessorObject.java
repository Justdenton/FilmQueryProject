package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

  private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
  private static String user = "student";
  private static String pass = "student";
  
  static {
	    
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
	  } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	  }
  }
  
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  String sql = "SELECT * FROM film WHERE id = ?";
	
	  try {
		  Connection conn = DriverManager.getConnection(url, user, pass);
		  
		  conn.close();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
		return film;
	}
  
  @Override
  public Actor findActorById(int actorId) {
	// TODO Auto-generated method stub
	  return null;
  }

  @Override
  public List<Actor> findActorsByFilmId(int filmId) {
	// TODO Auto-generated method stub
	  return null;
  }

}
