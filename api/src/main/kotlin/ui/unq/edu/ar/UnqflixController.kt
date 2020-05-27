package ui.unq.edu.ar

import domain.*
import domain.Available
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import ui.unq.edu.ar.JWT.TokenJWT
import ui.unq.edu.ar.mappers.*
import java.lang.NullPointerException

class UnqflixController(val tokenJWT: TokenJWT, val unqFlix: UNQFlix) {
    private val idGenerator = IdGenerator()

    fun register(ctx : Context) {
        try {
            val newUser = ctx.bodyValidator<UserRegisterMapper>()
                    .check({
                        it.name != null
                        it.email != null
                        it.password != null
                        it.image != null
                        it.creditCard != null
                    }, "Request inválido: Faltan parámetros.")
                    .get()

            if (!emailExists(newUser.email!!)) {
                val id = idGenerator.nextUserId()
                val user = User(id, newUser.name!!, newUser.creditCard!!,
                        newUser.image!!, newUser.email, newUser.password!!, mutableListOf(), mutableListOf())
                unqFlix.addUser(user)
                ctx.header("Authorization", tokenJWT.generateToken(UserLoginMapper(user.id, user.email, user.password)))
                ctx.status(201)
                ctx.json(mapOf("result" to "ok"))
            } else {
                throw BadRequestResponse("Ya existe un usuario con el mail = ${newUser.email}.")
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
                .check({
                    it.email !== null
                    it.password !== null
                }, "Request inválido: Faltan parámetros.")
                .get()

        val user : User? = unqFlix.users.firstOrNull { it.email == loginUser.email && it.password == loginUser.password }
        if(user !== null) {
            loginUser.id = user.id
            ctx.header("Authorization", tokenJWT.generateToken(loginUser))
            ctx.json(mapOf("result" to "ok"))
        } else {
            ctx.status(404)
            ctx.json(mapOf("result" to "error", "message" to "user not found"))
        }
    }

    private fun userExists(email: String, password: String): Boolean {
        return unqFlix.users.any {
            it.email == email && it.password == password
        }
    }

    fun getUser(ctx : Context) {
        val idUser : String = tokenJWT.validate(ctx.header("Authorization")!!)
        val user = unqFlix.users.find { it.id == idUser }
        val favs = user!!.favorites.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass ) }.toMutableList()
        val lastSeen = user.lastSeen.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }.toMutableList()

        val userMapper = GetUserViewMapper(user.name,user.image,favs,lastSeen)
        ctx.json(userMapper)
    }

    fun postLastSeen(ctx : Context){
        val id : String = tokenJWT.validate(ctx.header("Authorization")!!)
        val idMapper : IdMapper = ctx.bodyValidator<IdMapper>().check({it.id !== null}).get()
        try {
            unqFlix.addLastSeen(id, idMapper.id!!)
            ctx.json(mapOf("result" to "ok"))
        } catch (e : NotFoundException){
            throw NotFoundResponse(e.message!!)
        }
    }

    fun getContent(ctx : Context){
        val movies = unqFlix.movies.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }
        val serie = unqFlix.series.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }
        val content : MutableList<ContentViewMapper> = mutableListOf()
        content.addAll(movies)
        content.addAll(serie)
        ctx.json(mapOf("content" to content))
    }

    fun getBanners(ctx : Context){
        val banners = unqFlix.banners.map { ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) }
        ctx.json(mapOf("Banners" to banners))
    }

    fun getContentById(ctx : Context){
        var id = ctx.pathParam(":contentId")

        if (id.startsWith("mov")) return getMovieContent(ctx,id)
        if (id.startsWith("ser")) return getSerieContent(ctx,id)
        else throw NotFoundResponse("No se ha encontrado contenido con el id = ${id}")
    }

    private fun getSerieContent(ctx: Context,id: String) {
        val serie= unqFlix.series.find { it.id == id }
        if (serie!=null){
            val categories = serie.categories.map { it.name }.toMutableList()
            val relatedContent= serie.relatedContent.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass)}.toMutableList()
            val season = serie.seasons.map { SeasonViewMapper(it.id,it.title,it.description,it.poster,
                it.chapters.map{ ChapterViewMapper(it.id,it.title,it.description,it.duration,it.video,it.thumbnail)}.toMutableList())
            }

            ctx.json(SerieViewMapper(serie.id, serie.title,serie.description,serie.poster,categories ,relatedContent, season as MutableList<SeasonViewMapper>))
        } else{
            throw NotFoundResponse("No se ha encontrado la serie con el id = ${id}")
        }
    }
    private fun getMovieContent(ctx: Context,id: String) {
        val movie = unqFlix.movies.find { it.id == id }
        if (movie != null){
            val categories = movie.categories.map { it.name }.toMutableList()
            val relatedContent = movie.relatedContent.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }

            ctx.json(MovieViewMapper(movie.id,movie.title,movie.description,movie.poster,movie.video,movie.duration,movie.actors,movie.directors,categories,
                relatedContent as MutableList<ContentViewMapper>))
        } else{
            throw NotFoundResponse("No se ha encontrado la pelicula con el id = ${id}")
        }
    }

    fun postFavById(ctx : Context){
        val idUser : String = tokenJWT.validate(ctx.header("Authorization")!!)
        val contentId = ctx.pathParam(":contentId")
        try {
            unqFlix.addOrDeleteFav(idUser,contentId)
            ctx.json(mapOf("result" to "ok"))
        } catch (e : NotFoundException){
            throw NotFoundResponse(e.message!!)
        }
    }

    fun search(ctx : Context){
        val text : String? = ctx.queryParam("text")
        val contents : MutableList<ContentViewMapper> = mutableListOf()
        contents.addAll(unqFlix.searchSeries(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        contents.addAll(unqFlix.searchMovies(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        ctx.json(mapOf("Contents" to contents))
    }

}
