package ch.patchcode.pr3.logbook.utils

import org.springframework.test.web.servlet.ResultActions

inline fun <reified T> ResultActions.contentAs(): T {
	val content = this.andReturn().getResponse().getContentAsString()
	return CustomObjectMapper.readValue(content, T::class.java)
}
