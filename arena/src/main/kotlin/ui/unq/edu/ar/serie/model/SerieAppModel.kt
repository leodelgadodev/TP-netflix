package ui.unq.edu.ar.serie.model

import domain.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel
import ui.unq.edu.ar.season.model.SeasonAppModel


@Observable
class SerieAppModel(unqFlixAppModel: UNQFlixAppModel, serie: Serie? = null) {
    var model : Serie? = null
    var unqFlix : UNQFlixAppModel = unqFlixAppModel
    var id : String = ""
    var title : String = ""
    var poster : String = ""
    var description : String = ""
    var state : ContentState = Unavailable()
    var categories : MutableList<CategoryAppModel> = mutableListOf()
    var seasons : MutableList<SeasonAppModel> = mutableListOf()
    var relatedContent : MutableList<ContentAppModel> = mutableListOf()
    var nonSelectedCategories : MutableList<CategoryAppModel> = mutableListOf()
    var nonRelatedContent : MutableList<ContentAppModel> = mutableListOf()
    var cantSeasons = 0
    var selectedSeason : SeasonAppModel? = null
    var selectedCategory : CategoryAppModel? = null
    var selectedContent : ContentAppModel? = null

    init{
        if (serie != null) {
            this.id = serie.id
            this.title = serie.title
            this.poster = serie.poster
            this.description = serie.description
            this.state = serie.state
            this.categories = unqFlix.getCategoriesAppModel(serie.categories.map { it.id }.toMutableList())
            this.relatedContent = unqFlix.getContentsAppModel(serie.relatedContent.map { it.id }.toMutableList())
            this.model = serie
        }
        this.nonSelectedCategories = unqFlixAppModel.allCategories().filter { !this.categories.map{it.id}.contains(it.id) && it.id != id }.toMutableList()
        this.nonRelatedContent = unqFlixAppModel.allContents().filter { !this.relatedContent.map { it.id }.contains(it.id) && it.id != id }.toMutableList()

    }

    fun nuevaSerie(title : String, poster : String, description : String, state: ContentState, categories : MutableList<CategoryAppModel>, relatedContent: MutableList<ContentAppModel>){
        this.id = unqFlix.idGenerator.nextSerieId()
        this.title = title
        this.poster = poster
        this.description = description
        this.state = state
        this.categories = categories
        this.relatedContent = relatedContent
        this.model = Serie(id, title, description, poster, state, categories.map { it.model }.toMutableList(), mutableListOf(),relatedContent.map { it.model }.toMutableList())
        unqFlix.newSerie(this)
    }

    fun modificarSerie(title : String, poster : String, description : String, state : ContentState, categories : MutableList<CategoryAppModel>, relatedContent: MutableList<ContentAppModel>) {
        model!!.title = title
        model!!.poster = poster
        model!!.description = description
        model!!.state = state
        model!!.categories = categories.map { it.model }.toMutableList()
        model!!.relatedContent = relatedContent.map { it.model }.toMutableList()
        unqFlix.modificarSerie(this)
    }

    fun addCategory(selectedCategory: CategoryAppModel?) {
        if(selectedCategory != null && !categories.contains(selectedCategory)) {
            categories.add(selectedCategory)
            nonSelectedCategories.remove(selectedCategory)
        }
    }
    fun deleteCategory(selectedCategory: CategoryAppModel?) {
        if (selectedCategory != null && !nonSelectedCategories.contains(selectedCategory)) {
            nonSelectedCategories.add(selectedCategory)
            categories.remove(selectedCategory)
        }
    }
    fun addContent(selectedContent : ContentAppModel?){
        if(selectedContent != null && !relatedContent.contains(selectedContent)) {
            relatedContent.add(selectedContent)
            nonRelatedContent.remove(selectedContent)
        }
    }

    fun deleteContent(selectedContent : ContentAppModel?){
        if(selectedContent != null && !nonRelatedContent.contains(selectedContent)) {
            nonRelatedContent.add(selectedContent)
            relatedContent.remove(selectedContent)
        }
    }

    fun stateDescription() : String{
        return if(Available().javaClass === state.javaClass){
            "Available"
        } else {
            "Unavailable"
        }
    }

    fun deleteSeason(aSeasonAppModel: SeasonAppModel) {
        model!!.deleteSeason(aSeasonAppModel.id)
        seasons.remove(aSeasonAppModel)
    }

    fun newSeason(aSeasonAppModel: SeasonAppModel) {
        model!!.addSeason(aSeasonAppModel.model!!)
        this.seasons.add(aSeasonAppModel)
        this.cantSeasons++
    }

}
