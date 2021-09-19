/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    @Override
    public void add(double x) {
        items.addLast(x);
    }

    @Override
    public double floor(double x) {
        double biggest=Double.NEGATIVE_INFINITY;
        for(int i=0; i<items.size(); i++){
            double thisItem=items.get(i);
            if(thisItem<=x&&thisItem>biggest){
                biggest=thisItem;
            }
        }
        return biggest;
    }
}
