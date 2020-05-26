package ui.unq.edu.ar.mappers

data class UserRegisterMapper (val name: String?, val email: String?, val password: String?,
                               val image: String?, val creditCard: String?)

data class UserLoginMapper(var id : String?, val email: String?, val password: String?)

data class UserViewMapper (val id : String, val name: String, val email: String,
                           val image: String)
