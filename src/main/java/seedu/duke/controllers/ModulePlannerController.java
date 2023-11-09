package seedu.duke.controllers;

import seedu.duke.models.logic.CompletePreqs;
import seedu.duke.models.logic.Storage;
import seedu.duke.models.schema.Student;
import seedu.duke.models.schema.ModuleList;
import seedu.duke.models.schema.CommandManager;
import seedu.duke.models.schema.UserCommand;
import seedu.duke.utils.Parser;
import seedu.duke.utils.exceptions.CorruptedFileException;
import seedu.duke.utils.exceptions.MissingFileException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static seedu.duke.controllers.ModuleServiceController.validateMajorInput;

import static seedu.duke.views.CommandLineView.displayWelcome;
import static seedu.duke.views.CommandLineView.displayReady;
import static seedu.duke.views.CommandLineView.displayGoodbye;
import static seedu.duke.views.CommandLineView.displayGetMajor;
import static seedu.duke.views.CommandLineView.displayGetYear;

public class ModulePlannerController {
    private final Parser parser;
    private Student student;
    private ModuleList modulesTaken;
    private CompletePreqs addModulePreqs;
    private CommandManager commandManager;
    private Storage storage;

    public ModulePlannerController() {
        this.commandManager = new CommandManager();
        this.parser = new Parser();
        this.student = new Student();

        this.modulesTaken = new ModuleList();


        //Pass in Hashmap of mods with Preqs
        //this.addModulePreqs = new CompletePreqs(addModsWithPreqs(new HashMap<String, List<String>>()));
        this.addModulePreqs = new CompletePreqs();

        //Pass in the list of mods completed.
        addModulePreqs.initializeCompletedMods(modulesTaken);
    }

    /**
     * Starts the application, guiding the user through its execution.
     * This method performs the following steps:
     * 1. Display a welcome message to the user.
     * 2. Initialize user-related data or settings.
     * 3. Display a message indicating that the application is ready for input.
     * 4. Handle user input until an exit command is given.
     * 5. Display a goodbye message when the application is finished.\
     * @author ryanlohyr
     *
     */
    public void start() {
        displayWelcome();
        initialiseUser();
        displayReady();
        handleUserInputTillExitCommand();
        displayGoodbye();
    }

    public void initialiseUser() {
        Scanner in = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Please enter your name: ");
            userInput = in.nextLine().trim();
        } while (!parser.checkNameInput(userInput, commandManager.getListOfCommandNames()));
        student.setName(userInput);

        // Create storage file based on userName
        storage = new Storage(userInput);
        try {
            System.out.println("Attempting to retrieve data from save file...");
            student.setSchedule(storage.loadSchedule());
            System.out.println("Data successfully retrieved!\n");
        } catch (MissingFileException e) {
            System.out.println("First time user, creating new save file...");
            storage.createUserStorageFile();
            System.out.println("File successfully created!\n");
        } catch (CorruptedFileException e) {
            System.out.println("It seems that your save file is corrupted and we are unable to retrieve any data.\n" +
                    "Please continue using the application to create a new save file!\n");
        }

        // Get and set student's major
        displayGetMajor(student.getName());
        do {
            userInput = in.nextLine().trim();
        } while (!validateMajorInput(userInput));
        student.setFirstMajor(userInput);

        // Get and set student's year
        displayGetYear();
        do {
            userInput = in.nextLine().trim();
        } while (!Parser.isValidAcademicYear(userInput.toUpperCase()));
        student.setYear(userInput.toUpperCase());
    }

    public void handleUserInputTillExitCommand() {

        Scanner in = new Scanner(System.in);
        UserCommand currentUserCommand = new UserCommand();
        while (!currentUserCommand.isBye()) {
            currentUserCommand = new UserCommand(in.nextLine().replace("\r", ""));
            if (currentUserCommand.isValid() && !currentUserCommand.isBye()) {
                currentUserCommand.processCommand(student);
            }
        }
        in.close();
    }

    //    private void processCommand(String command, String[] arguments, String userInput) {
    //        switch (command) {
    //        case UserCommandWord.LEFT_COMMAND: {
    //            showModulesLeft(student.getModuleCodesLeft());
    //            break;
    //        }
    //        case UserCommandWord.PACE_COMMAND: {
    //            computePace(arguments, student.getCurrentModuleCredits(), student.getYear());
    //            break;
    //        }
    //        case UserCommandWord.PREREQUISITE_COMMAND: {
    //            String module = arguments[0];
    //            determinePrereq(module.toUpperCase(), student.getMajor()); //to convert "CEG" to dynamic course
    //            break;
    //        }
    //        case UserCommandWord.RECOMMEND_COMMAND: {
    //            recommendScheduleToStudent(student);
    //            break;
    //        }
    //        case UserCommandWord.ADD_MODULE_COMMAND: {
    //            String module = arguments[0].toUpperCase();
    //            int targetSem = Integer.parseInt(arguments[1]);
    //
    //            addModule(module, targetSem, student);
    //            break;
    //        }
    //        case UserCommandWord.DELETE_MODULE_COMMAND: {
    //            String module = arguments[0].toUpperCase();
    //
    //            deleteModule(module,student);
    //            break;
    //        }
    //        case UserCommandWord.SHIFT_MODULE_COMMAND: {
    //            String module = arguments[0].toUpperCase();
    //            int targetSem = Integer.parseInt(arguments[1]);
    //
    //            shiftModule(module, targetSem, student);
    //            break;
    //        }
    //        case UserCommandWord.VIEW_SCHEDULE_COMMAND: {
    ////            getStudentSchedule();
    //            student.printSchedule();
    //            break;
    //        }
    //        case UserCommandWord.COMPLETE_MODULE_COMMAND: {
    //            String module = arguments[0].toUpperCase();
    //            //to add to user completed module
    //            completeModule(student, module);
    //
    //            break;
    //        }
    //        case UserCommandWord.REQUIRED_MODULES_COMMAND: {
    //            getRequiredModulesForStudent(student.getMajor());
    //            break;
    //        }
    //        case UserCommandWord.INFO_COMMAND: {
    //            Api.infoCommands(arguments[0], userInput);
    //            break;
    //        }
    //        case UserCommandWord.SEARCH_MODULE_COMMAND: {
    //            Api.searchCommand(userInput);
    //            break;
    //        }
    //        case UserCommandWord.HELP_COMMAND: {
    //            printListOfCommands(commandManager);
    //            break;
    //        }
    //        case UserCommandWord.TIMETABLE_COMMAND: {
    //            student.timetableShowOrModify(student, userInput);
    //            break;
    //        }
    //        default: {
    //            break;
    //        }
    //        }
    //
    //    }

    /**
     * Add all mods that require prerequisites to a map storing the mod and a set of preqs
     *
     * @param list HashMap of ModsWithPreqs
     * @return HashMap of Mods with their corresponding preqs
     */

    private HashMap<String, List<String>> addModsWithPreqs(HashMap<String, List<String>> list) {
        //Only two mods don't have preqs MA1511 and CS1231S.
        // In the future this will be dealt
        addValue(list, "CS3230", "CS2030S");
        addValue(list, "CS3230", "CS1231S");

        addValue(list, "CS2030S", "CS1231S");

        addValue(list, "CS2040S", "CS1231S");

        addValue(list, "CS2106", "CS1231S");

        addValue(list, "CS2109S", "CS1231S");

        return list;
    }


    /**
     * Adds a value to a HashMap with a list of values associated with a key.
     * If the key does not exist in the map, it creates a new key-value pair with an empty list.
     * The value is added to the list associated with the key.
     *
     * @param map   The HashMap in which the value will be added.
     * @param key   The key to which the value will be associated.
     * @param value The value to add to the list.
     */
    public static void addValue(HashMap<String, List<String>> map, String key, String value) {
        // If the map does not contain the key, put an empty list for that key
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        // Add the value to the list associated with the key
        map.get(key).add(value);
    }
}
