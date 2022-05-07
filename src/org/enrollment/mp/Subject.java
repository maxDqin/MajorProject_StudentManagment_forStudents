package org.enrollment.mp;

public class Subject {
	//fields
	public String[] subjectCode= {"BIT100", "BIT101", "BIT108", 
			 "BIT111", "BIT112", "BIT121","BIT230", "BIT241"};
	public String[] subjectName= {"Effective Business Communication", "Foundations of IT", 
								"Foundations of Business","Programming Concepts","Maths for IT", 								
								"Network Communication Concepts","System Analysis and Design", 
								"Professional IT Practice and Ethics" };
	public int[] semester= {1,1,1,1,2,2,2,2};
	
	// getters and setters
	public String[] getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String[] subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String[] getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String[] subjectName) {
		this.subjectName = subjectName;
	}
	public int[] getSemester() {
		return semester;
	}
	public void setSemester(int[] semester) {
		this.semester = semester;
	}
	

}
