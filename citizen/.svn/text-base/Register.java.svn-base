import javax.microedition.lcdui.*;
class Register implements CommandListener
{
	Form f=null;
	Command ok=null;
	Command exit=null;
	Register()
	{
		f=new Form("Citizen Journalist");
		ok=new Command("OK",Command.OK,1);
		exit=new Command("Exit",Command.EXIT,1);
		display();
	}

	public void display()
	{
		f.append("\n\n");
		f.append("Please Register Yourself To CitizenJournalist");
		f.addCommand(ok);
		f.setCommandListener(this);
		f.addCommand(exit);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==ok && arg1==f)
		{
			Registration r=new Registration();
		}
		if(arg0==exit && arg1==f){
			CitizenJournalist.fj.notifyDestroyed();
		}
	}
	
}