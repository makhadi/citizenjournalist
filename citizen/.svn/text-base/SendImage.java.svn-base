import java.io.*;
import javax.microedition.lcdui.*;
public class SendImage implements CommandListener
{
	
	Form form=null;
	Command send=null;
	Command cancel=null;
	Image image=null;

	public SendImage()
	{
		form=new Form("                                 Send Image");
		cancel=new Command("Cancel",Command.CANCEL,1);
		send=new Command("send",Command.OK,1);
		try
		{
			image=Image.createImage("/img.jpg");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		display();
	}

	public void display() 
	{
		form.append("                         Image Captured"); 
		form.append(image);
		form.addCommand(cancel);
		form.setCommandListener(this);
		form.addCommand(send);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==cancel && arg1==form)
		{
			image i=new image();
		}
		else if(arg0==send && arg1==form)
		{
			ContentAkg ia=new ContentAkg();
		}
	}

}