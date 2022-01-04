package com.example.app.community.api

import com.example.app.community.dto.CommunityCondition
import com.example.app.community.dto.CreateCommunityRequest
import com.example.app.community.service.CommunityQueryService
import com.example.app.community.service.CommunityService
import com.example.app.member.domain.Member
import com.example.common.security.Authenticated
import com.example.common.security.JwtClaim
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityService: CommunityService,
    private val communityQueryService: CommunityQueryService
) {

    @GetMapping
    fun findByCondition(communityCondition: CommunityCondition, pageable: Pageable) : ResponseEntity<Any> {
        return ResponseEntity.ok(communityQueryService.findByCondition(communityCondition, pageable))
    }

    @GetMapping("/{communityId}")
    fun findDetail(@PathVariable communityId: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(communityQueryService.findDetail(communityId))
    }

    @Authenticated
    @PostMapping
    fun createCommunity(@JwtClaim member: Member, @RequestBody createCommunityRequest: CreateCommunityRequest) : ResponseEntity<Any> {
        return ResponseEntity.ok(communityService.createCommunity(member, createCommunityRequest))
    }

    @Authenticated
    @PostMapping("/join/{communityId}")
    fun joinCommunity(@JwtClaim member: Member, @PathVariable communityId: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(communityService.joinCommunity(member, communityId))
    }
}