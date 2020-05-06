package ui.unq.edu.ar.chapter.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import ui.unq.edu.ar.chapter.ChaptersWindow
import ui.unq.edu.ar.chapter.model.ChapterAppModel


class NewChapterDialog(owner: ChaptersWindow, chapterAppModel: ChapterAppModel) : Dialog<ChapterAppModel>(owner,chapterAppModel) {

    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(panel: Panel) {

        Label(panel) with {text = "Title:";alignLeft()}

        TextBox(panel) with {
            width = 200
            bindTo("title")
        }

        Label(panel) with {text = "Description:";alignLeft()}

        TextBox(panel) with {
            width = 200
            height = 70
            bindTo("description")
        }

        Label(panel) with {text = "Duration:";alignLeft()}

        NumericField(panel) with {
            width = 200
            bindTo("duration")
        }

        Label(panel) with {text = "Thumbnail:";alignLeft()}

        TextBox(panel) with {
            width = 200
            bindTo("thumbnail")
        }

        Label(panel) with {text = "Video:";alignLeft()}

        TextBox(panel) with {
            width = 200
            bindTo("video")
        }
        Panel(this) with {
            asHorizontal()
            Button(this) with {
                caption = "Aceptar"
                onClick {
                    accept()
                }
            }

            Button(this) with {
                caption = "Cancelar"
                onClick {  }
            }
        }
    }

    override fun accept() {
        newChapter()
        close()
    }

    private  fun newChapter(){
        modelObject.addNewChapter(modelObject.title,modelObject.description,modelObject.duration,modelObject.thumbnail,modelObject.video)
    }
}


