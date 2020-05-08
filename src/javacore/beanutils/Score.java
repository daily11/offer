package javacore.beanutils;

public class Score {
    private String className;
    private Integer score;
    private Integer hardType;//难易程度
    private String hardTypeName;

    public Score() {
    }

    public Score(String className, Integer score) {
        this.className = className;
        this.score = score;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHardType() {
        return hardType;
    }

    public void setHardType(Integer hardType) {
        this.hardType = hardType;
    }

    public String getHardTypeName() {
        return hardTypeName;
    }

    public void setHardTypeName(String hardTypeName) {
        this.hardTypeName = hardTypeName;
    }
}
