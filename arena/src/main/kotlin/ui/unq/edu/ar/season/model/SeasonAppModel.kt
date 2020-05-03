package ui.unq.edu.ar.season.model

import domain.Chapter
import domain.Season
import org.uqbar.commons.model.annotations.Observable
import ui.unq.edu.ar.serie.model.SerieAppModel

@Observable
class SeasonAppModel(serieAppModel: SerieAppModel, season: Season? = null) {
    var model : Season? = null
    var id: String = ""
    var descripcion: String = ""
    var capitulos: MutableList<Chapter> = mutableListOf()
    var poster: String = ""

}