

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


class Surface extends JPanel {

    private ZRectangle zrect;
    private ZEllipse zell;

    public Surface() {

        initUI();
    }
    
    private void initUI() {
        
        MovingAdapter ma = new MovingAdapter();

        addMouseMotionListener(ma);
        addMouseListener(ma);
        addMouseWheelListener(new ScaleHandler());

        zrect = new ZRectangle(50, 50, 50, 50);
        zell = new ZEllipse(150, 70, 80, 80);

        setDoubleBuffered(true);        
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font font = new Font("Serif", Font.BOLD, 40);
        g2d.setFont(font);
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 200));
        g2d.fill(zrect);
        g2d.setColor(new Color(0, 200, 0));
        g2d.fill(zell);        
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);        
    }

    class ZEllipse extends Ellipse2D.Float {
        
        public ZEllipse(float x, float y, float width, float height) {
            
            setFrame(x, y, width, height);
        }

        public boolean isHit(float x, float y) {
            
            if (getBounds2D().contains(x, y)) {
                
                return true;
            } else {
                
                return false;
            }
        }

        public void addX(float x) {
            
            this.x += x;
        }

        public void addY(float y) {
            
            this.y += y;
        }

        public void addWidth(float w) {
            
            this.width += w;
        }

        public void addHeight(float h) {
            
            this.height += h;
        }
    }

    class ZRectangle extends Rectangle2D.Float {

        public ZRectangle(float x, float y, float width, float height) {
            
            setRect(x, y, width, height);
        }

        public boolean isHit(float x, float y) {
            
            if (getBounds2D().contains(x, y)) {
                
                return true;
            } else {
                
                return false;
            }
        }

        public void addX(float x) {
            
            this.x += x;
        }

        public void addY(float y) {
            
            this.y += y;
        }

        public void addWidth(float w) {
            
            this.width += w;
        }

        public void addHeight(float h) {
            
            this.height += h;
        }
    }

    class MovingAdapter extends MouseAdapter {

        private int x;
        private int y;

        @Override
        public void mousePressed(MouseEvent e) {
            
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            int dx = e.getX() - x;
            int dy = e.getY() - y;

            if (zrect.isHit(x, y)) {
                
                zrect.addX(dx);
                zrect.addY(dy);
                repaint();
            }

            if (zell.isHit(x, y)) {
                
                zell.addX(dx);
                zell.addY(dy);
                repaint();
            }

            x += dx;
            y += dy;
        }   
    }

    class ScaleHandler implements MouseWheelListener {
        
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            int x = e.getX();
            int y = e.getY();

            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                if (zrect.isHit(x, y)) {
                    
                    float amount =  e.getWheelRotation() * 5f;
                    zrect.addWidth(amount);
                    zrect.addHeight(amount);
                    repaint();
                }

                if (zell.isHit(x, y)) {
                    
                    float amount =  e.getWheelRotation() * 5f;
                    zell.addWidth(amount);
                    zell.addHeight(amount);
                    repaint();
                }
            }
        }
    }
}

public class MovingScaling extends JFrame {
    
    public MovingScaling() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Moving and scaling");

        add(new Surface());

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MovingScaling ms = new MovingScaling();
                ms.setVisible(true);
            }
        });
    }
}