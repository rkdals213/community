package com.example.app.member.service

import com.example.app.member.domain.Password
import com.example.app.member.dto.LoginForm
import com.example.app.member.repository.MemberRepository
import com.example.app.member.repository.findByEmail
import com.example.common.config.WebConfig.Companion.EXPIRATION
import com.example.common.security.MemberPayload
import com.example.common.security.Payload
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.NoSuchElementException

@Service
@Transactional
class SessionService(
    private val memberRepository: MemberRepository
) {

    fun login(loginForm: LoginForm): Payload {
        val member = memberRepository.findByEmail(loginForm.email) ?: throw NoSuchElementException("멤버가 존재하지 않습니다")
        member.authenticate(Password(loginForm.password))
        return Payload(
            LocalDateTime.now()
                .plusSeconds(EXPIRATION.toLong()),
            MemberPayload(member.id, member.email)
        )
    }
}