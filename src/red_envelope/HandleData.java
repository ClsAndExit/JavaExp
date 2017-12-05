package red_envelope;


import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.*;
public class HandleData {
	private Red_PacketDao redDao=new Red_PacketDao();
    private RedLogDao logDao=new RedLogDao();
    
    
    ///发红包
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
    //发红包html包装
    public StringBuilder sendRedPacketHtml(Map<String,String> map,String Address ) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("money")&&map.containsKey("num")) {
    		AnalysisData an_Old=new AnalysisData();
        	an_Old.setConveryParam(new String[] {map.get("money"),map.get("num")});
        	AnalysisData an_New=sendRedPacket(an_Old, Address);
        	int state=an_New.getRetrunParam();
        	
       	    str.append("<h1>发红包结果如下：</h1>");
        	if(state==200) { 
            	 str.append("<h3>发送成功！</h3>");
        	}
        	else {
        		str.append("<h3>发送失败！</h3>");
        	}
    	}
    	else {
    		str.append("<h3>解析失败！</h3>");
    	}
    	
    	return str;
    }
    
    
    ///抢红包
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
    			if(listRed.get(0).getRemain_Money()!=0) {      			                //抢到红包
        			newAn=updateData(an.getConveryParam()[0], Address); 
        		}
        		else {
        			newAn.setRetrunParam(202);             //202红包抢完了
            		newAn.setConveryParam(new String[]{"恭喜你，你来晚了！"});
        		}
    		}
    		catch(java.lang.IndexOutOfBoundsException e) {
    			newAn.setRetrunParam(201);             //201已经抢过红包
        		newAn.setConveryParam(new String[]{"你输错了！"});
    		}
    		
    	}
    	else {
    		newAn.setRetrunParam(201);             //201已经抢过红包
    		newAn.setConveryParam(new String[]{"你都抢过了，还抢呢！"});
    	}
    	return newAn;
    }
    ///抢红包html包装
    public StringBuilder getRedPacketHtml(Map<String,String> map,String Address) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("redId")) {
    		AnalysisData an_Old=new AnalysisData();
        	an_Old.setConveryParam(new String[] {map.get("redId")});
        	AnalysisData an_New=getRedPacket(an_Old,Address);
        	 
        	 str.append("<h1>抢红包金额结果如下：</h1>");
             for (String strs : an_New.getConveryParam()) {
    				str.append("<h3>"+strs+"</h3>");
    			}
    	}
    	else {
    		str.append("<h3>解析失败</h3>");
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
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		List<RedLog> listLog_New=new ArrayList<RedLog>();
		listLog_New.add(new RedLog(redId, listRed.get(0).getFounderMan(), Address, money));
		try {
			logDao.Add(listLog_New);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		newAn.setRetrunParam(203);                  // 203 抢到红包
    	newAn.setConveryParam(new String[] {Double.toString(money)});
    	return newAn;
		
    }
  //获取所有红包记录
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
    //返回这个所有红包的包装html
    public StringBuilder getAllRecordHtml(){
    	AnalysisData an=getAllRecord();
    	 StringBuilder str=new StringBuilder();
    	 str.append("<h1>所有红包结果如下：</h1>");
         for (String red : an.getConveryParam()) {
				str.append("<h3>"+red+"</h3>");
			}
         return str;
    }
   //获取关于某一红包的所有记录 
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
    //获取关于某一红包的所有记录 的html包装
    public StringBuilder getAllLogByRedIdHtml(Map<String,String> map) {
    	StringBuilder str=new StringBuilder();
    	if(map.containsKey("redId")) {
    		String redId=map.get("redId");
    		AnalysisData an=getAllLogByRedId(redId);
        	
        	
        	if(an.getRetrunParam()!=200) {
        		 str.append("<h1>无此红包号</h1>");
        	}
        	else {
        		 str.append("<h1>查询结果如下：</h1>");
        		 for (String redLog : an.getConveryParam()) {
     				str.append("<p>"+redLog+"</p>");
     			}
        	}
    	}
    	else {
    		 str.append("<h1>数据发送错误，能长的心吗</h1>");
    	}
    	
    	return str;
    }
    
}
