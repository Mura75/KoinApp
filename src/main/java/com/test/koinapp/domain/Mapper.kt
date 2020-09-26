package com.test.koinapp.domain

interface Mapper<N, M> {

    fun from(model: N): M

    fun to(model: M): N
}