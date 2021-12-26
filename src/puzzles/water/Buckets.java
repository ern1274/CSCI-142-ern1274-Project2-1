package puzzles.water;

import puzzles.clock.ClockConfig;

import java.util.ArrayList;
import java.util.BitSet;

public class Buckets {
    private int firstCapacity;
    private int secondCapacity;
    private int thirdCapacity;
    private int firstCurrent;
    private int secondCurrent;
    private int thirdCurrent;


    public Buckets(ArrayList<Integer> capacities){
        if(capacities.size()==1){
            this.firstCapacity = capacities.get(0);
            this.firstCurrent = 0;
        }
        else if(capacities.size()==2){
            firstCapacity = capacities.get(0);
            secondCapacity = capacities.get(1);
            firstCurrent = 0;
            secondCurrent = 0;
        }
        else{
            firstCapacity = capacities.get(0);
            secondCapacity = capacities.get(1);
            thirdCapacity = capacities.get(2);
            firstCurrent = 0;
            secondCurrent = 0;
            thirdCurrent = 0;
        }
    }
    public Buckets(int capacity, int cur){
        this.firstCapacity = capacity;
        this.firstCurrent = cur;
    }
    public Buckets(int first, int second, int cur1, int cur2){
        firstCapacity = first;
        secondCapacity = second;
        firstCurrent = cur1;
        secondCurrent = cur2;
    }
    public Buckets(int uno, int dos, int tres, int cur1, int cur2, int cur3){
        firstCapacity = uno;
        secondCapacity = dos;
        thirdCapacity = tres;
        firstCurrent = cur1;
        secondCurrent = cur2;
        thirdCurrent = cur3;
    }
////// edit all these methods
    public int getCapacity(int which) {
        if(which == 1&& firstCapacity > 0){
            return firstCapacity;
        }
        else if(which == 2&& secondCapacity > 0){
            return secondCapacity;
        }
        else if(which == 3&& thirdCapacity > 0){
            return thirdCapacity;
        }
        return 0;
    }
    public int getCurrent(int which){

        if(which == 1&& firstCapacity > 0){
            return firstCurrent;
        }
        else if(which == 2&& secondCapacity > 0){
            return secondCurrent;
        }
        else if(which == 3&& thirdCapacity > 0){
            return thirdCurrent;
        }
        return 0;
    }

    public void dump(int which){
        if(which == 1&& firstCapacity > 0){
            firstCurrent -= firstCurrent;
        }
        else if(which == 2&& secondCapacity > 0){
            secondCurrent -= secondCurrent;
        }
        else if(which == 3&& thirdCapacity > 0){
            thirdCurrent -= thirdCurrent;
        }
        else{
            System.out.println("Couldnt dump anything with bucket number: "+which);
        }
    }

    public void fill(int which){
        if(which == 1&& firstCapacity > 0){
            setFlow(firstCapacity,1);
        }
        else if(which == 2&& secondCapacity > 0){
            setFlow(secondCapacity,2);
        }
        else if(which == 3&& thirdCapacity > 0){
            setFlow(thirdCapacity,3);
        }
        else{
            System.out.println("Couldnt fill anything with bucket number: "+which);
        }
    }

    public void pour(int which, int which2){

        if(which == 1&& firstCapacity > 0){
                if(which2 ==2 && secondCapacity > 0){

                        if(firstCurrent>secondCapacity-secondCurrent){
                            setFlow(firstCurrent-(secondCapacity-secondCurrent),1);
                            fill(2);
                        }
                        else{
                            setFlow(secondCurrent+=firstCurrent,2);
                            dump(1);
                        }
                }

                else if(which2 == 3&& thirdCapacity > 0){

                        if(firstCurrent>thirdCapacity-thirdCurrent){
                            setFlow(firstCurrent-(thirdCapacity-thirdCurrent),1);
                            fill(3);
                        }
                        else{
                            setFlow(thirdCurrent+firstCurrent,3);
                            dump(1);
                        }
                }
        }
        else if(which == 2&& secondCapacity > 0){
                if(which2 ==1 && firstCapacity > 0){
                        if(secondCurrent>firstCapacity-firstCurrent){

                            setFlow(secondCurrent-(firstCapacity-firstCurrent),2);
                            fill(1);
                        }
                        else{
                            setFlow(firstCurrent+secondCurrent,1);
                            dump(2);
                        }
                }
                else if(which2 == 3&& thirdCapacity > 0){
                        if(secondCurrent>thirdCapacity-thirdCurrent){
                            setFlow(secondCurrent-(thirdCapacity-thirdCurrent),2);
                            fill(3);
                        }
                        else{
                            setFlow(thirdCurrent+secondCurrent,3);
                            dump(2);
                        }
                }
        }
        else if(which == 3&& thirdCapacity > 0){
                if(which2 ==2 && secondCapacity > 0){
                        if(thirdCurrent>secondCapacity-secondCurrent){
                            setFlow(thirdCurrent-(secondCapacity-secondCurrent),3);
                            fill(2);
                        }
                        else{
                            setFlow(secondCurrent+thirdCurrent,2);
                            dump(3);
                        }
                }
                else if(which2 == 1&& firstCapacity > 0){
                        if(thirdCurrent>firstCapacity-firstCurrent){
                            setFlow(thirdCurrent-(firstCapacity-firstCurrent),3);
                            fill(1);
                        }
                        else{
                            setFlow(firstCurrent+thirdCurrent,1);
                            dump(3);
                        }
                }
        }
        else{
            System.out.println("Couldnt pour anything with bucket number: "+which+ "into bucket number"+which2+ "with amount" +": ");
        }
    }
    public void setFlow(int amount, int which){

        if(which == 1&& firstCapacity >0){
            dump(1);
            firstCurrent =+ amount;
        }
        else if(which == 2&& secondCapacity > 0){
            dump(2);
            secondCurrent += amount;
        }
        else if(which == 3&& thirdCapacity > 0){
            dump(3);
            thirdCurrent += amount;
        }
        else{
            System.out.println("Couldnt set anything with bucket number: "+which+ " to: "+amount);
        }
    }
    public String buck(int which){
        if(which==1){
            return "First";
        }
        if(which==2){
            return "Second";
        }
        if(which == 3){
            return "Third";
        }
        return "";
    }
    @Override
    public String toString(){
        String bucaneer = "[";
        for (int i = 1; i < 4; i++) {
            if(getCapacity(i)!= 0){
                bucaneer += getCurrent(i);
                if(i+1!=4){
                    bucaneer +=",";
                }
            }
        }
        bucaneer += "]";
        return bucaneer;
    }

}
