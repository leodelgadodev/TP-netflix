package ui.unq.edu.ar.chapter

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.model.ChapterAppModel
import ui.unq.edu.ar.season.model.SeasonAppModel


class ShowChaptersWindow( owner: WindowOwner, model: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner,model) {

    override fun addActions(panel: Panel?) {

    }

    override fun createFormPanel(panel: Panel) {

        Label(panel) with { bindTo("nombre"); alignLeft()}

        Label(panel) with { text="Chapters:"}

        table<ChapterAppModel>(panel) {
            bindItemsTo("capitulos")
            bindSelectionTo("selectChapter")
            visibleRows = 5

            column {
                title = "#"
                weight = 50
                bindContentsTo("id")
            }

            column {
                title = "Title"
                weight = 50
                bindContentsTo("title")
            }

            column {
                title = "Duration"
                weight = 50
                bindContentsTo("duration")
            }

            Panel(panel) with {
                asHorizontal()
                Button(this) with {
                    caption = "Add new chapter"
                    onClick {
                        ChapterWindow(owner, modelObject as ChapterAppModel)
                    }
                }

                Button(this) with {
                    caption = "Modified Chapter"
                    onClick {  }
                }
            }

        }
    }
}
