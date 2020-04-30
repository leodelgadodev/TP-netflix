package ui.unq.edu.ar.season

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.ChapterWindow
import ui.unq.edu.ar.chapter.model.ChapterAppModel
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel
import ui.unq.edu.ar.season.model.SeasonAppModel
import ui.unq.edu.ar.season.view.ModifySeasonDialog
import ui.unq.edu.ar.serie.model.SerieAppModel
import ui.unq.edu.ar.serie.view.NewSerieDialog

class SeasonWindow(owner: WindowOwner, seasonAppModel: SerieAppModel) : SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
    val model = UNQFlixAppModel

    override fun addActions(p0: Panel?) { }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Seasons"
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Test"
                fontSize = 12
                height = 16
                width = 50
            }
        }

        Panel(mainPanel) with{
            asHorizontal()

            Button(it) with {
                text = "New Season"
                onClick { NewSerieDialog(
                    this@SeasonWindow,
                    SeasonAppModel(model)
                ).open()}
            }

            Button(it) with {
                text = "View Chapters"
                onClick {
                    //ChapterWindow(
                    //    this@SeasonWindow, SeasonAppModel(model)
                    //).open()
                }
            }

            Button(it) with {
                text = "Modify Season"
                onClick {
                    if(model.selectedSeason != null)
                        ModifySeasonDialog(
                            this@SeasonWindow,
                            SeasonAppModel(model, model.selectedSeason!!.model)
                        ).open()
                }
            }

            Button(it) with {
                text = "Delete Season"
                onClick {
                    if(model.selectedSeason != null)
                        ConfirmDeleteSeasonDialog(this@SeasonWindow, model).open()
                }
            }
        }
    }
}