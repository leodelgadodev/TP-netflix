package ui.unq.edu.ar.mappers

data class ContentViewMapper(val id: String, val title: String, val description : String, val state : Boolean) {
}

data class Contents(val contents : MutableList<ContentViewMapper>)