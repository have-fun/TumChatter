package havefun.server.chatservice.network.dto

import com.google.gson.annotations.SerializedName

internal class MessageResponse(text: String) {
    @SerializedName("text") var text: String
    @SerializedName("status") var status: Status? = null

    init {
        this.text = text
    }
}