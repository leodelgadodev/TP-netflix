package ui.unq.edu.ar

import data.getUNQFlix

import domain.Available

class UnqflixController {

    val unqFlix = getUNQFlix()

    fun register(ctx : Context){

    }

    fun login(ctx : Context){

    }

    fun getUser(ctx : Context){

    }

    fun postLastSeen(ctx : Context){

    }

    fun getContent(ctx : Context){

    }

    fun getBanners(ctx : Context){
        val banners = unqFlix.banners.map { BannerMapper(it.id, it.title, it.description, it.poster, it.state::javaClass === Available()::javaClass) }

        ctx.json(banners)

    }

    fun getContentById(ctx : Context){

    }

    fun postFavById(ctx : Context){

    }

    fun search(ctx : Context){

    }

}
