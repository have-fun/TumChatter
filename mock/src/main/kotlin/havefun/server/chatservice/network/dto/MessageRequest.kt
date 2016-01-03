package havefun.server.chatservice.network.dto

import com.google.gson.annotations.SerializedName

class MessageRequest {
    @SerializedName("text") var text: String = ""
}