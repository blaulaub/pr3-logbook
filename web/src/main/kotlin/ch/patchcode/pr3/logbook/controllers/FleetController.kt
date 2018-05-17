package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.entities.FleetJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.model.FleetModel
import ch.patchcode.pr3.logbook.services.FleetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
class FleetController @Autowired constructor(
		private val fleetService: FleetService
) {

	@GetMapping("/games/{gameId}/fleets")
	@Transactional
	fun getCities(
			@PathVariable gameId: Long
	): List<FleetModel> = fleetService.findByGame(gameId)

	@PostMapping("/games/{gameId}/fleets")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createFleet(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): FleetModel = fleetService.createFleet(gameId, name)

	@GetMapping("/games/{gameId}/fleets/{fleetId}")
	@Transactional
	fun getFleet(
			@PathVariable gameId: Long,
			@PathVariable fleetId: Long
	): FleetModel = fleetService.getFleet(gameId, fleetId)

	@DeleteMapping("/games/{gameId}/fleets/{fleetId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteFleet(@PathVariable gameId: Long, @PathVariable fleetId: Long) {
		fleetService.deleteFleet(gameId, fleetId)
	}
}