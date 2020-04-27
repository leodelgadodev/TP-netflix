package ui.unq.edu.ar.serie.model

import domain.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAppModel(category: Category) {
    var name: String = category.name
    var id: String = category.id
    var model: Category = category
}