package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.entities.FacilityJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.model.FacilityModel
import ch.patchcode.pr3.logbook.services.FacilityService
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
class FacilityController @Autowired constructor(
		private val facilityService: FacilityService
) {

	@GetMapping("/games/{gameId}/facilities")
	@Transactional
	fun getCities(
			@PathVariable gameId: Long
	): List<FacilityModel> = facilityService.findByGame(gameId)

	@PostMapping("/games/{gameId}/facilities")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createFacility(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): FacilityModel = facilityService.createFacility(gameId, name)

	@GetMapping("/games/{gameId}/facilities/{facilityId}")
	@Transactional
	fun getFacility(
			@PathVariable gameId: Long,
			@PathVariable facilityId: Long
	): FacilityModel = facilityService.getFacility(gameId, facilityId)

	@DeleteMapping("/games/{gameId}/facilities/{facilityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteFacility(@PathVariable gameId: Long, @PathVariable facilityId: Long) {
		facilityService.deleteFacility(gameId, facilityId)
	}
}