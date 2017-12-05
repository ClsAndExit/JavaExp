package red_envelope;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class RedLogDao {
	public static List<RedLog> list = new ArrayList<RedLog>();

	public static List<RedLog> readAllRedLog() throws ClassNotFoundException {
		List<RedLog> lists = new ArrayList<RedLog>();
		if (list.size() != 0) {
			list = new ArrayList<RedLog>();
		}
		try {
			File file = new File("RedLog.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			else {
				if(file.length()!=0) {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

				try {
					try {
						RedLog temp = new RedLog();
						while (true) {
							temp = (RedLog) ois.readObject();
							list.add(temp);
							lists.add(temp);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (EOFException e) {
					System.out.println("获取完了");
				} finally {
					ois.close();
				}
			}
		}
		}
			catch (IOException e) {
			e.printStackTrace();
		}
		
		return lists;

	}

	public static boolean writeRedLog(List<RedLog> list2) throws ClassNotFoundException {
		try {
			if(list2.size()!=0)
		    list2.addAll(readAllRedLog());
			File file = new File("RedLog.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			for(RedLog red :list2)
			oos.writeObject(red);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;

	}
    //获取所有数据
	public List<RedLog> getAllRedLog()  {
		try {
			return readAllRedLog();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return  null;
		}
	}
	///添加
	public boolean Add(List<RedLog> lists) throws ClassNotFoundException {
		return writeRedLog(lists);
	}
	public List< RedLog> getRedLogById(String redId){
		List<RedLog> lists=new ArrayList<RedLog>();
		lists=getAllRedLog();
		Iterator it=lists.iterator();
		while(it.hasNext()) {
			RedLog redLog=(RedLog) it.next();
			if(!redLog.getRedId().equals(redId)){
				it.remove();
			}
		}
		return lists;
		}

}
