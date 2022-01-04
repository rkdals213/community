package com.example

import com.example.app.community.domain.Category
import com.example.app.community.domain.Community
import com.example.app.community.domain.CommunityImages
import com.example.app.community.domain.CommunityMemberIds
import com.example.app.member.domain.Member
import com.example.app.member.domain.MemberInformation
import com.example.app.member.domain.Password
import com.example.common.infra.Location
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.Month
import javax.annotation.PostConstruct
import javax.persistence.EntityManager

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Component
class InitService(
    private val init: Init
) {

    @PostConstruct
    fun init() {
        init.init()
    }

    companion object {
        @Component
        @Transactional
        class Init(private val em: EntityManager) {
            fun init() {
                val member = Member(
                    name = "test",
                    email = "test@naver.com",
                    phoneNumber = "010-0000-0000",
                    gender = MemberInformation.Gender.MALE,
                    birthday = LocalDate.of(1992, Month.OCTOBER, 26),
                    password = Password("1q2w3e4r")
                )
                em.persist(member)

                val category = Category("category")
                em.persist(category)

                for (i in 0 until 10) {
                    val community = Community(
                        name = "community$i",
                        category = category,
                        communityMemberIds = CommunityMemberIds(),
                        maxMemberCount = 1,
                        location = Location(
                            "state",
                            "city"
                        ),
                        communityImages = CommunityImages(),
                        description = "description"
                    )
                    em.persist(community)
                }
            }
        }
    }
}