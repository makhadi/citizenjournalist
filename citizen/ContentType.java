import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
public class ContentType implements CommandListener,ItemCommandListener,Runnable
{
	Form f=null;
	Command ok=null;
	Command back=null;
	static ImageItem text=null;
	static ImageItem image=null;
	static ImageItem video=null;
	static String response = "";
	String contentType;
	Image img,img1,img2;
	
	public ContentType()
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
				contentType = ContentType.text.getAltText();
				System.out.println("it shud print text::::::::and it is printing"+contentType);
				Thread t=new Thread(this);
				t.start();
			}
			else if(arg0==ok && i==image)
			{
				contentType = ContentType.image.getAltText();
				Thread t=new Thread(this);
				t.start();

				//ViewingImage vi=new ViewingImage();
			}
			else if(arg0==ok && i==video)
			{
				contentType = ContentType.video.getAltText();
				Thread t=new Thread(this);
				t.start();

				//ViewingVideo vv=new ViewingVideo();
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
				while ((i = inputStream.read()) != -1) {
					response += (char) i;
				}
				
				if(contentType.equals("text")){
					showText(response);
				}else if(contentType.equals("image")){
					showImage(response);
					
				}else if(contentType.equals("video")){
					showVideo(response);					
				}
			}catch(Exception e){
					Alert alert = new Alert("Exception");
					alert.setString("msg::::"+e.getMessage());
					alert.setTimeout(5000);
					CitizenJournalist.disp.setCurrent(alert);
				}

		}
		
		
		
		public void showText(String response){
			System.out.println("in show text");
			ViewText vt=new ViewText();
			vt.display(response);
			
			
		}
		public void showImage(String response){
			viewImage vt=new viewImage();
			vt.display(response);
		}
		public void showVideo(String response){
			ViewVideo vt=new ViewVideo();
			vt.display(response);
		}

}