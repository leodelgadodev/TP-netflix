package ui.unq.edu.ar.window

import domain.Category
import domain.Content
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List
import org.uqbar.arena.windows.Dialog
import ui.unq.edu.ar.appModel.CategoryAppModel
import ui.unq.edu.ar.appModel.ContentAppModel
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
            List<CategoryAppModel>(it) with{
                bindSelectedTo("selectedCategory")
                width = 150
                height = 180
                bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
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
            List<CategoryAppModel>(it) with{
                width = 150
                height = 180
                bindSelectedTo("selectedCategory")
                bindItemsTo("nonSelectedCategories").adaptWithProp<CategoryAppModel>("name")
            }
        }
        Label(mainPanel) with{
            text = "Related Content: "
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<ContentAppModel>(it) with{
                bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("title")
                bindSelectedTo("selectedContent")
                width = 150
                height = 200
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    text= "<"
                    onClick {
                        addContent()
                    }
                }
                Button(it) with {
                    text= ">"
                    onClick {
                        deleteContent()
                    }
                }
            }
            List<ContentAppModel>(it) with{
                bindItemsTo("nonRelatedContent").adaptWithProp<ContentAppModel>("title")
                bindSelectedTo("selectedContent")
                width = 150
                height = 200
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
        nuevaSerie()
        close()
    }
    open fun cancelar(){
        close()
    }
    fun nuevaSerie(){
        modelObject.nuevaSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.contentState, modelObject.categories, modelObject.relatedContent)
    }
    fun addCategory(){
        modelObject.addCategory(modelObject.selectedCategory)
    }
    fun deleteCategory(){
        modelObject.deleteCategory(modelObject.selectedCategory)
    }
    fun addContent(){
        modelObject.addContent(modelObject.selectedContent)
    }
    fun deleteContent(){
        modelObject.deleteContent(modelObject.selectedContent)
    }
}