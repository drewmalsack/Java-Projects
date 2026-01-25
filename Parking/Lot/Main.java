package Parking.Lot;

public class Main {
    public static void main(String args[]){
        Lot lot = new Lot(10, 10);

        Space space = lot.addSpace(1, 1, 1, 1);
        System.out.println(space.toString());

        Space space2 = lot.addSpace(2, 2, 2, 2);
        System.out.println(space2.toString());

        Space space3 = lot.addSpace(1, 1, 2, 2);
        System.out.println(space3.toString());
    }
}
