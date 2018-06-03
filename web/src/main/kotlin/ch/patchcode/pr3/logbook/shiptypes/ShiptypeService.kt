package ch.patchcode.pr3.logbook.shiptypes

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShiptypeService @Autowired constructor(
		private val shiptypeRepository: ShiptypeRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = shiptypeRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	@Transactional
	fun createShiptype(gameId: Long, name: String) = shiptypeRepository.save(ShiptypeJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@Transactional
	fun getShiptype(gameId: Long, shiptypeId: Long): ShiptypeModel {
		gameService.resolveGame(gameId)
		val shiptype = resolveShiptype(shiptypeId)
		if (shiptype.game.id != gameId) throw EntityNotFoundException("Shiptype #" + shiptypeId)
		return shiptype.toModel()
	}

	@Transactional
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

	@Transactional
	fun deleteShiptype(gameId: Long, shiptypeId: Long) {
		val game = gameService.resolveGame(gameId)
		shiptypeRepository.deleteByGameAndId(game, shiptypeId)
	}

	fun deleteByGameId(gameId: Long) = shiptypeRepository.deleteByGameId(gameId)
	
	private fun resolveShiptype(shiptypeId: Long): ShiptypeJpa {
		val shiptype = shiptypeRepository.findById(shiptypeId)
		if (!shiptype.isPresent) throw EntityNotFoundException("Shiptype #" + shiptypeId)
		return shiptype.get()
	}

}