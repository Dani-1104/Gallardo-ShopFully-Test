package com.example.gallardoshopfullytest.core.tracking

import com.example.gallardoshopfullytest.data.model.Flyer

data class StreamFullyEvent(
    val eventType: String,
    val attributes: HashMap<String, String>
) {

    companion object {
        const val FLYER_OPEN_EVENT_TYPE = "flyer_open"
        const val FLYER_SESSION_EVENT_TYPE = "flyer_session"
        const val RETAILER_ID = "retailer_id"
        const val FLYER_ID = "flyer_id"
        const val TITLE = "title"
        const val POSITION = "position"
        const val FIRST_READ = "first_read"
        const val SESSION_DURATION = "session_duration"
    }


}

fun createFlyerOpenEvent(flyer: Flyer, position: Int): StreamFullyEvent {
    val attributes = HashMap<String, String>()
    attributes[StreamFullyEvent.RETAILER_ID] = flyer.retailerId
    attributes[StreamFullyEvent.FLYER_ID] = flyer.id
    attributes[StreamFullyEvent.TITLE] = flyer.title
    attributes[StreamFullyEvent.POSITION] = position.toString()
    attributes[StreamFullyEvent.FIRST_READ] = (flyer.timesRead == 1).toString()

    return StreamFullyEvent(
        eventType = StreamFullyEvent.FLYER_OPEN_EVENT_TYPE,
        attributes = attributes
    )
}

fun createFlyerSessionEvent(
    flyerId: String,
    sessionDuration: Long,
    isFirstRead: Boolean
): StreamFullyEvent {
    val attributes = HashMap<String, String>()
    attributes[StreamFullyEvent.FLYER_ID] = flyerId
    attributes[StreamFullyEvent.FIRST_READ] = isFirstRead.toString()
    attributes[StreamFullyEvent.SESSION_DURATION] = sessionDuration.toString()

    return StreamFullyEvent(
        eventType = StreamFullyEvent.FLYER_SESSION_EVENT_TYPE,
        attributes = attributes
    )
}