package havefun.chatter.core.network.dto

import com.google.gson.annotations.SerializedName

class Status {
    @SerializedName("code") var code: Int = 0
    @SerializedName("level") var level: Int = 0
    @SerializedName("description") var description: String? = null
}