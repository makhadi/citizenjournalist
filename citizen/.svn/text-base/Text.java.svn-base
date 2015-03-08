import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
public class Text implements CommandListener,Runnable
{
	
	Form form=null;
	Command sendOlny=null;
	Command sendAndSave=null;
	Command cancel=null;
	static TextField tf=null;
	String textDescript=null;
	
	
	public Text()
	{
		form=new Form("                                 Citizen Journalist");
		cancel=new Command("Cancel",Command.CANCEL,1);
		sendOlny=new Command("SendOnly",Command.OK,1);
		sendAndSave=new Command("Send&Save",Command.OK,1);
		tf=new TextField("Enter your Text Here:","",225,TextField.ANY);
		display();
	}

	public void display()throws IllegalStateException
	{
		form.append("Send Text Message"); 
		form.append(tf);
		form.addCommand(sendOlny);
		form.setCommandListener(this);
		form.addCommand(sendAndSave);
		form.setCommandListener(this);
		form.addCommand(cancel);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==cancel && arg1==form)
		{
			ContentType1 ct= new ContentType1();
		}
		else if(arg0==sendOlny && arg1==form)
		{
			Thread t=new Thread(this);
			t.start();
			//ContentAkg ta=new ContentAkg();
			
		}
		else if(arg0==sendAndSave && arg1==form)
		{
			//ContentAkg ta=new ContentAkg();
			
		}
	}
	public void run() {
		System.out.println("in text action");
		textDescript = Text.tf.getString();
		System.out.println("in text action");
		String userName = CitizenJournalist.userName;
		System.out.println("in text action");
		String contentName =ContentType1.contentType1;
		String category=Category.cg.getString(Category.cg.getSelectedIndex());
		Date uploadDate = new Date();
		uploadDate.getTime();
		// validate the data
		String url = CitizenJournalist.serverURL+"textAction.do?method=textDescript&textDescript="
				+ textDescript + "&contentName=" + contentName+ "&category="
				+ category +"&userName="+userName;
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
				Alert ar=new Alert("Text Uploaded succesfully");
				CitizenJournalist.disp.setCurrent(ar);
			} else if (response.trim().equals("failure")) {
				Alert al=new Alert("Sorry","Text UpLoading Failed",null,null);
				CitizenJournalist.disp.setCurrent(al);
			}

			

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	

}