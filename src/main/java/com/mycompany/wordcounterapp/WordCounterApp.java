///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
// */
//
package com.mycompany.wordcounterapp;
//
///**
// *
// * @author Roshini
// */
////public class WordCounterApp {
////
////    public static void main(String[] args) {
////        System.out.println("Hello World!");
////        
////    }
////}
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class WordCounterApp extends JFrame {
//    // GUI components
//    private JTextArea textArea;
//    private JButton countButton;
//    private JLabel wordLabel, charLabel, paraLabel;
//
//    // Constructor to set up the GUI
//    public WordCounterApp() {
//        setTitle("Word, Character, and Paragraph Counter");
//        setSize(500, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null); // Center the window
//
//        // Text area for user input
//        textArea = new JTextArea(10, 30);
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//
//        // Button to trigger the counting
//        countButton = new JButton("Count");
//
//        // Labels to display counts
//        wordLabel = new JLabel("Words: 0");
//        charLabel = new JLabel("Characters: 0");
//        paraLabel = new JLabel("Paragraphs: 0");
//
//        // ActionListener for the button
//        countButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String text = textArea.getText().trim();
//                
//                // Count words, characters, and paragraphs
//                String[] words = text.split("\\s+"); // Splits by space
//                String[] paragraphs = text.split("\\n+"); // Splits by newlines
//
//                wordLabel.setText("Words: " + words.length);
//                charLabel.setText("Characters: " + text.length());
//                paraLabel.setText("Paragraphs: " + paragraphs.length);
//            }
//        });
//
//        // Layout of the components
//        setLayout(new BorderLayout());
//        add(scrollPane, BorderLayout.CENTER); // Add the text area
//        JPanel southPanel = new JPanel(); // Panel for buttons and labels
//        southPanel.add(countButton);
//        southPanel.add(wordLabel);
//        southPanel.add(charLabel);
//        southPanel.add(paraLabel);
//        add(southPanel, BorderLayout.SOUTH);
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new WordCounterApp().setVisible(true);
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WordCounterApp extends JFrame {
    // GUI components
    private JTextArea textArea;
    private JButton countButton, clearButton, saveButton, openButton;
    private JLabel wordLabel, charLabel, paraLabel;

    // Constructor to set up the GUI
    public WordCounterApp() {
        setTitle("Word, Character, and Paragraph Counter");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Text area for user input
        textArea = new JTextArea(15, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Buttons to trigger actions
        countButton = new JButton("Count");
        clearButton = new JButton("Clear Text");
        saveButton = new JButton("Save to File");
        openButton = new JButton("Open File");

        // Labels to display counts
        wordLabel = new JLabel("Words: 0");
        charLabel = new JLabel("Characters: 0");
        paraLabel = new JLabel("Paragraphs: 0");

        // ActionListener for the Count button
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText().trim();
                
                // Count words, characters, and paragraphs
                String[] words = text.split("\\s+");
                String[] paragraphs = text.split("\\n+");

                wordLabel.setText("Words: " + words.length);
                charLabel.setText("Characters: " + text.length());
                paraLabel.setText("Paragraphs: " + paragraphs.length);
            }
        });

        // ActionListener for the Clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(""); // Clear the text area
                wordLabel.setText("Words: 0");
                charLabel.setText("Characters: 0");
                paraLabel.setText("Paragraphs: 0");
            }
        });

        // ActionListener for the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("Text: \n" + textArea.getText() + "\n\n");
                        writer.write("Words: " + wordLabel.getText().split(": ")[1] + "\n");
                        writer.write("Characters: " + charLabel.getText().split(": ")[1] + "\n");
                        writer.write("Paragraphs: " + paraLabel.getText().split(": ")[1] + "\n");
                        JOptionPane.showMessageDialog(null, "File saved successfully.");
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error saving file.");
                    }
                }
            }
        });

        // ActionListener for the Open button
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        textArea.setText(content);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error opening file.");
                    }
                }
            }
        });

        // Layout of the components
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER); // Add the text area

        // Panel for buttons and labels
        JPanel southPanel = new JPanel();
        southPanel.add(countButton);
        southPanel.add(clearButton);
        southPanel.add(saveButton);
        southPanel.add(openButton);

        // Panel for labels
        JPanel labelPanel = new JPanel();
        labelPanel.add(wordLabel);
        labelPanel.add(charLabel);
        labelPanel.add(paraLabel);

        add(southPanel, BorderLayout.SOUTH);
        add(labelPanel, BorderLayout.NORTH);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCounterApp().setVisible(true);
            }
        });
    }
}
