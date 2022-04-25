package IHM;

import javax.swing.*;

import Elements.toBeDrawn;

import java.awt.*;

public class GamePanel extends JPanel {
    private toBeDrawn[] drawings;
    private Image imageBuffer;
    private Image backGroundImage = new ImageIcon("src/Resource/background.jpg").getImage();

    public GamePanel(toBeDrawn... drawings){
        this.drawings = drawings;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawBufferedImage();
        g.drawImage(imageBuffer, 0, 0,this);

    }

    private void drawBufferedImage() {
        imageBuffer = createImage(this.getWidth(), this.getHeight());
        Graphics g = imageBuffer.getGraphics();
        g.drawImage(backGroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        //绘制
        for (toBeDrawn draw : this.drawings) {
            draw.drawImage(g);
        }
    }
}
