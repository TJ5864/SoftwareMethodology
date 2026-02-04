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
            return dob.toString().compareTo(other.dob.toString());
    }
    /**Determiens weather this profile is equal to another object
     * @param  obj the object to compare
     * @return True if profiles are equal false if they are not */

    @Override
    public boolean eqauls(Object obj){
            return;

    }
}
