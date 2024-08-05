package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	private DatabaseAccessor db = new DatabaseAccessorObject();

	// MAIN
	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	// SCANNER / LAUNCH
	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	// USER INTERFACE SWITCH
	private void startUserInterface(Scanner input) {
		boolean keepRunning = true;
		while (keepRunning) {
			displayMenu();
			int choice = input.nextInt();
			input.nextLine(); // consume newline

			switch (choice) {
			case 1:
				handleFilmById(input);
				break;
			case 2:
				handleFilmByKeyword(input);
				break;
			case 3:
				keepRunning = false;
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid choice - Please try again.");
			}
		}
	}

	// SEARCH BY ID ; sub-menu.
	private void handleFilmById(Scanner input) {
		System.out.println("Enter the film ID: ");
		int filmId = input.nextInt();
		input.nextLine();
		Film result = db.findFilmById(filmId);
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("No film found with ID: " + filmId);
		}
	}

	// SEARCH BY KEYWORD ; sub-menu.
	private void handleFilmByKeyword(Scanner input) {
		System.out.println("Enter a keyword: ");
		String keyword = input.nextLine();
		List<Film> result = db.findFilmsByKeyword(keyword);
		if (!result.isEmpty()) {
			for (Film film : result) {
				System.out.println(film);
			}
		} else {
			System.out.println("No films found with keyword: " + keyword);
		}
	}

	// BASIC MENU
	private void displayMenu() {
		System.out.println("Welcome to FilmQuery!");
		System.out.println("\t1. Find film by ID");
		System.out.println("\t2. Find film by keyword");
		System.out.println("\t3. Exit");
		System.out.print("\tPlease enter your choice: ");
	}
}
