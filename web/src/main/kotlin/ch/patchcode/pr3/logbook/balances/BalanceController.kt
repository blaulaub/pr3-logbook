package ch.patchcode.pr3.logbook.balances

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController @Autowired constructor(
		private val balanceService: BalanceService
) {

	@GetMapping("/games/{gameId}/goodBalances")
	fun getBalances(@PathVariable gameId: Long) : List<BalanceModel> =  balanceService.getBalanceByGame(gameId)
}