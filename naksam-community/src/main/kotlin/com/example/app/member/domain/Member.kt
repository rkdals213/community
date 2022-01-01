package com.example.app.member.domain

import com.example.common.infra.BaseRootEntity
import java.time.LocalDate
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
class Member(
    @Embedded
    var information: MemberInformation,

    @Embedded
    var password: Password,

    id: Long = 0L
) : BaseRootEntity<Member>(id) {
    val name: String
        get() = information.name

    val email: String
        get() = information.email

    val phoneNumber: String
        get() = information.phoneNumber

    val gender: MemberInformation.Gender
        get() = information.gender

    val birthday: LocalDate
        get() = information.birthday

    constructor(
        name: String,
        email: String,
        phoneNumber: String,
        gender: MemberInformation.Gender,
        birthday: LocalDate,
        password: Password,
        id: Long = 0L
    ) : this(
        MemberInformation(name, email, phoneNumber, gender, birthday), password, id
    )

    fun authenticate(password: Password) {
        identify(this.password == password) { "사용자 정보가 일치하지 않습니다." }
    }

    fun resetPassword(name: String, birthday: LocalDate, password: String) {
        identify(information.same(name, birthday)) { "사용자 정보가 일치하지 않습니다." }
        this.password = Password(password)
        registerEvent(PasswordResetEvent(id, name, email, password))
    }

    fun changePassword(oldPassword: Password, newPassword: Password) {
        identify(this.password == oldPassword) { "기존 비밀번호가 일치하지 않습니다." }
        this.password = newPassword
    }

    fun changePhoneNumber(phoneNumber: String) {
        information = information.copy(phoneNumber = phoneNumber)
    }

    private fun identify(value: Boolean, lazyMessage: () -> Any = {}) {
        if (!value) {
            val message = lazyMessage()
            throw RuntimeException(message.toString())
        }
    }
}