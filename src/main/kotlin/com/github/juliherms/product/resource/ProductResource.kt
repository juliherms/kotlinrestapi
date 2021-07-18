package com.github.juliherms.product.resource

import com.github.juliherms.product.model.Product
import com.github.juliherms.product.service.ProductService
import org.springframework.web.bind.annotation.*

/**
 * This class responsible to expose products to API
 */
@RestController
@RequestMapping("v1/products")
class ProductResource(private val productService: ProductService) {

    /**
     * List all products
     */
    @GetMapping
    fun findAll() = productService.findAll()

    /**
     * Return product by id
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable  id:String) = productService.findById(id)

    /**
     * Save product
     */
    @PostMapping
    fun save(@RequestBody product: Product) = productService.save(product)

    /**
     * Update product
     */
    @PutMapping
    fun update(@RequestBody product:Product) = productService.update(product)

    /**
     * Delete product
     */
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:String) = productService.deleteById(id)
}