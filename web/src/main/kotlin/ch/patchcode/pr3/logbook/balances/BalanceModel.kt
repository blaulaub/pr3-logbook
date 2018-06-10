package ch.patchcode.pr3.logbook.balances

data class BalanceModel(
		val good: String,
		var byRivalFactoryConsumption: Double = 0.0,
		var byRivalFactoryProduction: Double = 0.0,
		var byPlayerFactoryConsumption: Double = 0.0,
		var byPlayerFactoryProduction: Double = 0.0,
		var byCityConsumption: Double = 0.0
)