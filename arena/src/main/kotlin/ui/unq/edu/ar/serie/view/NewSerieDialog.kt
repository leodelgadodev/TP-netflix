package ui.unq.edu.ar.serie.view

import domain.ExistsException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List
import org.uqbar.arena.windows.Dialog
import org.uqbar.commons.model.exceptions.UserException
import ui.unq.edu.ar.exceptions.NoNameException
import ui.unq.edu.ar.transformers.StateToBooleanTransformer
import ui.unq.edu.ar.serie.model.CategoryAppModel
import ui.unq.edu.ar.serie.model.ContentAppModel
import ui.unq.edu.ar.serie.model.SerieAppModel

open class NewSerieDialog(owner: SeriesWindow, model: SerieAppModel) : Dialog<SerieAppModel>(owner, model){

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
                bindTo("state").setTransformer(StateToBooleanTransformer())
            }
        }
        Label(mainPanel) with{
            text = "Categories"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            List<CategoryAppModel>(it) with{
                bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
                bindSelectedTo("selectedCategory")
                width = 150
                height = 180
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick{
                        addCategory()
                    }
                }
                Button(it) with {
                    caption = ">"
                    onClick{
                        deleteCategory()
                    }
                }
            }
            List<CategoryAppModel>(it) with{
                bindItemsTo("nonSelectedCategories").adaptWithProp<CategoryAppModel>("name")
                bindSelectedTo("selectedCategory")
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
            List<ContentAppModel>(it) with{
                bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("title")
                bindSelectedTo("selectedContent")
                width = 150
                height = 200
            }
            Panel(it) with{
                asVertical()
                Button(it) with {
                    caption = "<"
                    onClick {
                        addContent()
                    }
                }
                Button(it) with {
                    caption = ">"
                    onClick {
                        deleteContent()
                    }
                }
            }
            List<ContentAppModel>(it) with{
                bindItemsTo("nonRelatedContent").adaptWithProp<ContentAppModel>("description")
                bindSelectedTo("selectedContent")
                width = 150
                height = 200
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Submit"
                onClick{
                        if (thisWindow.modelObject.title != "") {
                            accept()
                        } else {
                            throw NoNameException("Please, set a title.")
                        }
                }
            }
            Button(it) with {
                caption = "Cancel"
                onClick{
                    cancel()
                }
            }

        }
    }

    override fun accept(){
        nuevaSerie()
        close()
    }

    fun nuevaSerie(){
        modelObject.nuevaSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.state, modelObject.categories, modelObject.relatedContent)
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