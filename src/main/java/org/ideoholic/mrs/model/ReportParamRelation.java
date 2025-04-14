package org.ideoholic.mrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.ideoholic.mrs.util.HashUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report_param_relation")
public class ReportParamRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "relation_id", unique = true, nullable = false, updatable = false)
	private String relationId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "report_id", nullable = false)
	private Report report;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "param_id", nullable = false)
	private ReportParam reportParam;

	// If the param is enabled then it is sent to the UI
	@Column(name = "enabled")
	private Boolean enabled;

	// If the param is not enabled and use_default is true then the default value is used
	@Column(name = "use_default")
	private Boolean useDefault;
	
	@PrePersist
	public void generateRelationId() {
		if (this.relationId == null) {
			this.relationId = HashUtil.generateShortId();
		}
	}

}