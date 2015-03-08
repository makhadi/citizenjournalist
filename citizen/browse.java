import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import java.util.*;   
import java.io.*;   
import javax.microedition.io.*;   
import javax.microedition.io.file.*;   
import javax.microedition.midlet.*;   
import javax.microedition.lcdui.*;   



public class browse implements CommandListener{
	 private String currDirName;   
	    
	    private Command view = new Command("View", Command.ITEM, 1);   
	    private Command back = new Command("Back", Command.BACK, 2);   
	    private Command exit = new Command("Exit", Command.EXIT, 3);   
	    
	    private final static String UP_DIRECTORY = "/";   
	    private final static String MEGA_ROOT = "/";   
	    private final static String SEP_STR = "/";   
	    private final static char   SEP = '/';   
	public browse(){
		System.out.println("here1");   
        currDirName = MEGA_ROOT;  
		boolean isAPIAvailable = false;   
        if (System.getProperty("microedition.io.file.FileConnection.version") != null)   
        {   
            isAPIAvailable = true;   
            System.out.println("hi");   
            try    
            {   
                showCurrDir();   
                System.out.println("h5");   
            }    
            catch (SecurityException e)    
            {   
                System.out.println(e);   
            }    
            catch (Exception e) {System.out.println(e);}   
        }   
        else  
        {   
            StringBuffer splashText = new StringBuffer("MIDlet-Name").append("\n").append("MIDlet-Vendor").   
            append(isAPIAvailable?"":"\nFileConnection API not available");   
            Alert splashScreen = new Alert(null,splashText.toString(),   
            null,AlertType.INFO);   
            splashScreen.setTimeout(3000);   
            CitizenJournalist.disp.setCurrent(splashScreen);   
        }   
	}
	public void commandAction(Command c, Displayable d)    
    {   
        System.out.println("updir:"+UP_DIRECTORY);   
        if (c == view)    
        {   
            List curr = (List)d;   
            final String currFile = curr.getString(curr.getSelectedIndex());   
            System.out.println("currFile:"+currFile);   
               
            new Thread(new Runnable()    
            {   
                public void run()    
                {   
                    if (currFile.endsWith(SEP_STR) || currFile.equals(UP_DIRECTORY))    
                    {   
                        System.out.println("h7");   
              
                        traverseDirectory(currFile);   
                    }   
                    else    
                    {   
                        showFile(currFile);   
                    }   
                }   
            }                       ).start();   
        }   
        else if (c == back)    
        {   
            showCurrDir();   
        }    
        else if (c == exit)    
        {   
            //destroyApp(false);   
        }   
    }   
    
    void showCurrDir()    
    {   
        Enumeration e;   
        FileConnection currDir = null;   
        List browser;   
        try    
        {   
            System.out.println("In showCurrDir");   
            System.out.println("mega_root:"+MEGA_ROOT+"cur_dir:"+currDirName);   
            if (MEGA_ROOT.equals(currDirName))    
            {   
                e = FileSystemRegistry.listRoots();   
                browser = new List(currDirName, List.IMPLICIT);   
                System.out.println("here");   
            }    
            else    
            {   
                System.out.println("connector");   
                currDir = (FileConnection)Connector.open("file://localhost/" + currDirName);   
                System.out.println("curr_dir:"+currDir);   
                //currDir = (FileConnection)Connector.open("http://localhost:8080/" + currDirName);   
                e = currDir.list();   
                browser = new List( currDirName, List.IMPLICIT);   
                browser.append(UP_DIRECTORY,null);   
            }   
            while (e.hasMoreElements())    
            {   
                System.out.println("h2");   
                String fileName = (String)e.nextElement();   
                System.out.println("fileName:"+fileName+" char_at:"+fileName.charAt(fileName.length()-1));   
                       
                if (fileName.charAt(fileName.length()-1) == SEP)    
                {   
                    browser.append(fileName,null);   
                       
                }    
                //if((fileName.charAt(fileName.length()-1))).equals("g"))){}   
                else    
                {   
                    System.out.println("h4");   
                    //Image image = Image.createImage(fileName);   
                    browser.append(fileName,null);   
                    //Form f = new Form("Image here");   
                       
                    //f.append(image);   
                }   
            }   
            browser.setSelectCommand(view);   
            browser.addCommand(exit);   
            browser.setCommandListener(this);   
            if (currDir != null)    
            {   
                currDir.close();   
            }   
            CitizenJournalist.disp.setCurrent(browser);   
        }    
        catch (IOException ioe)    
        {   
            System.out.println(ioe);   
        }   
    }   
     
    void traverseDirectory(String fileName)    
    {   
        System.out.println("fileName:"+fileName+"cur_dir:"+currDirName+"mega_root:"+MEGA_ROOT);   
        if (currDirName.equals(MEGA_ROOT))    
        {   
            if (fileName.equals(UP_DIRECTORY))    
            {   
                // can not go up from MEGA_ROOT   
                return;   
            }   
            currDirName = fileName;   
        }    
        else if (fileName.equals(UP_DIRECTORY))    
        {   
            System.out.println("up");   
            // Go up one directory   
            // TODO use setFileConnection when implemented   
            int i = currDirName.lastIndexOf(SEP, currDirName.length()-2);   
            if (i != -1)    
            {   
                currDirName = currDirName.substring(0, i+1);   
            }    
            else    
            {   
                currDirName = MEGA_ROOT;   
            }   
        }    
        else    
        {   
            currDirName = currDirName + fileName;   
        }   
        showCurrDir();   
    }   
     
    void showFile(String fileName)    
    {   
        try    
        {   
            FileConnection fc = (FileConnection)   
            Connector.open("file://localhost/" + currDirName + fileName);   
            if (!fc.exists())    
            {   
                throw new IOException("File does not exists");   
            }   
            InputStream fis = fc.openInputStream();   
            byte[] b = new byte[1024];   
            int length = fis.read(b, 0, 1024);   
            fis.close();   
            fc.close();   
    
            TextBox tb = new TextBox("View File: " + fileName, null, 1024,   
            TextField.ANY | TextField.UNEDITABLE);   
    
            tb.addCommand(back);   
            tb.addCommand(exit);   
            tb.setCommandListener(this);   
    
            if (length > 0)    
            {   
                tb.setString(new String(b, 0, length));   
            }   
            CitizenJournalist.disp.setCurrent(tb);
        }   
        catch (Exception e) {}   
    }   

}
