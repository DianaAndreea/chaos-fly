package albinuta;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Albinute  {
  
  public Albinute() {
   
     Albinuta albina=new Albinuta();
     Thread thread=new Thread(albina);
     thread.start();
       
    }
  /* Clasa cu  interfata Runnable pentru un fir de executie
     cu interfata grafica
  */
  class Albinuta extends JFrame implements Runnable {
    
    Fereastra fereastra;
    Thread fir;
    boolean zbor;

    public Albinuta() {
      
      fereastra=new Fereastra();
      fereastra.setSize(400,400);
      fereastra.setBackground(Color.white);
      fereastra.culoare=Color.red;
      Box box=Box.createVerticalBox(); 
           
      box.add(fereastra);
      getContentPane().add(box);
      addWindowListener(new EndGandac(this));
      setSize(400,400);
      setVisible(true);
    }

    /* Definirea metodei run() a interfetei Runnable */
    public void run() {
   
     zbor=true;
     while(zbor) {
      fereastra.ampl=100;
      fereastra.repaint();       
      try {
        fir.sleep(30);
      }
      catch(Exception e) {
        System.out.println(e);
      }
     }
    }

  } 

  
  /* Fereastra in care evolueaza "bondarul" */
  class Fereastra extends Canvas {
    int x, y, x1, y1, x2, y2, ampl;
    Color culoare;

//incarcarea imaginii
    
BufferedImage im = null;
BufferedImage im2 = null;
BufferedImage im3 = null;

    public void paint(Graphics g) {
    	
    	try {
    im = ImageIO.read(new File("src/res/logo.jpg"));
    im2 = ImageIO.read(new File("src/res/logo.jpg"));
    im3 = ImageIO.read(new File("src/res/logo.jpg"));
    } catch (IOException e) {
          }
    	
     Rectangle r=getBounds();
     x+=(int)(ampl*(Math.random()-0.5));
     if(x<0) x=0;
     else if(x>=r.width) x=r.width-5;
     y+=(int)(ampl*(Math.random()-0.5));
     if(y<0) y=0;
     else if(y>=r.height) y=r.height-5;
     
     x1+=(int)(ampl*(Math.random()-0.5));
     if(x1<0) x1=0;
     else if(x1>=r.width) x1=r.width-5;
     y1+=(int)(ampl*(Math.random()-0.5));
     if(y1<0) y1=0;
     else if(y1>=r.height) y1=r.height-5;
     
     x2+=(int)(ampl*(Math.random()-0.5));
     if(x2<0) x2=0;
     else if(x2>=r.width) x2=r.width-5;
     y2+=(int)(ampl*(Math.random()-0.5));
     if(y2<0) y2=0;
     else if(y2>=r.height) y2=r.height-5;
     
    //desenarea imaginii
     g.drawImage(im, x,y, 50,50, null); 
     g.drawImage(im2, x1,y1, 50,50, null); 
     g.drawImage(im3, x2,y2, 50,50, null); 
    }
  } /* Sfarsitul clasei Fereastra */

  /* Incheierea executarii aplicatiei */
  class Iesire extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }
  } /* Sfarsitul clasei Iesire */

  /* Crearea si lansarea unui nou fir de executie */
  class CreareGandac implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int x, y, x1, y1, x2, y2;
      x=(int)(Math.random()*400);
      y=(int)(Math.random()*200);
      
      x1=(int)(Math.random()*400);
      y1=(int)(Math.random()*300);
      
      x2=(int)(Math.random()*400);
      y2=(int)(Math.random()*350);
      
     Albinuta albina=new Albinuta();
      albina.setLocation(new Point(x,y));
      Thread thread=new Thread(albina);
      thread.start();
    }
  } 

  /* Incheierea executarii unui fir de executie */
  class EndGandac extends WindowAdapter {
   Albinuta albina;
    EndGandac(Albinuta albina) {
      this.albina=albina;
    }

    public void windowClosing(WindowEvent e) {
      albina.zbor=false;
      System.exit(0);
    }
  }

  /* Metoda  principala a aplicatiei */
  public static void main(String args[]) {
    Albinute b=new Albinute();
  }
}