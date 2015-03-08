import javax.microedition.lcdui.*;
public class ContentAkg implements CommandListener
{
	
	Form form=null;
	Command ok=null;
	Command save=null;
	
			
	public ContentAkg()
	{
		form=new Form("Citizen Journalist");
		save=new Command("Save",Command.BACK,1);
		ok=new Command("Ok",Command.OK,1);
		display();
	}

	public void display()throws IllegalStateException
	{
		form.append(" Your Content has been");
		form.append("    sent successfully");
		form.append("		  Thank You");
		form.addCommand(save);
		form.setCommandListener(this);
		form.addCommand(ok);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==save && arg1==form)
		{
			Category c= new Category();
		}
		if(arg0==ok && arg1==form)
		{
			Category c= new Category();
			
		}
	}

}