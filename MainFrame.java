import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MainFrame extends JFrame {

    JTextArea textArea = null;
    JPanel panel = null;
    JButton playButton = null;

    Font buttonFont = new Font("Arial", Font.PLAIN, 18);
    Font asciiFont = new Font("Consolas", Font.PLAIN, 13);

    String fileName = "E:\\bad apple resized\\";
    String formatString = ".png";

    Thread playAscii_t = null;
    Thread playMusic_t = null;

    public MainFrame() {
        initUI();
        initListeners();
    }

    Runnable playAscii = new Runnable() {
        @Override
        public void run() {

            for (int i = 1; i <= 6472; i++) {
                textArea.setText(Image2Ascii.algorithm2(fileName + i + "" + formatString));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    Runnable playMusic = new Runnable() {

        String musicFile = "C:\\Users\\Elliot Lee\\Desktop\\bad apple.mp3";

        @Override
        public void run() {
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(musicFile));
                Player player = new Player(buffer);
                player.play();
            } catch (Exception e) {
            }

        }
    };

    private void initListeners() {

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    if (playAscii_t != null && playAscii_t.isAlive()) {
                        playAscii_t.interrupt();
                        playAscii_t = null;
                    }

                    if (playMusic_t != null && playMusic_t.isAlive()) {
                        playMusic_t.interrupt();
                        playMusic_t = null;
                    }

                    System.exit(0);
                }
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                validate();
                setUnvisibleMouse();

                if (playAscii_t == null) {
                    playAscii_t = new Thread(playAscii);
                    playAscii_t.start();
                }

                if (playMusic_t == null) {
                    playMusic_t = new Thread(playMusic);
                    playMusic_t.start();
                }
            }
        });

    }

    private void initUI() {
        textArea = new JTextArea();
        textArea.setFont(asciiFont);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(0, 20, 0, 0));
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel = new JPanel();
        playButton = new JButton("Play-- Press ESC to Exit");
        playButton.setFont(buttonFont);
        playButton.setForeground(Color.BLACK);
        playButton.setBackground(Color.WHITE);
        panel.add(playButton);


        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);

        this.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setUnvisibleMouse() {

        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Image image = defaultToolkit.createImage(new MemoryImageSource(0, 0, null, 0, 0));
        Cursor invisibleCursor = defaultToolkit.createCustomCursor(image, new Point(0, 0), "cursor");
        setCursor(invisibleCursor);

    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(null, ignored.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
