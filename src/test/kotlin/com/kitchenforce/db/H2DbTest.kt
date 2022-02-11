package com.kitchenforce.db

import com.kitchenforce.db.jpa.TestEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@DataJpaTest
@ActiveProfiles("test")
class H2DbTest @Autowired constructor(
    @PersistenceContext
    val em: EntityManager
) {

    @Test
    @DisplayName("Test DB 연동 테스트(H2 Database)")
    fun testH2DbTest() {
        val newEntity = TestEntity(null, "test")

        assertDoesNotThrow {
            em.persist(newEntity)

            em.flush()
            em.clear()
        }

        val searchedEntity = em.find(TestEntity::class.java, 1L)

        assertEquals(newEntity.value, searchedEntity.value)
    }
}
