package red_envelope;

import java.io.Serializable;

public class RedLog implements Serializable{
	private String redId;       //������
	private String founderMan;  //������
	private String Participants ;   //������
	private double get_Money;      //��ý��
	public String getRedId() {
		return redId;
	}
	public RedLog() {
		
	}
	@Override
	public String toString() {
		return "�������" + redId + ", �����ߣ� " + founderMan + ", �����ߣ� " + Participants
				+ ", �������=" + get_Money ;
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

