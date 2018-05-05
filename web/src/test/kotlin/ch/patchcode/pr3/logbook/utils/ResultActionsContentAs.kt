package ch.patchcode.pr3.logbook.utils

import org.springframework.test.web.servlet.ResultActions
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

inline fun <reified T: Any> ResultActions.contentAs(): T {
	val content = this.andReturn().getResponse().getContentAsString()
	return CustomObjectMapper.readValue(content, jacksonTypeRef<T>())
}
