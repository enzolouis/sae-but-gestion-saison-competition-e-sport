package vues;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

class EvenOddRenderer implements TableCellRenderer {

  public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);
    ((JLabel) renderer).setOpaque(true);
    Color foreground, background;
    if (isSelected) {
      foreground = Palette.WHITE;
      background = Palette.BLACKMOINS2;
    } else {
      if (row % 2 == 0) {
        foreground = Palette.WHITE;
        background = Palette.BLACK;
      } else {
        foreground = Palette.WHITE;
        background = Palette.BLACKMOINS2;
      }
    }
    renderer.setForeground(foreground);
    renderer.setBackground(background);
    
    // Ajoute l'alignement horizontal au centre
    ((JLabel) renderer).setHorizontalAlignment(JLabel.CENTER);

    return renderer;
  }
}