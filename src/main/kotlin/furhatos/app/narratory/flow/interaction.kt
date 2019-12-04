package furhatos.app.narratory.flow

import furhatos.app.narratory.getMessages
import furhatos.app.narratory.getInitialMessage
import furhatos.app.narratory.session
import furhatos.app.narratory.speak
import furhatos.flow.kotlin.*

val Start: State = state(Interaction) {
    onEntry {
        speak(getInitialMessage(session))
    }

    onResponse {
        speak(getMessages(session, it.text))
    }
}
