package com.github.juliherms.product


import com.fasterxml.jackson.databind.ObjectMapper
import com.github.juliherms.product.model.Product
import com.github.juliherms.product.service.ProductService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	private lateinit var webApplicationContext:WebApplicationContext

	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}

	@Autowired
	private lateinit var mapper:ObjectMapper

	@Autowired
	private lateinit var productService: ProductService

	/**
	 * Test case for list All Products
	 */
	@Test
	fun findAll() {

		val products:List<Product> = mockMvc.perform(MockMvcRequestBuilders.get("/v1/products"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(mapper)

		org.junit.jupiter.api.Assertions.assertTrue(!products.isEmpty())

	}
}
