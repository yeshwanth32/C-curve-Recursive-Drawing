import java.awt.*;
@SuppressWarnings("unused")
public class line implements Comparable{
    private Point a, b;
    private Color color;
    public line(){
        a = new Point(0,0);
        b = new Point(0,0);
    }
    public line(Point A, Point B, Color c){
        color = c;
        a = A;
        b = B;
    }
    public Point getA() {
        return a;
    }
    public void setA(Point a) {
        this.a = a;
    }
    public Point getB() {
        return b;
    }
    public void setB(Point b) {
        this.b = b;
    }
    public Color getColor(){ return color; }
    public void setColor(Color color){ this.color = color; }
    public void drawLine(Graphics g){
        g.setColor(color);
        g.drawLine(a.x, a.y, b.x, b.y);
    }
    public int compareTo(Object b){
        line x = (line)b;
        if (this.a.getX() < ((line) b).a.getX()){return -1;}
        if (this.a.getX() == ((line) b).a.getX()){return 0;}
        return +1;
    }
}
