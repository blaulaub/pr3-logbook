package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.utils.CustomObjectMapper
import ch.patchcode.pr3.logbook.utils.contentAs
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.ResultActions


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
	@Transactional
	fun test() {
		val result = mvc.perform(post("/games").param("captainsName", "Morgan"))

		result.andExpect(status().isCreated())
		assertThat(result.contentAs<Game>().captainsName, equalTo("Morgan"))
	}
}