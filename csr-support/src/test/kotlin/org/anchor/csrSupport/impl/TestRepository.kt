package org.anchor.csrSupport.impl

import org.anchor.csrSupport.BaseRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : BaseRepository<TestAuditableBaseEntity, Long>
