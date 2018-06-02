package ch.patchcode.pr3.logbook.cities

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController @Autowired constructor(
		private val cityService: CityService
) {

	@GetMapping("/games/{gameId}/cities")
	@Transactional
	fun getCities(
			@PathVariable gameId: Long
	): List<CityModel> = cityService.findByGame(gameId)

	@PostMapping("/games/{gameId}/cities")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createCity(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): CityModel = cityService.createCity(gameId, name)

	@GetMapping("/games/{gameId}/cities/{cityId}")
	@Transactional
	fun getCity(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): CityModel = cityService.getCity(gameId, cityId)

	@DeleteMapping("/games/{gameId}/cities/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteCity(@PathVariable gameId: Long, @PathVariable cityId: Long) {
		cityService.deleteCity(gameId, cityId)
	}
}