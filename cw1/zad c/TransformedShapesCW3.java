import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class TransformedShapes extends JPanel {

    private Graphics2D g2;

    private void resetTransform() {
        // ustawia transformację na wartość domyślną
        g2.setTransform(new AffineTransform());
    }

    private void triangle() {
        // rysuje trójkąt o wierzchołkach w punktach (0, -50), (-50, 50), (50, 50)
        int[] xPoints = { 0, -50, 50 };
        int[] yPoints = { -50, 50, 50 };
        g2.fillPolygon(xPoints, yPoints, 3);
    }

    private void square() {
        // rysuje kwadrat o szerokości 200 i wysokości 100 w punkcie (0,0)
        int width = 200;
        int height = 100;
        g2.drawRect(0, 0, width, height);
        g2.fillRect(0, 0, width, height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //ustawia kolor g2 na niebieskie
        g2.setColor(Color.BLUE);
        //resetuje transformację do wartości domyślnej
        resetTransform();

        // przesuwa układ o (275, 300) i rysuje kwadrat
        g2.translate(275,300);
        square();
        
        // przesuwa układ o (100, -50), obraca o 180 stopni i rysuje trójkąt
        g2.translate(100, -50);
		g2.rotate(Math.PI);
		triangle();
		
		// obraca układ o 180 stopni i przesuwa o wektor (0, 200), następnie rysuje trójkąt
		g2.rotate(Math.PI);
        g2.translate(0, 200);
        triangle();

        // resetuje transformację do wartości domyślnej
        resetTransform();

    }

    public TransformedShapes() {
        // ustawia preferowaną wielkość panelu, tło i obramowanie
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    }

    public static void main(String[] args) {
        // tworzy okno aplikacji z panelem TransformedShapes
        JFrame window = new JFrame("Drawing With Transforms");
        window.setContentPane(new TransformedShapes());
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
        window.setVisible(true);
    }
}
