package com.example.app.wallet.repository

import com.example.app.wallet.domain.Wallet
import org.springframework.data.jpa.repository.JpaRepository

fun WalletRepository.findByOwnerId(id: Long, type: Wallet.TargetType) = findByTargetIdAndTargetType(id, type) ?: throw NoSuchElementException("지갑이 없습니다")

interface WalletRepository : JpaRepository<Wallet, Long> {
    fun findByTargetIdAndTargetType(id: Long, type: Wallet.TargetType): Wallet?
}
