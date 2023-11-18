import java.util.*;
import java.io.*;

public class driver {


    /**
     * Main driver function for CSCI 232 program 2 
     * 
     * Function to take in 3 files, sort the files according to their data and dates
     * then return the min and max of the data along with its date
     * if the same date is in another tree, it should print that number out as well
     * 
     * for some reason my double value is wrong for the temperature keys, im not too sure whats 
     * going on with that
     * 
     * @since 11/8/2023
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException{

        Scanner co2 = new Scanner( new File("co2.csv"));
        Scanner sea = new Scanner( new File("sea_level.csv"));
        Scanner temp = new Scanner( new File("temperature_anomaly.csv"));

        redBlackTree<Double, String> coRB = new redBlackTree<Double, String>();
        redBlackTree<Double, String> seaRB = new redBlackTree<Double, String>();
        redBlackTree<Double, String> tempRB = new redBlackTree<Double, String>();
        redBlackTree<String, Double> coDate = new redBlackTree<String, Double>();
        redBlackTree<String, Double> seaDate = new redBlackTree<String, Double>();
        redBlackTree<String, Double> tempDate = new redBlackTree<String, Double>();


        String data = "";
        String[] split = new String[5];
        String[] seaSplit = new String[6];
        String[] tempSplit = new String[4];

        co2.nextLine();
        sea.nextLine();
        temp.nextLine();
    
        while(co2.hasNextLine()) {
            data = co2.nextLine();
            split = data.split(",");
            double val = Double.valueOf(split[3]);
            coRB.put(val, split[2]);
            coDate.put(split[2], val);
        }

        while(sea.hasNextLine()) {
            data = sea.nextLine();
            seaSplit = data.split(",");
            double val;
            if(seaSplit[4].equals("")) {
                val = 0.0f;
                seaRB.put(val, seaSplit[2]);
                seaDate.put(seaSplit[2], val);
            } else {
                String idk = seaSplit[2];
                if(idk.charAt(4) == '-') {
                    String[] idk2 = idk.split("-");
                    String month = idk2[2];
                    String year = idk2[0];
                    String day = idk2[1];
                    String date2 = day + "/" + month + "/" + year;
                    val = Double.valueOf(seaSplit[4]);
                    seaRB.put(val, date2);
                    seaDate.put(date2, val);
                } else {
                    val = Double.valueOf(seaSplit[4]);
                    seaRB.put(val, seaSplit[2]);
                }
                
            }
            
        }
        
        while(temp.hasNextLine()) {
            data = temp.nextLine();
            tempSplit = data.split(",");
            String idk = tempSplit[2];
            double val = Double.valueOf(tempSplit[3]);
            if(idk.charAt(4) == '-') {
                String[] idk2 = idk.split("-");
                String month = idk2[2];
                String year = idk2[0];
                String day = idk2[1];
                String date2 = day + "/" + month + "/" + year;
                val = Double.valueOf(seaSplit[4]);
                tempRB.put(val, date2);
                tempDate.put(date2, val);
                } 
        }


        System.out.println("Minimum CO2 levels of " + coRB.min() + " on " + coRB.get(coRB.min()));
        String date11 = coRB.get(coRB.min());
        System.out.println("On the same day there was a temperature anomoly of " + tempDate.get(date11) + " and a sea level anomoly of " + seaDate.get(date11));
        System.out.println("Maximum CO2 levels of " + coRB.max() + " on " + coRB.get(coRB.max()));
        String date12 = coRB.get(coRB.max());
        System.out.println("On the same day there was a temperature anomoly of " + tempDate.get(date12) + " and a sea level anomoly of " + seaDate.get(date12));
        
        System.out.println("");

        System.out.println("Minimum Sea levels of " + seaRB.min() + " on " + seaRB.get(seaRB.min()));
        String date21 = seaRB.get(seaRB.min());
        System.out.println("On that same day there was a temperature anomoly of " + tempDate.get(date21) + " and a co2 anomly of " + coDate.get(date21));
        System.out.println("Maximum Sea levels of " + seaRB.max() + " on " + seaRB.get(seaRB.max()));
        String date22 = seaRB.get(seaRB.max());
        System.out.println("On the same day there was a temperature anomoly of " + tempDate.get(date22) + " and a co2 anomoly of " + coDate.get(date22));

        System.out.println("");

        System.out.println("Minumum temp anomoly of " + tempRB.min() * 1.8 + " on " + tempRB.get(tempRB.min()));
        String date31 = tempRB.get(tempRB.min());
        System.out.println("On the same day there was a co2 anomoly of " + coDate.get(date31) + " and a sea level anomoly of " + seaDate.get(date31));
        System.out.println("Maximum temp anomoly of " + tempRB.max() * 1.8 + " on " + tempRB.get(tempRB.max()));
        String date32 = tempRB.get(tempRB.max());
        System.out.println("On the same day there was a co2 anomoly of " + coDate.get(date32) + " and a sea level anomoly of " + seaDate.get(date32));
        // for some reason the min and max isnt correct, i think it might be the double.valueOf() function, but im not sure
    }

}