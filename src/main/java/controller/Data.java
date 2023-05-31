package controller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Data {
    public void addWorkingCycle(){
        FileWork fw = new FileWork();
        String fileName = "C:\\Users\\sergey\\Desktop\\guisem\\PomodoroTimer\\src\\main\\resources\\data.txt";
        ArrayList<String> x = fw.read(fileName);
        //цикл для поиска строки с датой
        int ii=0;
        for(int i = 1; i<4;i++){
            String c = String.valueOf(x.get(x.size()-i));
            if(c.contains("s")){
                ii = i;
                break;
            }
        }

        String c = String.valueOf(x.get(x.size()-ii)).substring(1);

        if(!LocalDate.now().toString().equals(c)) {
            fw.write(fileName, "s"+String.valueOf(LocalDate.now())+"\n\n");
        } else{
            ArrayList<String> s = fw.read(fileName);
            s.set(s.size() - 1, String.valueOf(Integer.parseInt(s.get(s.size() - 1)) +1));
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<s.size();i++){
                sb.append(s.get(i)+"\n");
            }
            System.out.println(sb.toString());
            fw.writes(fileName, sb.toString());
        }
    }
}
