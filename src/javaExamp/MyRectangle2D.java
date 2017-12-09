package javaExamp;

import java.lang.reflect.Array;

/**
 * 几何问题 定义MyRectangle2D类，包含： 
 * 两个名为x和y的double型属性，表明矩形的中心点，这两个数据域都带有get和set方法（假设这个矩形的边与x轴和y轴平行）。 
 * 带get和set方法的属性width和height。  一个无参构造方法，该方法创建一个(x, y)值为(0,
 * 0)且width和height为1的默认矩形。  一个构造方法，创建带指定的x、y、width和height的矩形。 
 * 方法getArea()返回矩形的面积。  方法getPerimeter()返回矩形的周长。  如果给定的点(x,
 * y)在矩形内，那么方法contains(double x, double y)返回true。 
 * 如果给定的矩形在这个矩形内，那么方法contains(MyRectangle2D r)返回true。 
 * 如果给定的矩形和这个矩形重叠，那么方法overlaps(MyRectangle2D r)返回true。
 * 画出该类的UML图。实现这个类。编写测试程序，创建一个MyRectangle2D对象r1（new MyRectangle2D(2, 2, 5.5,
 * 4.9)）， 显示它的面积和周长，然后显示r1.contains(3,3)、r1.contains(new MyRectangle2D(4, 5,
 * 10.5, 3.2)) 和r1.overlaps(new MyRectangle2D(3, 5, 2.3, 5.4))的结果。
 */

public class MyRectangle2D {
	double x;
	double y;
	double width;
	double height;

	public MyRectangle2D() {
		this.x = 0;
		this.y = 0;
		this.width = 1;
		this.height = 1;
	}

	public MyRectangle2D(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		double area = 0.0;
		area = r.width * r.height;
		return area;
	}

	public double getPerimeter(MyRectangle2D r) {
		double perimeter = 0.0;
		perimeter = 2 * (r.width + r.height);
		return perimeter;
	}

	public boolean contains(double x, double y) {
		boolean flag = false;
		double top;// 上
		double down;// 下
		double left;// 左
		double right;// 右
		top = this.x + (this.height / 2);
		down = this.x - (this.height / 2);
		left = this.y - (this.width / 2);
		right = this.y + (this.width / 2);
		if (x >= left && x <= right && y > down && y <= top) {
			flag = true;
		}
		return flag;
	}

	// 计算矩形各个顶点的坐标
	public double[] vertex(MyRectangle2D r) {
		double[] v = new double[8];
		// 一次按照左上、右上、右下、左下的顺序顺序存储X，Y的坐标值
		double top;// 上
		double down;// 下
		double left;// 左
		double right;// 右
		top = r.x + (r.height / 2);
		down = r.x - (r.height / 2);
		left = r.y - (r.width / 2);
		right = r.y + (r.width / 2);

		v[0] = left;
		v[1] = top;
		v[2] = right;
		v[3] = top;
		v[4] = right;
		v[5] = down;
		v[6] = left;
		v[7] = down;

		return v;
	}

	public boolean contains(MyRectangle2D r) {
		boolean flag = false;
		double[] v = vertex(r);
		// 算矩形的四角，看是否都在原矩形裡面
		if (contains(v[0], v[1]) && contains(v[2], v[3]) && contains(v[4], v[5]) && contains(v[6], v[7])) {
			flag = true;
		}
		return flag;
	}

	public boolean overlaps(MyRectangle2D r) {
		// 矩形的四角有的在原矩形裡面，有的在外面
		boolean flag = false;
		double[] v = vertex(r);
		// 判断矩形的四角，看是否存在点在原矩形裡面
		if (contains(v[0], v[1]) || contains(v[2], v[3]) || contains(v[4], v[5]) || contains(v[6], v[7])) {
			// 判断矩形的四角，看是否存在点不在原矩形裡面
			if (!contains(v[0], v[1]) || !contains(v[2], v[3]) || !contains(v[4], v[5]) || !contains(v[6], v[7])) {
				flag = true;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		// 默认的矩形
		MyRectangle2D r = new MyRectangle2D();
		System.out.println("矩形(0,0,1,1)的面积：" + r.getArea(r));
		System.out.println("矩形(0,0,1,1)的周长：" + r.getPerimeter(r));
		// 自定义的矩形
		MyRectangle2D r1 = new MyRectangle2D(0, 0, 2, 2);
		System.out.println("矩形(0,0,2,2)的面积：" + r1.getArea(r1));
		System.out.println("矩形(0,0,2,2)的周长：" + r1.getPerimeter(r1));

		if (r1.contains(0, 0)) {
			System.out.println("点（0，0）在矩形内部！");
		} else {
			System.out.println("点（0，0）不在矩形内部！");
		}
		if (r1.contains(r)) {
			System.out.println("矩形r在矩形r1的内部");
		} else {
			System.out.println("矩形r不在矩形r1的内部");
		}
		if (r1.overlaps(r)) {
			System.out.println("矩形r和矩形r1重叠");
		} else {
			System.out.println("矩形r和矩形r1不重叠");
		}
	}
}
