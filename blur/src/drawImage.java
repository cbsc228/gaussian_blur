import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class drawImage extends JPanel {

    //global variable to keep which image is being displayed
    BufferedImage imgToShow;

    //constructor
    public drawImage(BufferedImage img){
        imgToShow = img;
        repaint();
    }

    //sets the image that is being drawn next
    public void setImgToShow(BufferedImage img){
        imgToShow = img;
        repaint();
    }

    //removes the current image being displayed
    public void resetDisplay(){
        this.removeAll();
    }

    //displays the new image
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        JLabel imgDisplay = new JLabel(new ImageIcon(imgToShow));
        add(imgDisplay, BorderLayout.CENTER);
        this.setVisible(false);
        this.setVisible(true);
    }

}
