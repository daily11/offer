package javacore.beanutils;

import java.util.List;

public class Student {
    private String name;
    private Integer age;
    private Integer countryType;//国家类型
    private List<Score> list;

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
}
