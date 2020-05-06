package ui.unq.edu.ar.season.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import ui.unq.edu.ar.exceptions.NoNameException
import ui.unq.edu.ar.season.SeasonWindow
import ui.unq.edu.ar.season.model.SeasonAppModel

open class NewSeasonDialog(owner: SeasonWindow, model: SeasonAppModel) : Dialog<SeasonAppModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        Panel(mainPanel) with {
            asVertical()
            Label(it) with {
                text = "Season Name:"
                align = "left"
            }
            TextBox(it) with {
                bindTo("nombre")
            }

            Label(it) with {
                text = "Poster:"
                align = "left"
            }

            TextBox(it) with {
                bindTo("poster")
            }

            Label(mainPanel) with {
                text = "Description:"
                align = "left"
            }

            KeyWordTextArea(it) with {
                width = 200
                height = 75
                bindTo("descripcion")
            }

            Panel(mainPanel) with {
                asHorizontal()
                Button(it) with {
                    caption = "Submit"
                    onClick {
                        if (thisWindow.modelObject.nombre != "") {
                            accept()
                        } else {
                            throw NoNameException("Please, set a name.")
                        }
                    }
                }
                Button(it) with {
                    caption = "Cancel"
                    onClick {
                        cancel()
                    }
                }
            }
        }
    }

    override fun accept() {
        newSeason()
        close()
    }

    private fun newSeason() {
        modelObject.newSeason(modelObject.nombre, modelObject.poster, modelObject.descripcion)
    }

}