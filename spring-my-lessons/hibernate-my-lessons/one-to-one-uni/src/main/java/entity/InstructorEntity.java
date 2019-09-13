package entity;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTOR", schema = "ONE_TO_ONE_UNI", catalog = "DATABASE1")
public class InstructorEntity {
    private int id;
    private String name;
    private InstructorDetailEntity instructorDetail;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstructorEntity that = (InstructorEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "INSTRUCTOR_DETAIL_ID", referencedColumnName = "ID")
    public InstructorDetailEntity getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetailEntity instructorDetail) {
        this.instructorDetail = instructorDetail;
    }
}
