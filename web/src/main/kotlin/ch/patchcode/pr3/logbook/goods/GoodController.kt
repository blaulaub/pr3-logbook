package ch.patchcode.pr3.logbook.goods

import ch.patchcode.pr3.logbook.cities.CityModel
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
class GoodController @Autowired constructor(
		private val goodService: GoodService
) {

	@GetMapping("/games/{gameId}/goods")
	@Transactional
	fun getGoods(
			@PathVariable gameId: Long
	): List<GoodModel> = goodService.findByGame(gameId)

	@PostMapping("/games/{gameId}/goods")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createGood(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): GoodModel = goodService.createGood(gameId, name)

	@GetMapping("/games/{gameId}/goods/{goodId}")
	@Transactional
	fun getGood(
			@PathVariable gameId: Long,
			@PathVariable goodId: Long
	): GoodModel = goodService.getGood(gameId, goodId)

	@DeleteMapping("/games/{gameId}/goods/{goodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteCity(@PathVariable gameId: Long, @PathVariable goodId: Long) {
		goodService.deleteGood(gameId, goodId)
	}
}