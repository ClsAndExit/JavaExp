package red_envelope;


import java.io.Serializable;

public class Red_Packet implements Serializable {
	private String redId;       //红包编号
	private String founderMan;  //发起者
	private int sum;     //总人数
	private String time; //发送时间
	@Override
	public String toString() {
		return "发送时间:"+time+" ,红包号:" + redId + ", 发起者:" + founderMan + ", 总人数:" + sum + ", 总金额:" + all_Money
				+ ", 剩余金额:" + remain_Money + ", 剩余人数:" + remain_Man + ", 红包类型:平均红包"  ;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private double all_Money;   // 总金额
	private double remain_Money;  //剩余金额
	private double remain_Man ;   //剩余人数
	private int red_Type ;       //红包类型  1-均分红包  2-随机红包
	public Red_Packet(String redId,String founderMan,int sum,double all_Money,double remain_Money,double remain_Man,int red_Type) {
		this.redId=redId;
		this.founderMan=founderMan;
		this.sum=sum;
		this.all_Money=all_Money;
		this.remain_Money=remain_Money;
		this.remain_Man=remain_Man;
		this.red_Type=red_Type;
		
	}
	public Red_Packet() {
		
	}
	public String getRedId() {
		return redId;
	}
	public void setRedId(String redId) {
		this.redId = redId;
	}
	public String getFounderMan() {
		return founderMan;
	}
	public void setFounderMan(String founderMan) {
		this.founderMan = founderMan;
	}

	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getAll_Money() {
		return all_Money;
	}
	public void setAll_Money(double all_Money) {
		this.all_Money = all_Money;
	}
	public double getRemain_Money() {
		return remain_Money;
	}
	public void setRemain_Money(double remain_Money) {
		this.remain_Money = remain_Money;
	}
	public double getRemain_Man() {
		return remain_Man;
	}
	public void setRemain_Man(double remain_Man) {
		this.remain_Man = remain_Man;
	}
	public int getRed_Type() {
		return red_Type;
	}
	public void setRed_Type(int red_Type) {
		this.red_Type = red_Type;
	}

}
