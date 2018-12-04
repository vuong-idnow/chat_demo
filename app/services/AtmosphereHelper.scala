package services

import controllers.ChatController
import play.Configuration
import javax.inject.Inject
import org.atmosphere.cpr.ApplicationConfig
import play.api.inject.ApplicationLifecycle
import org.atmosphere.play.AtmosphereCoordinator.instance

import scala.concurrent.Future

class AtmosphereHelper @Inject()(applicationLifecycle: ApplicationLifecycle, configuration: Configuration) {

  @Inject def init = {
    instance.framework.addInitParameter(ApplicationConfig.BROADCASTER_LIFECYCLE_POLICY, configuration.getString(ApplicationConfig.BROADCASTER_LIFECYCLE_POLICY))
    instance.discover(classOf[ChatService])
    instance.ready()
    applicationLifecycle.addStopHook { () =>
      instance.shutdown()
      Future.successful(())
    }
  }

}
