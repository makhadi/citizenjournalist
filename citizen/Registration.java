import javax.microedition.lcdui.*;
public class Registration implements CommandListener
{
	
	Form form=null;
	Command next=null;
	Command cancel=null;
	static TextField userName=null;
	static TextField mobileNumber=null;
	static TextField emailId=null;
	static TextField address=null;
	static ChoiceGroup cg=null;
			
	public Registration()
	{
		form=new Form("WelCome To Citizen Journalist!");
		cancel=new Command("Cancel",Command.CANCEL,1);
		next=new Command("Next",Command.OK,1);
		userName=new TextField("UserName","",20,TextField.ANY);
		mobileNumber=new TextField("MobileNumber","",20,TextField.PHONENUMBER);
		cg=new ChoiceGroup("Gender",1);
		emailId=new TextField("Email-ID","",20,TextField.EMAILADDR);
		address=new TextField("Address","",100,TextField.ANY);
		display();
	}

	public void display()throws IllegalStateException
	{
		form.append("User Details");
		form.append(userName);
		form.append(mobileNumber);
		cg.append("Male",null);
		cg.append("Female",null);
		form.append(cg);
		form.append(emailId);
		form.append(address);
		form.addCommand(cancel);
		form.setCommandListener(this);
		form.addCommand(next);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(form);
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==cancel && arg1==form)
		{
			Register r= new Register();
		}
		if(arg0==next && arg1==form)
		{
			if (userName.getString().trim().equals("") || mobileNumber.getString().trim().equals("")) {
				Alert a=new Alert("Alert","User Name or Mobile Nunber cannot be empty",null,null);
				CitizenJournalist.disp.setCurrent(a);
				}else{
				Configure c=new Configure();
			}
			
		}
	}

}