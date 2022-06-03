import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
/**
 * The class organizes the Powerball document that is structured as follows:
 *
 * Feb 3, 2010 to Jan 14, 2012:
 * Game Name, Month, Day, Year, Num1, Num2, Num3, Num4, Num5, Powerball, Power Play
 * 0          1       2   3     4     5     6     7     8     9          10
 * Jan 18, 2012 to Jan 18, 2014:
 * Game Name, Month, Day, Year, Num1, Num2, Num3, Num4, Num5, Powerball
 *
 * Jan 22, 2014 to present:
 * Game Name, Month, Day, Year, Num1, Num2, Num3, Num4, Num5, Powerball, Power Play
 *
 * We will get rid of the Game Name, Month, Day, Year, Powerball and Powerplay then
 * put the rest in document "powerballRestruct.txt"
 * We put all powerballs in "powRestruct.txt"
 *
 *
 */
public class OrganizeData {
  public static void main(String[] args) throws IOException {
  
    Scanner countLine = new Scanner(new FileReader("powerballTxt.txt"));
    int lineCount = 0;
  
    while (countLine.hasNextLine()) {
      countLine.nextLine();
      lineCount++;
    }
  
    System.out.println("The line count is: " + lineCount);
  
    String powerballArray[][] = new String[lineCount][11];
    Scanner scnr = new Scanner(new FileReader("powerballTxt.txt"));
    String str;
    
    for (int i = 0; i < lineCount; i++) {
      powerballArray[i]= scnr.nextLine().split(",", 11);
        
      }
    System.out.println(Arrays.deepToString(powerballArray));
    /*
      Make file with the 5 powerball numbers.
     */
    File powerballNum = new File("powerballRestruct.txt");
    FileWriter writer = new FileWriter(powerballNum);
    for(int i = 0; i<lineCount; i++)
    {
      for(int j = 4; j < 9; j++)
      {
        writer.write(powerballArray[i][j] + " ");
      }
      writer.write("\n");
    }
    writer.close();
    
    /*
      Make file with powerball number.
     */
    File powerballs = new File("powRestruct.txt");
    writer = new FileWriter(powerballs);
    for(int i = 0; i<lineCount; i++)
    {
      writer.write(powerballArray[i][9]);
      writer.write("\n");
    }
    writer.close();
    
  }

}
