import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClockWindow extends JFrame {

    private static Logger logger = Logger.getLogger(ClockWindow.class.getName());

    private JLabel clockLbl;
    private Font font = new Font("", Font.BOLD, 35);

    ClockWindow() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("SimpleClock");
        super.setSize(400, 200);
        super.setLocation(300, 50);

        this.createUI();
        this.startClock();

        super.setVisible(true);
    }

    public void createUI() {
        clockLbl = new JLabel("Clock");
        clockLbl.setFont(font);
        this.setLayout(new GridLayout(1, 1));
        this.add(clockLbl);
    }

    public void startClock() {
        String timeColonPattern = "hh:mm:ss a";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeColonPattern);

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    clockLbl.setText(dtf.format(LocalTime.now()));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });

        thread.start();
    }
}
