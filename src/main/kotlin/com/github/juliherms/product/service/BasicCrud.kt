package com.github.juliherms.product.service

/**
 * Interface responsible to define all methods from crud
 */
interface BasicCrud<T,ID> {

    fun findAll():List<T>
    fun findById(id:ID): T?
    fun save(t:T):Boolean
    fun update(t:T): Boolean
    fun deleteById(id:ID): Boolean

}