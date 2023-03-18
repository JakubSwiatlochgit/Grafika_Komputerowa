import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private class Display extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.

			// Wybór odpowiedniej transformacji na podstawie wartości zmiennej whichTransform
	        int whichTransform = transformSelect.getSelectedIndex();
	        switch (whichTransform) {
	            case 1:
	                // Skalowanie o 50%
	                g2.scale(0.5, 0.5);
	                break;
	            case 2:
	                // Obrót o 45 stopni
	                g2.rotate(Math.PI/4);
	                break;
	            case 3:
	                // Obrót o 180 stopni, skalowanie o 50% w osi X, odbicie lustrzane w osi Y
	                g2.rotate(Math.PI);
	                g2.scale(0.5, 1);
	                g2.scale(-1, 1);
	                break;
	            case 4:
	                // Zniekształcenie przez pochylenie w osi X o 50%
	                g2.shear(0.5, 0);
	                break;
	            case 5:
	                // Skalowanie o 50% w osi Y, przesunięcie w górę o 450 pikseli
	                g2.scale(1, 0.5);
	                g2.translate(0, -450);
	                break;
	            case 6:
	                // Obrót o 90 stopni, zniekształcenie przez pochylenie w osi X o 50%
	                g2.rotate(Math.PI/2);
	                g2.shear(0.5, 0);
	                break;
	            case 7:
	                // Obrót o 180 stopni, skalowanie o 50% w osi X
	                g2.rotate(Math.PI);
	                g2.scale(0.5, 1);
	                break;
	            case 8:
	                // Przesunięcie o (-50, 100), obrót o 30 stopni, skalowanie o 100% w osi X i 50% w osi Y
	                g2.translate(-50, 100);
	                g2.rotate(Math.toRadians(30)); 
	                g2.scale(1.0, 0.5); 
	                break;
	            case 9:
	                // Obrót o 180 stopni, zniekształcenie przez pochylenie w osi Y o 25%, przesunięcie o (-100, 50)
	                g2.rotate(Math.PI);
	                g2.shear(0, 0.25);
	                g2.translate(-100, 50);
	                break;
	            default:
	                // Brak transformacji
	                break;
	        }

			g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}