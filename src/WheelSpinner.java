import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class WheelSpinner<E>{
    public HashMap wheel;

    public WheelSpinner(){
        this.wheel = new HashMap();
    }

    public void add(E what, double weight, int level){
        if (this.wheel.containsKey(Integer.valueOf(level))){
            ((ArrayList) this.wheel.get(Integer.valueOf(level))).add(new WheelSpinner.Weight(weight));
        }
        else {
            ArrayList newList = new ArrayList();
            newList.add(new WheelSpinner.Weight(weight));
            this.wheel.put(Integer.valueOf(level), newList);
        }
    }

    public E spin(int level){
        if (!this.wheel.containsKey(Integer.valueOf(level))) {
            return null;
        }
        ArrayList list = (ArrayList) this.wheel.get(Integer.valueOf(level));
        int totalWeight = 0;
        for (Iterator iterator = list.iterator(); iterator.hasNext();){
            WheelSpinner.Weight weight = (WheelSpinner.Weight) iterator.next();
            totalWeight = (int) (totalWeight + weight.weight);
        }

        double spin = Math.random() * totalWeight;
        int currentIndex = 0;
        WheelSpinner.Weight current;
        for (current = (WheelSpinner.Weight) list.get(currentIndex); spin - current.weight > 0.0D; current = (WheelSpinner.Weight) list.get(currentIndex)){
            spin -= current.weight;
            currentIndex++;
        }

        return (E) current.data;
    }

    public Object spinAll(int level){
        if (!this.wheel.containsKey(Integer.valueOf(level))) {
            return null;
        }
        int totalWeight = 0;
        for (Iterator iterator = this.wheel.entrySet().iterator(); iterator.hasNext();){
            Map.Entry levels = (Map.Entry) iterator.next();
            if (((Integer) levels.getKey()).intValue() > level) {
                continue;
            }
            for (Iterator iterator1 = ((ArrayList) levels.getValue()).iterator(); iterator1.hasNext();){
                WheelSpinner.Weight weight = (WheelSpinner.Weight) iterator1.next();
                totalWeight = (int) (totalWeight + weight.weight);
            }

        }

        if (totalWeight == 0) {
            return null;
        }
        double spin = Math.random() * totalWeight;
        int currentLevel = 0;
        int currentIndex = 0;
        while (!this.wheel.containsKey(Integer.valueOf(currentLevel))) {
            currentLevel++;
        }
        ArrayList currentList = (ArrayList) this.wheel.get(Integer.valueOf(currentLevel));

        WheelSpinner.Weight currentWeight;
        for (currentWeight = (WheelSpinner.Weight) currentList.get(currentIndex); spin - currentWeight.weight > 0.0D; currentWeight = (WheelSpinner.Weight) currentList.get(currentIndex)){
            spin -= currentWeight.weight;
            currentIndex++;
            if (currentIndex < currentList.size()) {
                continue;
            }
            for (currentLevel++; !this.wheel.containsKey(Integer.valueOf(currentLevel)); currentLevel++) {
                ;
            }
            currentList = (ArrayList) this.wheel.get(Integer.valueOf(currentLevel));
            currentIndex = 0;
        }

        return currentWeight.data;
    }

    private class Weight{
        double weight;
        E data;
        @SuppressWarnings("unused")
        final WheelSpinner this$0;

        public Weight(E data){
            this.this$0 = WheelSpinner.this;
            this.data = data;
            this.weight = (Double)data;
        }
    }
}
