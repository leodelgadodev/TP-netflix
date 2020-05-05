package ui.unq.edu.ar.chapter

import domain.Serie
import domain.UNQFlix
import org.uqbar.arena.Application
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.model.ChapterAppModel
import ui.unq.edu.ar.chapter.view.NewChapterDialog


class ChapterWindow(owner: WindowOwner,chapterAppModel: ChapterAppModel) : SimpleWindow<ChapterAppModel>(owner,chapterAppModel) {

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
                    NewChapterDialog(owner, modelObject as ChapterAppModel).open()
                }
            }

            Button(this) with {
                caption = "Cancelar"
                onClick {  }
            }
        }
    }
}


