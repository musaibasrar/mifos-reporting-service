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
@Table(name = "report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "report_id", unique = true, nullable = false, updatable = false)
	private String reportId;

	@Column(name = "report_name", unique = true, nullable = false)
	private String reportName;

	@Column(name = "report_file", unique = true, nullable = false)
	private String reportFile;

	@OneToMany(mappedBy = "report")
	private List<ReportParamRelation> paramMappings = new ArrayList<>();

	@PrePersist
	public void generateReportId() {
		if (this.reportId == null) {
			this.reportId = HashUtil.generateShortId();
		}
	}
}
