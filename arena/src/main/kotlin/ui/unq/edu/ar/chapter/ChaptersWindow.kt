package ui.unq.edu.ar.chapter

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.model.ChapterAppModel
import ui.unq.edu.ar.chapter.view.NewChapterDialog
import ui.unq.edu.ar.season.model.SeasonAppModel


class ChaptersWindow(owner: WindowOwner, model: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner,model) {

    override fun addActions(panel: Panel?) {

    }

    override fun createFormPanel(panel: Panel) {

        Label(panel) with { bindTo("nombre"); alignLeft()}

        Label(panel) with { text="Chapters:"}

        table<ChapterAppModel>(panel) {
            bindItemsTo("capitulos")
            bindSelectionTo("selectChapter")
            visibleRows = 9

            column {
                title = "#"
                fixedSize = 50
                bindContentsTo("id")
            }

            column {
                title = "Title"
                fixedSize = 350
                bindContentsTo("title")
            }

            column {
                title = "Duration"
                fixedSize = 80
                bindContentsTo("duration")
            }
        }

        Panel(panel) with {
            asHorizontal()
            Button(this) with {
                caption = "Add new chapter"
                onClick {
                    NewChapterDialog(
                        thisWindow,
                        ChapterAppModel(thisWindow.modelObject)
                    ).open()
                }
            }

            Button(this) with {
                caption = "Modified Chapter"
                onClick {  }
            }
        }
    }
}
