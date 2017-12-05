package red_envelope;


import java.io.Serializable;

public class Red_Packet implements Serializable {
	private String redId;       //������
	private String founderMan;  //������
	private int sum;     //������
	private String time; //����ʱ��
	@Override
	public String toString() {
		return "����ʱ��:"+time+" ,�����:" + redId + ", ������:" + founderMan + ", ������:" + sum + ", �ܽ��:" + all_Money
				+ ", ʣ����:" + remain_Money + ", ʣ������:" + remain_Man + ", �������:ƽ�����"  ;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private double all_Money;   // �ܽ��
	private double remain_Money;  //ʣ����
	private double remain_Man ;   //ʣ������
	private int red_Type ;       //�������  1-���ֺ��  2-������
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
