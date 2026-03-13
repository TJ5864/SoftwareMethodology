package project2;

import util.Date;

/** Create profiles for students with first name last name and date of birth
 * @author tjt97*/
public class Profile implements Comparable<Profile>{

        private String firstName;
        private String lastName;
        private Date dob;



        /** Creates a Profile with the given first name, last name, and date of birth
         * @param firstName student first name
         * @param lastName student last name
         * @param dob student date of birth */
        public Profile(String firstName, String lastName, Date dob){
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;

        }

        /** Returns the date of birth for this profile
         * @return the date of birth */
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

}
