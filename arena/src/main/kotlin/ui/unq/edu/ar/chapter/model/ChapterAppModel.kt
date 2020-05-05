package ui.unq.edu.ar.chapter.model

import domain.Chapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChapterAppModel (chapter: Chapter? = null){

    var model : Chapter? = null

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


}