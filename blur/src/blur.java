import java.awt.*;
import java.awt.image.BufferedImage;

public class blur{

    //the Gaussian kernels used for blurring images
    private static final double[][] kernel1 = {{0.003765, 0.015019, 0.023729, 0.015019, 0.003765},
            {0.015019, 0.059912, 0.094907, 0.059912, 0.015019},
            {0.23729, 0.094907, 0.150342, 0.094907, 0.023792},
            {0.015019, 0.059912, 0.094907, 0.059912, 0.015019},
            {0.003765, 0.015019, 0.023792, 0.015019, 0.003765}};

    private static final double[][] kernel2 = {{0.015026, 0.28569, 0.035391, 0.028569, 0.015026},
            {0.028569, 0.054318, 0.067288, 0.054318, 0.028569},
            {0.035391, 0.067288, 0.083355, 0.067288, 0.035391},
            {0.028569, 0.054318, 0.067288, 0.054318, 0.028569},
            {0.015026, 0.028569, 0.035391, 0.028569, 0.015026}};

    private static final double[][] kernel3 = {{0.023528, 0.033969, 0.038393, 0.033969, 0.023528},
            {0.033969, 0.049045, 0.055432, 0.049045, 0.033969},
            {0.038393, 0.055432, 0.062651, 0.055432, 0.038393},
            {0.033969, 0.049045, 0.055432, 0.049045, 0.033969},
            {0.023528, 0.033969, 0.038393, 0.033969, 0.023528}};

    private static final double[][] kernel4 = {{0.028672, 0.036333, 0.039317, 0.036333, 0.028672},
            {0.036333, 0.046042, 0.049824, 0.046042, 0.036333},
            {0.039317, 0.049824, 0.053916, 0.049824, 0.039317},
            {0.036333, 0.046042, 0.049824, 0.046042, 0.036333},
            {0.028672, 0.036333, 0.039317, 0.036333, 0.028672}};

    private static final double[][] kernel5 = {{0.031827, 0.037541, 0.039665, 0.037541, 0.031827},
            {0.037541, 0.044281, 0.046787, 0.044281, 0.037541},
            {0.039665, 0.046787, 0.049434, 0.046787, 0.039665},
            {0.037541, 0.044281, 0.046787, 0.044281, 0.037541},
            {0.031827, 0.037541, 0.039665, 0.037541, 0.031827}};


    //blurs the provided image according the one of the hard-coded kernels
    public static BufferedImage blurImg(BufferedImage srcImg, int kernelID){

        BufferedImage blurImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);

        //determines which kernel to use
        double[][] weights = switch (kernelID) {
            case 1 -> kernel1.clone();
            case 2 -> kernel2.clone();
            case 3 -> kernel3.clone();
            case 4 -> kernel4.clone();
            case 5 -> kernel5.clone();
            default -> null;
        };
        if (weights == null){
            return null;
        }
        int size = weights.length;

        //moves across the entire image
        for (int x = 0; x < srcImg.getWidth() - size; x++){
            for (int y = 0; y < srcImg.getHeight() - size; y++){

                //holds each color channel in the image
                double[][] redChannel = new double[size][size];
                double[][] greenChannel = new double[size][size];
                double[][] blueChannel = new double[size][size];

                //handles each weight for each pixel
                for (int weightX = 0; weightX < size; weightX++){
                    for (int weightY = 0; weightY < size; weightY++){

                        //makes sure that we are staying in bounds of the image with sampling
                        try {
                            int offsetX = x + weightX - (size / 2);
                            int offsetY = y + weightY - (size / 2);

                            double currWeight = weights[weightX][weightY];

                            Color origColor = new Color(srcImg.getRGB(offsetX, offsetY));

                            redChannel[weightX][weightY] = currWeight * origColor.getRed();
                            greenChannel[weightX][weightY] = currWeight * origColor.getGreen();
                            blueChannel[weightX][weightY] = currWeight * origColor.getBlue();
                        } catch (Exception e) {
                            //System.out.println("Out of image bounds");
                        }


                    }
                }

                //gets new color for the blurred image
                int r = weightColorValue(redChannel);
                int g = weightColorValue(greenChannel);
                int b = weightColorValue(blueChannel);

                if (r > 255){
                    r = 255;
                }
                if (g > 255){
                    g = 255;
                }
                if (b > 255){
                    b = 255;
                }

                //sets the color for the blurred image
                int newColor = new Color(r, g, b).getRGB();
                blurImg.setRGB(x, y, newColor);
            }
        }

        return blurImg;
    }

    //helper method to calculate new color
    private static int weightColorValue(double[][] weightColor){
        double newColor = 0;

        for (double[] doubles : weightColor) {
            for (double aDouble : doubles) {
                newColor += aDouble;
            }
        }
        return (int) newColor;
    }
}
