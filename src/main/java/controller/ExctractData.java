package controller;

import java.util.ArrayList;

public class ExctractData {
    private String date;
    private String workingCycleCount;
    private String breakCycleCount;
    private int dateCount;
    private int ii=0;
    public void next() {
        String fileName = "src\\main\\resources\\data.txt";
        FileWork fw = new FileWork();
        ArrayList<String> s = fw.read(fileName);
        for(int i = ii; i<s.size(); i++){
            if(s.get(i).contains("s")){
                ii=i+1;
                date = s.get(i).substring(1);
                workingCycleCount = s.get(i+1);
                breakCycleCount = s.get(i+2);
                break;
            }
        }
    }
    public int getDateCount(){
        dateCount=0;
        String fileName = "src\\main\\resources\\data.txt";
        FileWork fw = new FileWork();
        ArrayList<String> s = fw.read(fileName);
        for(int i = 0; i<s.size(); i++){
            if(s.get(i).contains("s")){
                dateCount++;
            }
        }
        return dateCount;
    }

    public String getDate() {
        return date;
    }

    public String getWorkingCycleCount() {
        return workingCycleCount;
    }

    public String getBreakCycleCount() {
        return breakCycleCount;
    }
}
