package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.goods.GoodJpa

interface Turnover {

	val facility: FacilityJpa;
	val good: GoodJpa;
	var amount: Double;

	fun toModel() = TurnoverModel(
			good = this.good.toModel(),
			amount = this.amount
	)
}