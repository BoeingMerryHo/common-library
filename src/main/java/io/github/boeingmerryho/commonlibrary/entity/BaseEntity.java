package io.github.boeingmerryho.commonlibrary.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(updatable = false)
	private Long createdBy;

	@Column
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	@LastModifiedBy
	private Long updatedBy;

	@Column(nullable = false)
	@Builder.Default
	private Boolean isDeleted = false;

	@Column
	private Long deletedBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime deletedAt;

	/**
	 * Soft delete method for entity
	 * @param deleteUserId 삭제자 id
	 */
	public void softDelete(Long deleteUserId) {
		this.deletedAt = LocalDateTime.now();
		this.deletedBy = deleteUserId;
		this.isDeleted = true;
	}

	/**
	 * Restore soft deleted entity
	 */
	public void restore() {
		this.deletedAt = null;
		this.deletedBy = null;
		this.isDeleted = false;
	}
}