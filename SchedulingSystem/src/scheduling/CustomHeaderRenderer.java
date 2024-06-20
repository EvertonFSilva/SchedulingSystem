package scheduling;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

class CustomHeaderRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -6821105263595767888L;

	public CustomHeaderRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        c.setFont(new Font("Arial", Font.BOLD, 16));
		c.setBackground(new Color(0xbababa));
		c.setForeground(Color.BLACK);

        return c;
    }
}
