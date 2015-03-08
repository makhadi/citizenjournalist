import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.media.control.VideoControl;
public class image implements CommandListener,Runnable
{
	int flag=0;
	byte[] raw=null;
	Form form=null;
	Command capture=null;
	Command back=null;
	Command stop=null;
	Command send=null;
	Player player=null;
	RecordControl rc =null;
	int iLen=0;
	ByteArrayOutputStream output=null;
	VideoControl videoControl=null;
	Canvas canvas = null;
	public image()
	{
		form=new Form("Camera");
		capture=new Command("Capture",Command.OK,1);
		stop=new Command("Stop",Command.STOP,1);
		send=new Command("Send",Command.OK,1);
		back=new Command("cancel",Command.BACK,1);
	}
	
		public void showCamera() {

		try {
			player = Manager.createPlayer("capture://video");
			player.realize();
			videoControl = (VideoControl) player.getControl("VideoControl");
			canvas = new VideoCanvas(videoControl);
			canvas.addCommand(back);
			canvas.addCommand(capture);
			canvas.setCommandListener(this);
			CitizenJournalist.disp.setCurrent(canvas);
			player.start();
			} catch (IOException ioe) {
				} catch (MediaException me) {
					}
		}
		
		public void sendImage()
		{
			try {
				form.append("in sendImage");
				//form.append("In Try");
				HttpConnection connection =
					(HttpConnection) Connector.open(CitizenJournalist.serverURL+"imageAction.do");
				
				form.append("Connection Established");
				connection.setRequestProperty("User-Agent",
											  System.getProperty("microedition.profiles"));
				connection.setRequestProperty("Content-Type",
											  "application/octet-stream");
				connection.setRequestMethod(HttpConnection.POST);
				
				
				form.append("Opening Stream...");
				DataOutputStream outputStream = connection.openDataOutputStream();
				form.append("Opened Stream...");
				//outputStream.writeByte(1);
				//outputStream.writeUTF(contentType);  //attribute : contentType = player.getContentType();

				
				//byte[] bytes = output.toByteArray();

				iLen = raw.length;
				System.out.println(iLen);
//				form.append("Lenght is::::"+iLen);
				
				Alert alert = new Alert("sending image");
				alert.setString("iLen::::"+iLen);
				alert.setTimeout(3000);
				CitizenJournalist.disp.setCurrent(alert);

				form.append("writing to op stream...");
				outputStream.write(raw, 0, iLen);
				form.append("writing complete...");
				outputStream.flush();
				form.append("flushed");
				outputStream.close();
				form.append("closed...");

			} catch (Exception e) {
				System.out.println(iLen);
				form.append("#####"+e.getMessage());
			}

		}


	
	
	public void commandAction(Command arg0,Displayable arg1)
	{
		if(arg0==back )
		{
			if (player!=null) {
				player.close();
				player = null;
				rc = null;
			}
			CitizenJournalist.fj.notifyDestroyed();
		}else if(arg0==back && arg1==form)
		{
			
			CitizenJournalist.fj.notifyDestroyed();
		}
		else if(arg0==capture && arg1==canvas)
		{
			canvas.removeCommand(capture);
			canvas.removeCommand(back);
			form.addCommand(send);
			form.addCommand(back);
			form.setCommandListener(this);
			flag=1;
			Thread t = new Thread(this);
			t.start();
			
			//canvas.addCommand(stop);
		}else if(arg0==send && arg1==form)
		{
			/*form.append("clicked send");
			flag=2;
			Thread t = new Thread(this);
			t.start();*/
		}/*else if(arg0==stop && arg1==canvas){
			canvas.removeCommand(stop);
			
			try {
				rc.commit();
				player.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MediaException e) {
				e.printStackTrace();
			}

		    
		    player.close();
		    player = null;
			rc = null;
			iLen = bytes.length;   
			System.out.println(iLen);
			
			Alert alert = new Alert("closed");
			alert.setString("iLen::::"+iLen);
			alert.setTimeout(3000);
			TestingVideo.disp.setCurrent(alert);
		}*/
	}
	public void snapshot() {
			try {
				raw = videoControl.getSnapshot(null);
				Image image = Image.createImage(raw, 0, raw.length);
				form.append(image);
				CitizenJournalist.disp.setCurrent(form);
				player.close();
				player = null;
				//videoControl = null;
			} catch (MediaException me) {
			}
	}
	public void run() {
		if(flag==1)
			snapshot();
		else if(flag==2)
			sendImage();
		}
	}