package org.anchor.csrSupport

import org.anchor.csrSupport.impl.TestAuditableBaseEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BaseEntityTest {

    @Test
    fun `should correctly initialize BaseEntity`() {
        // Arrange
        val entity = TestAuditableBaseEntity("test")

        // Assert
        assertEquals("test", entity.text)
    }
}