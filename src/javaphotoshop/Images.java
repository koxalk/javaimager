package javaphotoshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
    
    public int x_pixels;
    public int y_pixels;
    public String filename;
    public String input;
    public String output;

    Images() {
        
    }

    public int getX_pixels() {
        return x_pixels;
    }

    public void setX_pixels(int x_pixels) {
        this.x_pixels = x_pixels;
    }

    public int getY_pixels() {
        return y_pixels;
    }

    public void setY_pixels(int y_pixels) {
        this.y_pixels = y_pixels;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Images(int x_pixels, int y_pixels, String filename, String input, String output) {
        this.x_pixels = x_pixels;
        this.y_pixels = y_pixels;
        this.filename = filename;
        this.input = input;
        this.output = output;
    }
    
    
    public BufferedImage readImage(String filename){
        try{
          BufferedImage image = null;
          File f = null;
          
          f=new File(filename);
          
          image = new BufferedImage(this.x_pixels, this.y_pixels, BufferedImage.TYPE_INT_ARGB);
         
          image = ImageIO.read(f);
          
          return image;
        }catch(IOException e){
            System.out.println(e);
        }
        
        return null;
    }
    
    public String writeImage(BufferedImage image,String type,String outputLocation) {
        try {
            File f= new File(outputLocation);
            
            ImageIO.write(image, type, f);
            
            return "Write Sucess";
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        return null;
    }
    
    public BufferedImage watermrk_the_image(BufferedImage img, String watermark){
        // create BufferedImage object of same width and 
        // height as of input image 
        BufferedImage temp = new BufferedImage(img.getWidth(), 
                    img.getHeight(), BufferedImage.TYPE_INT_RGB); 
  
        // Create graphics object and add original 
        // image to it 
        Graphics graphics = temp.getGraphics(); 
        graphics.drawImage(img, 0, 0, null); 
  
        // Set font for the watermark text 
        graphics.setFont(new Font("Arial", Font.PLAIN, 80)); 
        graphics.setColor(new Color(0, 0, 0, 40)); 
        
        // Add the watermark text at (width/5, height/3) 
        // location 
        graphics.drawString(watermark, img.getWidth()/5, 
                                   img.getHeight()/3); 
  
        // releases any system resources that it is using 
        graphics.dispose();
        
        return temp;
    }
    
    public BufferedImage resize(BufferedImage img,int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(img, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();

        return resizedImage;
    }
}
