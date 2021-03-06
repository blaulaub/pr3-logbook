package ch.patchcode.pr3.logbook.fleets

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FleetService @Autowired constructor(
		private val fleetRepository: FleetRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = fleetRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	@Transactional
	fun createFleet(gameId: Long, name: String) = fleetRepository.save(FleetJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@Transactional
	fun getFleet(gameId: Long, fleetId: Long): FleetModel {
		gameService.resolveGame(gameId)
		val fleet = resolveFleet(gameId, fleetId)
		if (fleet.game.id != gameId) throw EntityNotFoundException("Fleet #" + fleetId)
		return fleet.toModel()
	}

	@Transactional
	fun deleteFleet(gameId: Long, fleetId: Long) {
		val game = gameService.resolveGame(gameId)
		fleetRepository.deleteByGameAndId(game, fleetId)
	}

	fun resolveFleet(gameId: Long, fleetId: Long): FleetJpa {
		val optionalFleet = fleetRepository.findById(fleetId)
		if (!optionalFleet.isPresent) throw EntityNotFoundException("Fleet #" + fleetId)
		val fleet = optionalFleet.get()
		if (fleet.game.id != gameId) throw EntityNotFoundException("Fleet #" + fleetId)
		return fleet
	}
}
