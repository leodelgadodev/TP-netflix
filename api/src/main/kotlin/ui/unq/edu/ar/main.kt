package ui.unq.edu.ar

import domain.ExistsException
import domain.NotFoundException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.http.NotFoundResponse

fun main() {
    val app = Javalin.create{
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
    app.start(7000)

    val unqflixController = UnqflixController()

    app.routes {
        path("register"){
            post(unqflixController::register)
        }
        path("login"){
            post(unqflixController::login)
        }
        path("user"){
            get(unqflixController::getUser)
            path("lastSeen"){
                post(unqflixController::postLastSeen)
            }
            path("fav/:contentId"){
                post(unqflixController::postFavById)
            }
        }
        path("content"){
            get(unqflixController::getContent)
            path(":contentId"){
                get(unqflixController::getContentById)
            }
        }
        path("banners"){
            get(unqflixController::getBanners)
        }
        path("search"){
            get(unqflixController::search)
        }
    }
}
