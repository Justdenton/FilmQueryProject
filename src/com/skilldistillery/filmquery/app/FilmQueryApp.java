package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  DatabaseAccessor db = new DatabaseAccessorObject();
  private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
  private static String user = "student";
  private static String pass = "student";
  
  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    startUserInterface(input);
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}
