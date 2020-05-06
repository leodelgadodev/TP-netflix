package ui.unq.edu.ar.chapter.view

import javafx.scene.layout.Pane
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import ui.unq.edu.ar.chapter.ChaptersWindow
import ui.unq.edu.ar.chapter.model.ChapterAppModel


open class NewChapterDialog(owner: ChaptersWindow, chapterAppModel: ChapterAppModel) : Dialog<ChapterAppModel>(owner,chapterAppModel) {

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(panel: Panel) {

        Panel(panel) with {
            asVertical()
            Label(it) with {
                text = "Title:"
                align = "left"
            }

            TextBox(it) with {
                width = 200
                bindTo("title")
            }

            Label(it) with {
                text = "Description:"
                align = "left"
            }

            TextBox(it) with {
                width = 200
                height = 70
                bindTo("description")
            }

            Label(it) with {
                text = "Duration:"
                align = "left"
            }

            NumericField(it) with {
                width = 200
                bindTo("duration")
            }

            Label(it) with {
                text = "Thumbnail:"
                align = "left"
            }

            TextBox(it) with {
                width = 200
                bindTo("thumbnail")
            }

            Label(it) with {
                text = "Video:"
                align = "left"
            }

            TextBox(it) with {
                width = 200
                bindTo("video")
            }
            Panel(panel) with {
                asHorizontal()
                Button(it) with {
                    caption = "Aceptar"
                    onClick {
                        accept()
                    }
                }

                Button(it) with {
                    caption = "Cancelar"
                    onClick {
                        close()
                    }
                }
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


