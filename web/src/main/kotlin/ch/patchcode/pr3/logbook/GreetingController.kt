package ch.patchcode.pr3.logbook

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import org.springframework.beans.factory.annotation.Autowired

@RestController
class GreetingController @Autowired constructor(private val greetingRepository: GreetingRepository) {

	@GetMapping("/greeting")
	fun greeting(
			@RequestParam(defaultValue = "World") name: String
	) = greetingRepository.save(Greeting(content = name))

}