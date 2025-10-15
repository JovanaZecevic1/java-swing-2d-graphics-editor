package mvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class DlgCommandReplay extends JDialog {
	private JTextArea commandArea;
    private JButton nextButton;
    private JButton executeAllButton;
    private JButton cancelButton;
    private JLabel progressLabel;
    
    private List<String> commands;
    private CommandParser parser;
    private FrameDrawing frame;
    private int currentIndex = 0;
	
	public DlgCommandReplay(JFrame parent, List<String> commands, CommandParser parser, FrameDrawing frame) {
        super(parent, "Replay Commands", true);
        this.commands = commands;
        this.parser = parser;
        this.frame = frame;
        
        initializeComponents();
        setupLayout();
        setupListeners();
        
        setSize(600, 400);
        setLocationRelativeTo(parent);
    }
    
    private void initializeComponents() {
        commandArea = new JTextArea();
        commandArea.setEditable(false);
        commandArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        nextButton = new JButton("Next Command");
        executeAllButton = new JButton("Execute All");
        cancelButton = new JButton("Cancel");
        
        progressLabel = new JLabel("Commands: 0 / " + commands.size());
        progressLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Prikaži prvu komandu
        if (!commands.isEmpty()) {
            commandArea.setText("Next command:\n\n" + commands.get(0));
        }
    }
    
    private void setupLayout() {
        getContentPane().setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane(commandArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(executeAllButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        getContentPane().add(progressLabel, BorderLayout.NORTH);
    }
    
    private void setupListeners() {
        nextButton.addActionListener(e -> executeNextCommand());
        executeAllButton.addActionListener(e -> executeAllCommands());
        cancelButton.addActionListener(e -> dispose());
    }
    
    private void executeNextCommand() {
        if (currentIndex < commands.size()) {
            String command = commands.get(currentIndex);
            
            // Izvršava komandu
            parser.parse(command);
            frame.repaint();
            
            currentIndex++;
            progressLabel.setText("Commands: " + currentIndex + " / " + commands.size());

            // Prikaži sledeću komandu ili završi
            if (currentIndex < commands.size()) {
                commandArea.setText("Executed:\n" + command + "\n\nNext command:\n\n" + commands.get(currentIndex));
            } else {
                commandArea.setText("All commands executed!\n\nLast command:\n" + command);
                nextButton.setEnabled(false);
                executeAllButton.setEnabled(false);
                
                JOptionPane.showMessageDialog(
                    this,
                    "All commands have been executed successfully!",
                    "Replay Complete",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    
    private void executeAllCommands() {
        while (currentIndex < commands.size()) {
            String command = commands.get(currentIndex);
            parser.parse(command);
            currentIndex++;
        }
        
        frame.repaint();
        progressLabel.setText("Commands: " + currentIndex + " / " + commands.size());
        commandArea.setText("All " + commands.size() + " commands executed!");
        nextButton.setEnabled(false);
        executeAllButton.setEnabled(false);
        
        JOptionPane.showMessageDialog(
            this,
            "All commands have been executed successfully!",
            "Replay Complete",
            JOptionPane.INFORMATION_MESSAGE
        );
    }


}
