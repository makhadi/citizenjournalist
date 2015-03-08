import java.io.IOException;

import javax.microedition.lcdui.*;
public class Category implements CommandListener,ItemCommandListener
{
	Form f=null;
	Command ok=null;
	Command exit=null;
	static ChoiceGroup cg=null;
	static ImageItem view=null;
	static ImageItem send=null;
	Image img1,img2;
	Ticker t=null;
	String s="Citizen Journalist - Thread of Humanity!";
	public Category()
	{
		f=new Form("Category");
		t=new Ticker(s);
		try {
			img1=Image.createImage("/ViewButton.JPG");
			img2=Image.createImage("/SendButton.JPG");
		} catch (IOException e) {
			e.printStackTrace();
		}
		view=new ImageItem("",img1,view.LAYOUT_CENTER,null,Item.BUTTON);
		//view.setAltText("view");
		send=new ImageItem("",img2,send.LAYOUT_CENTER,null,Item.BUTTON);
		//send.setAltText("send");
		ok=new Command("Ok",Command.OK,1);
		exit=new Command("Exit",Command.EXIT,1);
		cg=new ChoiceGroup("Select",ChoiceGroup.POPUP);
		display();
	}
	void display()
	{
		f.setTicker(t);
		f.append("                            Choose Your Category");
		f.append("                                                                                 ");
		cg.append("Crime",null);
		cg.append("Entertainment",null);	
		cg.append("News",null);
		cg.append("Political",null);
		cg.append("Sports",null);
		f.append(cg);
		f.append("                                                                                                          ");
		view.addCommand(ok);
		f.append(view);
		view.setItemCommandListener(this);
		f.append("                                                   ");
		send.addCommand(ok);
		f.append(send);
		send.setItemCommandListener(this);
		f.addCommand(exit);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}
	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==exit && arg1==f)
		{
			CitizenJournalist.fj.notifyDestroyed();
						
		}
	}
		public void commandAction(Command arg0,Item i)
		{
			if(arg0==ok && i==view)
			{
				ContentType ct=new ContentType();
			}
			if(arg0==ok && i==send)
			{
				ContentType1 ct=new ContentType1();
			}
		}

}