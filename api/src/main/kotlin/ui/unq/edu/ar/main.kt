package ui.unq.edu.ar

import data.getUNQFlix
import domain.ExistsException
import domain.IdGenerator
import domain.NotFoundException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.http.NotFoundResponse
import ui.unq.edu.ar.JWT.JWTAccessManager
import ui.unq.edu.ar.JWT.TokenJWT

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
        val unqflixController = UnqflixController(tokenJWT, unqFlix)
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            it.accessManager(jwtAccessManager)
        }
        app.start(port)

        app.routes {
            path("register") {
                post(unqflixController::register, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("login") {
                post(unqflixController::login, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("user") {
                get(unqflixController::getUser, mutableSetOf<Role>(Roles.USER))
                path("lastSeen") {
                    post(unqflixController::postLastSeen, mutableSetOf<Role>(Roles.USER))
                }
                path("fav/:contentId") {
                    post(unqflixController::postFavById, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("content") {
                get(unqflixController::getContent, mutableSetOf<Role>(Roles.USER))
                path(":contentId") {
                    get(unqflixController::getContentById, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("banners") {
                get(unqflixController::getBanners, mutableSetOf<Role>(Roles.USER))
            }
            path("search") {
                get(unqflixController::search, mutableSetOf<Role>(Roles.USER))
            }
        }

        return app
    }
}