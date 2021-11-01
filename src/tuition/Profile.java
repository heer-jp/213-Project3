package tuition;

/**
 * A class that stores information about a student's profile.
 * This information includes a name and a major.
 * @author Sai Maduri, Heer Patel
 */
public class Profile {
    private String name;
    private Major major;

    /**
     * Creates profile object type that assigns the appropriate major enum to 
     * the student based off of user input.
     * @param name The student's name
     * @param major The student's major
     */
    public Profile(String name, String major) {
        this.name = name;

        switch (major.toUpperCase()) {
            case "CS":
                this.major = Major.CS;
                break;
            case "IT":
                this.major = Major.IT;
                break;
            case "BA":
                this.major = Major.BA;
                break;
            case "EE":
                this.major = Major.EE;
                break;
            case "ME":
                this.major = Major.ME;
                break;
            default:
                this.major = null;
        }
    }

    /**
     * Converts String name and major in one string with name:major format.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + ":" + major;
    }

    /**
     * Returns String student name.
     * @return String student name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student major type.
     * @return enum Major 
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Compares profile objects by name and major.
     * @return true if the profile objects are equal, false otherwise 
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Profile))
            return false;

        Profile tmp = (Profile) obj;
        return name.equals(tmp.getName()) && major == tmp.getMajor();
    }
}