package com.victorgomes.test.myapplication.data.util

import com.victorgomes.test.myapplication.view.util.valueOrDefault

sealed class NetworkException(
    open val code: Int,
    open val friendlyMessage: String,
    override val message: String
) : Throwable(message)

class UnknownException(
    override val message: String = "Unknow",
    override val friendlyMessage: String = "Erro Desconhecido"
) : NetworkException(1000, friendlyMessage, message)

class ErrorConnectionException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(33, friendlyMessage, message)

class RequestTimeoutException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(34, friendlyMessage, message)

class BadRequestException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(400, friendlyMessage, message)

class WrongKeyCredentialsException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(401, friendlyMessage, message)

class AccessForbiddenException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(403, friendlyMessage, message)

class ResourceNotFoundException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(404, friendlyMessage, message)

class InternalErrorException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(500, friendlyMessage, message)

class ServiceUnavailableException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(503, friendlyMessage, message)

class JsonFormatException(
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(504, friendlyMessage, message)

class StatusRawAnalisysException(
    override val code: Int,
    override val message: String,
    override val friendlyMessage: String
) : NetworkException(code, friendlyMessage, message)

fun Throwable.tryGetFriendlyMessage(returnDefaultError: Boolean = false): String {
    return runCatching {
        (this as NetworkException).friendlyMessage
    }.getOrDefault(if (returnDefaultError) this.message.valueOrDefault("Erro desconhecido") else "")


}