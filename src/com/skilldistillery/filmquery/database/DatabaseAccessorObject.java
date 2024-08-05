package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	// ID	*****
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, filmId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					film = new Film();
					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt("release_year"));
					film.setLangID(rs.getInt("language_id"));
					film.setRentDuration(rs.getInt("rental_duration"));
					film.setRentRate(rs.getDouble("rental_rate"));
					film.setLength(rs.getInt("length"));
					film.setReplaceCost(rs.getDouble("replacement_cost"));
					film.setRating(rs.getString("rating"));
					film.setSpecFeatures(rs.getString("special_features"));
					film.setFilmCast(findActorsByFilmId(filmId));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, actorId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					actor = new Actor();
					actor.setId(rs.getInt("id"));
					actor.setFirstName(rs.getString("first_name"));
					actor.setLastName(rs.getString("last_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, filmId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					Actor actor = new Actor(id, firstName, lastName);
					actors.add(actor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			String searchKeyword = "%" + keyword + "%";
			stmt.setString(1, searchKeyword);
			stmt.setString(2, searchKeyword);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					int releaseYear = rs.getInt("release_year");
					int langID = rs.getInt("language_id");
					int rentDuration = rs.getInt("rental_duration");
					double rentRate = rs.getDouble("rental_rate");
					int length = rs.getInt("length");
					double replaceCost = rs.getDouble("replacement_cost");
					String rating = rs.getString("rating");
					String specFeatures = rs.getString("special_features");
					Film film = new Film(id, title, description, releaseYear, langID, rentDuration, rentRate, length,
							replaceCost, rating, specFeatures);
					film.setFilmCast(findActorsByFilmId(id));
					films.add(film);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public String findLanguageById(int langId) {
		String language = null;
		String sql = "SELECT name FROM language WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, langId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					language = rs.getString("name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return language;
	}

	public String getFilmDetailsById(int filmId) {
		Film film = findFilmById(filmId);
		if (film == null) {
			return "No film found with ID: " + filmId;
		}
		return formatFilmDetails(film);
	}

	public String getFilmsByKeyword(String keyword) {
		List<Film> films = findFilmsByKeyword(keyword);
		if (films.isEmpty()) {
			return "No films found with keyword: " + keyword;
		}
		StringBuilder sb = new StringBuilder();
		for (Film film : films) {
			sb.append(formatFilmDetails(film)).append("\n");
		}
		return sb.toString();
	}

	private String formatFilmDetails(Film film) {
		String language = findLanguageById(film.getLangID());
		StringBuilder sb = new StringBuilder();
		sb.append("Title: ").append(film.getTitle()).append("\n").append("Year: ").append(film.getReleaseYear())
				.append("\n").append("Rating: ").append(film.getRating()).append("\n").append("Description: ")
				.append(film.getDescription()).append("\n").append("Language: ").append(language).append("\n")
				.append("Cast: ").append("\n");
		for (Actor actor : film.getFilmCast()) {
			sb.append("\t").append(actor.getFirstName()).append(" ").append(actor.getLastName()).append("\n");
		}
		return sb.toString();
	}

}
