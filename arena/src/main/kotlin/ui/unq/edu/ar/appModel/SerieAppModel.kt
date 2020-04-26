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
    var categories : MutableList<String> = mutableListOf()
    var seasons : MutableList<Season> = mutableListOf()
    var relatedContent : MutableList<Content> = mutableListOf()
    var model : Serie? = null
    var nonSelectedCategories : MutableList<String> = mutableListOf()
    var nonRelatedContent : MutableList<Content> = mutableListOf()
    var cantSeason = seasons.size
    var selectedCategory : String? = null
    var selectedContent : Content?= null

    init{
        this.unqFlix = unqFlixAppModel
        if (unqFlixAppModel != null) {
            this.nonSelectedCategories = unqFlixAppModel.allCategories.filter{!this.categories.contains(it.name)}.map{it.name}.toMutableList()
            this.nonRelatedContent = unqFlixAppModel.allContents().toMutableList()
        }
        if (serie != null) {
            this.id = serie.id
            this.title = serie.title
            this.poster = serie.poster
            this.description = serie.description
            this.state = serie.state
            this.categories = serie.categories.map { it.name }.toMutableList()
            this.seasons = serie.seasons
            this.relatedContent = serie.relatedContent
            this.model = serie
            }


    }

    fun nuevaSerie(title : String, poster : String, description : String, state : Boolean, categories : MutableList<String>, relatedContent: MutableList<Content>){
        this.id = unqFlix?.idGenerator!!.nextSerieId()
        this.title = title
        this.poster = poster
        this.description = description
        this.state = if (state){Available()} else {Unavailable()}
        this.contentState = state
        this.categories = categories
        this.relatedContent = relatedContent
        this.model = Serie(id, title, description, poster, if (state){Available()} else {Unavailable()}, unqFlix!!.toCategories(categories), seasons, relatedContent)

        unqFlix!!.newSerie(this)
    }

    fun modificarSerie(title : String, poster : String, description : String, state : Boolean, categories : MutableList<String>, relatedContent: MutableList<Content>) {
        model!!.title = title
        model!!.poster = poster
        model!!.description = description
        model!!.state = if (state){Available()} else {Unavailable()}
        model!!.categories = unqFlix!!.toCategories(categories)
        model!!.relatedContent = relatedContent
    }
    fun cancelar(oldState : Boolean){
        if (model != null){
            this.title = model!!.title
            this.poster = model!!.poster
            this.description = model!!.description
            this.state = model!!.state
            this.contentState = oldState
            this.categories = toCategoryName(model!!.categories)
            this.relatedContent = model!!.relatedContent
        }

    }
    private fun toCategoryName(categories: MutableList<Category>): MutableList<String> {
        return categories.map{it.name}.toMutableList()
    }
    fun addCategory(selectedCategory: String?) {
        if(selectedCategory != null && !categories.contains(selectedCategory)) {
            categories.add(selectedCategory)
            nonSelectedCategories.remove(selectedCategory)
        }
    }
    fun deleteCategory(selectedCategory: String?) {
        if (selectedCategory != null && !nonSelectedCategories.contains(selectedCategory)) {
            categories.remove(selectedCategory)
            nonSelectedCategories.add(selectedCategory)
        }
    }
    fun addContent(selectedContent : Content?){
        if(selectedContent != null && !relatedContent.contains(selectedContent)) {
            nonRelatedContent.remove(selectedContent)
            relatedContent.add(selectedContent)
        }
    }

    fun deleteContent(selectedContent : Content?){
        if(selectedContent != null && !nonRelatedContent.contains(selectedContent)) {
            relatedContent.remove(selectedContent)
            nonRelatedContent.add(selectedContent)
        }
    }

}
