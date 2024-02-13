package com.MidWit.jframe.view;

import com.MidWit.jframe.common.IdeaCondition;
import com.MidWit.jframe.controller.IdeaController;
import com.MidWit.jframe.model.dto.IdeaDTO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame implements ActionListener, ListSelectionListener, KeyListener {
    //region 필드
    JPanel searchPanel;
    JPanel mainPanel;
    JList mainJList;
    JList workJList;
    JList choiJList;
    DefaultListModel<IdeaDTO> mainModel;
    DefaultListModel<IdeaDTO> workModel;
    DefaultListModel<IdeaDTO> choiModel;
    JButton searchBTN;
    JButton main2seleBTN;
    JButton sele2mainBTN;
    JButton sele2workBTN;
    JButton seleDeleBTN;
    JButton workDeleBTN;
    JButton addIdeaBTN;
    JButton aditIdeaBTN;
    JButton recommendBTN;
    Font myFont = new Font("고딕", Font.TRUETYPE_FONT, 13);

    JComboBox<String> searchComboBox;
    JComboBox<String> sortComboBox;

    JTextField searchTextField;
    //endregion
    IdeaController ideaController = new IdeaController();

    private static final MainFrame instance = new MainFrame();

    public static MainFrame getInstance() {
        return instance;
    }

    private MainFrame() {

        //region 기본해상도,패널설정
        setTitle("Recommend Idea Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1050, 720);
        setResizable(false);

        searchPanel = new JPanel();
        searchPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "검색"));
        searchPanel.setBounds(10, 10, 1020, 50);
        searchPanel.setLayout(null);
        add(searchPanel);

        mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "메인"));
        mainPanel.setBounds(10, 70, 1020, 600);
        mainPanel.setLayout(null);
        add(mainPanel);
        //endregion

        //region serchPanel
        String[] section = {"검색기준", "코드", "이름", "타입", "난이도"};
        searchComboBox = new JComboBox<>(section);
        searchComboBox.setBounds(10, 18, 238, 25);
        searchTextField = new JTextField(22);
        searchTextField.setBounds(260, 18, 638, 25);
        searchTextField.addKeyListener(this);
        searchBTN = new JButton("검색");
        searchBTN.setBounds(900, 18, 110, 25);

        searchPanel.add(searchComboBox);
        searchPanel.add(searchTextField);
        searchPanel.add(searchBTN);
        //endregion

        //region mainPanel Object

        // JList
        mainModel = new DefaultListModel<>();
        workModel = new DefaultListModel<>();
        choiModel = new DefaultListModel<>();
        mainJList = new JList<>(mainModel);
        workJList = new JList<>(workModel);
        choiJList = new JList<>(choiModel);
        mainJList.setFixedCellHeight(20);
        workJList.setFixedCellHeight(20);
        choiJList.setFixedCellHeight(20);
        mainJList.setFont(myFont);
        workJList.setFont(myFont);
        choiJList.setFont(myFont);
        JScrollPane mainScroll = new JScrollPane(mainJList);

        mainJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //1개만 선택될 수 있도록
        choiJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        mainJList.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); //태두리 설정
        workJList.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        choiJList.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

        //mainJList.setBounds(360,20,650,505);
        mainScroll.setBounds(360, 20, 650, 505);
        workJList.setBounds(10, 50, 280, 160);
        choiJList.setBounds(10, 300, 280, 220);


        //mainPanel.add(mainJList);
        mainPanel.add(mainScroll);
        mainPanel.add(workJList);
        mainPanel.add(choiJList);

        // label
        JLabel workLabel = new JLabel("작업 중인 아이디어");
        JLabel seleLable = new JLabel("선택 중");

        workLabel.setBounds(12, 25, 180, 25);
        seleLable.setBounds(12, 275, 180, 25);

        mainPanel.add(workLabel);
        mainPanel.add(seleLable);

        // button
        main2seleBTN = new JButton("<<");
        sele2mainBTN = new JButton(">>");
        sele2workBTN = new JButton("작업 시작(미구현)");
        seleDeleBTN = new JButton("삭제");
        workDeleBTN = new JButton("작업 끝(미구현)");
        addIdeaBTN = new JButton("아이디어 추가");
        aditIdeaBTN = new JButton("수정");
        recommendBTN = new JButton("추천받기(미구현)");

        main2seleBTN.setBounds(300, 335, 50, 50);
        sele2mainBTN.setBounds(300, 420, 50, 50);
        sele2workBTN.setBounds(10, 520, 130, 40);
        seleDeleBTN.setBounds(215, 520, 75, 40);
        workDeleBTN.setBounds(10, 210, 280, 40);
        addIdeaBTN.setBounds(730, 530, 130, 40);
        aditIdeaBTN.setBounds(140, 520, 75, 40);
        recommendBTN.setBounds(880, 530, 130, 40);

        mainPanel.add(main2seleBTN);
        mainPanel.add(sele2mainBTN);
        mainPanel.add(sele2workBTN);
        mainPanel.add(seleDeleBTN, 1);
        mainPanel.add(workDeleBTN, 1);
        mainPanel.add(addIdeaBTN);
        mainPanel.add(aditIdeaBTN);
        mainPanel.add(recommendBTN);

        String[] sortBy = {"코드", "이름", "난이도"};
        sortComboBox = new JComboBox<>(sortBy);
        sortComboBox.setBounds(360, 530, 150, 25);
        sortComboBox.addActionListener(e -> sortMainJList());
        mainPanel.add(sortComboBox);

        //endregiona

        setFontAll();
        setVisible(true);
        addEventListener();
    }

    public void sortMainJList() {
        String sortOption = sortComboBox.getSelectedItem().toString();
        ideaController.setSortMap(sortOption);
        List<IdeaDTO> sortList = new ArrayList<>();
        for (int i = 0; i < mainJList.getModel().getSize(); i++) {
            sortList.add((IdeaDTO) mainJList.getModel().getElementAt(i));
        }
        setMainJList(ideaController.sortList(sortList));
    }

    public void setMainJList(List<IdeaDTO> ideaList) {
        mainModel.clear();
        mainModel.addAll(ideaList);
    }

    public void setChoiJList(List<IdeaDTO> ideaList) {
        choiModel.clear();
        choiModel.addAll(ideaList);
    }

    private void addEventListener() {
        searchBTN.addActionListener(this);
        main2seleBTN.addActionListener(this);
        sele2mainBTN.addActionListener(this);
        sele2workBTN.addActionListener(this);
        seleDeleBTN.addActionListener(this);
        workDeleBTN.addActionListener(this);
        addIdeaBTN.addActionListener(this);
        aditIdeaBTN.addActionListener(this);
        recommendBTN.addActionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        mainJList.addListSelectionListener(this);
        choiJList.addListSelectionListener(this);
        workJList.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton target = (JButton) e.getSource();
        // JList.getSelectedIndex() : 선택된 아이템의 index를 반환 선택되지 않았다면 -1반환
        switch (target.getText()) {
            case "<<" -> {
                IdeaDTO idea = getSelectedItem("main");
                ideaController.selectMainIdeaToChoiceList(idea);
            }
            case ">>" -> {
                IdeaDTO idea = getSelectedItem("choi");
                ideaController.deleteChoiceIdeaByIdeaDTO(idea);
            }
            case "작업 시작" -> {

            }
            case "삭제" -> {
                IdeaDTO idea = getSelectedItem("choi");
                ideaController.deleteIdea(idea);
            }
            case "작업 끝" -> {
            }
            case "아이디어 추가" -> {
                ideaController.insertIdea();
            }
            case "수정" -> {
                IdeaDTO idea = getSelectedItem("choi");
                ideaController.updateIdea(idea);
            }
            // TODO : 추천받기 기능 미구현
            case "추천받기" -> {
            }
            case "검색" -> {
                search();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void search() {
        String condition = searchComboBox.getSelectedItem().toString();
        String value = searchTextField.getText();
        IdeaCondition ideaCondition = new IdeaCondition(condition, value);
        System.out.println(value);

        ideaController.selectIdeaByCondition(ideaCondition);
    }

    private IdeaDTO getSelectedItem(String listName) {
        IdeaDTO idea = null;
        switch (listName) {
            case "main" -> {
                int index = mainJList.getSelectedIndex();
                idea = mainModel.elementAt(index);
            }
            case "choi" -> {
                int index = choiJList.getSelectedIndex();
                idea = choiModel.elementAt(index);
            }
        }
        return idea;
    }

    private void setFontAll() {
        searchPanel.setFont(myFont);
        mainPanel.setFont(myFont);
        mainJList.setFont(myFont);
        workJList.setFont(myFont);
        choiJList.setFont(myFont);
        searchBTN.setFont(myFont);
        main2seleBTN.setFont(myFont);
        sele2mainBTN.setFont(myFont);
        sele2workBTN.setFont(myFont);
        seleDeleBTN.setFont(myFont);
        workDeleBTN.setFont(myFont);
        addIdeaBTN.setFont(myFont);
        aditIdeaBTN.setFont(myFont);
        recommendBTN.setFont(myFont);
        searchComboBox.setFont(myFont);
        sortComboBox.setFont(myFont);
        searchTextField.setFont(myFont);
    }
}
