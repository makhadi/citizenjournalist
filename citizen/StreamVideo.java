import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;
import javax.microedition.media.control.RecordControl;
import javax.microedition.media.control.VideoControl;
public class StreamVideo implements CommandListener, Runnable
{
	int flag=0;
	String length="";

	int iLen=0;
	byte[] bytes;
	
	Form form=null;
	Command cancel=null;
	Command start=null;
	Command back=null;
	Command stop=null;
	Command ok=null;
	Player player=null;
	Command sendOnly=null;
	Command sendAndSave=null;
	
	//Player p = null;
	RecordControl rc =null;
	 ByteArrayOutputStream output=null;
	 VideoControl videoControl=null;
	 Canvas canvas = null;
	String contentType = null;
	public StreamVideo()
	{
		form=new Form("Stream Video");
		start=new Command("Start",Command.OK,1);
		stop=new Command("Stop",Command.STOP,1);
		back=new Command("cancel",Command.BACK,1);
		sendOnly=new Command("sendOnly",Command.OK,1);
		sendAndSave=new Command("sendAndSave",Command.OK,1);
		cancel=new Command("Cancel",Command.CANCEL,1);
		ok=new Command("Ok",Command.OK,1);
		display();
	}

	public void display() 
	{
		form.append("Do you want to send the recorded video");
		form.addCommand(cancel);
		form.setCommandListener(this);
		form.addCommand(sendOnly);
		//f.addCommand(stop);
		form.setCommandListener(this);
		form.addCommand(sendAndSave);
		form.setCommandListener(this);
	
		//CitizenJournalist.disp.setCurrent(f);
	}
	
	public void showCamera() {

		try {
			player = Manager.createPlayer("capture://video");
			player.realize();

			videoControl = (VideoControl) player.getControl("VideoControl");
			canvas = new VideoCanvas(videoControl);
			canvas.addCommand(back);
			canvas.addCommand(start);
			//canvas.addCommand(stop);
			canvas.setCommandListener(this);
			CitizenJournalist.disp.setCurrent(canvas);
			player.start();
			contentType = player.getContentType();
		} catch (IOException ioe) {
		} catch (MediaException me) {
		}
	}

	
	public void streamVideo()
	{
		try {
		   
			//Thread.sleep(5000);			
		    rc= (RecordControl)player.getControl("RecordControl");
		    
		   /* if(rc==null){
		    	Alert alert = new Alert("error");
				alert.setString("rc==null");
				alert.setTimeout(2000);
				CitizenJournalist.disp.setCurrent(alert);
		    }else{	   
		    
		    Alert alert = new Alert("error");
			alert.setString("rc!=null");
			alert.setTimeout(2000);
			CitizenJournalist.disp.setCurrent(alert);
		    }*/
		    
		    //Thread.sleep(2000);
		    
		    output = new ByteArrayOutputStream();
		    rc.setRecordStream(output);
		    rc.startRecord();
		    
		    byte[] bytes = output.toByteArray();
		    iLen = bytes.length; 
		    length+=iLen;
		    //player.start();
		   // Thread.sleep(5000);	
		    
		   /* Alert alert1 = new Alert("conf");
			alert1.setString("stopping record");
			alert1.setTimeout(2000);
			CitizenJournalist.disp.setCurrent(alert1);*/
		   		 } catch(Exception e){
			 Alert alert = new Alert("Exception");
				alert.setString("msg::"+e.getMessage());
				alert.setTimeout(2000);
				CitizenJournalist.disp.setCurrent(alert);

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
			ContentType1 ct=new ContentType1();
		}
		else if(arg0==start && arg1==canvas)
		{
			Alert alert = new Alert("");
			alert.setString("Record Starting");
			alert.setTimeout(2000);
			CitizenJournalist.disp.setCurrent(alert);
			flag=1;
			Thread t = new Thread(this);
			t.start();
			canvas.removeCommand(start);
			canvas.addCommand(stop);
			//StreamingVideo sv=new StreamingVideo();
		}else if(arg0==stop && arg1==canvas){
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
			 Alert a=new Alert("");
			    a.setString(length);
			    a.setTimeout(5000);
				CitizenJournalist.disp.setCurrent(a);
			
			
			
		    CitizenJournalist.disp.setCurrent(form);
	}else if(arg0 == sendOnly && arg1 == form){
		
		flag=2;
		Thread t = new Thread(this);
		t.start();
        
		//client side
		
		
		
		

		
		//the following code is for playing the recorded video.
			/*f.removeCommand(yes);
			f.removeCommand(no);
			f.addCommand(ok);
			f.deleteAll();
			  try {
				  ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray()); 
			       Player p = Manager.createPlayer(inputStream, null);
			       p.realize();
			       GUIControl gc;
			       if ((gc = (GUIControl)p.getControl("GUIControl")) != null) {
			         //  Form f = new Form("My GUI");
			           f.append((Item)gc.initDisplayMode(GUIControl.USE_GUI_PRIMITIVE, null));
			           //CitizenJournalist.disp.setCurrent(f);
			       }
			       p.start();
			   } catch (MediaException pe) {
			   } catch (IOException ioe) {
			   }*/	 
			 
		}
		else if(arg0 == cancel && arg1 == form){
			StreamVideo sv =new StreamVideo();
			sv.showCamera();
		}else if(arg0 == ok && arg1 == form){
			StreamVideo sv =new StreamVideo();
			sv.showCamera();
		}else if(arg0 == sendAndSave && arg1 == form){
			
		}
	}

	public void run() {
		if(flag==1)
			streamVideo();
		else if(flag==2)
			uploadVideo();
	}

	private void uploadVideo() {
		try {
			HttpConnection connection =
				(HttpConnection) Connector.open(CitizenJournalist.serverURL+"videoUpload.do");

			connection.setRequestProperty("User-Agent",
										  System.getProperty("microedition.profiles"));
			connection.setRequestProperty("Content-Type",
										  "application/octet-stream");
			connection.setRequestMethod(HttpConnection.POST);
			
			
			DataOutputStream outputStream = connection.openDataOutputStream();
			//outputStream.writeByte(1);
			//outputStream.writeUTF(contentType);  //attribute : contentType = player.getContentType();

			
			byte[] bytes = output.toByteArray();

			iLen = bytes.length;   
			System.out.println(iLen);
			
			Alert alert = new Alert("sending video");
			alert.setString("iLen::::"+iLen);
			alert.setTimeout(3000);
			CitizenJournalist.disp.setCurrent(alert);

			form.append("writing to op stream...");
			//outputStream.writeInt(iLen);
			outputStream.write(bytes,0,iLen);
			form.append("writing complete...");
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			System.out.println(iLen);
			Alert alert = new Alert("Exception");
			alert.setString("msg:::"+e.getMessage());
			alert.setTimeout(Alert.FOREVER);
			CitizenJournalist.disp.setCurrent(alert);
		}

	}
	
	
	

}