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
			g2.translate(300*0,300*0);  // Moves (0,0) to the center of the display.

	        
	        int R = 100; // promień piętnastokąta
	        int[] xPoints = new int[15]; // tablica przechowująca współrzędne x wierzchołków piętnastokąta
	        int[] yPoints = new int[15]; // tablica przechowująca współrzędne y wierzchołków piętnastokąta

	        // Pętla tworząca tablice z współrzędnymi wierzchołków piętnastokąta
	        for (int i = 0; i < 15; i++) {
	            double t = i * 2 * Math.PI / 15; // kąt obrotu dla i-tego wierzchołka
	            // obliczenie współrzędnej x i-tego wierzchołka
	            xPoints[i] = (int) (R * Math.cos(t)) + getWidth() / 2; 
	           
	            // obliczenie współrzędnej y i-tego wierzchołka
	            yPoints[i] = (int) (R * Math.sin(t)) + getHeight() / 2; 
	            
	        }
	        // narysowanie piętnastokąta na ekranie przy użyciu współrzędnych z tablic xPoints i yPoints.
	        g2.drawPolygon(xPoints, yPoints, 15); 
	        


			//g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
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