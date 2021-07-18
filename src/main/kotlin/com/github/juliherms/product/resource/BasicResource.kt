package com.github.juliherms.product.resource

import com.github.juliherms.product.service.BasicCrud
import org.springframework.web.bind.annotation.*

/**
 * This class responsible to provide basic operations to resource
 */
abstract  class BasicResource<T,ID>(private val basicCrud: BasicCrud<T,ID>) {

    /**
     * List all resource
     */
    @GetMapping
    fun findAll() = basicCrud.findAll()

    /**
     * Return resource by id
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable id:ID) = basicCrud.findById(id)

    /**
     * Save resource
     */
    @PostMapping
    fun save(@RequestBody t: T) = basicCrud.save(t)

    /**
     * Update resource
     */
    @PutMapping
    fun update(@RequestBody t: T) = basicCrud.update(t)

    /**
     * Delete resource
     */
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:ID) = basicCrud.deleteById(id)
}