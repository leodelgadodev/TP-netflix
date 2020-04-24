package ui.unq.edu.ar.window

import domain.Category
import domain.Content
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List
import org.uqbar.arena.windows.Dialog
import org.uqbar.lacar.ui.model.Action
import ui.unq.edu.ar.appModel.SerieAppModel

open class AddNewSerieDialog(owner: WindowUNQFlix, model: SerieAppModel) : Dialog<SerieAppModel>(owner, model){

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
                bindTo("title")
            }
            TextBox(it) with {
                width = 200
                bindTo("poster")
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
                bindTo("description")
            }
            Label(it)with {
                text = "  State: "
            }
            CheckBox(it)with{
                bindTo("contentState")
            }
        }
        Label(mainPanel) with{
            text = "Categories"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<Category>(it) with{
                bindSelectedTo("selectedCategory")
                width = 150
                height = 180
                bindItemsTo("categories")
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    text = "<"
                    onClick{
                        addCategory()
                    }
                }
                Button(it) with {
                    text = ">"
                    onClick{
                        deleteCategory()
                    }
                }
            }
            List<String>(it) with{
                width = 150
                height = 180
                bindSelectedTo("selectedCategory")
                bindItemsTo("nonSelectedCategories")
            }
        }
        Label(mainPanel) with{
            text = "Related Content: "
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<Content>(it) with{
                width = 280
                height = 180
                bindItemsTo("relatedContent")
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    text= "<"
                }
                Button(it) with {
                    text= ">"
                }
            }
            List<Content>(it) with{
                width = 280
                height = 180
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "aceptar"
                onClick{
                    aceptar()
                }
            }
            Button(it) with {
                caption = "cancelar"
                onClick{
                    cancelar()
                }
            }

        }
    }

    open fun aceptar(){
        onAccept{ nuevaSerie() }
        accept()
    }
    open fun cancelar(){
        cancel()
    }
    private fun nuevaSerie(){
        modelObject.nuevaSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.contentState, modelObject.categories, modelObject.relatedContent)
    }
    private fun addCategory(){
        modelObject.addCategory(modelObject.selectedCategory)
    }
    private fun deleteCategory(){
        modelObject.deleteCategory(modelObject.selectedCategory)
    }
}