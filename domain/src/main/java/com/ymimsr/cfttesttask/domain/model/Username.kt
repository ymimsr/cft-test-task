package com.ymimsr.cfttesttask.domain.model

class Username (private val firstName: String, private val lastName: String) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}