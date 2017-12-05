package red_envelope;


import java.io.Serializable;
import java.util.*;

public class AnalysisData implements Serializable {
	private String[] converyParam;          
	private String handle;
	private int retrunParam;
	public String[] getConveryParam() {
		return converyParam;
	}
	public void setConveryParam(String[] converyParam) {
		this.converyParam = converyParam;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public int getRetrunParam() {
		return retrunParam;
	}
	public void setRetrunParam(int retrunParam) {
		this.retrunParam = retrunParam;
	}
	
}
