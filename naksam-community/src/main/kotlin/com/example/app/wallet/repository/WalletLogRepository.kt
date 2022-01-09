package com.example.app.wallet.repository

import com.example.app.wallet.domain.WalletLog
import org.springframework.data.jpa.repository.JpaRepository

interface WalletLogRepository : JpaRepository<WalletLog, Long> {
}