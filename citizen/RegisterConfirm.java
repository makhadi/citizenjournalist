import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class RegisterConfirm implements CommandListener, Runnable {
	Command no = null;

	Command yes = null;

	Form f = null;

	public RegisterConfirm() {
		f = new Form("Registration Confirmation");
		no = new Command("No", Command.EXIT, 1);
		yes = new Command("Yes", Command.OK, 1);
		display();
	}

	public void display() {
		f.append("	Would you like to ");
		f.append("	Submit the details?");
		f.addCommand(no);
		f.setCommandListener(this);
		f.addCommand(yes);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == no && arg1 == f) {
			CitizenJournalist.fj.notifyDestroyed();
		}
		if (arg0 == yes && arg1 == f) {
			Thread t = new Thread(this);
			t.start();
			
		}
	}

	public void run() {
		int a = 0;
		boolean[] b = new boolean[5];
		a = Configure.cg.getSelectedFlags(b);
		String configure = "";
		String userName = Registration.userName.getString();
		String mobileNo = Registration.mobileNumber.getString();
		String address = Registration.address.getString();
		String gender = Registration.cg.getString(Registration.cg
				.getSelectedIndex());
		String emailId = Registration.emailId.getString();

		System.out.println("address:::" + address);
		// validate the data
		for (int n = 0; n < b.length; n++) {
			if (b[n])
				configure = configure.concat(Configure.cg.getString(n) + ",");
		}

		System.out.println(configure);
		String url = "http://localhost:8080/CJ/user.do?method=register&gender="
				+ gender + "&mobileNumber=" + mobileNo + "&userName="
				+ userName + "&address=" + address + "&emailId=" + emailId+"&categoryList=" + configure;
		HttpConnection connection;
		try {
			url = url.replace(' ', '+');
			connection = (HttpConnection) Connector.open(url);
			InputStream inputStream = connection.openDataInputStream();

			int i = 0;
			String response = "";
			while ((i = inputStream.read()) != -1) {
				response += (char) i;
			}

			if (response.trim().equals("success")) {
				Successful s = new Successful();
				RecordStore recordStore = RecordStore.openRecordStore("cj", true);
				if(recordStore!=null){
					byte[] bt = userName.getBytes();
					recordStore.addRecord(bt, 0, bt.length);
					recordStore.closeRecordStore();
				}
			} else if (response.trim().equals("failure")) {
				//alert
				Alert al=new Alert("Sorry","Registration failed please register again",null,null);
			}

			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}

	}

}