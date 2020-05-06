package ui.unq.edu.ar.chapter.model

import domain.Chapter
import domain.IdGenerator
import org.uqbar.commons.model.annotations.Observable
import ui.unq.edu.ar.season.model.SeasonAppModel

@Observable
class ChapterAppModel (seasonAppModel: SeasonAppModel, chapter: Chapter? = null){

    var model : Chapter? = null
    var seasonModel : SeasonAppModel = seasonAppModel
    var idGenerator : IdGenerator = seasonAppModel.idGenerator

    var id: String = ""
    var title: String = ""
    var description: String = ""
    var duration: Int = 0
    var video: String = ""
    var thumbnail: String = ""


    init {
        if (chapter != null) {
            this.id = chapter.id
            this.title = chapter.title
            this.description = chapter.description
            this.duration = chapter.duration
            this.video = chapter.video
            this.thumbnail = chapter.thumbnail
            this.model = chapter
        }
    }

    fun addChapter(title: String,description : String, duration : Int,thumbnail: String, video : String){
        this.id = idGenerator.nextChapterId()
        this.title = title
        this.description = description
        this.duration = duration
        this.thumbnail = thumbnail
        this.video = video
        this.model = Chapter(id, title, description, duration, video, thumbnail)

        seasonModel.addChapter(this)
    }

}