package com.mrozon.core_mvvm.base

import androidx.annotation.Nullable

abstract class BaseDto<Entity, Model> {

    abstract fun map(entity: Entity) : Model
    abstract fun reverseMap(model: Model) : Entity

    @JvmName("mapNullable")
    fun map(@Nullable entity: Entity?) : Model? {
        return if (entity==null)
            null
        else
            map(entity)
    }

    @JvmName("reverseNullable")
    fun reverseMap(@Nullable model: Model?) : Entity? {
        return if (model==null)
            null
        else
            reverseMap(model)
    }

    fun map(entityList: List<Entity>) : List<Model> {
        val modelList = arrayListOf<Model>()
        entityList.forEach { entity ->
            modelList.add(map(entity))
        }
        return modelList
    }

    fun reverseMap(modelList: List<Model>) : List<Entity> {
        val entityList = arrayListOf<Entity>()
        modelList.forEach { model ->
            entityList.add(reverseMap(model))
        }
        return entityList
    }
}