package ch.patchcode.pr3.logbook.savegames

import ch.patchcode.pr3.logbook.utils.CustomObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SaveGameController @Autowired constructor(
		private val saveGameService: SaveGameService
) {

	@GetMapping("/games/{gameId}/raw")
	fun getGame(@PathVariable gameId: Long): HttpEntity<String> {
		val headers = LinkedMultiValueMap<String, String>()
		headers.add("Content-Disposition", "attachement");
		return HttpEntity(CustomObjectMapper.writeValueAsString(saveGameService.getGame(gameId)), headers)
	}

	@PutMapping("/games/{gameId}/raw")
	fun putGame(@PathVariable gameId: Long, @RequestBody data: SaveGame) = saveGameService.putGame(gameId, data);
}

