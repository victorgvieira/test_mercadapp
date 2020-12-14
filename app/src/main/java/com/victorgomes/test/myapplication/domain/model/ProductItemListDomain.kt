package com.victorgomes.test.myapplication.domain.model

data class ProductItemListDomain(
    val name: String? = null,
    val image: String? = null,
    val regularPrice: String? = null,
    val actualPrice: String? = null,
    val discountPercentage: String? = null,
    val installments: String? = null,
    val sizes: List<String>? = null,
)
//"regular_price": "R$ 189,90",
//"actual_price": "R$ 189,90",
//"discount_percentage": "",
//"installments": "3x R$ 63,30",
//"image": "https://d3l7rqep7l31az.cloudfront.net/images/products/20002584_035_catalog_1.jpg?1459947139",
//"sizes": [