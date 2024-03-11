package controleurs;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import style.CustomJFrame;
import style.Palette;

public class MenuControleur implements ActionListener {
	
	private CustomJFrame vue;
	
	public MenuControleur(CustomJFrame vue) {
		this.vue = vue;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			if (bouton.getIcon() == Palette.CLOSE) {
				this.vue.closeCurrentWindow();
			}
			if (bouton.getIcon() == Palette.MINIMIZE) {
				this.vue.setState(Frame.ICONIFIED);
			}
		}
	}
}