package com.example.app.wallet.domain

import com.example.common.infra.BaseEntity
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Wallet(
    val address: String,

    var amount: BigDecimal = BigDecimal.ZERO,

    @Enumerated(EnumType.STRING)
    val targetType: TargetType,

    val targetId: Long,

    val targetName: String,

    id: Long = 0L
) : BaseEntity(id) {

    fun deposit(from: Wallet, amount: BigDecimal): WalletLog {
        this.amount += amount
        return WalletLog(WalletLog.ActionType.DEPOSIT, from, amount)
    }

    fun withdrawal(to: Wallet, amount: BigDecimal): Pair<BigDecimal, WalletLog> {
        require(this.amount >= amount) { "잔액이 부족합니다" }
        this.amount -= amount
        return Pair(amount, WalletLog(WalletLog.ActionType.WITHDRAWAL, to, amount))
    }

    enum class TargetType {
        COMMUNITY, MEMBER
    }
}