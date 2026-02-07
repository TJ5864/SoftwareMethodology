/**
 * @author mss444
 * this enum class includes ___ properties
 */
public enum Instructor{
    PATEL,
    LIM,
    ZIMNES,
    HARPER,
    KAUR,
    TAYLOR,
    RAMESH,
    CERAVOLO,
    BROWN;

    /**
     * global constant TOTALPERIODS offset by 1 to allign index number with period i.e. period 1 = index 1.
     */
    private final int TOTALPERIODS = 7;
    private int[] availability;

    Instructor(){
        this.availability = new int[TOTALPERIODS];
    }

    public boolean checkAvailability(int period) {

        return availability;
    }

    public void fillPeriod
//    public int getPeriodAvailability(int period) {
//        instructorAvailability = this.availability;
//        return instructorAvailabiltiy(period);
//    }


    public static void main(String[] args) {
        Instructor patel = Instructor.PATEL;
        patel.getAvailability();


//        for (int i = 0; i < instructorAvailability.length(); i++) {
//            System.out.println(instructorAvailability[i]);
//        }
    }
}