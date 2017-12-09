package red_envelope;


import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.*;
public class HandleData {
	private Red_PacketDao redDao=new Red_PacketDao();
    private RedLogDao logDao=new RedLogDao();
    
    
    ///�����
    public AnalysisData sendRedPacket(AnalysisData an,String Address ) {
    	try {
    		Red_Packet reds=new Red_Packet();
    		reds.setFounderMan(Address);
    		Calendar ca=Calendar.getInstance();
    		List<Red_Packet> lists=redDao.getAllRedPacket();
    		String redId;
    		
    		if(lists.size()!=0) {
    			redId=lists.get(0).getRedId();
    			for(Red_Packet red: lists) {
    			     if( Integer.parseInt(red.getRedId())>(Integer.parseInt(redId))) {
    			    	 redId=red.getRedId();
    			     }
    			}
    			redId=String.valueOf(Integer.parseInt(redId)+1);
    		}
    		else {
    			redId="1";
    		}
    		// redId=Address+ca.get(Calendar.DATE)+ca.get(Calendar.HOUR)+ca.get(Calendar.MARCH)+ca.get(Calendar.SECOND);
    		Date date=new Date();
    		DateFormat me=DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
    		reds.setTime(me.format(date));
    		reds.setRedId(redId);
    		reds.setAll_Money(Double.parseDouble( an.getConveryParam()[0]));
    		reds.setSum(Integer.parseInt(an.getConveryParam()[1]));
    		reds.setRed_Type(1);
    		reds.setRemain_Man(reds.getSum());
    		reds.setRemain_Money(reds.getAll_Money());
    		List<Red_Packet> list=new ArrayList<Red_Packet>();
    		list.add(reds);
    		redDao.Add(list);
    	}
    	catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	catch(java.lang.NumberFormatException e) {
    		AnalysisData newAn=new AnalysisData();
        	newAn.setRetrunParam(404);   	            //200 -ok
        	return newAn;
    	}
    	AnalysisData newAn=new AnalysisData();
    	newAn.setRetrunParam(200);   	            //200 -ok
    	return newAn;
    }
    //�����html��װ
    public StringBuilder sendRedPacketHtml(Map<String,String> map,String Address ) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("money")&&map.containsKey("num")) {
    		AnalysisData an_Old=new AnalysisData();
        	an_Old.setConveryParam(new String[] {map.get("money"),map.get("num")});
        	AnalysisData an_New=sendRedPacket(an_Old, Address);
        	int state=an_New.getRetrunParam();
        	
       	    str.append("<h1>�����������£�</h1>");
        	if(state==200) { 
            	 str.append("<h3>���ͳɹ���</h3>");
        	}
        	else {
        		str.append("<h3>����ʧ�ܣ�</h3>");
        	}
    	}
    	else {
    		str.append("<h3>����ʧ�ܣ�</h3>");
    	}
    	
    	return str;
    }
    
    
    ///�����
    public AnalysisData getRedPacket(AnalysisData an,String Address) {
    	List<RedLog> listLog=new ArrayList<RedLog>();
    	List<Red_Packet> listRed=new ArrayList<Red_Packet>();
    	listRed=redDao.getRedById(an.getConveryParam()[0]);
    	AnalysisData newAn=new AnalysisData();
    	listLog=logDao.getAllRedLog();
    	boolean flag=true;
    	for(RedLog log: listLog) {
    		if(log.getParticipants().equals(Address) &&log.getRedId().equals(an.getConveryParam()[0])) {
    			flag=false;
    			break;
    		}
    	}
    	if(flag) {
    		try {
    			if(listRed.get(0).getRemain_Money()!=0) {      			                //�������
        			newAn=updateData(an.getConveryParam()[0], Address); 
        		}
        		else {
        			newAn.setRetrunParam(202);             //202���������
            		newAn.setConveryParam(new String[]{"��ϲ�㣬�������ˣ�"});
        		}
    		}
    		catch(java.lang.IndexOutOfBoundsException e) {
    			newAn.setRetrunParam(201);             //201�Ѿ��������
        		newAn.setConveryParam(new String[]{"������ˣ�"});
    		}
    		
    	}
    	else {
    		newAn.setRetrunParam(201);             //201�Ѿ��������
    		newAn.setConveryParam(new String[]{"�㶼�����ˣ������أ�"});
    	}
    	return newAn;
    }
    ///�����html��װ
    public StringBuilder getRedPacketHtml(Map<String,String> map,String Address) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("redId")) {
    		AnalysisData an_Old=new AnalysisData();
        	an_Old.setConveryParam(new String[] {map.get("redId")});
        	AnalysisData an_New=getRedPacket(an_Old,Address);
        	 
        	 str.append("<h1>�������������£�</h1>");
             for (String strs : an_New.getConveryParam()) {
    				str.append("<h3>"+strs+"</h3>");
    			}
    	}
    	else {
    		str.append("<h3>����ʧ��</h3>");
    	}
    	
         return str;
    }
    public AnalysisData updateData(String redId,String Address) {
    	AnalysisData newAn=new AnalysisData();
		List<Red_Packet> listRed=redDao.getRedById(redId);
		double money=listRed.get(0).getAll_Money()/listRed.get(0).getSum();
		listRed.get(0).setRemain_Man(listRed.get(0).getRemain_Man()-1);
		listRed.get(0).setRemain_Money(listRed.get(0).getRemain_Money()-money);
		try {
			redDao.UpdateRed(listRed.get(0));
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		List<RedLog> listLog_New=new ArrayList<RedLog>();
		listLog_New.add(new RedLog(redId, listRed.get(0).getFounderMan(), Address, money));
		try {
			logDao.Add(listLog_New);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		newAn.setRetrunParam(203);                  // 203 �������
    	newAn.setConveryParam(new String[] {Double.toString(money)});
    	return newAn;
		
    }
  //��ȡ���к����¼
    public AnalysisData getAllRecord() {
    	AnalysisData an=new AnalysisData();
    	
        List<Red_Packet> list=redDao.getAllRedPacket(); 
        String [] str=new String[list.size()];
        for(int i=0;i<list.size();i++) {
        	str[i]=list.get(i).toString();
        }
        an.setConveryParam(str);
    	an.setRetrunParam(200);  
    	return an;
    }
    //����������к���İ�װhtml
    public StringBuilder getAllRecordHtml(){
    	AnalysisData an=getAllRecord();
    	 StringBuilder str=new StringBuilder();
    	 str.append("<h1>���к��������£�</h1>");
         for (String red : an.getConveryParam()) {
				str.append("<h3>"+red+"</h3>");
			}
         return str;
    }
   //��ȡ����ĳһ��������м�¼ 
    public AnalysisData getAllLogByRedId(String redId){
    	List<RedLog> list=new ArrayList<RedLog>();
    	AnalysisData an=new AnalysisData();
    	list=logDao.getAllRedLog();
    	Iterator it=list.iterator();
		while(it.hasNext()) {
			RedLog redLog=(RedLog) it.next();
			if(!redLog.getRedId().equals(redId)){
				it.remove();
			}
		}
		if(list.size()!=0) {
			String[] str=new String[list.size()];
			for(int i=0;i<list.size();i++) {
				str[i]=list.get(i).toString();
			}
			an.setConveryParam(str);
			an.setRetrunParam(200);  
			return an;
		}
		else {
			an.setRetrunParam(404);
			return an;
		}
    }
    //��ȡ����ĳһ��������м�¼ ��html��װ
    public StringBuilder getAllLogByRedIdHtml(Map<String,String> map) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("redId")) {
    		String redId=map.get("redId");
    		AnalysisData an=getAllLogByRedId(redId);
        	
        	
        	if(an.getRetrunParam()!=200) {
        		 str.append("<h1>�޴˺����</h1>");
        	}
        	else {
        		 str.append("<h1>��ѯ������£�</h1>");
        		 for (String redLog : an.getConveryParam()) {
     				str.append("<p>"+redLog+"</p>");
     			}
        	}
    	}
    	else {
    		 str.append("<h1>���ݷ��ʹ����ܳ�������</h1>");
    	}
    	
    	return str;
    }
    
}
