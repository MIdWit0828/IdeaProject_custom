package com.MidWit.jframe.model.dto;

public class IdeaDTO {
    private int ideaCode;
    private String ideaName;
    private int ideaRank;
    private int workCount;
    private int typeCode;
    private String workStatus;

    private TypeDTO typeDTO;

    public IdeaDTO() {

        workCount = 0;
        workStatus = "N";
    }

    public IdeaDTO(String name, int rank, String typeName) {
        this.ideaName = name;
        this.ideaRank = rank;
        this.typeCode = getTypeCode(typeName);
        workCount = 0;
        workStatus = "N";
    }
    public IdeaDTO(String name, int rank, int typeCode) {
        this.ideaName = name;
        this.ideaRank = rank;
        this.typeCode = typeCode;
        workCount = 0;
        workStatus = "N";
    }

    @Override
    public String toString() {
        if (workStatus.equals("blank")) {
            return "";
        }
        String nonChoice = "(" + ideaCode + ") " + getTypeName(typeCode) + " " + ideaName + " " + makeRankNumToStar(ideaRank);
        return nonChoice;
    }

    private String getTypeName(int typeCode) {
        String typeName = "";
        switch (typeCode) {
            case 1 -> typeName = "색";
            case 2 -> typeName = "동세";
            case 3 -> typeName = "캐릭터";
            case 4 -> typeName = "복장";
            case 5 -> typeName = "배경";
            case 6 -> typeName = "컨셉";
            default -> typeName = "비어있음";
        }
        return "[" + typeName + "]";
    }

    private String makeRankNumToStar(int ideaRank) {
        String star = "";
        for (int i = 0; i < ideaRank; i++) {
            star += "★";
        }
        return star;
    }

    public int getIdeaCode() {
        return ideaCode;
    }

    public String getIdeaName() {
        return ideaName;
    }

    public int getIdeaRank() {
        return ideaRank;
    }

    public int getWorkCount() {
        return workCount;
    }

    public int getTypeCode() {
        return typeCode;
    }
    private int getTypeCode(String typeName) {
        int temp = 1;
        switch (typeName) {
            case "색" -> temp =1;
            case "동세" -> temp = 2;
            case "캐릭터" -> temp = 3;
            case "복장" -> temp = 4;
            case "배경" -> temp = 5;
            case "컨셉" -> temp = 6;
        }
        return temp;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setIdeaCode(int ideaCode) {
        this.ideaCode = ideaCode;
    }

    public void setIdeaName(String ideaName) {
        this.ideaName = ideaName;
    }

    public void setIdeaRank(int ideaRank) {
        this.ideaRank = ideaRank;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public TypeDTO getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(TypeDTO typeDTO) {
        this.typeDTO = typeDTO;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
