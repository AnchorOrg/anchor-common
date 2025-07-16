package org.anchor.csrSupport.integrationTest

import org.anchor.csrSupport.BaseService
import org.anchor.csrSupport.impl.TestAuditableBaseEntity
import org.anchor.csrSupport.impl.TestRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.TestPropertySource

@TestPropertySource(locations = ["classpath:application-test.anchor-common.yml"])
@DataJpaTest
class BaseServiceTest {
    @Autowired
    private lateinit var repository: TestRepository

    private lateinit var baseService: BaseService<Long, TestAuditableBaseEntity>

    @BeforeEach
    fun setUpAll() {
        baseService = BaseService(repository)
    }

    @Test
    fun `should check if entity exists`() {
        val entity = baseService.upsert(TestAuditableBaseEntity("testValue"))
        assertTrue(baseService.exists(entity.id!!))
    }

    @Test
    fun `should count entities`() {
        baseService.upsert(TestAuditableBaseEntity("test1"))
        baseService.upsert(TestAuditableBaseEntity("test2"))
        assertEquals(2, baseService.count())
    }

    @Test
    fun `should delete all entities in batch successfully`() {
        val entities = listOf(TestAuditableBaseEntity("test1"), TestAuditableBaseEntity("test2"))
        baseService.saveAll(entities)
        baseService.deleteAllInBatch(entities)
        assertEquals(0, baseService.count())
    }

    @Test
    fun `should delete all entities successfully`() {
        baseService.upsert(TestAuditableBaseEntity("test1"))
        baseService.upsert(TestAuditableBaseEntity("test2"))
        assertEquals(2, baseService.count()) // Verification

        baseService.deleteAll()
        assertEquals(0, baseService.count()) // Verify all entities were deleted
    }

    @Test
    fun `should delete entity by ID successfully`() {
        val entity = baseService.upsert(TestAuditableBaseEntity("testValue"))
        assertTrue(baseService.exists(entity.id!!)) // Verification

        baseService.delete(entity.id!!)
        assertFalse(baseService.exists(entity.id!!)) // Verify entity was deleted by ID
    }

    @Test
    fun `should delete entity successfully`() {
        val entity = baseService.upsert(TestAuditableBaseEntity("testValue"))
        baseService.delete(entity)
        assertFalse(baseService.exists(entity.id!!))
    }

    @Test
    fun `should delete multiple entities by IDs successfully`() {
        val entity1 = baseService.upsert(TestAuditableBaseEntity("test1"))
        val entity2 = baseService.upsert(TestAuditableBaseEntity("test2"))
        assertEquals(2, baseService.count()) // Verification

        baseService.delete(listOf(entity1.id!!, entity2.id!!))
        assertEquals(0, baseService.count())
    }

    @Test
    fun `should delete entity by ID`() {
        val entity = baseService.upsert(TestAuditableBaseEntity("testValue"))
        baseService.deleteById(entity.id!!)
        assertFalse(baseService.exists(entity.id!!))
    }

    @Test
    fun `should find one entity by ID`() {
        val entity = baseService.upsert(TestAuditableBaseEntity("testValue"))
        val foundEntity = baseService.findOne(entity.id!!)
        assertEquals(entity.id, foundEntity.id)
    }

    @Test
    fun `should flush changes successfully`() {
        val entity = TestAuditableBaseEntity("testValue")
        baseService.upsert(entity)
        entity.text = "updatedValue"

        baseService.flush()

        val foundEntity = baseService.findOne(entity.id!!)
        assertEquals("updatedValue", foundEntity.text) // Verify changes were reflected in DB
    }

    @Test
    fun `should return all entities`() {
        baseService.upsert(TestAuditableBaseEntity("test1"))
        baseService.upsert(TestAuditableBaseEntity("test2"))
        val result = baseService.findAll()
        assertEquals(2, result.size)
    }

    @Test
    fun `should return all entities with sorting`() {
        baseService.upsert(TestAuditableBaseEntity("B"))
        baseService.upsert(TestAuditableBaseEntity("A"))
        val sortedEntities = baseService.findAll(Sort.by("text"))
        assertEquals("A", sortedEntities[0].text)
    }

    @Test
    fun `should return paginated entities`() {
        baseService.saveAll(
            listOf(
                TestAuditableBaseEntity("A"),
                TestAuditableBaseEntity("B"),
                TestAuditableBaseEntity("C")
            )
        )
        val pageable = PageRequest.of(0, 2)
        val page = baseService.findAll(pageable)
        assertEquals(2, page.size)
    }

    @Test
    fun `should save all entities successfully`() {
        val entities = listOf(TestAuditableBaseEntity("test1"), TestAuditableBaseEntity("test2"))
        val savedEntities = baseService.saveAll(entities)
        assertEquals(2, savedEntities.size)
    }

    @Test
    fun `should save and flush entity successfully`() {
        val entity = TestAuditableBaseEntity("testValue")
        val savedEntity = baseService.saveAndFlush(entity)
        assertNotNull(savedEntity.id)
        assertEquals("testValue", savedEntity.text)
    }

    @Test
    fun `should save entity successfully`() {
        val entity = TestAuditableBaseEntity("testValue")
        val savedEntity = baseService.upsert(entity)
        assertNotNull(savedEntity.id)
        assertEquals("testValue", savedEntity.text)
    }

    @Test
    fun `should update entity successfully`() {
        val e = TestAuditableBaseEntity("testValue")
        baseService.upsert(e)
        e.text = "updatedValue"
        val updatedEntity = baseService.upsert(e)
        assertEquals("updatedValue", updatedEntity.text)
    }
}