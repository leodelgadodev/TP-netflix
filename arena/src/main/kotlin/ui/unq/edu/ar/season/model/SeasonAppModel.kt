package ui.unq.edu.ar.season.model

import domain.Chapter
import domain.ExistsException
import domain.Season
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import ui.unq.edu.ar.chapter.model.ChapterAppModel
import ui.unq.edu.ar.exceptions.RepeatedNameException
import ui.unq.edu.ar.serie.model.SerieAppModel

@Observable
class SeasonAppModel(serieAppModel: SerieAppModel, season: Season? = null) {
    var model : Season? = null
    var serieAppModel : SerieAppModel = serieAppModel
    var idGenerator = serieAppModel.unqFlix.idGenerator
    var id: String = ""

    var nombre: String = ""
    var descripcion: String = ""
    var capitulos: MutableList<ChapterAppModel> = mutableListOf()
    var poster: String = ""

    var cantCapitulos: Int = 0

    var selectChapter : ChapterAppModel? = null

    init{
        if (season != null) {
            this.id = season.id
            this.nombre = season.title
            this.poster = season.poster
            this.descripcion = season.description
            this.capitulos = season.chapters.map { ChapterAppModel(this,it) }.toMutableList()
            this.cantCapitulos = capitulos.size
            this.model = season
        }
        for (c in capitulos){
            idGenerator.nextChapterId()
        }

    }

    fun newSeason(nombre: String, poster: String, descripcion: String) {
        this.id = idGenerator.nextSeasonId()
        this.nombre = nombre
        this.descripcion = descripcion
        this.poster = poster
        this.model = Season(id, nombre, descripcion, poster, mutableListOf())

        serieAppModel.newSeason(this)
    }

    fun modifySeason(nombre: String, poster: String, descripcion: String) {
        this.nombre = nombre
        this.descripcion = descripcion
        this.poster = poster
        serieAppModel.modifySeason(this)
        this.model!!.title = nombre
        this.model!!.poster = poster
        this.model!!.description = descripcion
    }

    fun addChapter(chapterAppModel: ChapterAppModel) {
    try {
        this.model!!.addChapter(chapterAppModel.model!!)
        this.capitulos.add(chapterAppModel)
        this.cantCapitulos++
    } catch (e: ExistsException) {
        throw RepeatedNameException("\"${chapterAppModel.title}\" already exists. Please, use another name.")
    }
    }

    fun modifyChapter(chapterAppModel: ChapterAppModel){
        val char = capitulos.find { it.title == chapterAppModel.title}
        if(char == null || char.id == chapterAppModel.id){
            model!!.deleteChapter(chapterAppModel.id)
            capitulos.remove(capitulos.find { it.id === chapterAppModel.id })
            capitulos.add(chapterAppModel)
            model!!.addChapter(chapterAppModel.model!!)
        } else {
            throw RepeatedNameException("\"${chapterAppModel.title}\" already exists. Please, use another name.")
        }


    }

}