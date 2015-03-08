import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;


public class CitizenJournalist extends MIDlet {

	public static Display disp=null;
	public static CitizenJournalist fj=null;
	public static  String userName = null;
	public static final String serverURL = "http://121.247.125.232:8080/CJ/";
	
	public CitizenJournalist() {
		fj=this;
		disp=Display.getDisplay(this);
		
	}
	protected void destroyApp(boolean b) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		
		RecordStore recordStore = null;
		try {
			recordStore = RecordStore.openRecordStore("cj", false);
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		if(recordStore==null){
			//Register r=new Register();
			Category category = new Category();
		}else{
			try {
				byte[] bt = recordStore.getRecord(1);
				userName = new String(bt);
				System.out.println(userName);
				Category category = new Category();
			} catch (RecordStoreNotOpenException e) {
				e.printStackTrace();
			} catch (InvalidRecordIDException e) {
				e.printStackTrace();
			} catch (RecordStoreException e) {
				e.printStackTrace();
			}
			
		}
						
	}

}
