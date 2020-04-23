package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable


@Observable
class NewSerieAppModel( val unqFlix: UNQFlixAppModel) {
    var title : String = ""
    var poster : String = ""
    var description : String = ""
    var state : Boolean = false
    var contentState : ContentState = if (state){Available()} else {Unavailable()}
    var categories : MutableList<Category> = mutableListOf()
    var relatedContent : MutableList<Content> = mutableListOf()

    fun newSerie(){
        unqFlix.newSerie(title, poster, description, contentState, categories, relatedContent)
    }
}