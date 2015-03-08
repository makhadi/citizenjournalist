import javax.microedition.lcdui.*;
public class ContentAkg1 implements CommandListener,ItemCommandListener
{
	
	Form form=null;
	Command back=null;
	StringItem s1,s2;
	static Gauge g;
	Alert  a=null;
	
			
	public ContentAkg1()
	{
		form=new Form("                DOWNLOAD");
		s1=new StringItem("Are you sure to download the image?","    click to download the image");
		s2=new StringItem("Download",null,Item.HYPERLINK);
		s2.setLayout(Item.LAYOUT_CENTER|Item.LAYOUT_NEWLINE_BEFORE);
		s2.setDefaultCommand(new Command("Download",Command.ITEM,1));
		s2.setItemCommandListener(this);
		back=new Command("Ok",Command.BACK,1);
		display();
	}

	public void display()throws IllegalStateException
	{
		form.append(s1);
		form.append(s2);
		form.addCommand(back);
		form.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(a);
		CitizenJournalist.disp.setCurrent(form);
		
	}

	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==form)
		{
			ViewingImage c= new ViewingImage();
		}
	}
	public void commandAction(Command command,Item item)
	{
		if(item==s2)
		{
			g=new Gauge("please wait...",false,300,0);
			form.append(g);

			new Thread(new gaugereaction()).start();
		}
	}

	class gaugereaction implements Runnable
	{
		public void run()
		{
			try
			{
				while(ContentAkg1.g.getValue()<ContentAkg1.g.getMaxValue())
				{
						Thread.sleep(10);
						ContentAkg1.g.setValue(ContentAkg1.g.getValue()+1);
				}
			}
			catch(InterruptedException e)
			{
			}
			ContentAkg1.g.setLabel("Download Completed");
			ViewingImage ct=new ViewingImage();
		}
	}
}