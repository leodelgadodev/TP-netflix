package ui.unq.edu.ar

import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import ui.unq.edu.ar.controllers.ContentController
import ui.unq.edu.ar.controllers.UserController
import ui.unq.edu.ar.jwt.*

fun main() {
    UnqflixAPI(7000).init()
}

enum class Roles : Role {
    ANYONE, USER
}

class UnqflixAPI(private val port: Int) {

    val tokenJWT = TokenJWT()
    val unqFlix = getUNQFlix()
    val jwtAccessManager = JWTAccessManager(tokenJWT, unqFlix)

    fun init() : Javalin {
        val userController = UserController(tokenJWT, unqFlix)
        val contentController = ContentController(tokenJWT, unqFlix)
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            it.accessManager(jwtAccessManager)
        }
        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }
        app.start(port)

        app.routes {
            path("register") {
                post(userController::register, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("login") {
                post(userController::login, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("user") {
                get(userController::getUser, mutableSetOf<Role>(Roles.USER))
                path("lastSeen") {
                    post(userController::postLastSeen, mutableSetOf<Role>(Roles.USER))
                }
                path("fav") {
                    post(userController::postFavById, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("content") {
                get(contentController::getContent, mutableSetOf<Role>(Roles.USER))
                path(":contentId") {
                    get(contentController::getContentById, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("banners") {
                get(contentController::getBanners, mutableSetOf<Role>(Roles.USER))
            }
            path("search") {
                get(contentController::search, mutableSetOf<Role>(Roles.USER))
            }
        }

        return app
    }
}