package com.github.juliherms.product.service

import com.github.juliherms.product.model.Product
import com.github.juliherms.product.utils.update
import org.springframework.stereotype.Service

/**
 * This class responsible to implements logic from product
 */
@Service
class ProductService:BasicCrud<Product,String> {

    private val products:MutableSet<Product> = mutableSetOf(Product("Apple", price = 22.2), Product(price = 33.3,name = "Banana"))

    override fun findAll(): List<Product> = products.toList()

    override fun findById(id: String): Product? = products.find { it.name == id }

    override fun save(t: Product): Boolean = products.add(t)

    override fun update(t: Product): Boolean = this.products.update(t)

    override fun deleteById(id: String): Boolean = products.remove(this.findById(id))
}
