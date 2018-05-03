package ch.patchcode.pr3

import org.junit.Test
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.objects.City
import ch.patchcode.pr3.logbook.objects.Good
import ch.patchcode.pr3.logbook.objects.Facility
import ch.patchcode.pr3.logbook.objects.ConstructionMaterial

class SomeTest {

	@Test
	fun `I can start a new game`() {
		Game(1L, "Cpt Hook")
	}

	@Test
	fun `I can discover a city within a game`() {
		val game = Game(1L, "Cpt Hook")
		City(1L, game, "Bad Nauheim")
	}

	@Test
	fun `I can discover a good within a game`() {
		val game = Game(1L, "Cpt Hook")
		Good(1L, game, "Wood")
	}

	@Test
	fun `I can discover a facility within a game`() {
		val game = Game(1L, "Cpt Hook")
		Facility(1L, game, "School")
	}

	@Test
	fun `I can tell the construction cost of a facility within a game`() {
		val game = Game(1L, "Cpt Hook")

		val wood = Good(1L, game, "Holz")
		val bricks = Good(1L, game, "Lehmziegel")

		val store = Facility(1L, game, "Lagerhaus",
				constructionCost = 6000,
				constructionDays = 5,
				maintenance = 500)
		ConstructionMaterial(store, wood, 20)
		ConstructionMaterial(store, bricks, 40)
	}

	

}