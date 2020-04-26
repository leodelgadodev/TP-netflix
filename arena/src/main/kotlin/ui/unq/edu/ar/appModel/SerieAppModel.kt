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
    var categories : MutableList<CategoryAppModel> = mutableListOf()
    var seasons : MutableList<Season> = mutableListOf()
    var relatedContent : MutableList<ContentAppModel> = mutableListOf()
    var model : Serie? = null
    var nonSelectedCategories : MutableList<CategoryAppModel> = mutableListOf()
    var nonRelatedContent : MutableList<ContentAppModel> = mutableListOf()
    var cantSeason = seasons.size
    var selectedCategory : CategoryAppModel? = null
    var selectedContent : ContentAppModel? = null

    init{
        this.unqFlix = unqFlixAppModel
        if (unqFlixAppModel != null) {
            this.nonSelectedCategories = unqFlixAppModel.allCategories()
            this.nonRelatedContent = unqFlixAppModel.allContents()
        }
        if (serie != null) {
            this.id = serie.id
            this.title = serie.title
            this.poster = serie.poster
            this.description = serie.description
            this.state = serie.state
            this.categories = unqFlix!!.toCategories(serie.categories.map { it.id }.toMutableList())
            this.seasons = serie.seasons
            this.relatedContent = unqFlix!!.getContentsAppModel(serie.relatedContent.map { it.id }.toMutableList())
            this.model = serie
        }


    }

    fun nuevaSerie(title : String, poster : String, description : String, state : Boolean, categories : MutableList<CategoryAppModel>, relatedContent: MutableList<ContentAppModel>){
        this.id = unqFlix?.idGenerator!!.nextSerieId()
        this.title = title
        this.poster = poster
        this.description = description
        this.state = if (state){Available()} else {Unavailable()}
        this.contentState = state
        this.categories = categories
        this.relatedContent = relatedContent
        this.model = Serie(id, title, description, poster, if (state){Available()} else {Unavailable()}, categories.map { it.model }.toMutableList(), seasons, unqFlix!!.getContents(relatedContent.map { it.id }.toMutableList()))

        unqFlix!!.newSerie(this)
    }

    fun modificarSerie(title : String, poster : String, description : String, state : Boolean, categories : MutableList<CategoryAppModel>, relatedContent: MutableList<ContentAppModel>) {
        model!!.title = title
        model!!.poster = poster
        model!!.description = description
        model!!.state = if (state){Available()} else {Unavailable()}
        model!!.categories = categories.map { it.model }.toMutableList()
        model!!.relatedContent = unqFlix!!.getContents(relatedContent.map { it.id }.toMutableList())
    }
    fun cancelar(oldState : Boolean){
        this.title = model!!.title
        this.poster = model!!.poster
        this.description = model!!.description
        this.state = model!!.state
        this.contentState = oldState
        this.categories = unqFlix!!.toCategories(model!!.categories.map { it.id }.toMutableList())
        this.relatedContent = unqFlix!!.getContentsAppModel(model!!.relatedContent.map { it.id }.toMutableList())
        this.nonRelatedContent = unqFlix!!.allContents().filter { !relatedContent.contains(it) && it.id != this.id }.toMutableList()
        this.nonSelectedCategories = unqFlix!!.allCategories().filter { !categories.map { it.id }.contains(it.id) }.toMutableList()
    }
    fun addCategory(selectedCategory: CategoryAppModel?) {
        val category = selectedCategory
        if(category != null && !categories.contains(category)) {
            nonSelectedCategories.remove(category)
            categories.add(category)
        }
    }
    fun deleteCategory(selectedCategory: CategoryAppModel?) {
        val category = selectedCategory
        if (category != null && !nonSelectedCategories.contains(category)) {
            categories.remove(category)
            nonSelectedCategories.add(category)
        }
    }
    fun addContent(selectedContent : ContentAppModel?){
        val content = selectedContent
        if(content != null && !relatedContent.contains(content)) {
            nonRelatedContent.remove(content)
            relatedContent.add(content)
        }
    }

    fun deleteContent(selectedContent : ContentAppModel?){
        val content = selectedContent
        if(content != null && !nonRelatedContent.contains(content)) {
            relatedContent.remove(content)
            nonRelatedContent.add(content)
        }
    }

}
