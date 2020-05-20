package ui.unq.edu.ar

import data.getUNQFlix
import domain.*
import domain.Available
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import ui.unq.edu.ar.mappers.*

class UnqflixController {

    val unqFlix = getUNQFlix()
    val idGenerator = IdGenerator()
    init {
        unqFlix.addUser(User(idGenerator.nextUserId(), "Nacho", "0", "image.png", "email@mail", "1234"))
    }

    fun register(ctx : Context){

    }

    fun login(ctx : Context){

    }

    fun getUser(ctx : Context){

    }

    fun postLastSeen(ctx : Context){
        val idMapper : IdMapper = ctx.bodyValidator<IdMapper>().check({it.id !== null}).get()
        try {
            unqFlix.addLastSeen("u_1", idMapper.id!!)
            ctx.json(mapOf("result" to "ok"))
        } catch (e : NotFoundException){
            throw BadRequestResponse(e.message!!)
        }

    }

    fun getContent(ctx : Context){

    }

    fun getBanners(ctx : Context){
        val banners = unqFlix.banners.map { BannerViewMapper(it.id, it.title, it.description, it.poster, it.state::javaClass === Available()::javaClass) }
        ctx.json(mapOf("Banners" to banners))
    }

    fun getContentById(ctx : Context){

    }

    fun postFavById(ctx : Context){

    }

    fun search(ctx : Context){
        val text : String? = ctx.queryParam("text")
        val contents : MutableList<ContentViewMapper> = mutableListOf()
        contents.addAll(unqFlix.searchSeries(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        contents.addAll(unqFlix.searchMovies(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        ctx.json(mapOf("Contents" to contents))
    }

}
