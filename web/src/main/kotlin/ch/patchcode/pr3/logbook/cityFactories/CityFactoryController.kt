package ch.patchcode.pr3.logbook.cityFactories

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CityFactoryController {

	@GetMapping("/games/{gameId}/cities/{cityId}/factoryCounts")
	@Transactional
	fun getCityProducts(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): List<FactoryCountModel> = listOf(
			FactoryCountModel(1, "Metzger", rivalCount = 3, playerCount = 1),
			FactoryCountModel(2, "Schneider", rivalCount = 7, playerCount = 0)
	)

}