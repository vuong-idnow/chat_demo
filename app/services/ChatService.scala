package services

import com.fasterxml.jackson.databind.ObjectMapper
import model.Message
import org.atmosphere.cache.DefaultBroadcasterCache
import org.atmosphere.config.managed.{Decoder, Encoder}
import org.atmosphere.config.service.{Disconnect, ManagedService, Ready}
import org.atmosphere.cpr.AtmosphereResourceEvent
import org.atmosphere.interceptor.{AtmosphereResourceLifecycleInterceptor, CacheHeadersInterceptor, CorsInterceptor, SuspendTrackerInterceptor}
import play.api.Logger

@ManagedService(path = "/chat", interceptors = Array(classOf[AtmosphereResourceLifecycleInterceptor], classOf[SuspendTrackerInterceptor], classOf[CorsInterceptor], classOf[CacheHeadersInterceptor]),
  broadcasterCache = classOf[DefaultBroadcasterCache])
class ChatService {
  @Ready def onReady(event : AtmosphereResourceEvent) = Logger.info(s"Ready $event.getResource.uuid")
  @Disconnect def onDisconnect(event : AtmosphereResourceEvent) = Logger.info(s"Disconnect ${event.getResource.uuid()}")
  @org.atmosphere.config.service.Message(encoders = Array(classOf[JacksonEncoder]), decoders = Array(classOf[JacksonDecoder]))
  def onMessage(chatMessage: Message): model.Message = {
    Logger.info(s"onMessage $chatMessage")
    chatMessage
  }
}


case class JacksonEncoder() extends Encoder[Message, String] {
  val mapper: ObjectMapper = new ObjectMapper()
  override def encode(s: Message): String = mapper.writeValueAsString(s)
}

case class JacksonDecoder() extends Decoder[String, Message] {
  val mapper: ObjectMapper = new ObjectMapper()
  override def decode(s: String): Message = mapper.readValue(s, classOf[Message])

}

