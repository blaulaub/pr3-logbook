package ch.patchcode.pr3.logbook.shiptypes

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
class ShiptypeController @Autowired constructor(
		private val shiptypeService: ShiptypeService
) {

	@GetMapping("/games/{gameId}/shiptypes")
	fun getCities(
			@PathVariable gameId: Long
	): List<ShiptypeModel> = shiptypeService.findByGame(gameId)

	@PostMapping("/games/{gameId}/shiptypes")
	@ResponseStatus(HttpStatus.CREATED)
	fun createShiptype(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): ShiptypeModel = shiptypeService.createShiptype(gameId, name)

	@GetMapping("/games/{gameId}/shiptypes/{shiptypeId}")
	fun getShiptype(
			@PathVariable gameId: Long,
			@PathVariable shiptypeId: Long
	): ShiptypeModel = shiptypeService.getShiptype(gameId, shiptypeId)

	@PutMapping("/games/{gameId}/shiptypes/{shiptypeId}")
	fun updateShiptype(
			@PathVariable gameId: Long,
			@PathVariable shiptypeId: Long,
			@RequestBody shiptype: ShiptypeModel
	): ShiptypeModel = shiptypeService.updateShiptype(gameId, shiptypeId, shiptype)

	@DeleteMapping("/games/{gameId}/shiptypes/{shiptypeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteShiptype(@PathVariable gameId: Long, @PathVariable shiptypeId: Long) {
		shiptypeService.deleteShiptype(gameId, shiptypeId)
	}
}
