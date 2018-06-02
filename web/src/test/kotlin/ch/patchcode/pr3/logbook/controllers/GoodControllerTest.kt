package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.cities.CityRepository
import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.games.GameRepository
import ch.patchcode.pr3.logbook.goods.GoodModel
import ch.patchcode.pr3.logbook.goods.GoodRepository
import ch.patchcode.pr3.logbook.utils.contentAs
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

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
}