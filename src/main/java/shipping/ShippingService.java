package shipping;

import java.util.*;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable transportable){
        packages.add(transportable);
    }
    public Map<String, Integer> collectTransportableByCountry(){
        Map<String,Integer> result = new HashMap<>();
        for (Transportable p : packages){
            if(result.containsKey(p.getDestinationCountry())){
                int numberOfpackagesAct = result.get(p.getDestinationCountry())+1;
                result.put(p.getDestinationCountry(),numberOfpackagesAct);
            }else{
                result.put(p.getDestinationCountry(),1);

            }
        }
        return result;
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight){
        List<Transportable> result = new ArrayList<>();
        for (Transportable p : packages){
            if(p.isBreakable()==breakable && p.getWeight()>=weight){

                result.add(p);
            }
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance(){

        List<Transportable> result = new ArrayList<>();
        for (Transportable p : packages){
            if(p instanceof InternationalPackage){
                result.add( p);
            }
        }
         Collections.sort(result, new Comparator<Transportable>() {
            @Override
            public int compare(Transportable o1, Transportable o2) {
                return ((InternationalPackage)o1).getDistance()-((InternationalPackage)o2).getDistance();
            }});
         return result;
    }

    public List<Transportable> getPackages() {
        return packages;
    }


}
