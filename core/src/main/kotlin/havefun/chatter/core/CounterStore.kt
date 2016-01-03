package havefun.chatter

class CounterStore {
    private var count: Int = 15

    fun add(num: Int) {
        count += num + 2
    }

    fun get(): Int {
        return count
    }
}
