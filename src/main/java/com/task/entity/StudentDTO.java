package com.task.entity;
import java.util.List;

public class StudentDTO {
    private String name;
    private String dateOfBirth;
    private String gender;
    private String uniqueStudentCode;
    private String email;
    private String mobileNumber;
    private String parentsName;
    private List<StudentAddressDTO> addresses;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUniqueStudentCode() {
		return uniqueStudentCode;
	}
	public void setUniqueStudentCode(String uniqueStudentCode) {
		this.uniqueStudentCode = uniqueStudentCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getParentsName() {
		return parentsName;
	}
	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}
	public List<StudentAddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<StudentAddressDTO> addresses) {
		this.addresses = addresses;
	}
    }
