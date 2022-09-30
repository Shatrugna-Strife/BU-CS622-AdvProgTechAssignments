package com.met622.entity;

import com.met622.constant.Constant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static com.met622.singleton.Singleton.*;

/**
 * Class to handle all the swing operations
 */
public class SwingGui {
    private JFrame jFrame;
    private final JLabel label;
    private JButton mergeButton;
    private JButton searchButton;
    private JButton historyButton;
    private JTextField searchTextField;
    private JTable jTable;
    private JScrollPane sp;
    private JPanel jPanel;
    public SwingGui(){
        jFrame = new JFrame();
        label = new JLabel();
        jPanel = new JPanel();
        jTable = new JTable();
        sp=new JScrollPane(jTable);
        jPanel.setBounds(10,200,480,600);
        jTable.setBounds(10,0,400,300);
        searchTextField = new JTextField();
        searchTextField.setBounds(20,150,100,30);
        label.setSize(300,100);
        searchButton = new JButton("Search");
        historyButton = new JButton("History");
        historyButton.setBounds(221,150,100,30);
        searchButton.setBounds(120,150,100,30);
        mergeButton=new JButton("Merge Files");
        mergeButton.setBounds(20,100,100,30);
        jPanel.add(sp);
        jFrame.add(mergeButton); jFrame.add(label);jFrame.add(jPanel);
        jFrame.setSize(510,700);
        sp.setVisible(true);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        mergeButton.addActionListener(e -> {
            try {
                fileOperations.mergeFilesInDataFolder(Constant.OUTPUT_FILE_NAME);
                label.setText("Files merged successfully in data folder : "+ Constant.OUTPUT_FILE_NAME);
                jFrame.add(searchButton);
                jFrame.add(searchTextField);
                jFrame.add(historyButton);
                searchButton.updateUI();
                searchTextField.updateUI();
                historyButton.updateUI();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
                label.setText("ERROR: " + ex);
            } catch (IOException ex) {
                ex.printStackTrace();
                label.setText("ERROR: " + ex);
            }catch(Exception ex){
                ex.printStackTrace();
                label.setText("ERROR: " +ex);
            }
        });

        searchButton.addActionListener(e -> {
            try {
                searchButton.setEnabled(false);
                label.setText("Search data which are related to : "+ searchTextField.getText());
                List<Map<String, Object>> result = searchOperation.searchJsonFileByKeyword(new File(fileOperations.getDataFolderFile().getAbsoluteFile() + File.separator + Constant.OUTPUT_FILE_NAME), searchTextField.getText());
                String[][] data = new String[result.size()][Constant.COLUMN_NAME_SEARCH.length];
                for(int i = 0;i<result.size();i++){
                    Map<String, Object> temp = result.get(i);
                    for(int j = 0; j<Constant.COLUMN_NAME_SEARCH.length; j++){
                        data[i][j] = String.valueOf(temp.get(Constant.COLUMN_NAME_SEARCH[j]));
                    }
                }
                jTable.setModel(new DefaultTableModel(data,Constant.COLUMN_NAME_SEARCH));
                printUtility.printSearchResults(data, searchTextField.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
                label.setText("ERROR: " +ex);
            }catch(Exception ex){
                ex.printStackTrace();
                label.setText("ERROR: " +ex);
            }
            searchButton.setEnabled(true);
        });

        historyButton.addActionListener(e->{
            historyButton.setEnabled(false);
            label.setText("Commands History");
            Map<String, List<Long>> result = history.getCommandHistory();
            String[][] data = new String[result.size()][2];
            int i=0;
            for(Map.Entry<String, List<Long>> entry : result.entrySet()){
                data[i][0] = "\'"+entry.getKey()+"\'";
                data[i][1] = entry.getValue().size() + " times";
                i++;
            }
            jTable.setModel(new DefaultTableModel(data, Constant.COLUMN_NAME_HISTORY));
            printUtility.printHistoryCommands(data);
            historyButton.setEnabled(true);
        });


        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
