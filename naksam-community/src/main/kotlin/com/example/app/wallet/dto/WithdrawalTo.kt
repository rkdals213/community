package com.example.app.wallet.dto

import java.math.BigDecimal

data class WithdrawalTo(
    val to: Long,
    val amount: BigDecimal
)