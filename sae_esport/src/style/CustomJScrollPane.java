package style;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CustomJScrollPane extends JScrollPane {
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JScrollPane avec la définition d'un panel et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Panel
	*/
	public CustomJScrollPane(JPanel panel) {
		super(panel);
		
        setBorder(BorderFactory.createEmptyBorder());
        setViewportBorder(BorderFactory.createEmptyBorder());
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
	}
}
