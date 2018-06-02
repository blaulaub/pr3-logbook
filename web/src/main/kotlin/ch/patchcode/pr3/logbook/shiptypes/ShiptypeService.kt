package ch.patchcode.pr3.logbook.shiptypes

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShiptypeService @Autowired constructor(
		private val shiptypeRepository: ShiptypeRepository,
		private val gameService: GameService
) {

	fun findByGame(gameId: Long) = shiptypeRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	fun createShiptype(gameId: Long, name: String) = shiptypeRepository.save(ShiptypeJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	fun getShiptype(gameId: Long, shiptypeId: Long): ShiptypeModel {
		gameService.resolveGame(gameId)
		val shiptype = resolveShiptype(shiptypeId)
		if (shiptype.game.id != gameId) throw EntityNotFoundException("Shiptype #" + shiptypeId)
		return shiptype.toModel()
	}

	fun updateShiptype(gameId: Long, shiptypeId: Long, shiptype: ShiptypeModel): ShiptypeModel {
		if (shiptypeId != shiptype.id) throw IllegalArgumentException("URL shiptypeId does not match model shiptype id")
		gameService.resolveGame(gameId)
		val oldShiptype = resolveShiptype(shiptype.id)
		if (oldShiptype.game.id != gameId) throw EntityNotFoundException("Shiptype #" + shiptype.id)
		return shiptypeRepository.save(ShiptypeJpa(
				id = shiptype.id,
				game = oldShiptype.game,
				name = shiptype.name,
				cargoSpace = shiptype.cargoSpace,
				maneuverability = shiptype.maneuverability,
				draft = shiptype.draft,
				minSpeed = shiptype.minSpeed,
				maxSpeed = shiptype.maxSpeed,
				cannons = shiptype.cannons,
				sailors = shiptype.sailors,
				hitPoints = shiptype.hitPoints,
				dailyCost = shiptype.dailyCost,
				price = shiptype.price
		)).toModel()
	}

	fun deleteShiptype(gameId: Long, shiptypeId: Long) {
		val game = gameService.resolveGame(gameId)
		shiptypeRepository.deleteByGameAndId(game, shiptypeId)
	}

	private fun resolveShiptype(shiptypeId: Long): ShiptypeJpa {
		val shiptype = shiptypeRepository.findById(shiptypeId)
		if (!shiptype.isPresent) throw EntityNotFoundException("Shiptype #" + shiptypeId)
		return shiptype.get()
	}

}