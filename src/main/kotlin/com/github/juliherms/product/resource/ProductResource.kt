package com.github.juliherms.product.resource

import com.github.juliherms.product.model.Product
import com.github.juliherms.product.service.ProductService
import org.springframework.web.bind.annotation.*

/**
 * This class responsible to expose products to API
 */
@RestController
@RequestMapping("v1/products")
class ProductResource(productService: ProductService): BasicResource<Product,String>(productService)