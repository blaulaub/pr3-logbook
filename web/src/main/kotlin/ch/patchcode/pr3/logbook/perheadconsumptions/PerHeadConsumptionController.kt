package ch.patchcode.pr3.logbook.perheadconsumptions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping

@RestController
class PerHeadConsumptionController @Autowired constructor(
		private val perHeadConsumptionService: PerHeadConsumptionService
) {

	@GetMapping("/games/{gameId}/perHeadConsumptions")
	fun getGameSettings(
			@PathVariable gameId: Long
	): List<PerHeadConsumptionModel> = perHeadConsumptionService.findByGame(gameId)

	@PutMapping("/games/{gameId}/perHeadConsumptions")
	fun updateGameSettings(
			@PathVariable gameId: Long,
			@RequestBody perHeadConsumptions: List<PerHeadConsumptionModel>
	): List<PerHeadConsumptionModel> = perHeadConsumptionService.updatePerHeadConsumptions(gameId, perHeadConsumptions)
}