
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;



public class ContentDescription  implements CommandListener,Runnable{
	
	Form form=null;
	Command send=null;
	Command cancel=null;

	String fileName=null;
	String fileDesc=null;
	static TextField name=null;
	static TextField desc=null;
	
	public ContentDescription()
	{
		form=new Form("content description");
		name= new TextField("Enter File name:","",20,TextField.ANY);
		desc=new TextField("Enter description:","",225,TextField.ANY);
		send=new Command("Send",Command.OK,1);
		cancel=new Command("Cancel",Command.BACK,1);
		display();
	}
	
	public void display()
	{
		form.append(name);
		form.append(desc);
		form.addCommand(send);
		form.setCommandListener(this);
		form.addCommand(cancel);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
	}
	
	public void commandAction(Command arg0, Displayable arg1)
	{
		if(arg0==cancel && arg1==form)
		{
			new ContentType1(); 
		}
		if(arg0==send && arg1==form)
		{
			Thread t=new Thread(this);
			t.start();
			
		}
		
	}
		
	public void run() {
		
		if(ContentType1.contentType1=="image"){
			image im=new image();
			im.sendImage();
			}else if(ContentType1.contentType1=="video")
			{
				new Thread(new Runnable(){
					public void run(){
						new UploadVideo().display();
					}
				}).start();
			}
		
		
		/*fileName=ContentDescription.name.getString();
		fileDesc=ContentDescription.desc.getString();
		String url=null;
		if(ContentType1.contentType1=="image")
		{
			url = CitizenJournalist.serverURL+"ImageAction.do?name="+fileName+"&desc="+fileDesc;
		}else if(ContentType1.contentType1=="video")
		{
			url = CitizenJournalist.serverURL+"VideoUploadAction.do?name="+fileName+"&desc="+fileDesc;
		}
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
				
				if(ContentType1.contentType1=="image"){
				image im=new image();
				im.sendImage();
				}else if(ContentType1.contentType1=="video")
				{
					new Thread(new Runnable(){
						public void run(){
							new UploadVideo().display();
						}
					}).start();
				}
			} else if (response.trim().equals("failure")) {
				Alert alert=new Alert("");
				alert.setString("sending failed");
				alert.setTimeout(3000);
				CitizenJournalist.disp.setCurrent(alert);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} */

	}

}
