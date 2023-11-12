package seedu.duke.views;


import seedu.duke.models.schema.CommandManager;
import seedu.duke.utils.exceptions.InvalidPrereqException;

import java.io.IOException;

import static seedu.duke.models.logic.Prerequisite.getModulePrereqBasedOnCourse;
import static seedu.duke.utils.errors.HttpError.displaySocketError;

/**
 * The CommandLineView class provides methods to display messages and user prompts in the command line interface.
 * It also includes a method to show prerequisites for a given module based on the course and major.
 */
public class CommandLineView {

    public static void displayWelcome(){

        String logo = "  _   _ _   _ ____  ____                 \n"
                + " | \\ | | | | / ___||  _ \\  ___  __ _ ___ \n"
                + " |  \\| | | | \\___ \\| | | |/ _ \\/ _` / __|\n"
                + " | |\\  | |_| |___) | |_| |  __/ (_| \\__ \\\n"
                + " |_| \\_|\\___/|____/|____/ \\___|\\__, |___/\n"
                + "                               |___/     ";

        System.out.println("Hey there CS and CEG Students! Welcome to ");
        System.out.println(logo);
    }

    public static void displayGoodbye(){
        System.out.println("Goodbye!");
    }

    public static void displayReady(){
        System.out.println("Now you're all set to use NUSDegs to kick start your degree planning!");
        displayHelp();
    }

    public static void displayHelp(){
        System.out.println("Type 'help' to see the available commands");
    }


    public static void displayGetMajor(String name){
        System.out.println("Welcome " + name + "! What major are you? (Only two available: CEG or CS)");
    }

    public static void displayGetYear(){
        System.out.println("What Year and Semester are you? Ex: Y1/S2 for year 1 semester 2");
    }

    public static void displayCommands(CommandManager commandManager) {
        displayMessage("Here are all the commands currently available in NUSDegs!");
        displayMessage("- Words in UPPER_CASE are the parameters to be supplied by the user.");
        displayMessage("- Parameters in [] are optional.");
        System.out.println();
        for (String command : commandManager.printListOfCommands()) {
            displayMessage(command);
        }
        System.out.println();
        String userGuideURL = "https://ay2324s1-cs2113-t17-4.github.io/tp/UserGuide.html";
        displayMessage("For more information, please read our User Guide at this link:");
        displayMessage(userGuideURL);
    }

    /**
     * Displays the prerequisites for a given module based on the course and major.
     *
     * @param module The module for which prerequisites need to be displayed.
     * @param major The major or course associated with the module.
     */
    public static void showPrereq(String module,String major){
        try{
            System.out.println("This module's prerequisites are "
                    + getModulePrereqBasedOnCourse(module.toUpperCase(),major));
        }catch (InvalidPrereqException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            displaySocketError();
        }
    }
    public static void displaySuccessfulAddMessage() {
        displayMessage("Module Successfully Added");
    }

    public static void displaySuccessfulDeleteMessage() {
        displayMessage("Module Successfully Deleted");

    }

    public static void displaySuccessfulShiftMessage() {
        displayMessage("Module Successfully Shifted");
    }

    public static void displaySuccessfulClearMessage() {
        displayMessage("Schedule successfully cleared");
    }

    public static void displayUnsuccessfulClearMessage() {
        displayMessage("Clear schedule operation cancelled");
    }
    public static void displaySuccessfulCompleteMessage() {
        displayMessage("Module Successfully Completed");
    }

    public static void displayUnsuccessfulCompleteMessage() {
        displayMessage("Module cannot be completed as its prereqs have not been completed.");
    }


    /**
     * Display a message to the command line view.
     *
     * @param o The object to be displayed.
     */
    public static void displayMessage(Object o) {
        System.out.println(o);
    }

    public static void printNewline(){
        System.out.println();
    }

}
