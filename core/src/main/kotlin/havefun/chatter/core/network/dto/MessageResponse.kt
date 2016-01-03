package havefun.chatter.core.network.dto

import com.google.gson.annotations.SerializedName

class MessageResponse {
    @SerializedName("text") lateinit var text: String
    @SerializedName("status") var status: Status? = null
}