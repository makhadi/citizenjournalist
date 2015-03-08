import java.io.*;
import java.util.Hashtable;

import javax.microedition.lcdui.*;
public class ViewText implements CommandListener
{
	
	List list=null;
	Command view=null;
	Command cancel=null;
	
	Hashtable table = new Hashtable();
	
	public ViewText()
	{
		list=new List("View Text", List.IMPLICIT);
		cancel=new Command("Cancel",Command.CANCEL,1);
		view=new Command("View",Command.OK,1);
	}

	public void display(String response) 
	{
		int i = response.indexOf("$");
		
		while(i!=-1){
			String record = response.substring(0, i);
			
			int j = record.indexOf("*");
			String contentName = record.substring(0, j);
			
			int k = record.indexOf("*", j+1);
			String date = record.substring(j+1, k);
			
			
			record = record.substring(k+1);
			table.put(contentName+":"+date, record);
			list.append(contentName+":"+date, null);
			
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
		}else if(arg0==view && arg1==list)
		{
			String selContent = list.getString(list.getSelectedIndex());
			String record = table.get(selContent).toString();
			System.out.println("from command view");			
			int j = record.indexOf("*");
			String path = record.substring(0, j);
			String desc = record.substring(j+1);
			
			System.out.println("path:::"+path);
			System.out.println("desc:::"+desc);
			
			ViewingText vt=new ViewingText();
			vt.display(desc);
		}
	}

}