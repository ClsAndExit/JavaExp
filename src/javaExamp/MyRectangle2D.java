package javaExamp;
/**
 几何问题
定义MyRectangle2D类，包含：
	两个名为x和y的double型属性，表明矩形的中心点，这两个数据域都带有get和set方法（假设这个矩形的边与x轴和y轴平行）。
	带get和set方法的属性width和height。
	一个无参构造方法，该方法创建一个(x, y)值为(0, 0)且width和height为1的默认矩形。
	一个构造方法，创建带指定的x、y、width和height的矩形。
	方法getArea()返回矩形的面积。
	方法getPerimeter()返回矩形的周长。
	如果给定的点(x, y)在矩形内，那么方法contains(double x, double y)返回true。
	如果给定的矩形在这个矩形内，那么方法contains(MyRectangle2D r)返回true。
	如果给定的矩形和这个矩形重叠，那么方法overlaps(MyRectangle2D r)返回true。
画出该类的UML图。实现这个类。编写测试程序，创建一个MyRectangle2D对象r1（new MyRectangle2D(2, 2, 5.5, 4.9)），
	显示它的面积和周长，然后显示r1.contains(3,3)、r1.contains(new MyRectangle2D(4, 5, 10.5, 3.2))
	和r1.overlaps(new MyRectangle2D(3, 5, 2.3, 5.4))的结果。
*/

public class MyRectangle2D {
	double x;
	double y;
	double width;
	double height;
	
	public MyRectangle2D(){
		this.x=0;
		this.y=0;
		this.width=1;
		this.height=1;
	}
	public MyRectangle2D(double x,double y,double width,double height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	

	public double getArea(MyRectangle2D r) {
		double area=0.0;
		area=r.width*r.height;
		return area;
	}

	public double getPerimeter(MyRectangle2D r) {
		double perimeter=0.0;
		perimeter=2*(r.width+r.height);
		return perimeter;
	}
	public boolean contains(double x, double y){
		boolean flag = false;
		double top;//上
		double down;//下
		double left;//左
		double right;//右
		top = this.x+(this.height/2);
		down = this.x-(this.height/2);
		left = this.y-(this.width/2);
		right = this.y+(this.width/2);
		if(x>=left&&x<=right&&y>down&&y<=top){
			flag=true;
		}
		return flag;
	}
	public boolean contains(MyRectangle2D r){
		//算矩形的四角，看是否都在原矩形裡面
		return true;
	}
	public boolean overlaps(MyRectangle2D r){
		//矩形的四角有的在原矩形裡面，有的在外面
		return true;
	}
	public static void main(String [] args){
		//
		MyRectangle2D r= new MyRectangle2D();
		System.out.println(r.getArea(r));
		System.out.println(r.getPerimeter(r));
		//
		MyRectangle2D r1= new MyRectangle2D(0,0,2,2);
		System.out.println(r1.getArea(r1));
		System.out.println(r1.getPerimeter(r1));
	}
}

