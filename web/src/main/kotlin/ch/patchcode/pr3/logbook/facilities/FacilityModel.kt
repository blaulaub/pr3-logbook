package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.goods.GoodModel

data class ConsumptionModel(
		val good: GoodModel,
		val amount: Double)

data class ProductionModel(
		val good: GoodModel,
		val amount: Double)

data class FacilityModel(
		val id: Long,
		val name: String,
		val constructionCost: Int? = null,
		val constructionDays: Int? = null,
		val maintenancePerDay: Int? = null,
		val workers: Int? = null,
		val consumption: List<ConsumptionModel> = ArrayList(),
		val production: ProductionModel? = null)
