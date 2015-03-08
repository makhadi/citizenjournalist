import javax.microedition.lcdui.*;
public class Configure implements CommandListener
{
	Command back=null;
	Command submit=null;
	Form f=null;
	static ChoiceGroup cg=null;
	public Configure()
	{
		f=new Form("Configure");
		back=new Command("Back",Command.BACK,1);
		submit=new Command("Submit",Command.OK,1);
		cg=new ChoiceGroup("",2);
		display();
	}
	public void display()
	{
		f.append("Send me alerts,if a new Content is added");
		cg.append("Crime",null);
		cg.append("Entertainment",null);
		cg.append("News",null);
		cg.append("Political",null);
		cg.append("Sports",null);		
		f.append(cg);
		f.addCommand(back);
		f.setCommandListener(this);
		f.addCommand(submit);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}
	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==f)
		{
			Registration r=new Registration();
		}
		if(arg0==submit && arg1==f)
		{
			RegisterConfirm rc=new RegisterConfirm();
		}
	}
	
}