package ui.unq.edu.ar.chapter.view

import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import ui.unq.edu.ar.chapter.ChaptersWindow
import ui.unq.edu.ar.chapter.model.ChapterAppModel

class ChapterComfirmDialog (owner: ChaptersWindow, model: ChapterAppModel) : Dialog<ChapterAppModel>(owner,model) {

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

    override fun accept() {

    }

    /*
    * override fun accept(){
        nuevaSerie()
        close()
    }

    fun nuevaSerie(){
        modelObject.nuevaSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.state, modelObject.categories, modelObject.relatedContent)
    }*/
}






