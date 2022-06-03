import java.io.*;
import java.util.*;
import java.io.FileWriter;
/**
 * The occurrence class takes the files and creates hashmaps that track the occurrence
 * of each number, to see which number is hit the most.
 *
 */
public class Occurrence {
  static private File powerballTxt = new File("powerballTxt.txt");
  static private File pow = new File("powRestruct.txt");
  static private File most = new File("TopNumbersHit.txt");
  static private HashMap<Integer, Integer> powerballNumbers = new HashMap<>();
  static private HashMap<Integer, Integer> powerball = new HashMap<>();
  
  public static void setUpHashmaps()
  {
    //powerball numbers
    for(int i =1; i<70; i++)
    {
      powerballNumbers.put(i, 0);
    }
    //powerball
    for(int i =1; i<27; i++)
    {
      powerball.put(i,0);
    }
  }
  public static void checkOccurrencePB(File powerball) throws FileNotFoundException {
    Scanner scnrPower = new Scanner(new FileReader("powerballRestruct.txt"));
    String[] str;
    while (scnrPower.hasNextLine()) {
      str = scnrPower.nextLine().split(" ", 5);
  
      for (int i = 0; i < str.length; i++) {
        if (powerballNumbers.containsKey(Integer.parseInt(str[i].trim()))) {
          powerballNumbers.put(Integer.parseInt(str[i].trim()),
              powerballNumbers.get(Integer.parseInt(str[i].trim())) + 1);
        }
      }
    }
  }
  public static void checkOccurrencePow(File pow) throws FileNotFoundException {
    Scanner scnrPow = new Scanner(new FileReader("powRestruct.txt"));
    String str;
    while (scnrPow.hasNextLine()) {
      str = scnrPow.nextLine();
      if (powerball.containsKey(Integer.parseInt(str)))
      {
        powerball.put(Integer.parseInt(str),
            powerball.get(Integer.parseInt(str)) + 1);
      }
    }
  }
  public static void highestToLowestSort(HashMap<Integer, Integer> powerNum,
      HashMap<Integer, Integer> pow) throws IOException {
    List<Map.Entry<Integer, Integer> > list =
        new LinkedList<Map.Entry<Integer, Integer> >(powerNum.entrySet());
  
    Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
      public int compare(Map.Entry<Integer, Integer> o1,
          Map.Entry<Integer, Integer> o2)
      {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });
    Collections.reverse(list);
    System.out.println(list);
  
    List<Map.Entry<Integer, Integer> > list2 =
        new LinkedList<Map.Entry<Integer, Integer> >(pow.entrySet());
    
    Collections.sort(list2, new Comparator<Map.Entry<Integer, Integer> >() {
      public int compare(Map.Entry<Integer, Integer> o1,
          Map.Entry<Integer, Integer> o2)
      {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });
    Collections.reverse(list2);
    System.out.println(list2);
    
    writeInFile(list, list2);
  }
  public static void writeInFile(List powerNumList, List powList) throws IOException {
    FileWriter writer = new FileWriter("highestToLowest.txt");
    writer.write("Most hit Powerball numbers, from HIGHEST to LOWEST (WHITE BALL): " +
        "\n");
    writer.write("------------------------------------------------------------"+"\n");
    for (int i = 0; i < powerNumList.size(); i++) {
      String[] parts = powerNumList.get(i).toString().split("=");
      writer.write(parts[0] + " was drawn " + parts[1] +" times."+ "\n");
    }
    writer.write("\n");
    writer.write("Most hit POWERBALL(RED BALL): " + "\n");
    writer.write("------------------------------------------------------------" + "\n");
    for(int i =0; i<powList.size(); i++)
    {
      String[] parts = powList.get(i).toString().split("=");
      writer.write(parts[0] + " was drawn " + parts[1] +" times."+ "\n");
    }
    writer.close();
  }
  public static void printOutAllValues()
  {
    System.out.println("Powerball numbers (white): ");
    for (int i = 1; i < 70; i++) {
      System.out.println(i + " " + powerballNumbers.get(i));
    }
    System.out.println("Powerball (red): ");
    for (int i = 1; i < 27; i++) {
      System.out.println(i + " " + powerball.get(i));
    }
  }
  public static void main(String[] args) throws FileNotFoundException, IOException {
    setUpHashmaps();
    checkOccurrencePB(powerballTxt);
    checkOccurrencePow(pow);
    //printOutAllValues();
    highestToLowestSort(powerballNumbers,powerball);
  }
}
