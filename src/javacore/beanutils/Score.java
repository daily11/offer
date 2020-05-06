package javacore.beanutils;

public class Score {
    private String className;
    private Integer score;

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

    @Override
    public String toString() {
        return "Score{" +
                "className='" + className + '\'' +
                ", score=" + score +
                '}';
    }
}
