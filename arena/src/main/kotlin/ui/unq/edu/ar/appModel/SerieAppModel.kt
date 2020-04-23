package ui.unq.edu.ar.appModel

import domain.Category
import domain.ContentState
import domain.Serie
import domain.Unavailable
import org.uqbar.commons.model.annotations.Observable


@Observable
class SerieAppModel () {

    var id : String = ""
    var title : String = ""
    var cantSeason : Int = 0
    var state : ContentState = Unavailable()
    var description : String = ""
    var poster : String = ""
    var categories = mutableListOf<Category>()


}