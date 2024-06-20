package scheduling;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 7187610727954817195L;
	private JTextField usernameField;
	private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("ReumaCare - Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 5));

        Font font = new Font("Arial", Font.PLAIN, 14);

        JLabel usernameLabel = new JLabel("Usuário:");
        JLabel passwordLabel = new JLabel("Senha:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Entrar");

        Color customBlue = new Color(0x2a73d2);
        Color customBlue2 = new Color(0x67a3e9);

        usernameLabel.setForeground(customBlue);
        passwordLabel.setForeground(customBlue);
        usernameLabel.setFont(font);
        passwordLabel.setFont(font);
        loginButton.setBackground(customBlue2);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        usernameField.setFont(font);
        passwordField.setFont(font);

        usernameField.setMargin(new Insets(5, 5, 5, 5));
        passwordField.setMargin(new Insets(5, 5, 5, 5));

        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(customBlue, 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(customBlue, 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        loginButton.setFont(font);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(customBlue, 2));
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidLogin(username, password)) {
                    openMainPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Credenciais de login inválidas");
                }
            }
        });
        panel.add(loginButton);
        mainPanel.add(panel);
        add(mainPanel);
        setVisible(true);
    }
    
	private boolean isValidLogin(String username, String password) {
		return username.equals("admin") && password.equals("admin");
	}

	private void openMainPage() {
		MainFrame mainFrame = new MainFrame();
	}
}