package org.akachi.humanTraining.entity;

public class Symbol {
    private int id;
    private String type;
    private String typeItem;
    private String mark;
    private String filePath;
    private String input;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getInput() {


        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
