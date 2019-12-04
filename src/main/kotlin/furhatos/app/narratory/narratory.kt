package furhatos.app.narratory

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import com.google.protobuf.ProtocolStringList
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import java.io.FileInputStream
import java.util.*

/* Dialogflow initialization */
val credentials = ServiceAccountCredentials.fromStream(FileInputStream("google_credentials.json"))
var sessionsSettings = SessionsSettings.newBuilder()
        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
        .build()
val client = SessionsClient.create(sessionsSettings)
val session = SessionName.of(credentials.projectId, UUID.randomUUID().toString())

/* Initiates the dialog by sending the WELCOME message */
fun getInitialMessage(session: SessionName) : ProtocolStringList? {
    val event = EventInput.newBuilder().setName("WELCOME").setLanguageCode("en").build()
    val queryInput = QueryInput.newBuilder().setEvent(event).build()
    val response = client.detectIntent(session, queryInput)
    val queryResult = response.queryResult
    return queryResult.fulfillmentMessagesList.map { it.text.textList }.firstOrNull()
}

/* Sends query text and gets responses */
fun getMessages(session: SessionName,
                message: String): ProtocolStringList? {
    val textInput = TextInput.newBuilder().setText(message).setLanguageCode("en").build()
    val queryInput = QueryInput.newBuilder().setText(textInput).build()
    val response = client.detectIntent(session, queryInput)
    val queryResult = response.queryResult
    return queryResult.fulfillmentMessagesList.map { it.text.textList }.firstOrNull()
}

/* Flow convenience method to have Furhat speak out */
fun FlowControlRunner.speak(messages: List<String>?) {
    val _messages = messages ?: listOf("I didn't have anything to say unfortunately")
    _messages.forEachIndexed { index, text ->
        if (index == _messages.size - 1) {
            furhat.ask(text)
        } else {
            furhat.say(text)
        }
    }
}