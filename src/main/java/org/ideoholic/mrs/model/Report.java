package org.ideoholic.mrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "report_id", unique = true, nullable = false)
	private Long reportId;

	@Column(name = "report_name", unique = true, nullable = false)
	private String reportName;

	@Column(name = "report_file", unique = true, nullable = false)
	private String reportFile;

}
