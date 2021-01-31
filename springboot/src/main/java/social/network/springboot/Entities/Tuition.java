package social.network.springboot.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuition implements Serializable {

	String tuitionCode;
	String studentCode;
	String studentName;
	String studentClass;
	String dob;
	String semester;
	String academicYear;
	String unit;
	String majors;
	int amount;
	String content;
	String status;
}
