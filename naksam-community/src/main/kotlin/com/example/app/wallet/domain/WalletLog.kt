package com.example.app.wallet.domain

import com.example.common.infra.BaseEntity
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class WalletLog(
    @Enumerated(EnumType.STRING)
    val actionType: ActionType,

    @ManyToOne
    val target: Wallet,

    val amount: BigDecimal,

    id: Long = 0L
) : BaseEntity(id) {

    enum class ActionType {
        DEPOSIT, WITHDRAWAL
    }
}