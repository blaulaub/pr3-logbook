package ch.patchcode.pr3.logbook.perheadconsumptions

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PerHeadConsumptionService @Autowired constructor(
		private val perHeadConsumptionRepository: PerHeadConsumptionRepository,
		private val goodService: GoodService
) {

	@Transactional
	fun findByGame(gameId: Long): List<PerHeadConsumptionModel> {
		val goods = goodService.resolveByGameId(gameId)
		return goods.map { it -> getConsumption(it).toModel() }
	}

	@Transactional
	fun findByGameAndGoodName(gameId: Long, goodName: String): PerHeadConsumptionModel {
		val good = goodService.resolveByGameIdAndName(gameId, goodName)
		val result = perHeadConsumptionRepository.findOneByGood(good)
		if (result == null) throw EntityNotFoundException("PerHeadConsumption for Good #" + good.id)
		return result.toModel()
	}

	private fun getConsumption(good: GoodJpa): PerHeadConsumptionJpa {
		return perHeadConsumptionRepository.findOneByGood(good) ?: PerHeadConsumptionJpa(
				good = good,
				consumptionPerHundred = 0.0
		)
	}

	@Transactional
	fun updatePerHeadConsumptions(gameId: Long, perHeadConsumptions: List<PerHeadConsumptionModel>): List<PerHeadConsumptionModel> {
		return perHeadConsumptions.map { it -> updateConsumption(gameId, it).toModel() }
	}

	private fun updateConsumption(gameId: Long, item: PerHeadConsumptionModel): PerHeadConsumptionJpa {
		val good = goodService.resolveByGameIdAndName(gameId, item.good)
		val entity = getConsumption(good)
		entity.consumptionPerHundred = item.consumptionPerHundred
		return perHeadConsumptionRepository.save(entity)
	}
}