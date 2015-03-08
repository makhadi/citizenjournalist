import java.io.*;
import java.util.Hashtable;

import javax.microedition.lcdui.*;
public class ViewVideo implements CommandListener
{
	
	List list=null;
	Command view=null;
	Command cancel=null;
	
	Hashtable table;
	
	public ViewVideo()
	{
		table = new Hashtable();
		list=new List("View Video", List.IMPLICIT);
		cancel=new Command("Cancel",Command.CANCEL,1);
		view=new Command("View",Command.OK,1);
		//display();
	}

	public void display(String response) 
	{
		int i = response.indexOf("$");
		
		while(i!=-1){
			String record = response.substring(0, i);
			
			int j = record.indexOf("*");
			String contentName = record.substring(0, j);
			record = record.substring(j+1);
			
			list.append(contentName, null);
			
			table.put(contentName, record);
			
			response = response.substring(i+1);
			i = response.indexOf("$");
		}
		
		list.addCommand(cancel);
		list.setCommandListener(this);
		list.addCommand(view);
		list.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(list);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==cancel && arg1==list)
		{
			ContentType ct=new ContentType();
		}
		else if(arg0==view && arg1==list)
		{
			String selContent = list.getString(list.getSelectedIndex());
			
			String record = table.get(selContent).toString();
			
			int i = record.indexOf("*");
			String date = record.substring(0, i);
			String path = record.substring(i+1);
			//String desc = record.substring(i+2);
			
			System.out.println("date:::"+date);
			System.out.println("path:::"+path);
			//System.out.println("desc:::"+desc);
			
			
			ViewingVideo vt=new ViewingVideo();
		}
	}

}