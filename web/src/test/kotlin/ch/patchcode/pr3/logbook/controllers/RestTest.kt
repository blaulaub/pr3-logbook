package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.repositories.GameRepository
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
		assertThat(result.contentAs<Game>().captainsName, equalTo("Morgan"))
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
		assertThat(result.contentAs<List<Game>>(), allOf(
				hasItem(game1.toDto()),
				hasItem(game2.toDto())))
	}

	@Test
	@Transactional
	fun `can get an existing game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))

		// act
		val result = mvc.perform(get("/games/{gameId}", game.id)).andDo(print())

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<Game>(), equalTo(game.toDto()))
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
		result.andExpect(status().isOk)
		assertThat(gameRepository.findAll(), allOf(
				not(hasItem(game1)),
				hasItem(game2)))
	}
}
