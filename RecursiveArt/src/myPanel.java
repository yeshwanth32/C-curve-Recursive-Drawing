import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class myPanel extends JPanel{
    private int level;
    private int width, height;
    private int angle;
    private ArrayList<line> arr;
    private ArrayList<Color> color;
    public myPanel() {
        super();
        this.setBackground(Color.BLACK);
        this.setSize(800,100);
        arr = new ArrayList<line>();
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setAngle(int angle) {
        this.angle = angle;
    }
    private void InitializeRainbow(int Size) {
        color = new ArrayList<Color>();
        for(int n = 0; n < Size; n++) {
            int c1 = (int)(Math.sin(n) * 127 + 128);
            int c2 = (int)(Math.sin(n + Math.PI/2) * 127 + 128);
            int c3 = (int)(Math.sin(n + Math.PI) * 127 + 128);
            color.add( new Color(c1,c2 ,c3 ));
        }
    }
    public void clearAndPaint(Graphics g) {
        gradient();
        color.clear();
        drawArr(g);
        arr.clear();
    }
    public int cCurveHelper(int x, int y, int len, int n, Graphics g) {
        int Lines = 0;
        cCurve(x		,y		,len	,270	,n	,Color.RED,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x		,y-len	,len	,0	,n	,Color.BLUE,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x+len	,y-len	,len	,90,n	,Color.GREEN,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x+len	,y		,len	,180,n	,Color.YELLOW,angle);
        Lines += arr.size();
        clearAndPaint(g);
        return Lines;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Angle" + angle, 0, getHeight()-10);
        g.drawString(" Level" + level, 50, getHeight()-10);
        int n = level;
        int x0 = 400, y0 = 375;
        int len = 200;
        int x = x0-25, y = y0;
        int Lines = 0;
        cCurve(x		,y		,len	,0	,n	,Color.RED,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x		,y-len	,len	,90	,n	,Color.BLUE,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x+len	,y-len	,len	,180,n	,Color.GREEN,angle);
        Lines += arr.size();
        clearAndPaint(g);
        cCurve(x+len	,y		,len	,270,n	,Color.YELLOW,angle);
        Lines += arr.size();
        clearAndPaint(g);
        x = x0- 300; y = y0- 50;
        len = 50;
        Lines += cCurveHelper(x,y,len,n,g);
        x = x0 + 400;
        len = 50;
        Lines +=cCurveHelper(x,y,len,n,g);
        y-= 200;
        len = 50;
        Lines +=cCurveHelper(x,y,len,n,g);
        y+= 400;
        len = 50;
        Lines +=cCurveHelper(x,y,len,n,g);
        x = x0- 300;
        Lines +=cCurveHelper(x,y,len,n,g);
        y-= 400;
        Lines +=cCurveHelper(x,y,len,n,g);
        g.setColor(Color.DARK_GRAY);
        g.drawString(" NumberOfLines" + Lines, 100, getHeight()-10);


    }
    private void cCurve(double i, double j, double distance, double theta, double Level, Color c, int angle){
        if (Level == 0){
            int x = (int) (i + (distance * Math.cos(Math.toRadians(theta))));
            int y = (int) (j + (distance * Math.sin(Math.toRadians(theta))));
            Point k = new Point((int)i, (int)j);
            arr.add(new line (new Point((int)i, (int)j), new Point(x, y), c));
        }
        else{
            double OriginalDistance = distance;
            distance = (distance*Math.cos(Math.toRadians(angle)));
            cCurve(i,j,distance,theta+angle,Level-1,c,angle);
            int x = (int) (i + (distance*Math.cos(Math.toRadians(theta+angle))));
            int y = (int) (j + (distance*Math.sin(Math.toRadians(theta+angle))));
            distance = (OriginalDistance*Math.sin(Math.toRadians(angle)));
            cCurve(x,y,distance,theta-(90-angle),Level-1,c, angle);
        }
    }
    private void generateArrOfRainbow(int arrSize) {
        int incremenrementValue = Math.min(255, 255/arr.size());
        for (int i = 0; i < 255; i+= incremenrementValue) {
            color.add(new Color(0,0,i));
        }
        for (int i = 0; i < 255; i+= incremenrementValue) {
            color.add(new Color(0,i,255));
        }
        for (int i = 0; i < 255; i+= incremenrementValue) {
            color.add(new Color(i,255,255));
        }
    }
    private Color getColor(int i) {
        if (i == 0) {
            return new Color(255,0,0);
        }
        if (i == 1) {
            return new Color(255,127,0);
        }
        if (i == 2) {
            return new Color(255,255,0);
        }
        if (i == 3) {
            return new Color(0,255,0);
        }
        if (i == 4) {
            return new Color(0,0,255);
        }
        if (i == 5) {
            return new Color(75,0,130);
        }
        if (i == 6) {
            return new Color(143,0,255);
        }
        else {
            return new Color(143,0,255);
        }
    }
    private void gradient() {
        InitializeRainbow(arr.size());
        Collections.sort(arr);
        int LowerLimit = 0;
        int UpperLimit = arr.size()/7;
        int ColorIndex = 0;
        for (int i = 0; i < arr.size();i++) {
            if (i >= LowerLimit && i <= UpperLimit) {
                arr.get(i).setColor(getColor(ColorIndex));
            }
            if (i == UpperLimit) {
                ColorIndex++;
                LowerLimit = UpperLimit;
                UpperLimit+=arr.size()/7;
            }
        }
        //System.out.println( "color " + color.size() );
        System.out.println( "arr " + arr.get(0).getA().getX() + " " + arr.get(arr.size()-1).getA().getX() );
    }

    private void drawArr(Graphics g){
        for (line i: arr){
            i.drawLine(g);
        }
    }
}