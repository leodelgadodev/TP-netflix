package ui.unq.edu.ar.mappers

class IdMapper (val id : String?) {}

data class ContentViewMapper(val id: String, val title: String, val description : String, val state : Boolean)



data class MovieViewMapper(
    val id: String, val title: String, val description: String, val poster: String, val video: String,
    val duration: Int, val actors: MutableList<String>, val directors: MutableList<String>,
    val categories: MutableList<String>, val relatedContent: MutableList<ContentViewMapper>)

data class SerieViewMapper(
    val id: String, val title: String, val description: String, val poster: String,
    val categories: MutableList<String>, val relatedContent: MutableList<ContentViewMapper>,
    val season: MutableList<SeasonViewMapper>)

data class SeasonViewMapper(val id: String, val title: String, val description: String, val poster: String,
                            val chapters: MutableList<ChapterViewMapper>)

data class ChapterViewMapper (val id: String, val title: String, val description: String, val duration: Int,
                              val video: String, val thumbnail: String)



// UserMappers

data class UserRegisterMapper (val name: String?, val email: String?, val password: String?,
                               val image: String?, val creditCard: String?)

data class UserLoginMapper(var id : String?, val email: String?, val password: String?)

data class UserViewMapper (val id : String, val name: String, val email: String,val image: String)

data class GetUserViewMapper (val name: String, val image: String,
                              val favorites : MutableList<ContentViewMapper>,
                              val lastSeen : MutableList<ContentViewMapper>)
