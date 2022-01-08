package com.example.app.member.repository

import com.example.app.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

fun MemberRepository.findByEmail(email: String) = findByInformationEmail(email) ?: throw NoSuchElementException("멤버가 존재하지 않습니다")

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByInformationEmail(email: String) : Member?
}