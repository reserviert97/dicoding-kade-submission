package com.nurlatif.submission.network

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val repository = mock(ApiRepository::class.java)
        val url = "https://www.google.com"
        repository.doRequest(url)
        verify(repository).doRequest(url)
    }
}