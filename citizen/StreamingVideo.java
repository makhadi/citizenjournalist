import java.io.*;
import javax.microedition.lcdui.*;
public class StreamingVideo implements CommandListener
{
	
	Form form=null;
	Command stop=null;
	Command cancel=null;
	
	public StreamingVideo()
	{
		form=new Form("                                 Streaming Video");
		stop=new Command("Stop",Command.STOP,1);
		cancel=new Command("Cancel",Command.CANCEL,1);
		display();
	}

	public void display() 
	{
		form.append("                         Video Is Streaming"); 
		form.addCommand(stop);
		form.setCommandListener(this);
		form.addCommand(cancel);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==stop && arg1==form)
		{
			ContentAkg ca=new ContentAkg();
		}
		else if(arg0==cancel && arg1==form)
		{
			//StreamVideo sv=new StreamVideo();
		}
	}

}