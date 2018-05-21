package ch.patchcode.pr3

import org.junit.Test
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.objects.City
import ch.patchcode.pr3.logbook.objects.Good
import ch.patchcode.pr3.logbook.objects.Facility
import ch.patchcode.pr3.logbook.objects.ConstructionMaterial
import ch.patchcode.pr3.logbook.objects.FacilityConsumption
import ch.patchcode.pr3.logbook.objects.FacilityProduction
import java.time.LocalDate
import ch.patchcode.pr3.logbook.objects.CityPopulation
import ch.patchcode.pr3.logbook.objects.CityProduct

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
	fun `I can tell the construction cost of a store house within a game`() {
		val game = Game(1L, "Cpt Hook")

		val wood = Good(1L, game, "Holz")
		val bricks = Good(1L, game, "Lehmziegel")

		val store = Facility(1L, game, "Lagerhaus",
				constructionCost = 6_000,
				constructionDays = 5,
				maintenancePerDay = 50)
		ConstructionMaterial(store, wood, 20)
		ConstructionMaterial(store, bricks, 40)
	}

	@Test
	fun `I can tell production and consumption of a taylor within a game`() {
		val game = Game(1L, "Cpt Hook")

		val wood = Good(1L, game, "Holz")
		val bricks = Good(1L, game, "Lehmziegel")

		val taylor = Facility(1L, game, "Schneiderei",
				constructionCost = 18_000,
				constructionDays = 17,
				maintenancePerDay = 50)
		ConstructionMaterial(taylor, wood, 60)
		ConstructionMaterial(taylor, bricks, 120)

		val textiles = Good(1L, game, "Tuch")
		val dyes = Good(1L, game, "Farbstoffe")
		val clothes = Good(1L, game, "Kleidung")

		FacilityConsumption(taylor, textiles, 1.0)
		FacilityConsumption(taylor, dyes, 1.0)
		FacilityProduction(taylor, clothes, 1.0)
	}

	@Test
	fun `I can update the city population within a game`() {
		val game = Game(1L, "Cpt Hook")
		val dorf = City(1L, game, "Dorf")
		CityPopulation(dorf, LocalDate.parse("1561-12-18"), 1200)
		CityPopulation(dorf, LocalDate.parse("1562-03-12"), 1300)
	}

	@Test
	fun `I can declare goods a city produces within a game`() {
		val game = Game(1L, "Cpt Hook")
		val dorf = City(1L, game, "Dorf")
		CityProduct(1L, dorf, Good(1L, game, "Holz"))
		CityProduct(2L, dorf, Good(2L, game, "Tuch"))
	}
}