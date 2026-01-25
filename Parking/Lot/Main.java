package Parking.Lot;

public class Main {
    public static void main(String args[]){
        Lot lot = new Lot(10, 10);

        Space space = lot.addSpace(1, 1, 1, 1);
        System.out.println(space.toString());
        System.out.println(space);
        System.out.println(lot.getSpaces().get(0));

        Space space2 = lot.addSpace(2, 2, 2, 2);
        System.out.println(space2.toString());
        System.out.println(space2);
        System.out.println(lot.getSpaces().get(1));

        //Space space3 = lot.addSpace(1, 1, 2, 2);
        //System.out.println(space3.toString());
    }
}
