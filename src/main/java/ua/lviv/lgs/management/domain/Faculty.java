package ua.lviv.lgs.management.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "faculty")
	public class Faculty {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		@Column
		private String name;

		@Column
		private Integer students;

		@ElementCollection
		private List<Subjects> subjects;

		public Faculty() {

		}

		public Faculty(String name, Integer students, List<Subjects> subjects) {
			this.name = name;
			this.students = students;
			this.subjects = subjects;
		}

		public Faculty(Integer id, String name, Integer students, List<Subjects> subjects) {
			this.id = id;
			this.name = name;
			this.students = students;
			this.subjects = subjects;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getStudents() {
			return students;
		}

		public void setStudents(Integer students) {
			this.students = students;
		}

		public List<Subjects> getSubjects() {
			return subjects;
		}
		public void setSubjects(List<Subjects> subjects) {
			this.subjects = subjects;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((students == null) ? 0 : students.hashCode());
			result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Faculty other = (Faculty) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (students == null) {
				if (other.students != null)
					return false;
			} else if (!students.equals(other.students))
				return false;
			if (subjects == null) {
				if (other.subjects != null)
					return false;
			} else if (!subjects.equals(other.subjects))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Faculty [id=" + id + ", name=" + name + ", students=" + students + ", subjects=" + subjects + "]";
		}

	}