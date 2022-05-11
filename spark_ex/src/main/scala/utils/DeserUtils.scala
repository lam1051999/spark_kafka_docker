package utils
import io.confluent.kafka.schemaregistry.client.rest.RestService

object DeserUtils {
  def getSchemaMessage(schemaRegistryAddress: String, topic: String) = {
    val restService = new RestService(schemaRegistryAddress)
    val valueRestResponseSchema = restService.getLatestVersion(topic + "-value")
    valueRestResponseSchema.getSchema
  }
}
