package com.github.juliherms.product.resource

import com.github.juliherms.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This class responsible to expose products to API
 */
@RestController
@RequestMapping("v1/products")
class ProductResource(private val productService: ProductService) {

    @GetMapping
    fun findAll() = productService.findAll()
}