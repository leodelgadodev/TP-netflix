package ui.unq.edu.ar.controllers

import domain.*
import domain.Available
import io.javalin.http.*
import ui.unq.edu.ar.jwt.TokenJWT
import ui.unq.edu.ar.mappers.*


class ContentController(val tokenJWT: TokenJWT, val unqFlix: UNQFlix) {

    fun getContent(ctx : Context){
        val movies = unqFlix.movies.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }
        val serie = unqFlix.series.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }
        val content : MutableList<ContentViewMapper> = mutableListOf()
        content.addAll(movies)
        content.addAll(serie)
        content.sortWith(compareBy{it.title})
        ctx.json(mapOf("content" to content))
    }


    fun getContentById(ctx : Context){
        val id = ctx.pathParam(":contentId")

        if (id.startsWith("mov")) return getMovieContent(ctx,id)
        if (id.startsWith("ser")) return getSerieContent(ctx,id)
        else throw NotFoundResponse("No se ha encontrado contenido con el id = $id")
    }

    private fun getSerieContent(ctx: Context, id: String) {
        val serie= unqFlix.series.find { it.id == id }
        if (serie!=null){
            val categories = serie.categories.map { it.name }.toMutableList()
            val relatedContent= serie.relatedContent.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }.toMutableList()
            val season = serie.seasons.map { SeasonViewMapper(it.id,it.title,it.description,it.poster,
                    it.chapters.map{ ChapterViewMapper(it.id,it.title,it.description,it.duration,it.video,it.thumbnail) }.toMutableList())
            }

            ctx.json(SerieViewMapper(serie.id, serie.title,serie.description,serie.poster,categories ,relatedContent, season as MutableList<SeasonViewMapper>))
        } else{
            throw NotFoundResponse("No se ha encontrado la serie con el id = $id")
        }
    }
    private fun getMovieContent(ctx: Context, id: String) {
        val movie = unqFlix.movies.find { it.id == id }
        if (movie != null){
            val categories = movie.categories.map { it.name }.toMutableList()
            val relatedContent = movie.relatedContent.map { ContentViewMapper(it.id,it.title,it.description,it.state.javaClass === Available().javaClass) }

            ctx.json(MovieViewMapper(movie.id,movie.title,movie.description,movie.poster,movie.video,movie.duration,movie.actors,movie.directors,categories,
                    relatedContent as MutableList<ContentViewMapper>))
        } else{
            throw NotFoundResponse("No se ha encontrado la pelicula con el id = $id")
        }
    }


    fun getBanners(ctx : Context){
        val banners = unqFlix.banners.map { ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) }
        ctx.json(mapOf("Banners" to banners))
    }

    fun search(ctx : Context){
        val text : String? = ctx.queryParam("text")
        val contents : MutableList<ContentViewMapper> = mutableListOf()
        contents.addAll(unqFlix.searchSeries(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        contents.addAll(unqFlix.searchMovies(text.toString()).map{ ContentViewMapper(it.id, it.title, it.description, it.state.javaClass === Available().javaClass) })
        ctx.json(mapOf("Contents" to contents))
    }
}