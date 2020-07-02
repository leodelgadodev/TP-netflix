package ui.unq.edu.ar.controllers

import domain.*
import domain.Available
import io.javalin.http.*
import ui.unq.edu.ar.jwt.TokenJWT
import ui.unq.edu.ar.mappers.*
import java.lang.NullPointerException

class UserController(val tokenJWT: TokenJWT, val unqFlix: UNQFlix) {

    private val idGenerator = IdGenerator()

    fun register(ctx : Context) {
        try {
            val newUser = ctx.bodyValidator<UserRegisterMapper>()
                    .check({!it.email.isNullOrEmpty()}, "Request Invalido: Falta Email.")
                    .check({!it.name.isNullOrEmpty()}, "Request Invalido: Falta Name.")
                    .check({!it.password.isNullOrEmpty()}, "Request Invalido: Falta Password.")
                    .check({!it.image.isNullOrEmpty()}, "Request Invalido: Falta Image.")
                    .check({!it.creditCard.isNullOrEmpty()}, "Request Invalido: Falta Credit Card.")
                    .get()

            if (!emailExists(newUser.email!!)) {
                val id = idGenerator.nextUserId()
                val user = User(id, newUser.name!!, newUser.creditCard!!,
                        newUser.image!!, newUser.email, newUser.password!!, mutableListOf(), mutableListOf())
                unqFlix.addUser(user)
                ctx.header("Authentication", tokenJWT.generateToken(UserLoginMapper(user.id, user.email, user.password)))
                ctx.status(201)
                ctx.json(mapOf("result" to "ok"))
            } else {
                throw ConflictResponse("Ya existe un usuario con el mail ${newUser.email}.")
            }
        } catch(e: NullPointerException) {
            throw BadRequestResponse("Request inválido: Faltan parámetros.")
        }
    }

    private fun emailExists(email: String): Boolean {
        return unqFlix.users.map {
            UserViewMapper(it.id, it.name, it.email, it.image)
        }.toMutableList().any { it.email == email }
    }

    fun login(ctx: Context) {
        val loginUser = ctx.bodyValidator<UserLoginMapper>()
                .check({!it.email.isNullOrEmpty()}, "Request Invalido: Falta Email.")
                .check({!it.password.isNullOrEmpty()}, "Request Invalido: Falta Password.")
                .get()

        val user : User? = unqFlix.users.firstOrNull { it.email == loginUser.email && it.password == loginUser.password }
        if(user !== null) {
            loginUser.id = user.id
            ctx.header("Authentication", tokenJWT.generateToken(loginUser))
            ctx.json(mapOf("result" to "ok"))
        } else {
            ctx.status(404)
            ctx.json(mapOf("result" to "error", "message" to "user not found"))
        }
    }

    fun getUser(ctx : Context) {
        val idUser : String = tokenJWT.validate(ctx.header("Authentication")!!)
        val user = unqFlix.users.find { it.id == idUser }
        val favs = user!!.favorites.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass ) }.toMutableList()
        val lastSeen = user.lastSeen.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }.toMutableList()

        val userMapper = GetUserViewMapper(user.name,user.image,favs,lastSeen)
        ctx.json(userMapper)
    }

    fun postFavById(ctx : Context){
        val idUser : String = tokenJWT.validate(ctx.header("Authentication")!!)
        val contentId = ctx.bodyValidator<IdMapper>().check({it.id !== null}).get()
        try {
            unqFlix.addOrDeleteFav(idUser, contentId.id!!)
            print(getUser(ctx))
            ctx.json(mapOf("result" to "ok"))
        } catch (e : NotFoundException){
            throw NotFoundResponse(e.message!!)
        }
    }

    fun postLastSeen(ctx : Context){
        val id : String = tokenJWT.validate(ctx.header("Authentication")!!)
        val idMapper : IdMapper = ctx.bodyValidator<IdMapper>().check({it.id !== null}).get()
        try {
            unqFlix.addLastSeen(id, idMapper.id!!)
            ctx.json(mapOf("result" to "ok"))
        } catch (e : NotFoundException){
            throw NotFoundResponse(e.message!!)
        }
    }
}
