package red_envelope;

import java.io.Serializable;

public class RedLog implements Serializable{
	private String redId;       //红包编号
	private String founderMan;  //发起者
	private String Participants ;   //参与者
	private double get_Money;      //获得金额
	public String getRedId() {
		return redId;
	}
	public RedLog() {
		
	}
	@Override
	public String toString() {
		return "红包名：" + redId + ", 发起者： " + founderMan + ", 参与者： " + Participants
				+ ", 所抢金额=" + get_Money ;
	}
	public RedLog(String redId,String founderMan,String Participants,double get_Money) {
		this.redId=redId;
		this.founderMan=founderMan;
		this.Participants=Participants;
		this.get_Money=get_Money;
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
	public String getParticipants() {
		return Participants;
	}
	public void setParticipants(String participants) {
		Participants = participants;
	}
	public double getGet_Money() {
		return get_Money;
	}
	public void setGet_Money(double get_Money) {
		this.get_Money = get_Money;
	}

}

