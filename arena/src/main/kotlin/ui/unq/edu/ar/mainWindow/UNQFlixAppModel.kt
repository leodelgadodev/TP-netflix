package ui.unq.edu.ar.mainWindow

import domain.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import ui.unq.edu.ar.serie.model.CategoryAppModel
import ui.unq.edu.ar.serie.model.ContentAppModel
import ui.unq.edu.ar.serie.model.SerieAppModel


@Observable
class UNQFlixAppModel {
    var system : UNQFlix = UNQFlix()
    var series : MutableList<SerieAppModel> = initSeries()
    var selectSerie : SerieAppModel? = null
    val idGenerator = IdGenerator()
    var categories : MutableList<CategoryAppModel> = initCategories()
    var content : MutableList<ContentAppModel> = initMovies()

    fun initSeries() : MutableList<SerieAppModel>{
        return system.series.map{ SerieAppModel(serie = it) }.toMutableList()
    }
    fun initCategories() : MutableList<CategoryAppModel> {
        val categoriesName = arrayOf("Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy",
                                     "History", "Horror", "Music", "Mistery", "Romance", "Sience Fiction", "TV Movie", "War", "Thriller", "Western", "Unknown")
        for(name : String in categoriesName){
            system.addCategory(Category(idGenerator.nextCategoryId(), name))
        }
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun newSerie(serieAppModel: SerieAppModel) {
        try{
            system.addSerie(serieAppModel.model!!)
        } catch (e : ExistsException){
            throw UserException(e.message)
        }
        this.content.add(ContentAppModel(serieAppModel.model!!))
        this.series.add(serieAppModel)
    }

    fun eliminarSerie(serie: SerieAppModel){
        system.deleteSerie(serie.id)
        series.remove(serie)
    }

    fun toCategories(ids : MutableList<String>) : MutableList<CategoryAppModel> {
        return categories.filter { ids.contains(it.id) }.toMutableList()
    }

    fun allContents() : MutableList<ContentAppModel>{
        return content
    }

    fun getContentsAppModel(ids: MutableList<String>) : MutableList<ContentAppModel>{
        return content.filter { ids.contains(it.id) }.toMutableList()
    }
    fun getContents(ids: MutableList<String>): MutableList<Content> {
        return content.filter { ids.contains(it.id) }.map{it.model!!}.toMutableList()
    }

    fun initMovies() : MutableList<ContentAppModel>{
        val newMovies : MutableList<Movie> = mutableListOf()
        newMovies.add(Movie(idGenerator.nextMovieId(), "Dilwale Dulhania Le Jayenge", "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
            "https://image.tmdb.org/t/p/w500/2CAL2433ZeIihfX1Hb2139CX0pW.jpg", Available(), "https://www.youtube.com/watch?v=cmax1C1p660", 100,   mutableListOf("Russ Deckard", "Jada Heng", "Elias Lemarr"), mutableListOf("Phylicia Castile", "Cinderella Albertson", "Ailene Whitten")))
        newMovies.add(Movie(idGenerator.nextMovieId(),"The Shawshank Redemption", "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
            "https://image.tmdb.org/t/p/w500/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg", Available(), "https://www.youtube.com/watch?v=K_tLp7T6U1c", 100, arrayListOf("Tamara Dandridge", "Danika Burman", "Barrett Philhower"), arrayListOf("Dan Duropan", "Phylicia Castile", "Lavera Blaise")))
        newMovies.add(Movie(idGenerator.nextMovieId(),"The Godfather", "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            "https://image.tmdb.org/t/p/w500/d4KNaTrltq6bpkFS01pYtyXa09m.jpg", Available(), "https://www.youtube.com/watch?v=fBNpSRtfIUA",100,arrayListOf("Mariana Bourgault", "Tish Lytle", "Jada Heng"), arrayListOf("Lyda Kennamer", "Chantay Roache", "Russ Deckard")))
        newMovies.add(Movie(idGenerator.nextMovieId(),"Schindler's List","The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
            "https://image.tmdb.org/t/p/w500/yPisjyLweCl1tbgwgtzBCNCBle.jpg",Available(), "https://www.youtube.com/watch?v=bJcLRFWxRno", 130, arrayListOf("Dan Duropan", "Dan Duropan", "Barrett Philhower"), arrayListOf("Lyda Kennamer", "Armando Sidener", "Darwin Fain")))
        newMovies.add(Movie(idGenerator.nextMovieId(),"The Godfather: Part II",    "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
            "https://image.tmdb.org/t/p/w500/bVq65huQ8vHDd1a4Z37QtuyEvpA.jpg", Available(),"https://www.youtube.com/watch?v=9O1Iy9od7-A", 120, arrayListOf("Tamara Dandridge", "Roslyn Yepez", "Tamara Dandridge"), arrayListOf("Tamara Dandridge", "Armando Sidener", "Debbra Delker")))
        newMovies.forEach { system.addMovie(it) }
        return newMovies.map { ContentAppModel(it) }.toMutableList()
    }

    fun allCategories(): MutableList<CategoryAppModel> {
        return categories
    }
}