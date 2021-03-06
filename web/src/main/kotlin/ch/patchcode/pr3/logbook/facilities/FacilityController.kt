package ch.patchcode.pr3.logbook.facilities

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class FacilityController @Autowired constructor(
		private val facilityService: FacilityService
) {

	@GetMapping("/games/{gameId}/facilities")
	fun getCities(
			@PathVariable gameId: Long
	): List<FacilityModel> = facilityService.findByGame(gameId)

	@PostMapping("/games/{gameId}/facilities")
	@ResponseStatus(HttpStatus.CREATED)
	fun createFacility(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): FacilityModel = facilityService.createFacility(gameId, name)

	@GetMapping("/games/{gameId}/facilities/{facilityId}")
	fun getFacility(
			@PathVariable gameId: Long,
			@PathVariable facilityId: Long
	): FacilityModel = facilityService.getFacility(gameId, facilityId)

	@PutMapping("/games/{gameId}/facilities/{facilityId}")
	fun updateFacility(
			@PathVariable gameId: Long,
			@PathVariable facilityId: Long,
			@RequestBody facility: FacilityModel
	): FacilityModel = facilityService.updateFacility(gameId, facilityId, facility)

	@DeleteMapping("/games/{gameId}/facilities/{facilityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteFacility(@PathVariable gameId: Long, @PathVariable facilityId: Long) {
		facilityService.deleteFacility(gameId, facilityId)
	}
}
