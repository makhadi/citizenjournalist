import java.util.*;
import java.io.*;

import javax.microedition.io.*;
import javax.microedition.io.file.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class UploadVideo implements CommandListener {
    
    private String currDirName;
    
    Command view = new Command("View", Command.ITEM, 1);
    Command upLoadCmd = new Command("UpLoad", Command.OK, 2);
    Command back = new Command("Back", Command.BACK, 3);
    Command downloadCmd=new Command("Download",Command.OK,1);
    
    private Image dirIcon, fileIcon;
    private Image[] iconList;
    
    /* special string denotes upper directory */
    private final static String UP_DIRECTORY = "..";
    
    /* special string that denotes apper directory accessible by this browser.
     * this virtual directory contains all roots.
     */
    private final static String MEGA_ROOT = "/";
    
    /* separator string as defined by FC specification */
    private final static String SEP_STR = "/";
    
    /* separator character as defined by FC specification */
    private final static char   SEP = '/';
    
    public UploadVideo() {
        currDirName = MEGA_ROOT;
       
    }
    

	
	protected void display() {
		
		 try {
	            showCurrDir();
	        } catch (SecurityException e) {
	            Alert alert = new Alert("Error",
	                "You are not authorized to access the restricted API",
	                null, AlertType.ERROR);
	            alert.setTimeout(Alert.FOREVER);
	            Form form = new Form("Cannot access FileConnection");
	            form.append(new StringItem(null,
	                "You cannot run this MIDlet with the current permissions. "
	                + "Sign the MIDlet suite, or run it in a different security domain"));
	            form.addCommand(back);
	            form.setCommandListener(this);
	            CitizenJournalist.disp.setCurrent(alert, form);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
    void showCurrDir() {
        Enumeration e;
        FileConnection currDir = null;
        List browser;
        try {
            if (MEGA_ROOT.equals(currDirName)) {
                e = FileSystemRegistry.listRoots();
                 browser = new List(currDirName, List.IMPLICIT);
                 browser.addCommand(upLoadCmd);
            } else {
                currDir = (FileConnection)Connector.open("file://localhost/" + 
                            currDirName);
                e = currDir.list();
                 browser = new List(currDirName, List.IMPLICIT);
                 browser.addCommand(upLoadCmd);
                  // not root - draw UP_DIRECTORY
                 browser.append(UP_DIRECTORY, dirIcon);
            }
                    
            while (e.hasMoreElements()) {
                String fileName = (String)e.nextElement();
                if (fileName.charAt(fileName.length()-1) == SEP) {
                    // This is directory
                    browser.append(fileName, dirIcon);
                } else {
                    // this is regular file
                    browser.append(fileName, fileIcon);
                }
            }
            
            browser.setSelectCommand(view);
            //Do not allow creating files/directories beside root
            
            browser.addCommand(back);
            
            browser.setCommandListener(this);
            
            if (currDir != null) {
                currDir.close();
            }
            
            CitizenJournalist.disp.setCurrent(browser);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
    void traverseDirectory(String fileName) {
        /* In case of directory just change the current directory
         * and show it
         */
            if (currDirName.equals(MEGA_ROOT)) {
                if (fileName.equals(UP_DIRECTORY)) {
                    // can not go up from MEGA_ROOT
                    return;
                }
                currDirName = fileName;
            } else if (fileName.equals(UP_DIRECTORY)) {
                // Go up one directory
                // TODO use setFileConnection when implemented
                int i = currDirName.lastIndexOf(SEP, currDirName.length()-2);
                if (i != -1) {
                    currDirName = currDirName.substring(0, i+1);
                } else {
                    currDirName = MEGA_ROOT;
                }
            } else {
                currDirName = currDirName + fileName;
            }
            showCurrDir();
        }
    

	public void commandAction(Command c, Displayable d) {
		
		if (c == view) {
            List curr = (List)d;
            final String currFile = curr.getString(curr.getSelectedIndex());
            
            new Thread(new Runnable() {
                public void run() {
                    if (currFile.endsWith(SEP_STR) || currFile.equals(UP_DIRECTORY)) {
                        traverseDirectory(currFile);
                    } else {
                        // Show file contents
                        //showFile(currFile);
                    }
                }
            }).start();
        }
		if(c== back)
		{ 
			new ContentType1();
		}
		
		
		if(c==upLoadCmd)
		{
			List curr=(List)d;
			final String currFile=curr.getString(curr.getSelectedIndex());
			
			if(currFile.endsWith(SEP_STR) || currFile.equals(UP_DIRECTORY))
			{
				Alert a=new Alert("Alert...");
				a.setString("Cannot Upload Folder Select a File...");
				a.setTimeout(Alert.FOREVER);
			}else{
				// logic to upload a file to server..
				new Thread(new Runnable(){
					public void run(){
						
						String url=CitizenJournalist.serverURL+"videoUpload.do?fileName="+ContentDescription.name.getString()+"&fileDesc="+ContentDescription.desc.getString();
						try {
							System.out.println(currDirName+currFile);
							FileConnection fconnection=(FileConnection)Connector.open("file:///"+currDirName+currFile);
							DataInputStream dis=fconnection.openDataInputStream();
							HttpConnection connection=(HttpConnection)Connector.open(url.replace(' ', '+'));
							DataOutputStream dos=connection.openDataOutputStream();
							int i=0;
							
							while((i=dis.read())!=-1)
							{
								dos.write(i);
							}
							Alert a=new Alert("Alert");
							a.setString("Uploading file.. Please wait..");
							a.setTimeout(10000);
							InputStream is=connection.openInputStream();
							
							
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
						
					}
				}).start();
				Alert a=new Alert("Alert");
				a.setString("Uploaded file.. Thank you..");
				a.setTimeout(3000);
			}
			
		}
		
		
	}
	
}