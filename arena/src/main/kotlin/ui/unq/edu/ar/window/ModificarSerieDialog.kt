package ui.unq.edu.ar.window

import domain.Category
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.lacar.ui.model.Action
import ui.unq.edu.ar.appModel.UNQFlixAppModel

class ModificarSerieDialog(owner : WindowUNQFlix, model : UNQFlixAppModel) : Dialog<UNQFlixAppModel>(owner, model){
    override fun createFormPanel(mainPanel: Panel) {
        Panel(mainPanel) with {
            width = 400
            asHorizontal()
            Label(it) with {
                text = "Title"
                align = "left"
                width = 210
            }
            Label(it) with {
                text = "Poster"
                width = 200
                align = "left"
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            TextBox(it) with {
                width = 200
            }
            TextBox(it) with {
                width = 200
            }
        }
        Label(mainPanel) with {
            text = "Description:"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            KeyWordTextArea(it) with {
                width = 200
                height = 75
            }
            Label(it) with {
                text = "  State: "
            }
            CheckBox(it) with{}
        }
        Label(mainPanel) with{
            text = "Categories"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<Category>(it) with{
                width = 150
                height = 180

            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    caption = "<"
                }
                Button(it) with {
                    caption = ">"
                }
            }
            List<Category>(it) with{
                width = 150
                height = 180

            }
        }
        Label(mainPanel) with{
            text = "Related Content: "
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<Category>(it) with{
                width = 280
                height = 180
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    caption= "<"
                }
                Button(it) with {
                    caption= ">"
                }
            }
            List<Category>(it) with{
                width = 280
                height = 180
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "aceptar"
                onClick(Action {
                    //nuevaSerie()
                    close()
                })
            }
            Button(it) with {
                caption = "cancelar"
                onClick(Action {
                    close()
                })
            }
        }
        fun modificarSerie(){
            //modelObject.newSerie()
        }
    }
}