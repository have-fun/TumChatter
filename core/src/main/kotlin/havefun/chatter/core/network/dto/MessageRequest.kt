package havefun.chatter.core.network.dto

import com.google.gson.annotations.SerializedName

class MessageRequest {
    @SerializedName("text") var text: String? = null
    // TODO: Send values detected by sensors.

    constructor(text: String?) {
        this.text = text
    }
}