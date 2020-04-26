package ui.unq.edu.ar.appModel

import domain.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAppModel(category: Category) {
    var name : String = ""
    var id : String = ""
    var model  : Category = category
    init{
        this.name = category.name
        this.id = category.id
        this.model = category
    }
}