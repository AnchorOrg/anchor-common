package org.anchor.csrSupport.integrationTest

import org.anchor.csrSupport.impl.TestAuditableBaseEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AuditableBaseEntityTest {

    @Test
    fun test() {
        val entity = TestAuditableBaseEntity(text = "testValue")
        entity.createdBy = "testUser"
        entity.updatedBy = "testUser"
        assertEquals("testUser", entity.createdBy)
        assertEquals("testUser", entity.updatedBy)
    }
}