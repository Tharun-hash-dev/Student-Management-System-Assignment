package com.task.entity;

import javax.persistence.*;
import java.util.List;

@Entity

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String description;
    private String courseType;
    private int duration; // in months or days, specify in description
    @ElementCollection
    private List<String> topics;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}

