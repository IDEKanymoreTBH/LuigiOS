//TODO: Use Relative Positioning To Handle All Aspect Ratios(getWidth/getHeight + maybe some layout managers)
//TODO: Find A Ubuntu Emulator That Can Run On Windows So You Can Run Stuff There
//TODO: Get Android Studio And try to translate this stuff
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class LuigiOS extends JPanel implements MouseListener{
    public LuigiOS() {
        frame.setUndecorated(true);
        frame.setResizable(false);
        this.addMouseListener(this);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isFullScreenSupported()) {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(screenSize.width, screenSize.height);
            frame.setLocation(0, 0);
        }
        System.out.println(Integer.toString(getWidth()).concat(", ").concat(Integer.toString(getHeight())));
        if(imageURL != null) {
            try {
                image = ImageIO.read(imageURL);
            } catch(IOException e) {}
        }
        if(loadingImageURL != null) {
            try {
                image2 = ImageIO.read(loadingImageURL);
            } catch(IOException e) {}
        }
        timer.start();
    }
    //Global Values/Objects
    int loadingTimer = 1;
    boolean isInLoading = true;
    int loadingLoopIncrement = 0;
    public static JFrame frame = new JFrame("LuigiOS");
    public static JPanel panel = new JPanel();
    Timer timer = new Timer(500, e -> {
        repaint();
    });
    URL imageURL = LuigiOS.class.getResource("Resources/BGPic01.png");
    BufferedImage image;
    URL loadingImageURL = LuigiOS.class.getResource("Resources/LoadingScrPic.png");
    BufferedImage image2;
    public static void main(String[] args) {
        // JOptionPane.showMessageDialog(null, "Transfiguring Electrons...\n\n Please Do Not Exit The Program...", "LuigiOS Progress", JOptionPane.PLAIN_MESSAGE);
        // try {
        //     Thread.sleep(Math.round((Math.random() * 10000)));
        // } catch(InterruptedException e) {}
        // JOptionPane.showMessageDialog(null, "Compiling Raw Assembly...\n\n Please Do Not Exit The Program...", "LuigiOS Progress", JOptionPane.PLAIN_MESSAGE);
        // try {
        //     Thread.sleep(Math.round((Math.random() * 10000)));
        // } catch(InterruptedException e) {}
        // JOptionPane.showMessageDialog(null, "Doing Other Random Stuff Idk...\n\n Please Do Not Exit The Program...", "LuigiOS Progress", JOptionPane.PLAIN_MESSAGE);
        // try {
        //     Thread.sleep(Math.round((Math.random() * 10000)));
        // } catch(InterruptedException e) {}
        // JOptionPane.showMessageDialog(null, "Done! Welcome To LuigiOS!", "LuigiOS Install Complete", JOptionPane.PLAIN_MESSAGE);
        new LuigiOS();
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isInLoading) {
            if(image2 != null) {
                g.drawImage(image2, 0, 0, image2.getWidth(), image2.getHeight(), this);
            }
            if(loadingLoopIncrement <= 5) {
                g.setColor(Color.BLACK);
                g.fillRect((getWidth() / 2) - 100, getHeight() - 44, 200, 30);
                if(loadingTimer == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect((getWidth() / 2) - 98, getHeight() - 41, 12, 24);
                    loadingTimer++;
                } else if(loadingTimer == 2) {
                    g.setColor(Color.WHITE);
                    g.fillRect(100, getHeight() - 41, 12, 24);
                    loadingTimer++;
                } else if(loadingTimer == 3) {
                    g.setColor(Color.WHITE);
                    g.fillRect(150, getHeight() - 41, 12, 24);
                    loadingTimer = 1;
                    loadingLoopIncrement++;
                }
            } else {
                isInLoading = false;
                g.clearRect(0, 0, getWidth(), getHeight());
                if(image2 != null) {
                    g.drawImage(image2, 0, 0, image2.getWidth(), image2.getHeight(), this);
                }
                timer.stop();
                setRepeats(new Timer(1000, e -> {
                    new javax.swing.Timer(1000, e2 -> repaint()).start();
                }), false).start();
            }
        } else {
            if(image != null) {
                g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
                ZonedDateTime now = ZonedDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                g.drawString(now.format(formatter), image.getWidth() - 100, image.getHeight() - 10);
            }
        }
    }
    public Timer setRepeats(Timer timer, boolean toRepeat) {
        timer.setRepeats(toRepeat);
        return timer;
    }
}