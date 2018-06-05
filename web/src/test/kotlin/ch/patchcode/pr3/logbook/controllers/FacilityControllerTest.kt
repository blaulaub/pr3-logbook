package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.facilities.FacilityJpa
import ch.patchcode.pr3.logbook.facilities.FacilityModel
import ch.patchcode.pr3.logbook.facilities.FacilityRepository
import ch.patchcode.pr3.logbook.facilities.TurnoverModel
import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.games.GameRepository
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodRepository
import ch.patchcode.pr3.logbook.utils.contentAs
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext


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
		val goodIn = goodRepository.save(GoodJpa(game = game, name = "Bäume"))
		val goodEx = goodRepository.save(GoodJpa(game = game, name = "Bretter"))

		// act
		val updated = FacilityModel(
				id = facility.id!!,
				name = "Sägewerk 2",
				constructionCost = 1001,
				constructionDays = 1002,
				maintenancePerDay = 1003,
				workers = 1004,
				consumption = listOf(TurnoverModel(good = goodIn.toModel(), amount = 10.0)),
				production = TurnoverModel(good = goodEx.toModel(), amount = 2.0)
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

		val production = model.production!!
		assertThat(production.good.name, equalTo("Bretter"))
		assertThat(production.amount, equalTo(2.0))
	}

	@Test
	@Transactional
	fun `can update facility production amount in a game`() {
		// arrange
		val game = gameRepository.save(GameJpa(captainsName = "Morgan"))
		val facility = facilityRepository.save(FacilityJpa(game = game, name = "Sägewerk"))
		val goodEx = goodRepository.save(GoodJpa(game = game, name = "Bretter"))

		val before = FacilityModel(
				id = facility.id!!,
				name = "Sägewerk 2",
				constructionCost = 1001,
				constructionDays = 1002,
				maintenancePerDay = 1003,
				workers = 1004,
				consumption = listOf(),
				production = TurnoverModel(good = goodEx.toModel(), amount = 2.0)
		)
		mvc.perform(put("/games/{gameId}/facilities/{facilityId}", game.id, facility.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(before)))
				.andExpect(status().isOk())

		// act
		val after = FacilityModel(
				id = before.id,
				name = before.name,
				constructionCost = before.constructionCost,
				constructionDays = before.constructionDays,
				maintenancePerDay = before.maintenancePerDay,
				workers = before.workers,
				consumption = before.consumption,
				production = TurnoverModel(good = goodEx.toModel(), amount = 4.0)
		)

		val result = mvc.perform(put("/games/{gameId}/facilities/{facilityId}", game.id, facility.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(after)))

		// assert
		result.andExpect(status().isOk())

		val model = result.contentAs<FacilityModel>()
		assertThat(model.name, equalTo("Sägewerk 2"))
		assertThat(model.constructionCost, equalTo(1001))
		assertThat(model.constructionDays, equalTo(1002))
		assertThat(model.maintenancePerDay, equalTo(1003))
		assertThat(model.workers, equalTo(1004))

		val production = model.production!!
		assertThat(production.good.name, equalTo("Bretter"))
		assertThat(production.amount, equalTo(4.0))
	}
}
