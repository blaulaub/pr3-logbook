package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.cities.CityRepository
import ch.patchcode.pr3.logbook.entities.CityProductJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodModel
import ch.patchcode.pr3.logbook.repositories.CityProductRepository
import ch.patchcode.pr3.logbook.repositories.GameRepository
import ch.patchcode.pr3.logbook.repositories.GoodRepository
import ch.patchcode.pr3.logbook.utils.contentAs
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.transaction.Transactional


@RunWith(SpringRunner::class)
@SpringBootTest
class CityProductsControllerTest {

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

	lateinit var game: GameJpa

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
		game = gameRepository.save(GameJpa(captainsName = "Morgan"))
	}

	@Test
	@Transactional
	fun `a bare city produces nothing`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))

		// act
		val result = mvc.perform(get("/games/{gameId}/cities/{cityId}/products", game.id, city.id))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<List<GoodModel>>(), empty())
	}

	@Test
	@Transactional
	fun `can get products of a city`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good = goodRepository.save(GoodJpa(game = game, name = "Bretter"))
		cityProductRepository.save(CityProductJpa(city = city, good = good))

		// act
		val result = mvc.perform(get("/games/{gameId}/cities/{cityId}/products", game.id, city.id))

		// assert
		val model = result.contentAs<List<GoodModel>>()
		assertThat(model, hasSize(1))
		assertThat(model, hasItem(good.toModel()))
	}

	@Test
	@Transactional
	fun `can define products for a city`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good = goodRepository.save(GoodJpa(game = game, name = "Bretter"))

		// act
		val result = mvc.perform(put("/games/{gameId}/cities/{cityId}/products", game.id, city.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(listOf(good))))

		result.andExpect(status().isOk())

		val model = result.contentAs<List<GoodModel>>()
		assertThat(model, hasSize(1))
		assertThat(model, hasItem(good.toModel()))
	}

	@Test
	@Transactional
	fun `can add more products for a city`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good1 = goodRepository.save(GoodJpa(game = game, name = "Bretter"))
		val good2 = goodRepository.save(GoodJpa(game = game, name = "Nägel"))
		cityProductRepository.save(CityProductJpa(city = city, good = good1))

		// act
		val result = mvc.perform(put("/games/{gameId}/cities/{cityId}/products", game.id, city.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(listOf(good1, good2))))

		result.andExpect(status().isOk())

		val model = result.contentAs<List<GoodModel>>()
		assertThat(model, hasSize(2))
		assertThat(model, hasItem(good1.toModel()))
		assertThat(model, hasItem(good2.toModel()))
	}

	@Test
	@Transactional
	fun `can change products for a city`() {
		// arrange
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val good1 = goodRepository.save(GoodJpa(game = game, name = "Bretter"))
		val good2 = goodRepository.save(GoodJpa(game = game, name = "Nägel"))
		cityProductRepository.save(CityProductJpa(city = city, good = good1))

		// act
		val result = mvc.perform(put("/games/{gameId}/cities/{cityId}/products", game.id, city.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(listOf(good2))))

		result.andExpect(status().isOk())

		val model = result.contentAs<List<GoodModel>>()
		assertThat(model, hasSize(1))
		assertThat(model, hasItem(good2.toModel()))
	}
}