package lb.dm.model



interface Root : Element
{

}


@Final
interface Project: Root
{

    val concepts: Family<ConModel>

}


@Final
interface Farm: Root
{

}
