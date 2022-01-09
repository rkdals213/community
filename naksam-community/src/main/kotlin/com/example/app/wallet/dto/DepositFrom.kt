package com.example.app.wallet.dto

import java.math.BigDecimal

data class DepositFrom(
    val from: Long,
    val amount: BigDecimal
)