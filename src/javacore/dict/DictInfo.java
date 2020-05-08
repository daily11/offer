package javacore.dict;

/**
 * 字典表
 */
public class DictInfo {
    private Integer typeCode;
    private String typeName;
    private String category;

    public DictInfo(Integer typeCode, String typeName, String category) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.category = category;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
