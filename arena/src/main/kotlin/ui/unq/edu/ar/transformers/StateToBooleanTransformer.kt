package ui.unq.edu.ar.transformers

import domain.Available
import domain.ContentState
import domain.Unavailable
import org.uqbar.arena.bindings.ValueTransformer

class StateToBooleanTransformer : ValueTransformer<ContentState, Boolean> {

    override fun getModelType(): Class<ContentState> {
        return ContentState::class.java
    }

    override fun getViewType(): Class<Boolean> {
        return Boolean::class.java
    }

    override fun modelToView(state: ContentState): Boolean {
        return state.javaClass === Available().javaClass
    }

    override fun viewToModel(bool: Boolean): ContentState {
        if(bool){
            return Available()
        } else {
            return Unavailable()
        }
    }
}