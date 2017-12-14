package lb.dm.model


/**
 * Abstract element of a conceptual model.
 */
interface ConElement : NamedElement
{

}


/**
 * Abstract subject area — can be a [ConModel] or a [ConSubjectArea].
 */
interface ConArea : ConElement
{

    val areas:    Family<ConArea>
    val domains:  Family<ConDomain>
    val entities: Family<ConElement>

}


/**
 * Conceptual model.
 */
@Final
interface ConModel : ConElement
{

}


/**
 * Inner subject area.
 */
@Final
interface ConSubjectArea : ConArea
{

}


@Final
interface ConDomain : ConElement
{

    @State
    val category: DataCategory

}


@Final
interface ConEntity : ConElement
{

    val inheritor: Refs<ConInheritor>

    val attributes: Family<ConAttribute>

}


interface ConInfraAttribute : ConElement
{

    @State
    val category: DataCategory


}


@Final
interface ConAttribute : ConInfraAttribute
{


}


@Final
interface ConInheritor : ConElement
{

    val entity: Ref<ConEntity>

    @Single
    val discriminants: Family<ConDiscriminant>

    @State
    val exclusive: Boolean

    @State
    val complete: Boolean

}


@Final
interface ConDiscriminant : ConInfraAttribute
{

}


@Final
interface ConLink : ConElement
{

    @State
    val entity1: Ref<ConEntity>

    @State
    val entity2: Ref<ConEntity>

    @State
    val end1: LinkEndKind

    @State
    val end2: LinkEndKind

}
