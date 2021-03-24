package javaphotoshop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Transform {

    public Transform() {
    }

    public BufferedImage make_Grayscale(BufferedImage img) {
        BufferedImage new_image = null;

        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Here (x,y)denotes the coordinate of image  
                // for modifying the pixel value. 
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // calculate average 
                int avg = (r + g + b) / 3;

                // replace RGB value with avg 
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(x, y, p);
            }
        }

        new_image = img;

        return new_image;
    }

    public BufferedImage make_negative_convertion(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();

        // Convert to negative 
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //subtract RGB from 255 
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                //set new RGB value 
                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }

        return img;
    }

    public BufferedImage make_sepia_image(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to sepia 
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int R = (p >> 16) & 0xff;
                int G = (p >> 8) & 0xff;
                int B = p & 0xff;

                //calculate newRed, newGreen, newBlue 
                int newRed = (int) (0.393 * R + 0.769 * G + 0.189 * B);
                int newGreen = (int) (0.349 * R + 0.686 * G + 0.168 * B);
                int newBlue = (int) (0.272 * R + 0.534 * G + 0.131 * B);

                //check condition 
                if (newRed > 255) {
                    R = 255;
                } else {
                    R = newRed;
                }

                if (newGreen > 255) {
                    G = 255;
                } else {
                    G = newGreen;
                }

                if (newBlue > 255) {
                    B = 255;
                } else {
                    B = newBlue;
                }

                //set new RGB value 
                p = (a << 24) | (R << 16) | (G << 8) | B;

                img.setRGB(x, y, p);
            }
        }

        return img;
    }

    public BufferedImage brighter(BufferedImage img, float brighten) {

        RescaleOp op = new RescaleOp(brighten, 0, null);
        img = op.filter(img, img);

        return img;
    }

    public BufferedImage blur(BufferedImage img) {
        BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int i = 0;
        int max = 400, rad = 10;
        int a1 = 0, r1 = 0, g1 = 0, b1 = 0;
        Color[] color = new Color[max];

        //core section of code responsible for blurring of an image
        int x = 1, y = 1, x1, y1, ex = 5, d = 0;
        for (x = rad; x < img.getHeight() - rad; x++) {
            for (y = rad; y < img.getWidth() - rad; y++) {
                for (x1 = x - rad; x1 < x + rad; x1++) {
                    for (y1 = y - rad; y1 < y + rad; y1++) {
                        color[i++] = new Color(img.getRGB(y1, x1));
                    }
                }
                i = 0;
                for (d = 0; d < max; d++) {
                    a1 = a1 + color[d].getAlpha();
                }
                a1 = a1 / (max);
                for (d = 0; d < max; d++) {
                    r1 = r1 + color[d].getRed();
                }
                r1 = r1 / (max);
                for (d = 0; d < max; d++) {
                    g1 = g1 + color[d].getGreen();
                }
                g1 = g1 / (max);
                for (d = 0; d < max; d++) {
                    b1 = b1 + color[d].getBlue();
                }
                b1 = b1 / (max);
                int sum1 = (a1 << 24) + (r1 << 16) + (g1 << 8) + b1;
                output.setRGB(y, x, (int) (sum1));
            }
        }

        return output;
    }

    public BufferedImage combine_images(BufferedImage img1, BufferedImage img2) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();

        return newImage;
    }
    
    
    public BufferedImage color_red(BufferedImage img){
        // get width and height
        int width = img.getWidth();
        int height = img.getHeight();
  
        // convert to red image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = img.getRGB(x,y);
  
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
  
                // set new RGB
                // keeping the r value same as in original
                // image and setting g and b as 0.
                p = (a<<24) | (r<<16) | (0<<8) | 0;
  
                img.setRGB(x, y, p);
            }
        }
        
        return img;
    }
    
    public BufferedImage color_blue(BufferedImage img){
        // get width and height
        int width = img.getWidth();
        int height = img.getHeight();
  
        // convert to blue image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = img.getRGB(x,y);
  
                int a = (p>>24)&0xff;
                int b = p&0xff;
  
                // set new RGB
                // keeping the b value same as in original
                // image and setting r and g as 0.
                p = (a<<24) | (0<<16) | (0<<8) | b;
  
                img.setRGB(x, y, p);
            }
        }
        
        return img;
    }
}
