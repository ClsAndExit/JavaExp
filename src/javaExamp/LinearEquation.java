package javaExamp;

import java.util.Scanner;

public class LinearEquation {  
    private double x1;  
    private double x2;  
    private double y1;  
    private double y2;  
    private double x;  
    private double y;  
    public LinearEquation(double x1, double y1, double x2, double y2) {  
        this.x1 = x1;  
        this.y1 = y1;  
        this.x2 = x2;  
        this.y2 = y2;  
    }  
  
    public double getK() {  
        double k;  
        k = (y1 - y2) / (x1 - x2);  
        return k;  
    }  
  
    public double getB() {  
        double b;  
        b = y1 - x1 * ((y1 - y2) / (x1 - x2));  
        return b;  
    }  
  
    public double getX(double k1, double b1, double k2, double b2) {  
        x = (b2 - b1) / (k1 - k2);  
        return x;  
    }  
  
    public double getY(double k1, double b1, double k2, double b2) {  
        y = ((b2 - b1) / (k1 - k2)) * k1 + b1;  
        return y;  
    }  
  
    public static void main(String[] args) {  
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0, x = 0, y = 0;  
        Scanner input = new Scanner(System.in);  
        System.out.println("请输入第1条线的两个坐标：");  
        x1 = input.nextDouble();  
        y1 = input.nextDouble();  
        x2 = input.nextDouble();  
        y2 = input.nextDouble();  
        System.out.println("请输入第2条线的两个坐标：");  
        x3 = input.nextDouble();  
        y3 = input.nextDouble();  
        x4 = input.nextDouble();  
        y4 = input.nextDouble();  
        input.close();
        LinearEquation lineE1 = new LinearEquation(x1, y1, x2, y2);  
        LinearEquation lineE2 = new LinearEquation(x3, y3, x4, y4);  
        x = lineE1.getX(lineE1.getK(), lineE1.getB(), lineE2.getK(), lineE2.getB());  
        y = lineE1.getY(lineE1.getK(), lineE1.getB(), lineE2.getK(), lineE2.getB());  
  
  
        if (lineE1.getK() == lineE2.getK()) {  
            System.out.println("两线段平行没有交点！！！");  
        } else if (y==lineE1.getK()*x+lineE1.getB()&&y==lineE2.getK()*x+lineE2.getB()) {  
                System.out.println("两线段的交点是：(" + x + "," + y + ")");  
            } else {  
                System.out.println("两线段不相交！！长度不够哦！");  
            }  
        }  
}  
