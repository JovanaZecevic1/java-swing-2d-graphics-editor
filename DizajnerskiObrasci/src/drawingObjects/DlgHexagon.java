package drawingObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import geometry.HexagonAdapter;

public class DlgHexagon extends JDialog{
	private final JPanel contentPanel = new JPanel();
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldRadius;
    private JButton btnOutlineColor;
    private JButton btnInnerColor;
    
    private Color outlineColor = Color.BLACK;
    private Color innerColor = Color.WHITE;
    private boolean ok;

    public static void main(String[] args) {
        try {
            DlgHexagon dialog = new DlgHexagon();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgHexagon() {
        setTitle("Hexagon");
        setModal(true);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // Kreiranje labela
        JLabel lblX = new JLabel("X coordinate:");
        JLabel lblY = new JLabel("Y coordinate:");
        JLabel lblRadius = new JLabel("Radius:");
        JLabel lblOutline = new JLabel("Outline color:");
        JLabel lblInner = new JLabel("Inner color:");
        
        // Kreiranje text field-ova
        textFieldX = new JTextField();
        textFieldX.setColumns(10);
        
        textFieldY = new JTextField();
        textFieldY.setColumns(10);
        
        textFieldRadius = new JTextField();
        textFieldRadius.setColumns(10);
        
        // Dugme za outline boju
        btnOutlineColor = new JButton("Choose");
        btnOutlineColor.setBackground(outlineColor);
        btnOutlineColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose outline color", outlineColor);
            if (newColor != null) {
                outlineColor = newColor;
                btnOutlineColor.setBackground(outlineColor);
            }
        });
        
        // Dugme za inner boju
        btnInnerColor = new JButton("Choose");
        btnInnerColor.setBackground(innerColor);
        btnInnerColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
            if (newColor != null) {
                innerColor = newColor;
                btnInnerColor.setBackground(innerColor);
            }
        });
        
        // Layout za content panel
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblX)
                        .addComponent(lblY)
                        .addComponent(lblRadius)
                        .addComponent(lblOutline)
                        .addComponent(lblInner))
                    .addGap(30)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(textFieldX, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(textFieldY, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(textFieldRadius, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(btnOutlineColor, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addGap(20)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblX)
                        .addComponent(textFieldX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblY)
                        .addComponent(textFieldY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblRadius)
                        .addComponent(textFieldRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOutline)
                        .addComponent(btnOutlineColor))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblInner)
                        .addComponent(btnInnerColor))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        contentPanel.setLayout(gl_contentPanel);
        
        // Button panel sa OK i Cancel dugmadima
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        // OK dugme
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                int x = Integer.parseInt(textFieldX.getText());
                int y = Integer.parseInt(textFieldY.getText());
                int radius = Integer.parseInt(textFieldRadius.getText());
                
                if (radius <= 0) {
                    JOptionPane.showMessageDialog(null, 
                        "Radius must be greater than 0!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ok = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid input! Please enter valid numbers.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        // Cancel dugme
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            ok = false;
            dispose();
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    // GETTERI za text field-ove
    public JTextField getTextFieldX() {
        return textFieldX;
    }

    public JTextField getTextFieldY() {
        return textFieldY;
    }

    public JTextField getTextFieldRadius() {
        return textFieldRadius;
    }

    // GETTERI za boje
    public Color getOutlineColor() {
        return outlineColor;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    // Provera da li je korisnik kliknuo OK
    public boolean isOk() {
        return ok;
    }

    // Kreira HexagonAdapter na osnovu unetih vrednosti
    public HexagonAdapter getHexagon() {
        int x = Integer.parseInt(textFieldX.getText());
        int y = Integer.parseInt(textFieldY.getText());
        int radius = Integer.parseInt(textFieldRadius.getText());
        
        return new HexagonAdapter(x, y, radius);
    }
    
    // SETTERI za boje (za modify operaciju)
    public void setOutlineColor(Color color) {
        this.outlineColor = color;
        if (btnOutlineColor != null) {
            btnOutlineColor.setBackground(color);
        }
    }
    
    public void setInnerColor(Color color) {
        this.innerColor = color;
        if (btnInnerColor != null) {
            btnInnerColor.setBackground(color);
        }
    }


}
