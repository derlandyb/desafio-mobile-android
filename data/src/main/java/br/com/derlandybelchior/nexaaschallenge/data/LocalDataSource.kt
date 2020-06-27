package br.com.derlandybelchior.nexaaschallenge.data

import br.com.derlandybelchior.nexaaschallenge.domain.product.Product
import br.com.derlandybelchior.nexaaschallenge.domain.product.ProductDataSource

class LocalDataSource(
    private val productDao: ProductDAO
) : ProductDataSource {
    override suspend fun fetchProducts(): List<Product> {
        return productDao.fetchAll().map {
            Product(
                name = it.name,
                description = it.description,
                tax = it.tax,
                stock = it.stock,
                shipping = it.shipping,
                quantity = it.quantity,
                price = it.price,
                image = it.image
            )
        }
    }

    override suspend fun save(products: List<Product>) {
        val data = products.map {
            ProductDTO(
                id = 0L,
                name = it.name,
                description = it.description,
                tax = it.tax,
                stock = it.stock,
                shipping = it.shipping,
                quantity = it.quantity,
                price = it.price,
                image = it.image
            )
        }.toTypedArray()

        productDao.insert(products = *data)
    }
}