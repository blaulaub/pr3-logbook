package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.cities.CityRepository
import ch.patchcode.pr3.logbook.fleets.FleetJpa
import ch.patchcode.pr3.logbook.fleets.FleetRepository
import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.games.GameRepository
import ch.patchcode.pr3.logbook.routes.RouteModel
import ch.patchcode.pr3.logbook.routes.RoutePointModel
import ch.patchcode.pr3.logbook.utils.contentAs
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.number.IsCloseTo.closeTo
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.hamcrest.core.IsEqual.equalTo
import ch.patchcode.pr3.logbook.routes.RoutePointJpa
import ch.patchcode.pr3.logbook.routes.RouteJpa
import ch.patchcode.pr3.logbook.routes.RouteRepository


@RunWith(SpringRunner::class)
@SpringBootTest
class RouteControllerTest {

	@Autowired
	lateinit var context: WebApplicationContext

	@Autowired
	lateinit var gameRepository: GameRepository

	@Autowired
	lateinit var fleetRepository: FleetRepository

	@Autowired
	lateinit var cityRepository: CityRepository

	@Autowired
	lateinit var routeRepository: RouteRepository

	lateinit var mvc: MockMvc

	lateinit var game: GameJpa

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
		game = gameRepository.save(GameJpa(captainsName = "Morgan"))
	}

	@Test
	@Transactional
	fun `vanilla fleet has empty route`() {
		// arange
		val fleet = fleetRepository.save(FleetJpa(game = game, name = "Santa Maria"))

		// act
		val result = mvc.perform(get("/games/{gameId}/fleets/{fleetId}/route", game.id, fleet.id))

		// assert
		result.andExpect(status().isOk())

		val model = result.contentAs<RouteModel>()
		assertThat(model.travelDays, closeTo(0.0, 0.001))
		assertThat(model.routePoints, empty())
	}

	@Test
	@Transactional
	fun `can assign route to vanilla fleet`() {
		// arange
		val fleet = fleetRepository.save(FleetJpa(game = game, name = "Santa Maria"))
		val city = cityRepository.save(CityJpa(game = game, name = "Wil"))

		// act
		val route = RouteModel(travelDays = 5.0, routePoints = listOf(RoutePointModel(city = city.name)))
		val result = mvc.perform(put("/games/{gameId}/fleets/{fleetId}/route", game.id, fleet.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(route)))

		// assert
		result.andDo(print())
		result.andExpect(status().isOk())

		result.andDo(print())
		val model = result.contentAs<RouteModel>()
		assertThat(model.travelDays, closeTo(route.travelDays, 0.001))
		assertThat(model.routePoints, hasSize(1))
		assertThat(model.routePoints.get(0).city, equalTo(city.name))
	}

	@Test
	@Transactional
	fun `can get route of a fleet`() {
		// arange
		val fleet = fleetRepository.save(FleetJpa(game = game, name = "Santa Maria"))
		val city1 = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val city2 = cityRepository.save(CityJpa(game = game, name = "Brugg"))
		val city3 = cityRepository.save(CityJpa(game = game, name = "Turgi"))
		val route = createRoute(fleet, 4.3, city1, city2, city3)

		// act
		val result = mvc.perform(get("/games/{gameId}/fleets/{fleetId}/route", game.id, fleet.id))

		// assert
		result.andDo(print())
		result.andExpect(status().isOk())

		result.andDo(print())
		val model = result.contentAs<RouteModel>()
		assertThat(model.travelDays, closeTo(route.travelDays, 0.001))
		assertThat(model.routePoints, hasSize(route.routePoints.size))
		assertThat(model.routePoints.get(0).city, equalTo(city1.name))
		assertThat(model.routePoints.get(1).city, equalTo(city2.name))
		assertThat(model.routePoints.get(2).city, equalTo(city3.name))
	}

	@Test
	@Transactional
	fun `can update route of a fleet`() {
		// arange
		val fleet = fleetRepository.save(FleetJpa(game = game, name = "Santa Maria"))
		val city1 = cityRepository.save(CityJpa(game = game, name = "Wil"))
		val city2 = cityRepository.save(CityJpa(game = game, name = "Brugg"))
		val city3 = cityRepository.save(CityJpa(game = game, name = "Turgi"))
		val oldRoute = createRoute(fleet, 4.3, city1, city2)

		// act
		val route = RouteModel(travelDays = 5.0, routePoints = listOf(RoutePointModel(city = city3.name), RoutePointModel(city = city1.name)))
		val result = mvc.perform(put("/games/{gameId}/fleets/{fleetId}/route", game.id, fleet.id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectMapper().writeValueAsString(route)))

		// assert
		result.andDo(print())
		result.andExpect(status().isOk())

		result.andDo(print())
		val model = result.contentAs<RouteModel>()
		assertThat(model.travelDays, closeTo(route.travelDays, 0.001))
		assertThat(model.routePoints, hasSize(route.routePoints.size))
		assertThat(model.routePoints.get(0).city, equalTo(city3.name))
		assertThat(model.routePoints.get(1).city, equalTo(city1.name))
		
		val newRoute = routeRepository.findById(oldRoute.id!!).get()
		assertThat(newRoute.travelDays, closeTo(route.travelDays, 0.001))
		assertThat(newRoute.routePoints, hasSize(route.routePoints.size))
		assertThat(newRoute.routePoints.get(0).city.name, equalTo(city3.name))
		assertThat(newRoute.routePoints.get(0).position, equalTo(0))
		assertThat(newRoute.routePoints.get(1).city.name, equalTo(city1.name))
		assertThat(newRoute.routePoints.get(1).position, equalTo(1))
	}

	private fun createRoute(fleet: FleetJpa, travelDays: Double, vararg cities: CityJpa): RouteJpa {
		val r = RouteJpa(
				fleet = fleet,
				travelDays = travelDays,
				routePoints = mutableListOf<RoutePointJpa>()
		)
		cities.forEachIndexed { i, item -> r.routePoints.add(RoutePointJpa(route = r, city = item, position = i)) }
		return routeRepository.save(r)
	}
}
