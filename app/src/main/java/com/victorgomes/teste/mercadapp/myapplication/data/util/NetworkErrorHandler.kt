package com.victorgomes.teste.mercadapp.myapplication.data.util

import com.squareup.moshi.JsonEncodingException
import com.victorgomes.teste.mercadapp.myapplication.view.util.logDebug
import retrofit2.HttpException
import java.net.ConnectException
import java.util.concurrent.TimeoutException

class NetworkErrorHandler {
    companion object {
        fun handlerNetworkError(error: Throwable): NetworkException {
            error.printStackTrace()
            if (error is NetworkException) {
                return error
            }
            if (error is HttpException) {
                if (error.response() != null) {
                    val friendlyMessage: String
                    val debugMessage = error.response()?.raw()?.toString() ?: ""
                    val message = getErrorMessageByCode(error.code())

                    friendlyMessage = message

                    return when (error.code()) {
                        400 -> BadRequestException(debugMessage, friendlyMessage)
                        else -> UnknownException(
                            debugMessage,
                            friendlyMessage
                        )
                    }
                }
            } else if (error is TimeoutException)
                return RequestTimeoutException(
                    "Request timeout exception",
                    "Tempo de resposta com o Servidor excedido!"
                )
            else if (error is ConnectException)
                return ErrorConnectionException(
                    "Connect exception ${error.message}",
                    "Falha ao conectar com o servidor!"
                )
            else if (error is JsonEncodingException)
                return JsonFormatException(
                    "Json format ${error.message}",
                    "Ocorreu um erro ao ler os dados do servidor!"
                )
            return UnknownException()
        }
    }
}

fun getErrorMessageByCode(code: Int): String {
    logDebug(
        "ErrorByCode",
        "ErroCode: $code"
    )
    return when (code) {
        400 -> "Parâmetro inválido"
        404 -> "Recurso não encontrado"
        500 -> "Problema no servidor"
        else -> "Erro desconhecido"
    }
}
