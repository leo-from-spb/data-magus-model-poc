package lb.dm.meta

import lb.dm.model.Family
import kotlin.reflect.KClass
import kotlin.collections.*
import kotlin.reflect.*
import kotlin.reflect.full.*

import java.lang.System
import java.util.*


internal object MetaIntrospector {

    private val klasses: MutableMap<KClass<Any>, MetaEntity> = kotlin.collections.HashMap<KClass<Any>, MetaEntity>()

    fun introspect() {

    }

    private fun introspectEntityRecursively(klass: KClass<Any>) {
        var entity = obtainEntityFor(klass)

        val familyDeclarations =
            klass.declaredMemberProperties
                    .filter { it.returnType.classifier == Family::class }
        for (fd in familyDeclarations) {
            val fn = fd.name
            val ft = fd.returnType
            assert(ft.arguments.size == 1)
            val fk = getFirstParameterKlass(ft)
            introspectEntityRecursively(fk)
        }
    }

    @Suppress("unchecked_cast")
    private fun getFirstParameterKlass(fk: KType): KClass<Any> =
            fk.arguments.first().type!!.classifier as KClass<Any>

    private fun obtainEntityFor(klass: KClass<Any>): MetaEntity {
        var entity = klasses[klass]
        if (entity == null) {
            entity = introspectEntityClass(klass)
        }
        return entity
    }

    private fun introspectEntityClass(klass: KClass<Any>): MetaEntity {
        assert(klass !in klasses)
        val entity = MetaEntity(klass)
        klasses.put(klass, entity)

    }



    private fun log(message: String) {
        java.lang.System.out.println(message)
    }

}