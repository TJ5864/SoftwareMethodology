package project1;

/** Create profiles for studetns with first name last name and date of birth
 * @author tjt97*/
public class Profile implements Comparable<Profile>{

        private String firstName;
        private String lastName;
        private Date dob;



        public Profile(String firstName, String lastName, Date dob){
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;

        }

        public Date getDob () {
            return dob;
        }


    @Override
    public int compareTo(Profile other) {
            int last =lastName.compareToIgnoreCase(other.lastName);
            if(last != 0){
                return last;
            }
            int first = firstName.compareToIgnoreCase(other.firstName);
            if (first != 0) {
                return first;
            }
            return this.dob.compareTo(other.dob);

    }
    /**Determiens weather this profile is equal to another object
     * @param  obj the object to compare
     * @return True if profiles are equal false if they are not */

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Profile)){
            return false;
        }
        Profile other = (Profile) obj;

        return firstName.equalsIgnoreCase(other.firstName)
                && lastName.equalsIgnoreCase(other.lastName)
                && dob.equals(other.dob);

    }


    /** this toString method returns the string representation of the profile
     * @return profile as a string */
    @Override
    public String toString(){
        return "[" + firstName + " " + lastName + " "+ dob + "]";
    }

    /** Testbed for profile class
     * @param args  from command line  */
    public static void main(String[] args){
        Date d1 = new Date(2, 15, 2004);
        Date d2 = new Date(2, 15, 2004);
        Date d3 = new Date(3, 10, 2003);

        Profile p1 = new Profile("John", "Smith", d1);
        Profile p2 = new Profile("John", "Smith", d2);
        Profile p3 = new Profile("Jane", "Smith", d3);

        System.out.println(p1.equals(p2));   // should print true
        System.out.println(p1.equals(p3));   // should print false

        System.out.println(p1.compareTo(p3)); // verifies ordering

    }
}
