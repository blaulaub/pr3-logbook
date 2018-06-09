package ch.patchcode.pr3.logbook.perheadconsumptions

import ch.patchcode.pr3.logbook.goods.GoodJpa
import org.springframework.data.repository.CrudRepository

interface PerHeadConsumptionRepository : CrudRepository<PerHeadConsumptionJpa, Long> {
	fun findOneByGood(good: GoodJpa) : PerHeadConsumptionJpa?
}