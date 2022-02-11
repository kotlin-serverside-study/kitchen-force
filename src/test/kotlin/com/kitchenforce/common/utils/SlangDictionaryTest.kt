package com.kitchenforce.common.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class SlangDictionaryTest {

    @Test
    @DisplayName("비속어 사전의 기능 테스트")
    fun slangDictionaryTest() {
        val result = SlangDictionary.isSlang("정상적입니다.")
        assertFalse(result)
    }
}
