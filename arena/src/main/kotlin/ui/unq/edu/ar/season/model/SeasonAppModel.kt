package ui.unq.edu.ar.season.model

import domain.Chapter
import domain.Season
import org.uqbar.commons.model.annotations.Observable
import ui.unq.edu.ar.chapter.model.ChapterAppModel
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
        this.model!!.title = nombre
        this.model!!.poster = poster
        this.model!!.description = descripcion

        this.nombre = nombre
        this.descripcion = descripcion
        this.poster = poster

        serieAppModel.modifySeason(this)
    }

    fun addChapter(chapterAppModel: ChapterAppModel) {
        this.model!!.addChapter(chapterAppModel.model!!)
        this.capitulos.add(chapterAppModel)
        this.cantCapitulos++
    }

    fun modifyChapter(chapterAppModel: ChapterAppModel){
        model!!.deleteChapter(chapterAppModel.id)
        capitulos.remove(capitulos.find { it.id === chapterAppModel.id })
        capitulos.add(chapterAppModel)
        model!!.addChapter(chapterAppModel.model!!)

    }

}