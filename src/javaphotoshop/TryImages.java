package javaphotoshop;

import java.awt.image.BufferedImage;

public class TryImages {

    public static void main(String args[]) {
        //instance of image class
        Images imgt = new Images();
        
        //instance of transform class
        Transform trf = new Transform();

        //location of images
        String imglocation = "C://Users/kosta/Documents/NetBeansProjects/JavaPhotoShop/src/input/city.png";

        //type of image
        String type = "png";
        
        //svae the image using the type of image
        String output = "C://Users/kosta/Documents/NetBeansProjects/JavaPhotoShop/src/output/coloredBlue." + type;
        
        //set pixel of image
        imgt.setX_pixels(800);
        imgt.setY_pixels(600);

        //read image
        BufferedImage img = imgt.readImage(imglocation);

        //write image
        //imgt.writeImage(img,type,output);
        
        //make image gray
        //img= trf.make_Grayscale(img);
        
        //negative  convertion
        // img = trf.make_negative_convertion(img);
        
        //sepia ccnversion
//        img= trf.make_sepia_image(img);

        //
//        img= imgt.watermrk_the_image(img,"images example");

        //make image brighter
  //      img = trf.brighter(img, 2.0f);
  
        //blur image
  //      img = trf.blur(img);
  
        //combine images
        //img = trf.combine_images(img,img);
          
        //resize image
        //img= imgt.resize(img,1024,768);
        
        //resize by percent
        //img= imgt.resize_by_percent(img, 1.5);
        
        //img = imgt.screenshot();
        
        
       // img= trf.color_red(img);
        
       img = trf.color_blue(img);
       
        //save the new image
        imgt.writeImage(img, type, output);
    }
}
