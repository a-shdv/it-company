package ru.ulstu.is.sbapp.developer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String firstName;
    private String lastName;

    @ManyToOne()
    @JoinColumn(name = "company_fk")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "developed_projects",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    List<Project> worksOnProjects;

/*    @ManyToMany
    @JoinTable(name = "joined_developer_project",
     joinColumns = @JoinColumn(name = "developer_fk"),
      inverseJoinColumns = @JoinColumn(name = "project_fk"))
    private List<Project> worksOnProjects = new ArrayList<>();*/

    public Developer() {
    }


    public List<Project> getWorksOnProjects() {
        return worksOnProjects;
    }

    public List<Project> setWorksOnProjects(List<Project> worksOnProjects) {
        return this.worksOnProjects = worksOnProjects;
    }


    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Developer(String firstName, String lastName, List<Project> worksOnProjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.worksOnProjects = worksOnProjects;
    }

    public void addProject(Project project) {
        if(this.worksOnProjects == null)
            this.worksOnProjects = new ArrayList<>();

        if (!worksOnProjects.contains(project)) {
            worksOnProjects.add(project);
            project.setDeveloper(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        if (!company.getDevelopers().contains(this)) {
            company.setDeveloper(this);
        }
    }

    public void setProject(Project project) {
        if (this.worksOnProjects == null)
            this.worksOnProjects = new ArrayList<>();

        if (!worksOnProjects.contains(project)) {
            worksOnProjects.add(project);
            project.addDeveloper(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Developer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
