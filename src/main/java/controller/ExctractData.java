package controller;

import java.util.ArrayList;

public class ExctractData {
    private String date;
    private String workingCycleCount;
    private String breakCycleCount;
    private String kpd;
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

    public ExctractData(String date, String workingCycleCount, String breakCycleCount, String kpd) {
        this.date = date;
        this.workingCycleCount = workingCycleCount;
        this.breakCycleCount = breakCycleCount;
        this.kpd = kpd;
    }
    public ExctractData(){};

    public void setDate(String date) {
        this.date = date;
    }

    public void setWorkingCycleCount(String workingCycleCount) {
        this.workingCycleCount = workingCycleCount;
    }

    public void setBreakCycleCount(String breakCycleCount) {
        this.breakCycleCount = breakCycleCount;
    }

    public String getKpd() {
        if(Integer.parseInt(workingCycleCount)+Integer.parseInt(breakCycleCount)!=0) {
            kpd = String.valueOf(Integer.parseInt(workingCycleCount) * 25 * 100 / ((Integer.parseInt(workingCycleCount) * 25) + (Integer.parseInt(breakCycleCount) * 5)));
        } else{
            kpd = "0";
        }
        return kpd;
    }

    public void setKpd(String kpd) {
        this.kpd = kpd;
    }
}
