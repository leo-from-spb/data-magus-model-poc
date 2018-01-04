package lb.dm.model



sealed class BaseFamily<out E: Element>
(
    override val elements: List<E>
)
    : Family<E>


fun <E: Element> familyOf(elements: Collection<E>): BaseFamily<E>
{
    val n = elements.size
    return when {
        n == 0 -> EmptyFamily()
        n < 7  -> SmallFamily(elements.toList())
        else   -> LargeFamily(elements.toList())
    }
}



class EmptyFamily<out E: Element> : BaseFamily<E>(emptyList())
{
    override fun byId(id: Int): E? = null
}



class SmallFamily<out E: Element> : BaseFamily<E>
{
    internal constructor(elements: List<E>) : super(elements)

    override fun byId(id: Int): E? = elements.find { it.id == id }
}


class LargeFamily<out E: Element> : BaseFamily<E>
{
    private val ie: Array<E>

    internal constructor(elements: List<E>)
            : super(elements)
    {
        ie = elements.toTypedArray()
        ie.sortBy { e -> e.id }
    }

    override fun byId(id: Int): E? {
        // TODO implement binary search here
        return ie.find { it.id == id }
    }
}

