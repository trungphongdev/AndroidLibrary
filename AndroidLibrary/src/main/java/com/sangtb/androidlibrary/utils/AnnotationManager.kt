package com.sangtb.androidlibrary.utils

import java.util.*

object AnnotationManager {
    private val treeManager by lazy {  Hashtable<String, Annotation>() }
    private const val MIN_DEEP = 0
    private const val MAX_DEEP = 5

    fun <A : Annotation> getAnnotation1(clazz : Class<*>, annotationClass: Class<A>): A? {
        if (!treeManager.contains(clazz.name)) {
            return findAnnotation(clazz,annotationClass, MIN_DEEP)?.apply {
                treeManager[javaClass.name] = this
            }
        }
        return treeManager[clazz.name] as A
    }

    fun <A : Annotation> findAnnotation(clazz: Class<*>, annotationClass : Class<A>, deep : Int) : A?{
        val annotation = clazz.getAnnotation(annotationClass)
        if(annotation != null) return annotation
        if(deep > MAX_DEEP) return null
        return javaClass.superclass?.let {
            println("supperclass: ${it}")
            findAnnotation(it,annotationClass,deep + 1)
        }
    }

    fun <A : Annotation> hasAnnotation() = treeManager.contains(this.javaClass.name)
}

public inline fun <reified A : Annotation> Any.getAnnotation(): A?{
    return AnnotationManager.getAnnotation1<A>(javaClass,A::class.java)
}