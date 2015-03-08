import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
public class ContentType1 implements CommandListener,ItemCommandListener
{
	Form f=null;
	Command ok=null;
	Command back=null;
	static ImageItem text=null;
	static ImageItem image=null;
	static ImageItem video=null;
	static String response = "";
	static String contentType1;
	Image img,img1,img2;
	
	public ContentType1()
	{
		f=new Form("ContentType");
		try {
			img=Image.createImage("/TextButton.JPG");
			img1=Image.createImage("/ImageButton.JPG");
			img2=Image.createImage("/VideoButton.JPG");
		} catch (IOException e) {
			e.printStackTrace();
		}
		text=new ImageItem("",img,text.LAYOUT_CENTER,null,Item.BUTTON);
		text.setAltText("text");
		image=new ImageItem("",img1,image.LAYOUT_CENTER,null,Item.BUTTON);
		image.setAltText("image");
		video=new ImageItem("",img2,video.LAYOUT_CENTER,null,Item.BUTTON);
		video.setAltText("video");
		ok=new Command("Ok",Command.OK,1);
		back=new Command("Back",Command.BACK,1);
		display();
	}
	void display()
	{
		f.append("Content Type");
		text.addCommand(ok);
		f.append(text);
		text.setItemCommandListener(this);
		image.addCommand(ok);
		f.append(image);
		image.setItemCommandListener(this);
		video.addCommand(ok);
		f.append(video);
		video.setItemCommandListener(this);
		f.addCommand(back);
		f.setCommandListener(this);
		CitizenJournalist.disp.setCurrent(f);
	}
	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back && arg1==f)
		{
			Category c=new Category();						
		}
	}
		public void commandAction(Command arg0,Item i)
		{
			if(arg0==ok && i==text)
			{
				contentType1 =text.getAltText();
				//Thread t = new Thread(this);
				//t.start();
				Text t=new Text();
			}
			else if(arg0==ok && i==image)
			{
				contentType1 =image.getAltText();
				image t=new image();
				t.showCamera();
			}
			else if(arg0==ok && i==video)
			{
				//browse b=new browse();
				//System.out.println("clicked video");
				//contentType1 =video.getAltText();
				//ContentDescription cd=new ContentDescription();
				StreamVideo sv =new StreamVideo();
				sv.showCamera();
				/*new Thread(new Runnable(){
					public void run(){
						new UploadVideo().display();
					}
				}).start();*/
				
				
			}
		}
		
			/*public void run() {
				System.out.println("in text action");
				textDescript = Text.tf.getString();
				System.out.println("in text action");
				String contentName =contentType1;
				String category=Category.cg.getString(Category.cg.getSelectedIndex());
				Date uploadDate = new Date();
				uploadDate.getTime();
				// validate the data
				String url = CitizenJournalist.serverURL+"textAction.do?method=textDescript&textDescript="
						+ textDescript + "&contentName=" + contentName+ "&category="
						+ category + "&uploadDate=" + uploadDate;
				HttpConnection connection;
				try {
					url = url.replace(' ', '+');
					connection = (HttpConnection) Connector.open(url);
					InputStream inputStream = connection.openDataInputStream();

					int i = 0;
					String response = "";
					while ((i = inputStream.read()) != -1) {
						response += (char) i;
					}

					if (response.trim().equals("success")) {
						Alert ar=new Alert("Text Uploaded succesfully");
						CitizenJournalist.disp.setCurrent(ar);
					} else if (response.trim().equals("failure")) {
						Alert al=new Alert("Sorry","Text UpLoading Failed",null,null);
						CitizenJournalist.disp.setCurrent(al);
					}

					

				} catch (IOException e) {
					e.printStackTrace();
				} 

			}*/

}