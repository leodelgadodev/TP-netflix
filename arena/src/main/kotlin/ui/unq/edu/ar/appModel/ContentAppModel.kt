package ui.unq.edu.ar.appModel

import domain.Content
import org.uqbar.commons.model.annotations.Observable

@Observable
class ContentAppModel(content : Content) {

    var id : String = ""
    var title : String = ""
    var model : Content? = null

    init{
        this.id = content.id
        this.title = content.title
        this.model = content
    }
}