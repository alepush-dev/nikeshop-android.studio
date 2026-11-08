package pe.isil.nikeshop.features.home.domain

import pe.isil.nikeshop.features.home.data.ProductRepositoryImpl

class GetProductsUseCase(
    private val repository: ProductRepository = ProductRepositoryImpl()
) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}