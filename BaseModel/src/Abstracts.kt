package lb.dm.model

/**
 * An abstract node of the tree; can be either [Element] or [Family].
 */
interface Node
{

    val innerNodes: List<Node>

}


interface Family<out E: Element> : Node, Sequence<E>
{

    val elements: List<E>

    override val innerNodes: List<Node>
        get() = elements

    override fun iterator(): Iterator<E>
        = elements.iterator()

}


interface Element : Node
{

    val id: Int

    val families: List<Family<*>>

    override val innerNodes: List<Node>
        get() = families

}


interface NamedElement : Element
{
    
    @State
    val name: String?

}



interface Ref<out E: Element>
{

    @State
    val id: Int

}


interface Refs<out E: Element>
{

    @State
    val ids: List<Int>

}