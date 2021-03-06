package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.cities.CityModel
import ch.patchcode.pr3.logbook.cities.CityRepository
import ch.patchcode.pr3.logbook.facilities.FacilityModel
import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.games.GameModel
import ch.patchcode.pr3.logbook.games.GameRepository
import ch.patchcode.pr3.logbook.utils.contentAs
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
class RestTest {

	@Autowired
	lateinit var context: WebApplicationContext

	@Autowired
	lateinit var gameRepository: GameRepository

	@Autowired
	lateinit var cityRepository: CityRepository

	lateinit var mvc: MockMvc

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
	}

	@Test
	@Transactional
	fun `can post new game`() {
		// act
		val result = mvc.perform(post("/games").param("captainsName", "Morgan"))

		// assert
		result.andExpect(status().isCreated())
		assertThat(result.contentAs<GameModel>().captainsName, equalTo("Morgan"))
	}

	@Test
	@Transactional
	fun `can get list of games`() {
		// arrange
		val game1 = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val game2 = gameRepository.save(GameJpa(captainsName = "Blackbeard"))

		// act
		val result = mvc.perform(get("/games"))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<List<GameModel>>(), allOf(
				hasItem(game1.toModel()),
				hasItem(game2.toModel())))
	}

	@Test
	@Transactional
	fun `can get an existing game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))

		// act
		val result = mvc.perform(get("/games/{gameId}", game.id))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<GameModel>(), equalTo(game.toModel()))
	}

	@Test
	@Transactional
	fun `can delete a game`() {
		// arrange
		val game1 = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val game2 = gameRepository.save(GameJpa(captainsName = "Blackbeard"))

		// act
		val result = mvc.perform(delete("/games/{gameId}", game1.id))

		// assert
		result.andExpect(status().isNoContent)
		assertThat(gameRepository.findAll(), allOf(
				not(hasItem(game1)),
				hasItem(game2)))
	}

	@Test
	@Transactional
	fun `can post new city in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))

		// act
		val result = mvc.perform(post("/games/{gameId}/cities", game.id).param("name", "Cat Island"))

		// assert
		result.andExpect(status().isCreated())
		assertThat(result.contentAs<CityModel>().name, equalTo("Cat Island"))
	}

	@Test
	@Transactional
	fun `can get list of cities in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val city1 = cityRepository.save(CityJpa(game = game, name = "Cat Island"))
		val city2 = cityRepository.save(CityJpa(game = game, name = "Tortuga"))

		// act
		val result = mvc.perform(get("/games/{gameId}/cities", game.id))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<List<CityModel>>(), allOf(
				hasItem(city1.toModel()),
				hasItem(city2.toModel())))
	}

	@Test
	@Transactional
	fun `can get an existing city in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val city = cityRepository.save(CityJpa(game = game, name = "Cat Island"))

		// act
		val result = mvc.perform(get("/games/{gameId}/cities/{cityId}", game.id, city.id))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<CityModel>(), equalTo(city.toModel()))
	}

	@Test
	@Transactional
	fun `can delete a city in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val city1 = cityRepository.save(CityJpa(game = game, name = "Cat Island"))
		val city2 = cityRepository.save(CityJpa(game = game, name = "Tortuga"))

		// act
		val result = mvc.perform(delete("/games/{gameId}/cities/{cityId}", game.id, city1.id))

		// assert
		result.andExpect(status().isNoContent)
		assertThat(cityRepository.findAll(), allOf(
				not(hasItem(city1)),
				hasItem(city2)))
	}

	@Test
	@Transactional
	fun `deleting a game deletes all its cities`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Kurt"))
		val city1 = cityRepository.save(CityJpa(game = game, name = "Engelberg"))
		val city2 = cityRepository.save(CityJpa(game = game, name = "Sion"))

		// act
		val result = mvc.perform(delete("/games/{gameId}", game.id)).andDo(print())

		// assert
		result.andExpect(status().isNoContent)
		assertThat(gameRepository.findAll(), not(hasItem(game)))
		assertThat(cityRepository.findAll(), allOf(
				not(hasItem(city1)),
				not(hasItem(city2))))
	}

	@Test
	@Transactional
	fun `can post new facility in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))

		// act
		val result = mvc.perform(post("/games/{gameId}/facilities", game.id).param("name", "Sägewerk"))

		// assert
		result.andExpect(status().isCreated())
		assertThat(result.contentAs<FacilityModel>().name, equalTo("Sägewerk"))
	}
}
