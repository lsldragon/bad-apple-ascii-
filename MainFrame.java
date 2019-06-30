import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    JTextArea textArea = null;
    JPanel panel = null;
    JButton playButton = null;

    Font buttonFont = new Font("Arial", Font.PLAIN, 18);
    Font asciiFont = new Font("Consolas", Font.PLAIN, 9);

    public MainFrame() {
        initUI();
        initListeners();
    }

    private void initListeners() {

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                validate();
            }
        });

    }

    private void initUI() {
        textArea = new JTextArea();
        textArea.setFont(asciiFont);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel = new JPanel();
        playButton = new JButton("Play-- Press ESC to Exit");
        playButton.setFont(buttonFont);
        panel.add(playButton);

        this.add(textArea, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);

//        this.setUndecorated(true);
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//        gd.setFullScreenWindow(this);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
