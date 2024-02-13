package com.MidWit.jframe.view;

import com.MidWit.jframe.model.dto.IdeaDTO;

import java.util.List;

public class MenuPrint {
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    public void printSuccessMsg(String successCode, int count) {
        String massage = "";
        switch (successCode) {
            case "selectAllIdea" -> massage = "모든 아이디어 검색 성공!";
            case "byCondition" -> massage = "조건으로 검색 성공!";
            case "insertIdea" -> massage = "아이디어 입력 성공!";
            case "updateIdea" -> massage = "아이디어 수정 성공!";
            case "deleteIdea" -> massage = count + "개의 아이디어 삭제";
            case "selectAllChoice" -> massage = "선택리스트 검색 성공!";
            case "selectMainIdeaToChoiceList" -> massage = "선택리스트로 이동 성공";
            case "deleteChoiceIdeaByIdeaDTO" -> massage = "선택리스트의 항목 삭제 성공";
        }
        System.out.println("=========================");
        System.out.println(BLUE + massage + RESET);
        System.out.println("=========================");
    }

//    public void printIdeas(List<IdeaDTO> ideaList) {
//        System.out.print(GREEN);
//        ideaList.forEach(System.out::println);
//        System.out.println(RESET + "=========================");
//    }

    public void printErrorMsg(String errCode) {
        String massage = "";
        switch (errCode) {
            case "selectAllIdea" -> massage = "모든 아이디어 조회 실패!";
            case "byCondition" -> massage = "조건에 맞는 아이디어가 없습니다.";
            case "insertIdea" -> massage = "아이디어를 추가할 수 없습니다.";
            case "updateIdea" -> massage = "아이디어를 수정할 수 없습니다.";
            case "deleteIdea" -> massage = "아이디어를 삭제할 수 없습니다.";
            case "selectAllChoice" -> massage = "선택리스트를 불러올 수 없습니다.";
            case "selectMainIdeaToChoiceList" -> massage = "선택 실패";
            case "deleteChoiceIdeaByIdeaDTO" -> massage = "항목삭제 실패";
        }
        System.out.println("=========================");
        System.out.println(RED + massage + RESET);
        System.out.println("=========================");
    }
}
