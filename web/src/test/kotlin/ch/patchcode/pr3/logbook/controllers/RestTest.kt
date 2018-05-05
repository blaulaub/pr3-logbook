package ch.patchcode.pr3.logbook.controllers

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ch.patchcode.pr3.logbook.PortRoyale3LogbookApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class RestTest {

	@Autowired
	lateinit var context: WebApplicationContext

	lateinit var mvc: MockMvc

	@Before
	fun setup() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
	}

	@Test
	fun test() {
		val result = mvc.perform(post("/games").param("captainsName", "Morgan"))
		result
				.andDo(print())
				.andExpect(status().isCreated())
	}
}