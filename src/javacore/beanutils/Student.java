package javacore.beanutils;

import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private Integer age;
    private Integer countryType;//国家类型
    private List<Score> list;
    private List<Student> studentList;
    private List<Map<String,Object>> mapList;
    private String countryTypeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Score> getList() {
        return list;
    }

    public void setList(List<Score> list) {
        this.list = list;
    }

    public Integer getCountryType() {
        return countryType;
    }

    public void setCountryType(Integer countryType) {
        this.countryType = countryType;
    }

    public String getCountryTypeName() {
        return countryTypeName;
    }

    public void setCountryTypeName(String countryTypeName) {
        this.countryTypeName = countryTypeName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }
}
