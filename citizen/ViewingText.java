import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
public class ViewingText implements CommandListener,Runnable
{
	
	Form form=null;
	Command save=null;
	Command back=null;
	String contentType=null;
	
	public ViewingText()
	{
		form=new Form("                                 Viewing Text");
		back=new Command("Back",Command.BACK,1);
		save=new Command("Save",Command.OK,1);
		//display();
	}

	public void display(String desc) 
	{
		form.append(desc);
		form.addCommand(back);
		form.setCommandListener(this);
		form.addCommand(save);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==form)
		{
		
				contentType = ContentType.text.getAltText();
				Thread t=new Thread(this);
				t.start();
			//ViewText vt=new ViewText();
		}
		else if(arg0==save && arg1==form)
		{
			Category c=new Category();
		}
	}
	
	public void run() {
		System.out.println(contentType);
		String categoryName=Category.cg.getString(Category.cg.getSelectedIndex());
		System.out.println(categoryName);
		String url = CitizenJournalist.serverURL+"contentView.do?method=contentView&category="+categoryName+"&contentType="+contentType;
		HttpConnection connection;
		try {
			url = url.replace(' ', '+');
			System.out.println("url:::"+url);
			connection = (HttpConnection) Connector.open(url);
			InputStream inputStream = connection.openDataInputStream();

			int i = 0;
			String response = "";
			while ((i = inputStream.read()) != -1) {
				response += (char) i;
			}
			
			if(contentType.equals("Text")){
				showText(response);
			}
		}catch(Exception e){
				Alert alert = new Alert("Exception");
				alert.setString("msg::::"+e.getMessage());
				alert.setTimeout(5000);
				CitizenJournalist.disp.setCurrent(alert);
			}
	}
	public void showText(String response){
		ViewText vt=new ViewText();
		vt.display(response);
		
		
	}
}