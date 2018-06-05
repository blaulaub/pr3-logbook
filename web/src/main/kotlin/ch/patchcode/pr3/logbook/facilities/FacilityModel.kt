package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.goods.GoodModel

data class TurnoverModel(
		val good: GoodModel,
		val amount: Double)

data class FacilityModel(
		val id: Long,
		val name: String,
		val constructionCost: Int? = null,
		val constructionDays: Int? = null,
		val maintenancePerDay: Int? = null,
		val workers: Int? = null,
		val material: List<TurnoverModel> = ArrayList(),
		val consumption: List<TurnoverModel> = ArrayList(),
		val production: TurnoverModel? = null)
