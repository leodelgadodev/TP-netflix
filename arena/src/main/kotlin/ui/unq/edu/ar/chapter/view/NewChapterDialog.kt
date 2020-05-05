package ui.unq.edu.ar.chapter.view

import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.model.ChapterAppModel

class NewChapterDialog (owner: WindowOwner,model: ChapterAppModel) : Dialog<ChapterAppModel>(owner,model) {

    override fun createFormPanel(panel: Panel?) {

        Label(panel) with {
            text = "Esta seguro de añadir este capitulo?"
        }

        Button(panel) with {
            caption = "Aceptar"
            onClick { accept() }
        }

        Button(panel) with {
            caption = "Cancelar"
            onClick { cancel() }
        }
    }
}