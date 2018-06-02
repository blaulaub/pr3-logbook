package ch.patchcode.pr3

import org.junit.Test
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.objects.Good
import ch.patchcode.pr3.logbook.objects.Facility
import ch.patchcode.pr3.logbook.objects.ConstructionMaterial
import ch.patchcode.pr3.logbook.objects.FacilityConsumption
import ch.patchcode.pr3.logbook.objects.FacilityProduction
import java.time.LocalDate

class SomeTest {

	@Test
	fun `I can start a new game`() {
		Game(1L, "Cpt Hook")
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
}