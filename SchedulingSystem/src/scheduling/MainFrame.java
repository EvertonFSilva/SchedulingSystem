package scheduling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 4002764568878797072L;
	private ArrayList<Appointment> appointments = new ArrayList<>();
	private JButton scheduleButton;
	private JButton cancelButton;
	private JButton saveCsvButton;
	private JButton loadCsvButton;
	private JTable appointmentTable;
	private AppointmentTableModel tableModel;
	private JTextField searchDateField;
	private JTextField searchPatientField;
	private JTextField searchDoctorField;
	private TableRowSorter<TableModel> rowSorter;

	public MainFrame() {
		setTitle("Sistema de Agendamento");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Font font = new Font("Arial", Font.PLAIN, 14);
		Color customBlue = new Color(0x2a73d2);
		Color customBlue2 = new Color(0x67a3e9);

		searchDateField = new JTextField();
		searchDateField.setFont(font);
		searchDateField.setForeground(Color.BLACK);
		searchDateField.setMargin(new Insets(5, 5, 5, 5));
		searchDateField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		searchPatientField = new JTextField();
		searchPatientField.setFont(font);
		searchPatientField.setForeground(Color.BLACK);
		searchPatientField.setMargin(new Insets(5, 5, 5, 5));
		searchPatientField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		searchDoctorField = new JTextField();
		searchDoctorField.setFont(font);
		searchDoctorField.setForeground(Color.BLACK);
		searchDoctorField.setMargin(new Insets(5, 5, 5, 5));
		searchDoctorField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		searchDateField.getDocument().addDocumentListener(new SearchDocumentListener());
		searchPatientField.getDocument().addDocumentListener(new SearchDocumentListener());
		searchDoctorField.getDocument().addDocumentListener(new SearchDocumentListener());

		tableModel = new AppointmentTableModel(appointments);

		appointmentTable = new JTable(tableModel);

		TableCellRenderer cellRenderer = new CustomCellRenderer();

		appointmentTable.setDefaultRenderer(Object.class, cellRenderer);

		JScrollPane scrollPane = new JScrollPane(appointmentTable);

		JTableHeader header = appointmentTable.getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer());
		header.setPreferredSize(new Dimension(1, 30));
		header.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		scheduleButton = new JButton("Agendar Consulta");
		scheduleButton.setFont(font);
		cancelButton = new JButton("Cancelar Consulta");
		cancelButton.setFont(font);
		saveCsvButton = new JButton("Salvar Agendamentos");
		saveCsvButton.setFont(font);
		loadCsvButton = new JButton("Carregar Agendamentos");
		loadCsvButton.setFont(font);

		scheduleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scheduleButton.setBackground(customBlue2);
		scheduleButton.setForeground(Color.WHITE);
		scheduleButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		scheduleButton.setPreferredSize(new Dimension(300, 40));
		scheduleButton.setFocusPainted(false);

		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBackground(customBlue2);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		cancelButton.setPreferredSize(new Dimension(300, 40));
		cancelButton.setFocusPainted(false);

		saveCsvButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveCsvButton.setBackground(customBlue2);
		saveCsvButton.setForeground(Color.WHITE);
		saveCsvButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		saveCsvButton.setPreferredSize(new Dimension(300, 40));
		saveCsvButton.setFocusPainted(false);

		loadCsvButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loadCsvButton.setBackground(customBlue2);
		loadCsvButton.setForeground(Color.WHITE);
		loadCsvButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(customBlue, 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		loadCsvButton.setPreferredSize(new Dimension(300, 40));
		loadCsvButton.setFocusPainted(false);

		scheduleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openSchedulePage();
			}
		});

		saveCsvButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAppointmentsToCsv();
			}
		});

		loadCsvButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAppointmentsFromCsv();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelSelectedAppointment();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
		buttonPanel.add(scheduleButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveCsvButton);
		buttonPanel.add(loadCsvButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		rowSorter = new TableRowSorter<>(tableModel);
		appointmentTable.setRowSorter(rowSorter);

		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new GridLayout(3, 2, 5, 5));

		JLabel dateLabel = new JLabel("Filtrar por Data:");
		dateLabel.setFont(font);
		filterPanel.add(dateLabel);
		filterPanel.add(searchDateField);

		JLabel patientLabel = new JLabel("Filtrar por Paciente:");
		patientLabel.setFont(font);
		filterPanel.add(patientLabel);
		filterPanel.add(searchPatientField);

		JLabel doctorLabel = new JLabel("Filtrar por Médico:");
		doctorLabel.setFont(font);
		filterPanel.add(doctorLabel);
		filterPanel.add(searchDoctorField);

		filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonFilterPanel = new JPanel();
		buttonFilterPanel.setLayout(new BorderLayout());
		buttonFilterPanel.add(buttonPanel, BorderLayout.NORTH);
		buttonFilterPanel.add(filterPanel, BorderLayout.SOUTH);

		panel.add(buttonFilterPanel, BorderLayout.SOUTH);

		add(panel);
		setVisible(true);
	}

	private void openSchedulePage() {
		ScheduleFrame scheduleFrame = new ScheduleFrame(this);
	}

	private void cancelSelectedAppointment() {
		int selectedRow = appointmentTable.getSelectedRow();
		if (selectedRow >= 0) {
			tableModel.removeAppointment(selectedRow);
		}
	}

	public void addAppointment(String patientName, String doctorName, String date) {
		Appointment appointment = new Appointment(patientName, doctorName, date);
		tableModel.addAppointment(appointment);
	}

	private void saveAppointmentsToCsv() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("Agendamentos.csv"));
		int returnValue = fileChooser.showSaveDialog(this);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try (PrintWriter writer = new PrintWriter(selectedFile)) {
				writer.write("Data,Paciente,Médico\n");

				for (int i = 0; i < appointments.size(); i++) {
					Appointment appointment = appointments.get(i);

					String line = appointment.getDate() + "," + appointment.getPatientName() + ","
							+ appointment.getDoctorName();
					writer.write(line + "\n");
				}
				writer.close();
				JOptionPane.showMessageDialog(this, "Dados salvos no arquivo com sucesso.");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao salvar os dados no arquivo.");
			}
		}
	}

	private void loadAppointmentsFromCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(this);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			List<String> appointmentStrings = readAppointmentsFromCsv(selectedFile);

			if (appointmentStrings != null) {
				appointments.clear();
				for (String appointmentStr : appointmentStrings) {
					String[] parts = appointmentStr.split(",");
					if (parts.length == 3) {
						Appointment appointment = new Appointment(parts[1], parts[2], parts[0]);
						appointments.add(appointment);
					}
				}
				tableModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(this, "Dados carregados com sucesso.");
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao carregar os dados do arquivo.");
			}
		}
	}

	private List<String> readAppointmentsFromCsv(File file) {
		List<String> appointments = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				appointments.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return appointments;
	}

	private class SearchDocumentListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateFilter();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateFilter();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateFilter();
		}
	}

	private void updateFilter() {
		RowFilter<TableModel, Object> dateFilter = RowFilter.regexFilter("(?i)" + searchDateField.getText(), 0);
		RowFilter<TableModel, Object> patientFilter = RowFilter.regexFilter("(?i)" + searchPatientField.getText(), 1);
		RowFilter<TableModel, Object> doctorFilter = RowFilter.regexFilter("(?i)" + searchDoctorField.getText(), 2);

		ArrayList<RowFilter<TableModel, Object>> filters = new ArrayList<>();
		filters.add(dateFilter);
		filters.add(patientFilter);
		filters.add(doctorFilter);

		RowFilter<TableModel, Object> combinedFilter = RowFilter.andFilter(filters);

		rowSorter.setRowFilter(combinedFilter);
	}
}