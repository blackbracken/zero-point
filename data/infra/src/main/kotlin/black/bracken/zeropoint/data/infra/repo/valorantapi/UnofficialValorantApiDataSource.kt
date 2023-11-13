package black.bracken.zeropoint.data.infra.repo.valorantapi

import black.bracken.zeropoint.data.infra.repo.valorantapi.response.AccountResponse
import black.bracken.zeropoint.data.infra.repo.valorantapi.response.MatchesResponse
import black.bracken.zeropoint.data.kernel.model.Region
import black.bracken.zeropoint.data.kernel.model.error.ValorantApiError
import black.bracken.zeropoint.util.Res
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
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
  ): Res<AccountResponse, Error> = binding {
    val resp = client.get("$URL_API_SERVER/v1/account/$riotId/$tagline")
    val jsonElement = runCatching { json.parseToJsonElement(resp.bodyAsText()) }
      .mapError { Error.SerializationError }
      .bind()

    jsonElement.jsonObject
      .parseToEntityOrError<AccountResponse>()
      .bind()
  }

  suspend fun getAccount(
    playerId: String,
  ): Res<AccountResponse, Error> = binding {
    val resp = client.get("$URL_API_SERVER/v1/by-puuid/account/$playerId")
    val jsonElement = runCatching { json.parseToJsonElement(resp.bodyAsText()) }
      .mapError { Error.SerializationError }
      .bind()

    jsonElement.jsonObject
      .parseToEntityOrError<AccountResponse>()
      .bind()
  }

  suspend fun getMatches(
    riotId: String,
    tagline: String,
    region: Region,
  ): Res<List<MatchesResponse>, Error> = binding {
    val resp = client.get("$URL_API_SERVER/v3/matches/${region.raw}/$riotId/$tagline")
    val jsonElement = runCatching { json.parseToJsonElement(resp.bodyAsText()) }
      .mapError { Error.SerializationError }
      .bind()

    jsonElement.jsonObject
      .parseToEntityOrError<List<MatchesResponse>>(::parserToEntities)
      .bind()
  }

  private inline fun <reified E : Any> JsonObject.parseToEntityOrError(
    parser: (JsonElement) -> E = ::parserToEntity,
  ): Res<E, Error> {
    return when (val statusCode = get("status")?.jsonPrimitive?.content?.toIntOrNull()) {
      200 -> {
        get("data")
          ?.let { jsonElement -> Ok(parser(jsonElement)) }
          ?: Err(Error.SerializationError)
      }

      is Int -> {
        val valorantApiError = ValorantApiError.fromStatusCode(statusCode)
        Err(Error.ApiError(valorantApiError))
      }

      else -> Err(Error.SerializationError)
    }
  }

  private inline fun <reified E : Any> parserToEntity(jsonElement: JsonElement): E =
    json.decodeFromJsonElement(jsonElement)

  private inline fun <reified E : Any> parserToEntities(jsonElement: JsonElement): List<E> =
    jsonElement.jsonArray.map { json.decodeFromJsonElement(jsonElement) }

  companion object {
    const val URL_API_SERVER = "https://api.henrikdev.xyz/valorant"
  }

  sealed interface Error {
    object SerializationError : Error
    data class ApiError(val valorantApiError: ValorantApiError) : Error
  }

}
