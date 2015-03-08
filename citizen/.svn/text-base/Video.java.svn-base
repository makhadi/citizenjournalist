import javax.microedition.media.control.VideoControl;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.media.control.*;

class Video extends Thread implements CommandListener
	{
		Player player=null;
		Form form=null;
		Command cancel=null;
		Command sendAndSave=null;
		Command sendOnly=null;
		VideoControl videoControl;
	    CitizenJournalist midlet;
        public Video() {
        	cancel=new Command("Cancel",Command.CANCEL,1);
        	sendAndSave = new Command("Send&Save",Command.OK,1);
        	sendOnly = new Command("SendOnly",Command.OK,1);
            //this.midlet = midlet;
        	Thread t=new Thread(this);
        	t.start();
        }
       
        
        public void run() {
            captureVideo();
            
            
        }
        
        public void captureVideo() {
            try {
            	byte[] raw = videoControl.getSnapshot(null);
                Image image = Image.createImage(raw, 0, raw.length);
                form.append(image);
                form.addCommand(cancel);
                form.setCommandListener(this);
                form.addCommand(sendOnly);
                form.setCommandListener(this);
                form.addCommand(sendAndSave);
                form.setCommandListener(this);
                CitizenJournalist.disp.setCurrent(form);
                
                player.close();
                player = null;
                videoControl = null;
            } catch (MediaException me) {
            	
            }
        }
        public void commandAction(Command arg0, Displayable arg1) {
    		if(arg0==cancel && arg1==form)
    		{
    			
    		}
    		else if(arg0==sendAndSave && arg1==form)
    		{
    			
    		}
    		else if(arg0==sendOnly && arg1==form)
    		{
    			
    		}
    	}
    
}
