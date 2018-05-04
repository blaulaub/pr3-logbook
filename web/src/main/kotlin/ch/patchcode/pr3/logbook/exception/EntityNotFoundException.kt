package ch.patchcode.pr3.logbook.exception

import java.lang.RuntimeException
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EntityNotFoundException(message: String) : RuntimeException(message)