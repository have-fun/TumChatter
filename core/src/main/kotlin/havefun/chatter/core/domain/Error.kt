package havefun.chatter.core.domain

open class Error(val level: Error.SeverityLevel, message: String?, extra: Any? = null, cause: Throwable? = null) : Throwable(message, cause) {
    enum class SeverityLevel {
        INFORMATIONAL, WARNING, SYSTEM
    }
}