package scheduling;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScheduleFrame extends JFrame {
	private static final long serialVersionUID = 9123002515661291455L;
	private JTextField patientNameField;
	private JTextField doctorNameField;
	private JTextField dateField;
	private JButton scheduleButton;

	public ScheduleFrame(MainFrame mainFrame) {
		setTitle("ReumaCare - Agendar Consulta");
		setSize(400, 250);
		setLocationRelativeTo(mainFrame);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		Font font = new Font("Arial", Font.PLAIN, 14);
		Color customBlue = new Color(0x2a73d2);
		Color customBlue2 = new Color(0x67a3e9);

		JLabel patientLabel = new JLabel("Nome do Paciente:");
		JLabel doctorLabel = new JLabel("Nome do Médico:");
		JLabel dateLabel = new JLabel("Data:");

		patientLabel.setFont(font);
		doctorLabel.setFont(font);
		dateLabel.setFont(font);

		patientLabel.setBounds(20, 20, 150, 30);
		doctorLabel.setBounds(20, 60, 150, 30);
		dateLabel.setBounds(20, 100, 150, 30);

		patientNameField = new JTextField(20);
		doctorNameField = new JTextField(20);
		dateField = new JTextField(20);

		patientNameField.setMargin(new Insets(5, 5, 5, 5));
		patientNameField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		doctorNameField.setMargin(new Insets(5, 5, 5, 5));
		doctorNameField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		dateField.setMargin(new Insets(5, 5, 5, 5));
		dateField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		patientNameField.setFont(font);
		doctorNameField.setFont(font);
		dateField.setFont(font);

		patientNameField.setBounds(170, 20, 200, 25);
		doctorNameField.setBounds(170, 60, 200, 25);
		dateField.setBounds(170, 100, 200, 25);

		scheduleButton = new JButton("Agendar Consulta");

		scheduleButton.setFont(font);
		scheduleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scheduleButton.setBorder(BorderFactory.createLineBorder(customBlue, 2));
		scheduleButton.setForeground(Color.WHITE);
		scheduleButton.setBackground(customBlue2);
		scheduleButton.setFocusPainted(false);

		scheduleButton.setBounds(18, 140, 350, 40);

		scheduleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String patientName = patientNameField.getText();
				String doctorName = doctorNameField.getText();
				String date = dateField.getText();

				if (!patientName.isEmpty() && !doctorName.isEmpty() && !date.isEmpty()) {
					if (isValidDate(date)) {
						mainFrame.addAppointment(capitalizeWords(patientName), capitalizeWords(doctorName), date);
						dispose();
					} else {
						JOptionPane.showMessageDialog(ScheduleFrame.this, "Por favor, preencha com uma data válida. Exemplo: 01/01/2023");						
					}
				} else {
					JOptionPane.showMessageDialog(ScheduleFrame.this, "Por favor, preencha todos os campos.");
				}
			}
		});

		mainPanel.add(patientLabel);
		mainPanel.add(doctorLabel);
		mainPanel.add(dateLabel);
		mainPanel.add(patientNameField);
		mainPanel.add(doctorNameField);
		mainPanel.add(dateField);
		mainPanel.add(scheduleButton);

		add(mainPanel);
		setVisible(true);
	}

	private boolean isValidDate(String date) {
		String regex = "\\d{2}/\\d{2}/\\d{4}";

		if (date.matches(regex)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);

			try {
				dateFormat.parse(date);
				return true;
			} catch (ParseException e) {
				return false;
			}
		}

		return false;
	}
	
	private static String capitalizeWords(String text) {
		String[] words = text.split(" ");
		StringBuilder result = new StringBuilder();
		for (String word : words) {
			if (word.length() > 0) {
				result.append(Character.toUpperCase(word.charAt(0)));
				if (word.length() > 1) {
					result.append(word.substring(1).toLowerCase());
				}
				result.append(" ");
			}
		}
		return result.toString().trim();
	}
}
