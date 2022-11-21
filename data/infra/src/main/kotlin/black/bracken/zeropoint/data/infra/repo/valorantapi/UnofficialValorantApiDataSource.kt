package black.bracken.zeropoint.data.infra.repo.valorantapi

import black.bracken.zeropoint.data.infra.repo.valorantapi.response.AccountResponse
import black.bracken.zeropoint.data.kernel.domain.error.ValorantApiException
import black.bracken.zeropoint.data.kernel.domain.error.fromStatusCode
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// cf. https://app.swaggerhub.com/apis-docs/Henrik-3/HenrikDev-API
class UnofficialValorantApiDataSource {

  private val client = HttpClient()
  private val json = Json {
    ignoreUnknownKeys = true
  }

  suspend fun getAccount(
    riotId: String,
    tagline: String,
  ): AccountResponse {
    val response = client.get("$URL_API_SERVER/v1/account/$riotId/$tagline")
    val jsonObject = json.parseToJsonElement(response.bodyAsText()).jsonObject

    return jsonObject.mapToEntityOrThrow()
  }

  @Throws(
    SerializationException::class,
    ValorantApiException::class,
  )
  private inline fun <reified E : Any> JsonObject.mapToEntityOrThrow(): E {
    return when (val statusCode = get("status")?.jsonPrimitive?.content?.toIntOrNull()) {
      200 -> get("data")
        ?.let { jsonElement -> json.decodeFromJsonElement(jsonElement) }
        ?: throw SerializationException()

      is Int -> throw ValorantApiException.fromStatusCode(statusCode)
      else -> throw SerializationException()
    }
  }

  companion object {
    const val URL_API_SERVER = "https://api.henrikdev.xyz/valorant"
  }

}
