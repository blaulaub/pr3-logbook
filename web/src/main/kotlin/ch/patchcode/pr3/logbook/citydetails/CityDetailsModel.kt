package ch.patchcode.pr3.logbook.citydetails

data class CityDetailsModel(
		val population: Int,
		val warehouses: Int,
		var support: Double,
		var isExportCity: Boolean
)
