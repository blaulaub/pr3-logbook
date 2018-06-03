package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.cities.CityRepository
import ch.patchcode.pr3.logbook.cityproducts.CityProductJpa
import ch.patchcode.pr3.logbook.cityproducts.CityProductRepository
import ch.patchcode.pr3.logbook.facilities.FacilityJpa
import ch.patchcode.pr3.logbook.facilities.FacilityRepository
import ch.patchcode.pr3.logbook.facilities.ProductionJpa
import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.games.GameRepository
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ch.patchcode.pr3.logbook.utils.contentAs
import ch.patchcode.pr3.logbook.cityFactories.FactoryCountModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.core.IsCollectionContaining.hasItem

@RunWith(SpringRunner::class)
@SpringBootTest
class CityFactoryControllerTest {

	@Autowired
	lateinit var context: WebApplicationContext

	@Autowired
	lateinit var gameRepository: GameRepository

	@Autowired
	lateinit var cityRepository: CityRepository

	@Autowired
	lateinit var goodRepository: GoodRepository

	@Autowired
	lateinit var cityProductRepository: CityProductRepository

	@Autowired
	lateinit var facilityRepository: FacilityRepository

	lateinit var mvc: MockMvc

	lateinit var game: GameJpa

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
		game = gameRepository.save(GameJpa(captainsName = "Morgan"))
	}

	@Test
	@Transactional
	fun `x`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good = goodRepository.save(GoodJpa(game = game, name = "Bretter"))
		cityProductRepository.save(CityProductJpa(city = city, good = good))
		var facility = facilityRepository.save(FacilityJpa(game = game, name = "Sägewerk"))
		facility.production = ProductionJpa(facility = facility, good = good, amount = 14.0)
		facility = facilityRepository.save(facility)

		// act
		val result = mvc.perform(get("/games/{gameId}/cities/{cityId}/factoryCounts", game.id, city.id))

		// assert
		result.andExpect(status().isOk())

		val model = result.contentAs<List<FactoryCountModel>>()
		assertThat(model, hasSize(1))
		assertThat(model, hasItem(FactoryCountModel(
				facilityId = facility.id!!,
				facilityName = facility.name,
				rivalCount = 0,
				playerCount = 0)))

	}
}
