package com.victorgomes.test.myapplication.data.service.model

import com.squareup.moshi.Json

class ProductItemListRaw(
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "regular_price")
    val regularPrice: String? = null,
    @Json(name = "actual_price")
    val actualPrice: String? = null,
    @Json(name = "discount_percentage")
    val discountPercentage: String? = null,
    @Json(name = "installments")
    val installments: String? = null,
    @Json(name = "sizes")
    val sizes: List<SizesRaw>? = null,
) {
    class SizesRaw(
        @Json(name = "available")
        val available: Boolean?,
        @Json(name = "size")
        val size: String?,
        @Json(name = "sku")
        val sku: String?
    )
}

//- Imagem;
//- Nome;
//- Preço;
//- Status de promoção;
//- Preço promocional (se houver);
//- Parcelamentos disponíveis;
//- Tamanhos disponíveis.