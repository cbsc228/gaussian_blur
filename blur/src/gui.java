import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gui extends JFrame {

    private static BufferedImage img = null;
    private static BufferedImage blurImg = null;
    private static String imgPath = "./src/Black Lotus.jpg";

    public gui (){

        //establish the interface for the user
        this.setTitle("Gaussian Blur");

        JPanel controlPanel = new JPanel();
        drawImage displayPanel = new drawImage(img);

        JButton blur1 = new JButton("Stigma: 1.0");
        JButton blur2 = new JButton("Stigma: 1.5");
        JButton blur3 = new JButton("Stigma: 2.0");
        JButton blur4 = new JButton("Stigma: 2.5");
        JButton blur5 = new JButton("Stigma: 3.0");
        JButton reset = new JButton("Reset");
        JButton fileSearch = new JButton("Select Image");
        JButton quit = new JButton("Quit");

        controlPanel.add(blur1);
        controlPanel.add(blur2);
        controlPanel.add(blur3);
        controlPanel.add(blur4);
        controlPanel.add(blur5);
        controlPanel.add(reset);
        controlPanel.add(fileSearch);
        controlPanel.add(quit);

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(displayPanel, BorderLayout.SOUTH);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);

        this.setVisible(true);

        //make the buttons perform their required actions
        blur1.addActionListener(e -> {
            //blur the image
            System.out.println("Blurring image...");
            blurImg = blur.blurImg(img, 1);
            System.out.println("Done");

            //display the new image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(blurImg);

            this.setVisible(false);
            this.setVisible(true);
        });

        blur2.addActionListener(e -> {
            //blur the image
            System.out.println("Blurring image...");
            blurImg = blur.blurImg(img, 2);
            System.out.println("Done");

            //display the new image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(blurImg);

            this.setVisible(false);
            this.setVisible(true);
        });

        blur3.addActionListener(e -> {
            //blur the image
            System.out.println("Blurring image...");
            blurImg = blur.blurImg(img, 3);
            System.out.println("Done");

            //display the new image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(blurImg);

            this.setVisible(false);
            this.setVisible(true);
        });

        blur4.addActionListener(e -> {
            //blur the image
            System.out.println("Blurring image...");
            blurImg = blur.blurImg(img, 4);
            System.out.println("Done");

            //display the new image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(blurImg);

            this.setVisible(false);
            this.setVisible(true);
        });

        blur5.addActionListener(e -> {
            //blur the image
            System.out.println("Blurring image...");
            blurImg = blur.blurImg(img, 5);
            System.out.println("Done");

            //display the new image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(blurImg);

            this.setVisible(false);
            this.setVisible(true);
        });

        reset.addActionListener(e -> {
            //reset the image displayed to the original source image
            displayPanel.resetDisplay();
            displayPanel.setImgToShow(img);

            this.setVisible(false);
            this.setVisible(true);
        });

        fileSearch.addActionListener(e -> {
            //establish a file browser for the user to select a new source image
            JFileChooser imgFinder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            imgFinder.setAcceptAllFileFilterUsed(false);
            imgFinder.setDialogTitle("Select a jpg or png image file");

            //restrict the allowable images to only jps and png files
            FileNameExtensionFilter restrictJPG = new FileNameExtensionFilter(".jpg files", "jpg");
            FileNameExtensionFilter restrictPNG = new FileNameExtensionFilter(".png files", "png");
            imgFinder.addChoosableFileFilter(restrictJPG);
            imgFinder.addChoosableFileFilter(restrictPNG);

            //get the response from the file explorer
            int saveDialog = imgFinder.showOpenDialog(null);

            //if the file is allowed, get its path and draw it on the display panel
            if (saveDialog == JFileChooser.APPROVE_OPTION){
                imgPath = imgFinder.getSelectedFile().getAbsolutePath();

                try {
                    img = ImageIO.read(new File(imgPath));
                    System.out.println("Success: Read image");
                } catch (IOException eIO){
                    System.out.println("Fail: Could not read source image");
                }

                displayPanel.resetDisplay();
                displayPanel.setImgToShow(img);

                this.setVisible(false);
                this.setVisible(true);
            }
        });

        //quit the program
        quit.addActionListener(e -> System.exit( 0 ));
    }


    public static void main(String[] args){
        //grab the default image provided
        try {
            img = ImageIO.read(new File(imgPath));
            System.out.println("Success: Read image");
        } catch (IOException eIO){
            System.out.println("Fail: Could not read source image");
        }

        //instantiate a new gui instance
        gui userInterface = new gui();
        userInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
