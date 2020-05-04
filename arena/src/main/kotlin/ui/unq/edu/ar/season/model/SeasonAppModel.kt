package ui.unq.edu.ar.season.model

import domain.Chapter
import domain.Season
import org.uqbar.commons.model.annotations.Observable
import ui.unq.edu.ar.serie.model.SerieAppModel

@Observable
class SeasonAppModel(serieAppModel: SerieAppModel, season: Season? = null) {
    var model : Season? = null
    var serie : SerieAppModel = serieAppModel
    var idGenerator = serieAppModel.unqFlix.idGenerator
    var id: String = ""

    var nombre: String = ""
    var descripcion: String = ""
    var capitulos: MutableList<Chapter> = mutableListOf()
    var poster: String = ""

    var cantCapitulos: Int = 0

    init{
        if (season != null) {
            this.id = season.id
            this.nombre = season.title
            this.poster = season.poster
            this.descripcion = season.description
            this.model = season
        }
    }

    fun newSeason(nombre: String, poster: String, descripcion: String) {
        this.id = idGenerator.nextSeasonId()
        this.nombre = nombre
        this.descripcion = descripcion
        this.poster = poster
        this.model = Season(id, nombre, descripcion, poster, mutableListOf())

        serie.newSeason(this)
    }

    fun modifySeason() {

    }

    fun deleteSeason() {

    }
}