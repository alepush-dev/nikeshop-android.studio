package pe.isil.nikeshop.features.home.data

import pe.isil.nikeshop.features.home.data.remote.ProductApi
import pe.isil.nikeshop.features.home.domain.Product
import pe.isil.nikeshop.features.home.domain.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepositoryImpl(
    private val api: ProductApi = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/alepush-dev/nikeshop-android.studio/master/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        val response = api.getProducts()
        if (response.isSuccessful) {
            response.body()?.let { dto ->
                return dto.products.map {
                    Product(
                        id = it.id,
                        name = it.title,
                        brand = it.brand,
                        price = it.price,
                        image = it.image,
                        rating = it.rating
                    )
                }
            }
        }
        return emptyList()
    }
}