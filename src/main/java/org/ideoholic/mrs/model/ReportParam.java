package org.ideoholic.mrs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "report_param")
public class ReportParam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "param_id", unique = true, nullable = false, updatable = false)
	private String paramId;

	// User friendly name of the report parameter
	@Column(name = "param_name", unique = true, nullable = false)
	private String paramName;

	// Jasper report param name
	@Column(name = "param_field", nullable = false)
	private String paramFieldName;

	// Type of the parameter (String, Date, Integer, Float)
	@Column(name = "param_type")
	private ParameterType paramType;

	// The default value to be used in case use_default is true
	@Column(name = "default_value")
	private String defaultValue;

	@OneToMany(mappedBy = "reportParam")
	private List<ReportParamRelation> reportMappings = new ArrayList<>();

	@PrePersist
	public void generateReportId() {
		if (this.paramId == null) {
			this.paramId = HashUtil.generateShortId();
		}
	}

}