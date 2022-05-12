/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracion;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author user
 */
public class fondoPantalla implements Border{
   public BufferedImage insertar;
   
   public fondoPantalla(){
       try {
           URL imagen = new URL(getClass().getResource("/imagenes/escritorio.jpg").toString());
           insertar =  ImageIO.read(imagen);
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
   }

    @Override
    public void paintBorder(Component cmpnt, Graphics g, int x, int y, int width, int height) {
      g.drawImage(insertar,(x +(width - insertar.getWidth())/2), (y+ (height -insertar.getHeight())/2),null);
    }

    @Override
    public Insets getBorderInsets(Component cmpnt) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
   
}
