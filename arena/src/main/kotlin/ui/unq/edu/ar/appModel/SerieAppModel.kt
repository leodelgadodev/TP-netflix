package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable


@Observable
class SerieAppModel(unqFlixAppModel: UNQFlixAppModel? = null, serie: Serie? = null) {

    var unqFlix : UNQFlixAppModel? = null
    var id : String = ""
    var title : String = ""
    var poster : String = ""
    var description : String = ""
    var state : ContentState = Unavailable()
    var contentState : Boolean = false
    var categories : MutableList<Category> = mutableListOf()
    var seasons : MutableList<Season> = mutableListOf()
    var relatedContent : MutableList<Content> = mutableListOf()
    var model : Serie? = null
    var cantSeason = seasons.size

    init{
        this.unqFlix = unqFlixAppModel
        if (serie != null) {
            this.id = serie.id
            this.title = serie.title
            this.poster = serie.poster
            this.description = serie.description
            this.state = serie.state
            this.categories = serie.categories
            this.seasons = serie.seasons
            this.relatedContent = serie.relatedContent
            this.model = serie
        }

    }

    fun nuevaSerie(title : String, poster : String, description : String, state : Boolean, categories : MutableList<Category>, relatedContent: MutableList<Content>){
        this.id = unqFlix?.idGenerator!!.nextSerieId()
        this.title = title
        this.poster = poster
        this.description = description
        this.state = if (state){Available()} else {Unavailable()}
        this.contentState = state
        this.categories = categories
        this.relatedContent = relatedContent
        this.model = Serie(id, title, description, poster, if (state){Available()} else {Unavailable()}, categories, seasons, relatedContent)

        unqFlix!!.newSerie(this)
    }
    fun modificarSerie() {

    }
    fun cancelar(){

    }
}