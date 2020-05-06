package ui.unq.edu.ar.chapter.view

import org.uqbar.arena.widgets.Panel
import ui.unq.edu.ar.chapter.ChaptersWindow
import ui.unq.edu.ar.chapter.model.ChapterAppModel

class ModifyChapterDialog (owner : ChaptersWindow, model: ChapterAppModel) : NewChapterDialog(owner,model){

    override fun accept() {
        modifyChapter()
        close()
    }

    private fun modifyChapter(){
        modelObject.modifyChapter(modelObject.title,modelObject.description,modelObject.duration,modelObject.thumbnail,modelObject.video)
    }
}