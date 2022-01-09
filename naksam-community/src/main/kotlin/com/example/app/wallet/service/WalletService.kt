package com.example.app.wallet.service

import com.example.app.community.repository.CommunityRepository
import com.example.app.member.domain.Member
import com.example.app.member.repository.MemberRepository
import com.example.app.wallet.domain.Wallet
import com.example.app.wallet.domain.WalletLog
import com.example.app.wallet.dto.DepositFrom
import com.example.app.wallet.dto.WithdrawalTo
import com.example.app.wallet.repository.WalletLogRepository
import com.example.app.wallet.repository.WalletRepository
import com.example.app.wallet.repository.findByOwnerId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class WalletService(
    private val walletRepository: WalletRepository,
    private val communityRepository: CommunityRepository,
    private val memberRepository: MemberRepository,
    private val walletLogRepository: WalletLogRepository
) {
    fun depositFrom(member: Member, depositFrom: DepositFrom) {
        communityRepository.existsById(depositFrom.from)

        val from = walletRepository.findByOwnerId(depositFrom.from, Wallet.TargetType.COMMUNITY)
        val to = walletRepository.findByOwnerId(member.id, Wallet.TargetType.MEMBER)

        val walletLogs = depositFromTo(from, to, depositFrom.amount)
        walletLogRepository.saveAll(walletLogs)
    }

    fun withdrawalTo(member: Member, withdrawalTo: WithdrawalTo) {
        communityRepository.existsById(withdrawalTo.to)

        val from = walletRepository.findByOwnerId(member.id, Wallet.TargetType.MEMBER)
        val to = walletRepository.findByOwnerId(withdrawalTo.to, Wallet.TargetType.COMMUNITY)

        val walletLogs = depositFromTo(from, to, withdrawalTo.amount)
        walletLogRepository.saveAll(walletLogs)
    }

    private fun depositFromTo(from: Wallet, to: Wallet, amount: BigDecimal): List<WalletLog> {
        val (withdrawalAmount, withdrawalLog) = from.withdrawal(to, amount)
        val depositLog = to.deposit(from, withdrawalAmount)
        return listOf(withdrawalLog, depositLog)
    }
}