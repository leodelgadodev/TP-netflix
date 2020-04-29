package ui.unq.edu.ar.transformers

import domain.Available
import domain.ContentState
import org.apache.commons.collections15.Transformer

class StateToStringTransformer : Transformer<ContentState, String> {


    override fun transform(state: ContentState): String {
        return if (state.javaClass === Available().javaClass){
            "Available"
        } else {
            "Unavailable"
        }
    }
}