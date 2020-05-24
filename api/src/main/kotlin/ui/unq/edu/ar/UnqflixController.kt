package ui.unq.edu.ar

import data.getUNQFlix
import domain.*
import domain.Available
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

    }

    fun search(ctx : Context){
        val text : String? = ctx.queryParam("text")
        val contents : MutableList<ContentViewMapper> = mutableListOf()
        contents.addAll(unqFlix.searchSeries(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        contents.addAll(unqFlix.searchMovies(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        ctx.json(mapOf("Contents" to contents))
    }

}
