package com.github.juliherms.product


import com.fasterxml.jackson.databind.ObjectMapper
import com.github.juliherms.product.model.Product
import com.github.juliherms.product.service.ProductService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Matches
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.Assert
import org.springframework.web.context.WebApplicationContext
import java.util.*

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

	private val productEndpoint = "/v1/products"

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

	@Test
	fun findById(){

		val productsFromService = productService.findAll()
		assert(!productsFromService.isEmpty()){"Should not be empty"}

		val product = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/products/${product.name}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(product.name)))
	}

	@Test
	fun findByIdEmpty() {

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/products/${UUID.randomUUID()}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
	}

	@Test
	fun saveSuccessfully(){

		val product = Product(name = "Fruit",price = 50.0)

		val resut: Boolean = mockMvc.perform(MockMvcRequestBuilders.post(productEndpoint)
			.content(mapper.writeValueAsBytes(product))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(mapper)

		assert(resut)
	}
}
