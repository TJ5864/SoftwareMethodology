package project2;

import util.Date;

/** Create profiles for students with first name last name and date of birth
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

/** Compares two profiles to each other
 * @param other profile we want to compare
 * @return return 0 if equal, any other number if profiles are different*/
    @Override
    public int compareTo(Profile other) {
            int last =lastName.compareToIgnoreCase(other.lastName);
            if(last != 0){
                if(last > 0)return 1;
                else return -1;
            }
            int first = firstName.compareToIgnoreCase(other.firstName);
            if (first != 0) {
                if(first>0)return 1;
                else return -1;
            }
            int dateCheck = this.dob.compareTo(other.dob);
            if(dateCheck != 0){
                if (dateCheck > 0)return 1;
                else return -1;

            }
            return 0;

    }
    /**Determiens weather this profile is equal to another object
     * @param  obj the object to compare
     * @return True if profiles are equal false if they are not*/

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
     * Test Cases shown and described in Table in Test Cases folder
     * @param args  from command line  */
    public static void main(String[] args){
        Profile p1 = new Profile("John", "Doe", new Date(1,1,2000));
        Profile p2 = new Profile("Jane", "Doe", new Date(1,1,2000));
        Profile p3 = new Profile("Adam", "Smith", new Date(1,1,2000));
        Profile p4 = new Profile("John", "Brown", new Date(1,1,2000 ));
        Profile p5 = new Profile("John", "Doe", new Date(1,1,1999));
        Profile p6 = new Profile("John", "Doe", new Date(1,1,2001));
        Profile p7 = new Profile("John", "Doe", new Date(1,1,2000));

        System.out.println("P1 (Jane Doe vs John Doe): " + p2.compareTo(p1)); // negative
        System.out.println("P2 (Smith vs Doe): " + p3.compareTo(p1));       // positive
        System.out.println("P3 (Brown vs Doe): " + p4.compareTo(p1));       // negative
        System.out.println("P4 (Doe vs Brown): " + p1.compareTo(p4));       // positive
        System.out.println("P5 (older DOB): " + p5.compareTo(p1));          // negative
        System.out.println("P6 (younger DOB): " + p6.compareTo(p1));        // positive
        System.out.println("P7 (exact same): " + p1.compareTo(p7));         // expect 0

    }
}
