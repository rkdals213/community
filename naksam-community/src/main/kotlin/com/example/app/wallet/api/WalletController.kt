package com.example.app.wallet.api

import com.example.app.member.domain.Member
import com.example.app.wallet.dto.DepositFrom
import com.example.app.wallet.dto.WithdrawalTo
import com.example.app.wallet.service.WalletService
import com.example.common.security.JwtClaim
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wallet")
class WalletController(
    private val walletService: WalletService
) {
    @PostMapping("/deposit")
    fun depositFrom(@JwtClaim member: Member, @RequestBody depositFrom: DepositFrom): ResponseEntity<Any> {
        return ResponseEntity.ok(walletService.depositFrom(member, depositFrom))
    }

    @PostMapping("/withdrawal")
    fun withdrawal(@JwtClaim member: Member, @RequestBody withdrawalTo: WithdrawalTo): ResponseEntity<Any> {
        return ResponseEntity.ok(walletService.withdrawalTo(member, withdrawalTo))
    }
}