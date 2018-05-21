package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.entities.FacilityJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.entities.GoodJpa
import ch.patchcode.pr3.logbook.model.FacilityModel
import ch.patchcode.pr3.logbook.repositories.FacilityRepository
import ch.patchcode.pr3.logbook.repositories.GameRepository
import ch.patchcode.pr3.logbook.repositories.GoodRepository
import ch.patchcode.pr3.logbook.utils.contentAs
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.http.MediaType


@RunWith(SpringRunner::class)
@SpringBootTest
class FacilityControllerTest {

	@Autowired
	lateinit var context: WebApplicationContext

	@Autowired
	lateinit var gameRepository: GameRepository

	@Autowired
	lateinit var goodRepository: GoodRepository

	@Autowired
	lateinit var facilityRepository: FacilityRepository

	lateinit var mvc: MockMvc

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
	}

	@Test
	@Transactional
	fun `can null-update a facility in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val facility = facilityRepository.save(FacilityJpa(game = game, name = "Sägewerk"))

		// act
		val result = mvc.perform(put("/games/{gameId}/facilities/{facilityId}", game.id, facility.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(facility.toModel())))

		// assert
		result.andExpect(status().isOk())
		assertThat(result.contentAs<FacilityModel>().name, equalTo("Sägewerk"))
	}

	@Test
	@Transactional
	fun `can update a vanilla facility in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val facility = facilityRepository.save(FacilityJpa(game = game, name = "Sägewerk"))
		val good = goodRepository.save(GoodJpa(game = game, name = "Holz"))

		// act
		val updated = FacilityModel(
				id = facility.id!!,
				name = "Sägewerk 2",
				constructionCost = 1001,
				constructionDays = 1002,
				maintenancePerDay = 1003,
				workers = 1004
		)
		val result = mvc.perform(put("/games/{gameId}/facilities/{facilityId}", game.id, facility.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(updated)))

		// assert
		result.andExpect(status().isOk())

		val model = result.contentAs<FacilityModel>()
		assertThat(model.name, equalTo("Sägewerk 2"))
		assertThat(model.constructionCost, equalTo(1001))
		assertThat(model.constructionDays, equalTo(1002))
		assertThat(model.maintenancePerDay, equalTo(1003))
		assertThat(model.workers, equalTo(1004))
	}
}