package havefun.server.chatservice.helper

class SimpleBrain : Brain {
    private var isStranger: Boolean = true
    private var meetUpRequested: Boolean = false

    override fun respond(input: String): String {
        val lcInput = input.toLowerCase()

        var response = when {
            lcInput.contains(Regex("hi|hello")) -> "Hi"
            lcInput.contains("how are you") -> "I'm fine thanks."
            lcInput.contains(Regex("what.+ your name")) -> "I’m taught not tell my name to a stranger."
            lcInput.contains("my name is") -> {
                isStranger = false
                if (meetUpRequested) "Where should we meet?" else "Nice name."
            }
            lcInput.contains("could we meet") -> if (isStranger) {
                meetUpRequested = true
                "I’m taught not to go out and meet up with strangers."
            } else {
                "Where?"
            }
            else -> "You're too complicated."
        }

        // I'm too fast, slow myself down.
        Thread.sleep(500)
        return response;
    }
}