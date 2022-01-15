package com.example.app.member.service

import com.example.app.member.domain.Password
import com.example.app.member.dto.LoginForm
import com.example.app.member.repository.MemberRepository
import com.example.app.member.repository.findByEmail
import com.example.common.security.MemberPayload
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SessionService(
    private val memberRepository: MemberRepository
) {

    fun login(loginForm: LoginForm): MemberPayload {
        val member = memberRepository.findByEmail(loginForm.email)
        member.authenticate(Password(loginForm.password))
        return MemberPayload(member.id, member.email, member.name)
    }
}