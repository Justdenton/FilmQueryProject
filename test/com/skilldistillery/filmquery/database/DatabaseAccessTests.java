package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessTests {
	private DatabaseAccessor db;

	@BeforeEach
	public void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@Test
	void test_findFilmById_returns_film() {
		Film film = db.findFilmById(1);
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
	}

	@Test
	void test_findActorById_returns_actor() {
		Actor actor = db.findActorById(1);
		assertNotNull(actor);
		assertEquals("Penelope", actor.getFirstName());
		assertEquals("Guiness", actor.getLastName());
	}

	@Test
	void test_findActorsByFilmId_returns_actors() {
		List<Actor> actors = db.findActorsByFilmId(1);
		assertNotNull(actors);
		assertFalse(actors.isEmpty());
	}

	@Test
	void test_findFilmsByKeyword_returns_films() {
		List<Film> films = db.findFilmsByKeyword("Academy");
		assertNotNull(films);
		assertFalse(films.isEmpty());
	}

	@Test
	void test_findLanguageById_returns_language() {
		String language = db.findLanguageById(1);
		assertNotNull(language);
		assertEquals("English", language);
	}
}
