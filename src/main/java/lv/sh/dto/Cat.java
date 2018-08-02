package lv.sh.dto;

import java.util.List;

public class Cat {

    private String id;
    private String name;
    private String lastname;
    private String breed;
    private Integer age;
    private List<String> vaccination;
    private List<Cat> ancestors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getVaccination() {
        return vaccination;
    }

    public void setVaccination(List<String> vaccination) {
        this.vaccination = vaccination;
    }

    public List<Cat> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<Cat> ancestors) {
        this.ancestors = ancestors;
    }

}
