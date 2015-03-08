import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;

public class ViewingImage implements CommandListener,Runnable
{

	Form f=null;
	Command save=null;
	Command back=null;
	
	
	public ViewingImage()
	{
		System.out.println("in viewing image");
		f=new Form("Viewing Image");
		back=new Command("Back",Command.BACK,1);
		save=new Command("Save",Command.OK,1);
		
	//	display();		
		Thread t = new Thread(this);
		t.start(); 
	}

	public void display() 
	{
		f.addCommand(back);
		f.setCommandListener(this);
		f.addCommand(save);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==f)
		{
			String response=ContentType.response;
			viewImage vt=new viewImage();
			vt.display(response);			
		}
		else if(arg0==save && arg1==f)
		{
			Category c=new Category();
		}
	}

	public void run() {
		System.out.println("in run methos");
		String url = CitizenJournalist.serverURL+"images/cj's.jpg";
		try {
			HttpConnection connection=(HttpConnection)Connector.open(url);
			System.out.println("connection created");
			DataInputStream dis=connection.openDataInputStream();
			System.out.println("stream created");

			Image image=Image.createImage(dis);
			System.out.println("image created");
			f.append(image);
			display();
		} catch (IOException e) {
			f.append("IO EXCEPTION....");
			e.printStackTrace();
		}catch(OutOfMemoryError oe)
		{
			f.append("Outof Memory exception...");
			oe.printStackTrace();
		}
		  
	}
}