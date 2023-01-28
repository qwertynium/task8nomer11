package vsu.cs.vega.gui;
//import com.intellij.uiDesigner.core.GridConstraints;
//import com.intellij.uiDesigner.core.GridLayoutManager;
//import com.intellij.uiDesigner.core.Spacer;


import vsu.cs.vega.logic.Solution;
import vsu.cs.vega.util.ArrayUtils;
import vsu.cs.vega.util.JTableUtils;
import vsu.cs.vega.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonRandomInput;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonOperationTurnRight;
    private JButton buttonSaveOutputIntoFile;
    private JTable tableOutput;


    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private final JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
        //tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JMenuBar menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        JTableUtils.writeArrayToJTable(tableInput, new int[][]{
                {1, 3, 4, 6, 7, 8, 37},
                {22, 6, 2, 30, 8, 11, 5},
                {61, 2, 7, 4, 2, 5, 5},
                {87, 5, 11, 7, 4, 9, 7},
                {3, 8, 12, 7, 3, 2, 8}
        });

        this.pack();


        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ArrayUtils.createRandomIntMatrix(
                            tableInput.getRowCount(), tableInput.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput, matrix);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonOperationTurnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);

                    int[][] a = Solution.TurnRight90(matrix);

                    JTableUtils.writeArrayToJTable(tableOutput, a);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
//        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
