package ui.unq.edu.ar.sharedviews

import org.eclipse.ui.internal.Model
import org.uqbar.arena.kotlin.extensions.asHorizontal
import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog

class RepeatedNameErrorDialog(owner: Dialog<Any>, model: Model): Dialog<Any>(owner, model) {
    override fun createFormPanel(mainPanel : Panel) {
        Label(mainPanel) with {
            text = "Error: This name already exist on the system. Please, choose another name."
            alignLeft()
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {
                    close()
                }
            }
        }
    }
}