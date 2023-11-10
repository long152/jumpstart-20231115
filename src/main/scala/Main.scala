import com.greenfossil.thorium.{Action, Server}
import controllers.jumpstart.day2.HomeController

@main def start: Unit =
  Server()
    .addServices(HomeController)
    .addDocService("/docs")
    .start()
