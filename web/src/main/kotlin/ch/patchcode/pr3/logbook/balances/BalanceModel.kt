package ch.patchcode.pr3.logbook.balances

data class BalanceModel(
		val good: String,
		var byRivalFactories: Double = 0.0,
		var byPlayerFactories: Double = 0.0,
		var byCityConsumption: Double = 0.0
)