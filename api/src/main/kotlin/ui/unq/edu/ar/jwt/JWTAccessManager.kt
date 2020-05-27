package ui.unq.edu.ar.jwt

import domain.UNQFlix
import domain.User
import io.javalin.core.security.*
import io.javalin.http.*
import ui.unq.edu.ar.Roles
import ui.unq.edu.ar.excepciones.TokenNotFoundException


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
        val token = ctx.header("Authentication")
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
