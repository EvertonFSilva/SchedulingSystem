package scheduling;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AppointmentTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -2152046201241910729L;
	private List<Appointment> appointments = new ArrayList<>();
	private String[] columnNames = { "Data", "Paciente", "Médico" };

	public AppointmentTableModel(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int getRowCount() {
		return appointments.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= appointments.size()) {
			return null;
		}

		Appointment appointment = appointments.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return appointment.getDate();
		case 1:
			return appointment.getPatientName();
		case 2:
			return appointment.getDoctorName();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
		fireTableDataChanged();
	}

	public void removeAppointment(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= appointments.size()) {
			return;
		}
		appointments.remove(rowIndex);
		fireTableDataChanged();
	}
}