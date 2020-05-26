package ui.unq.edu.ar.JWT

import domain.UNQFlix
import domain.User
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import ui.unq.edu.ar.Roles
import ui.unq.edu.ar.excepciones.TokenNotFoundException
import ui.unq.edu.ar.excepciones.UserNotFoundException
import ui.unq.edu.ar.mappers.UserViewMapper


class JWTAccessManager(val tokenJWT: TokenJWT, val unqFlix: UNQFlix): AccessManager {

    fun getUser(token: String){
        var user: User?
        try {
            val userId = tokenJWT.validate(token)
            user  = unqFlix.users.firstOrNull { it.id == userId }
        } catch (e: TokenNotFoundException) {
            throw UnauthorizedResponse("Token not found")
        }
        if (user === null){
            throw UnauthorizedResponse("Invalid Token")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            token == null && roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            roles.contains(Roles.USER) -> {
                getUser(token)
                handler.handle(ctx)
            }
        }
    }
}
