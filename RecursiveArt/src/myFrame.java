import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

public class myFrame  extends JFrame{
    private JSlider slider = new JSlider(SwingConstants.HORIZONTAL,15,1);
    private JSlider slider2 = new JSlider(SwingConstants.HORIZONTAL,180,1);
    private myPanel panel = new myPanel();
    private int level;
    private int angle;
    public myFrame() throws HeadlessException {
        super();
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(slider);
        slider.addChangeListener(new SliderListner());
        sliderPanel.add(slider2);
        slider2.addChangeListener(new SliderListner2());
        Container container = getContentPane();
        container.add(sliderPanel, BorderLayout.SOUTH);
        container.add(panel,BorderLayout.CENTER);
    }

    private class SliderListner implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            int value  = slider.getValue();
            if (value != level) {
                level = value;
                panel.setLevel(value);
                panel.repaint();
            }

        }
    }
    private class SliderListner2 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            int value  = slider2.getValue();
            if (value != angle) {
                angle = value;
                panel.setAngle(value);
                panel.repaint();
            }

        }
    }



}