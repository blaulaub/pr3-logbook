package ch.patchcode.pr3.logbook.citydetails

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CityDetailsController @Autowired constructor(
		private val cityDetailsService: CityDetailsService
) {

	@GetMapping("/games/{gameId}/cities/{cityId}/cityDetails")
	fun getCityDetails(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): CityDetailsModel {
		return cityDetailsService.findByGameAndCity(gameId, cityId)
	}

	@PutMapping("/games/{gameId}/cities/{cityId}/cityDetails")
	fun updateCityDetails(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long,
			@RequestBody details: CityDetailsModel
	): CityDetailsModel = cityDetailsService.updateCityDetails(gameId, cityId, details)
}