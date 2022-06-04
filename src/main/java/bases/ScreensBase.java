package bases;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class ScreensBase {
	
	
	public static void uploadFile(String fileLocation)
	{
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	public static void setClipboardData(String string) 
	{
		//String Selection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public String picPath(String picName)
	{
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\PayrollCorporate\\pictures\\" + picName;
        return fullScreenPath;
	}

	public String batchPath(String batchName)
	{
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\PayrollCorporate\\batches\\" + batchName;
        return fullScreenPath;
	}
}
