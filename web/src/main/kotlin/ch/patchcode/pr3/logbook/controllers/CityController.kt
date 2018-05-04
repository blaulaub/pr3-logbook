package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.entities.CityJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.model.CityModel
import ch.patchcode.pr3.logbook.repositories.CityRepository
import ch.patchcode.pr3.logbook.services.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController @Autowired constructor(
		private val gameService: GameService,
		private val cityRepository: CityRepository
) {

	@GetMapping("/games/{gameId}/cities")
	@Transactional
	fun getCities(
			@PathVariable gameId: Long
	): List<CityModel> = cityRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	@PostMapping("/games/{gameId}/cities")
	@Transactional
	fun createCity(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): CityModel = cityRepository.save(CityJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@GetMapping("/games/{gameId}/cities/{cityId}")
	@Transactional
	fun getCity(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): CityModel {
		gameService.resolveGame(gameId)
		val city = resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)
		return city.toModel()
	}

	private fun resolveCity(cityId: Long): CityJpa {
		val city = cityRepository.findById(cityId)
		if (!city.isPresent) throw EntityNotFoundException("City #" + cityId)
		return city.get()
	}
}