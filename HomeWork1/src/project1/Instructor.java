/**
 * @author mss444
 * this enum class includes 2 properties; name and availability
 */
public enum Instructor{
    PATEL ("Patel"),
    LIM ("Lim"),
    ZIMNES ("Zimnes"),
    HARPER ("Harper"),
    KAUR ("Kaur"),
    TAYLOR ("Taylor"),
    RAMESH ("Ramesh"),
    CERAVOLO ("Ceravolo"),
    BROWN ("Brown");

    /**
     * global constant TOTALPERIODS offset by 1 to allign index number with period i.e. period 1 = index 1.
     */
    private final int TOTALPERIODS = 7;
    private boolean[] availability;
    private String name;

    Instructor(String name){
        this.name = name;
        this.availability = new boolean[TOTALPERIODS];

        for (int i = 0; i < availability.length; i++) {
            availability[i] = true;
        }
    }

    /**
     * Getter method for the professors name
     * @return the professor's name
     */

    public String getName(){
        return name;
    }

    /**
     * checks to see if a professor is availabile during a given period within a schedule
     * @param period the plot of time that lectures can happen
     * @return the availability of the professor during the period. True if Available, False if not;
     */
    public boolean checkAvailability(int period) {
        return availability[period];
    }

    /**
     * Updates the availability of a professor if assigned to a section
     * @param period the plot of time that section can happen
     */
    public void fillAvailability(int period){
        if (availability[period]){
            availability[period] = false;
            System.out.println("Period filled. Instructor assigned to Period " + period + ".");
        }
        else {
            System.out.println("Period already filled. Operation Canceled.");
        }
    }

    /**
     * Free a professor from a section that closes.
     * @param period the plot of time that a section can occur
     */
    public void freeAvailability(int period) {
        if (availability[period]) {
            System.out.println("This professor was already available during this period. Cancelling Operation.");
        }
        else {
            availability[period] = true;
            System.out.println("This professor's schedule was freed up at period " + period);
        }
    }

    /**
     * testing
     * @param args
     */
    public static void main(String[] args) {
        Instructor patel = Instructor.PATEL;
        System.out.println(patel.checkAvailability(2));
        patel.fillAvailability(2);
        System.out.println(patel.checkAvailability(2));
        patel.fillAvailability(2);
        patel.freeAvailability(2);
        patel.freeAvailability(3);
        System.out.println(patel.getName());

    }



}