package seedu.duke.models.schema;


import java.util.ArrayList;


/**
 * The Student class represents a student with a name, major, and module schedule.
 */

public class Student {

    private String name;
    private Major major;


    private String year;

    private ArrayList<String> currentMods;

    public Student(String name, Major major) {
        this.name = name;
        this.major = major;
        this.year = "";

    private Schedule schedule;

    /**
     * Constructs a student with a name, major, and module schedule.
     *
     * @param name     The name of the student.
     * @param major    The major of the student.
     * @param schedule The module schedule of the student.
     */
    public Student(String name, Major major, Schedule schedule) {
        this.name = name;
        this.major = major;
        this.schedule = schedule;

    }

    /**
     * Constructs a student with a null name, null major, and an empty module schedule.
     */
    public Student() {
        this.name = null;
        this.major = null;
        this.schedule = new Schedule();
    }

    /**
     * Sets the class schedule of the student.
     *
     * @param schedule The new module schedule.
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Retrieves the module schedule of the student.
     *
     * @return The module schedule of the student.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Retrieves the name of the student.
     *
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }


    /**
     * Retrieves the major of the student.
     *
     * @return The major of the student.
     * @throws NullPointerException If the major has not been set (i.e., it is `null`).
     */

    public Major getMajor() throws NullPointerException {
        return major;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The new name of the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the major of the student.
     *
     * @param major The new major to set.
     */
    public void setMajor(Major major) {
        this.major = major;
    }


    public String updateMajor(String userInput) {
        String[] words = userInput.split(" ");
        if (words.length < 2) {
            return "currentMajor";
        }
        try {
            setMajor(Major.valueOf(words[1].toUpperCase()));
            return "newMajor";
        } catch (IllegalArgumentException e) {
            return "invalidMajor";
        }
    }

    /**
     * Sets the first major without the major command
     * @author Isaiah Cerven
     * @param userInput must be validated in parser as CS or CEG
     */
    public void setFirstMajor(String userInput){
        try {
            setMajor(Major.valueOf(userInput.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String printMajor() {
        String majorString = "";
        if (major == null) {
            System.out.println("Major is not set.");
        } else {
            switch (major) {
            case CS:
                majorString = "Computer Science";
                break;
            case CEG:
                majorString = "Computer Engineering";
                break;
            default:
                majorString = "Unknown Major";
            }
            return majorString;
        }
        return majorString;
    }

    /**
     * Returns all the known information about a certain student object
     * @author Isaiah Cerven
     */
    public void printStudentInformation(){
        String majorString = printMajor();
        System.out.println("Name: " + name);
        System.out.println("Major: " + majorString);
        System.out.println("Year " + year);
    }



}
