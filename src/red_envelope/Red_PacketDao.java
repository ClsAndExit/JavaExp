package red_envelope;
import java.io.*;
import java.util.*;

public class Red_PacketDao {
	public Red_PacketDao() {

	}

	public static List<Red_Packet> list = new ArrayList<Red_Packet>();

	///读取所有的红包数据
	public static List<Red_Packet> readAllRedPacket() throws ClassNotFoundException {
		List<Red_Packet> lists = new ArrayList<Red_Packet>();
		if (list.size() != 0) {
			list = new ArrayList<Red_Packet>();
		}
		try {
			File file = new File("RedPacket.txt");
			if (!file.exists()) {
				file.createNewFile();
			} else {
				if (file.length() != 0) {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					try {
						try {
							Red_Packet temp = new Red_Packet();
							while (true) {
								temp = (Red_Packet) ois.readObject();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lists;

	}

	
	//写入数据
	public static boolean writeRedPacket(List<Red_Packet> list2) throws ClassNotFoundException {
		try {
			if(list2.size()!=0)
			list2.addAll(readAllRedPacket());
			File file = new File("RedPacket.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			for (Red_Packet red : list2)
				oos.writeObject(red);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;

	}

	// 获取所有数据
	public List<Red_Packet> getAllRedPacket() {
		try {
			return readAllRedPacket();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/// 添加
	public boolean Add(List<Red_Packet> lists) throws ClassNotFoundException {
		return writeRedPacket(lists);
	}
	public List< Red_Packet> getRedById(String redId){
		List<Red_Packet> lists=new ArrayList<Red_Packet>();
		lists=getAllRedPacket();
		Iterator it=lists.iterator();
		while(it.hasNext()) {
			Red_Packet Red=(Red_Packet) it.next();
			if(!Red.getRedId().equals(redId)){
				it.remove();
			}
		}
		return lists;
		}
	public boolean UpdateRed(Red_Packet red) throws IOException {
		List<Red_Packet> lists=new ArrayList<Red_Packet>();
		lists=getAllRedPacket();
		Iterator it=lists.iterator();
		while(it.hasNext()) {
			Red_Packet Red=(Red_Packet) it.next();
			if(Red.getRedId().equals(red.getRedId())){
				it.remove();
			}
		}
		lists.add(red);
		File file = new File("RedPacket.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		for (Red_Packet reds : lists)
			oos.writeObject(reds);
		oos.close();
		return true;
	}

	

}
