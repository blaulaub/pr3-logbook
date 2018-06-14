package ch.patchcode.pr3.logbook.routes

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RouteController @Autowired constructor(
		private val routeService: RouteService
) {

	@GetMapping("/games/{gameId}/fleets/{fleetId}/route")
	fun getRoute(
			@PathVariable gameId: Long,
			@PathVariable fleetId: Long
	): RouteModel = routeService.getRoute(gameId, fleetId)

	@PutMapping("/games/{gameId}/fleets/{fleetId}/route")
	fun putRoute(
			@PathVariable gameId: Long,
			@PathVariable fleetId: Long,
			@RequestBody route: RouteModel
	): RouteModel = routeService.updateRoute(gameId, fleetId, route)
}
