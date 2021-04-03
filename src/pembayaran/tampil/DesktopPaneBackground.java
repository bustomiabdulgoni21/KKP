
package pembayaran.tampil;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
public class DesktopPaneBackground extends JDesktopPane{
    @Override 
    protected void paintComponent (Graphics g) {
        Graphics2D graphics = (Graphics2D)g.create () ;
        
        Image img = new ImageIcon (getClass().getResource("/pembayaran/gambar/utama.jpg")).getImage();
        graphics.drawImage(img, 0, 0, getWidth(), getHeight(), null) ;
        graphics.dispose();
    }
}
