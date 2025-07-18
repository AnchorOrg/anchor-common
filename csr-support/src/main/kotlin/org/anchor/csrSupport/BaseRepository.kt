package org.anchor.csrSupport

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseRepository<T : BaseEntity, ID : Serializable> : JpaRepository<T, Long>