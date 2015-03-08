import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;
import javax.microedition.media.control.VideoControl;
public class ViewingVideo implements CommandListener, Runnable
{
	
	Form f=null;
	Command save=null;
	Command back=null;
	Command pause=null;
	Command play=null;
	Command stop=null;
	Player p=null;
	
	public ViewingVideo()
	{
		f=new Form("Viewing Video");
		back=new Command("Back",Command.BACK,1);
		save=new Command("Save",Command.OK,1);
		pause=new Command("Pause",Command.OK,1);
		play=new Command("Play",Command.OK,1);
		stop=new Command("Stop",Command.STOP,1);
	//	display();		
		Thread t = new Thread(this);
		t.start(); 
	}

	public void display() 
	{
		f.addCommand(stop);
		f.setCommandListener(this);
		f.addCommand(back);
		f.setCommandListener(this);
		f.addCommand(save);
		f.setCommandListener(this);
		f.addCommand(pause);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==f)
		{
			String response=ContentType.response;
			ViewVideo vt=new ViewVideo();
			vt.display(response);			
		}else if(arg0==stop && arg1==f)
		{
			String response=ContentType.response;
			ViewVideo vt=new ViewVideo();
			vt.display(response);			
		}
		else if(arg0==save && arg1==f)
		{
			Category c=new Category();
		}else if(arg0==pause && arg1==f)
		{
			f.removeCommand(pause);
			f.addCommand(play);
			f.setCommandListener(this);
			try {
				p.stop();
			} catch (MediaException e) {
				e.printStackTrace();
			}
			
		}else if(arg0==play && arg1==f)
		{
			f.removeCommand(play);
			f.addCommand(pause);
			f.setCommandListener(this);
			try {
				p.start();
			} catch (MediaException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		String url = CitizenJournalist.serverURL+"video/5.mp4";
		   //javax.microedition.lcdui.Canvas canvas = null;
		   try {
		       p = Manager.createPlayer(url);
		       p.realize();
		       GUIControl gc;
		       if ((gc = (GUIControl)p.getControl("GUIControl")) != null) {
		    	   
		           f.append((Item)gc.initDisplayMode(GUIControl.USE_GUI_PRIMITIVE, null));
		           display();
		           CitizenJournalist.disp.setCurrent(f);
		       }
		       p.start();
		   } catch (MediaException pe) {
		   } catch (IOException ioe) {
		   }
		 
		 
		 

	}

}