package ui.unq.edu.ar.season.view

import org.uqbar.arena.windows.Dialog
import ui.unq.edu.ar.season.SeasonWindow
import ui.unq.edu.ar.season.model.SeasonAppModel

class ModifySeasonDialog(owner: SeasonWindow, model: SeasonAppModel) : NewSeasonDialog(owner, model)  {

    override fun accept() {
        modifySeason()
        close()
    }

    private fun modifySeason() {
        modelObject.modifySeason(modelObject.nombre, modelObject.poster, modelObject.descripcion)
    }
}