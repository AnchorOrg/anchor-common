package org.anchor.csrSupport.integrationTest

import org.anchor.csrSupport.impl.TestAuditableBaseEntity
import org.anchor.csrSupport.impl.TestRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class BaseRepositoryTest {
    @Autowired
    private lateinit var repository: TestRepository

    private lateinit var entity: TestAuditableBaseEntity

    @BeforeEach
    fun setUp() {
        entity = TestAuditableBaseEntity("test")
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    @Test
    fun `should save and retrieve entity`() {

        // Act
        val savedEntity = repository.save(entity)
        val retrievedEntity = savedEntity.id?.let { repository.findById(it).orElse(null) }

        // Assert
        assertNotNull(retrievedEntity, "Retrieved entity should not be null")
    }
}
