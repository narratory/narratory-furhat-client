# Narratory Furhat client

Docs on how to build chatbots with Narratory @ [narratory.io](https://narratory.io). Furhat docs @ [furhat.io](https://furhat.io).

* Put your Google credentials for the Dialogflow project you use for Narratory in google_credentials.json in the root of this repo
* Run like a normal Furhat skill

Limitations
* No expressivity beyond basic attention
* Not resetting attention on user leave
* Not resetting sessionId for every new user
* Hardcoded to english language for now
* Not honoring Narratory's "end of conversation", so will loop forever in the end of dialogs