package ui.unq.edu.ar.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import javalinjwt.*
import ui.unq.edu.ar.excepciones.TokenNotFoundException
import ui.unq.edu.ar.mappers.UserLoginMapper


class UserGenerator : JWTGenerator<UserLoginMapper> {
    override fun generate(user: UserLoginMapper, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id", user.id)
        return token.sign(algorithm)
    }
}

class TokenJWT {
    val algorithm = Algorithm.HMAC256("bokita")
    val generator = UserGenerator()
    val verifier = JWT.require(algorithm).build()
    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: UserLoginMapper): String {
        return provider.generateToken(user)
    }

    fun validate(token: String): String {
        val token = provider.validateToken(token)
        if (!token.isPresent) throw TokenNotFoundException()
        return token.get().getClaim("id").asString()
    }
}