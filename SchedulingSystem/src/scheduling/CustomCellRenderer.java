package scheduling;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 853552580942088645L;

	public CustomCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        c.setFont(new Font("Arial", Font.PLAIN, 16));
		c.setBackground(new Color(0xdfdfdf));
		c.setForeground(Color.BLACK);

        return c;
    }
}
