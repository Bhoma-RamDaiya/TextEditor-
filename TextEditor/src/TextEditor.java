import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
//    creating frame
    JFrame frame;
//    create menubar for textEditor;
    JMenuBar menuBar;
//    creating menu
    JMenu file ,edit;
//    creating menuItem;
    JMenuItem newFile , openFile , saveFile;
    JMenuItem cut  ,copy , paste , slectAll,close;
//    creating textArea;
    JTextArea textarea ;

    TextEditor(){
//  initialize the frame
        frame = new JFrame();
        //  initialize the menuBar
        menuBar = new JMenuBar();
        //  initialize the TextArea
        textarea = new JTextArea();
        //  initialize the Jmenu
          file = new JMenu("File");
        edit = new JMenu("Edit");
// Initialize the file menu Item;
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //adding action listner in file menuItem
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
//        adding the file menuItem;
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

// Initialize the edit menu Item;
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy ");
        paste = new JMenuItem("Paste");
        slectAll = new JMenuItem("Slect All");
        close = new JMenuItem("Close");
// adding editItem to ActionListener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        slectAll.addActionListener(this);
        close.addActionListener(this);
        //        adding the edit menuItem;
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(slectAll);
        edit.add(close);
//        adding the menus to menuBAr
        menuBar.add(file);
        menuBar.add(edit);

//    set the menuBar

        frame.setJMenuBar(menuBar);
//      creating content pane
        JPanel panel = new JPanel();
//     set border
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));
//        add textArea to panel;
        panel.add(textarea,BorderLayout.CENTER);
//        create scrollPane
        JScrollPane scrollPane = new JScrollPane(textarea ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//         add ScrolPane to panel
        panel.add(scrollPane);
//        add pannel to frame
        frame.add(panel);
        //        initialize the size of frame;
        frame.setBounds(0, 0,600, 400);
        frame.setTitle("TextEditor By Ram");
        frame.setVisible(true);
//        frame.setLayout(null);
    }
    @Override
    public void actionPerformed (ActionEvent actionEvent){
    if(actionEvent.getSource()==cut){
        textarea.cut();
    }
        if(actionEvent.getSource()==copy){
            textarea.copy();
        }
        if(actionEvent.getSource()==paste){
            textarea.paste();
        }
        if(actionEvent.getSource()==slectAll){
            textarea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile) {
//      open A file Chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
//       if we have clicked on a open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
//            getting a slected file
                File file = fileChooser.getSelectedFile();
//                get the path of selected file
                String filePath = file.getPath();

            try {
                //    initialize file reader
                FileReader fileReader = new FileReader(filePath);
//                initialize bufferd reader
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String interMidiate = "" , outPut = "";
                while((interMidiate= bufferedReader.readLine())!= null){
                    outPut += interMidiate +"\n";
                }
                textarea.setText(outPut);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
        }
        if(actionEvent.getSource()== saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption =  fileChooser.showSaveDialog(null);
//            if we have clicked on save option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
//                    initialize file writer
                    FileWriter filterWriter = new FileWriter(file) ;
//                    initialize bufferdWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(filterWriter);
//                    write content of text area to file
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();

                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()== newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
       TextEditor textEditor = new TextEditor();
    }
}