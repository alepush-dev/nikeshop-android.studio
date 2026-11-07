package pe.isil.nikeshop.features.home.data

import pe.isil.nikeshop.R
import pe.isil.nikeshop.features.home.domain.Product
import pe.isil.nikeshop.features.home.domain.ProductRepository

class InMemoryProductRepository : ProductRepository {

    override fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Nike Pegasus Plus 2",
                brand = "Nike",
                price = 459.9,
                image = R.drawable.nike_air_motion,
                rating = 4.9
            ),
            Product(
                id = 2,
                name = "Nike Ava Edge",
                brand = "Nike",
                price = 399.9,
                image = R.drawable.asics_velocity_pro,
                rating = 4.8
            ),
            Product(
                id = 3,
                name = "Nike Air Force 1",
                brand = "Nike",
                price = 349.9,
                image = R.drawable.adidas_runboost_x,
                rating = 4.7
            )
        )
    }
}