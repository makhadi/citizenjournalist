import javax.microedition.lcdui.*;
public class Successful implements CommandListener
{
	Command ok=null;
	Form f=null;
	public Successful()
	{
		f=new Form("Successful");
		ok=new Command("Ok",Command.EXIT,1);
		display();
	}
	public void display()
	{
		f.append("                          You have successfully");
		f.append("                            Registered to CJ");
		f.append("                                                                                 ");
		f.append("                          Please Restart the");
		f.append("                              Application");
		f.addCommand(ok);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}
	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==ok && arg1==f)
		{
			CitizenJournalist.fj.notifyDestroyed();
			//Category c=new Category();
			
		}
	}
	
}