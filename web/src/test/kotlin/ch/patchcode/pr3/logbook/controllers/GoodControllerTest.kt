package ch.patchcode.pr3.logbook.controllers

import org.junit.Test
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.test.web.servlet.MockMvc
import org.junit.Before
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.context.SpringBootTest
import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNot.not
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ch.patchcode.pr3.logbook.utils.contentAs
import ch.patchcode.pr3.logbook.model.GoodModel
import ch.patchcode.pr3.logbook.repositories.GoodRepository
import ch.patchcode.pr3.logbook.repositories.CityRepository
import ch.patchcode.pr3.logbook.repositories.CityProductRepository
import ch.patchcode.pr3.logbook.entities.CityJpa
import ch.patchcode.pr3.logbook.entities.GoodJpa
import ch.patchcode.pr3.logbook.entities.CityProductJpa
import ch.patchcode.pr3.logbook.model.CityModel
import org.hamcrest.collection.IsCollectionWithSize.hasSize

@RunWith(SpringRunner::class)
@SpringBootTest
class GoodControllerTest {

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

	lateinit var mvc: MockMvc

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
	}

	@Test
	@Transactional
	fun `can post new good in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))

		// act
		val result = mvc.perform(post("/games/{gameId}/goods", game.id).param("name", "Holz"))

		// assert
		result.andExpect(status().isCreated())
		assertThat(result.contentAs<GoodModel>().name, equalTo("Holz"))
	}

	@Test
	@Transactional
	fun `can get city where good is produced`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good = goodRepository.save(GoodJpa(game = game, name = "Bretter"))
		cityProductRepository.save(CityProductJpa(city = city, good = good))

		// act
		val result = mvc.perform(get("/games/{gameId}/goods/{goodId}/producingCities", game.id, good.id).param("name", "Bretter"))

		// assert
		result.andExpect(status().isOk())
		
		val cities = result.contentAs<List<CityModel>>() 
		assertThat(cities, hasSize(1))
		assertThat(cities, hasItem(city.toModel()))
	}
}