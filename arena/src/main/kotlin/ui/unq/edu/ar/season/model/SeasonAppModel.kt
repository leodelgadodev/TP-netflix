package ui.unq.edu.ar.season.model

import domain.Chapter
import domain.Season
import domain.Serie
import org.uqbar.commons.model.annotations.Observable
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel

@Observable
class SeasonAppModel(unqFlixAppModel: UNQFlixAppModel? = null, season: Season? = null) {
    var id: String = ""
    var descripcion: String = ""
    var capitulos: MutableList<Chapter> = mutableListOf()
    var poster: String = ""
}