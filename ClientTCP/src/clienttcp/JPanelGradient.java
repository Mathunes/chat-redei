package clienttcp;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

class JPanelGradient extends JPanel {
        
        Color color1;
        Color color2;
        
        public JPanelGradient(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
        }
    
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
        
    }