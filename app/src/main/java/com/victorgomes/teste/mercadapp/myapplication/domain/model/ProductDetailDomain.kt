package com.victorgomes.teste.mercadapp.myapplication.domain.model

data class ProductDetailDomain(
    val name: String? = null,
    val image: String? = null,
    val regularPrice: String? = null,
    val actualPrice: String? = null,
    val discountPercentage: String? = null,
    val installments: String? = null,
    val sizes: List<String>? = null,
) {
    val discountApplied: Boolean
        get() = !discountPercentage.isNullOrBlank()

    val textSizes: String
        get() = "Tamanhos disponíveis\n${sizes?.joinToString() ?: "Nenhum"}"

    val showSizes: Boolean
        get() = !sizes.isNullOrEmpty()

    val discountText: String
        get() = "Desconto de $discountPercentage"
}