package ui.unq.edu.ar.serie.model

import domain.Content
import org.uqbar.commons.model.annotations.Observable

@Observable
class ContentAppModel(content : Content) {
    var id : String = content.id
    var title : String = content.title
    var model : Content? = content
}