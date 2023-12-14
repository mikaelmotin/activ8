package com.activ8.view;
import com.activ8.UI.UIConsole;

import java.util.Scanner;

public class StartProgramView {
    private final UIConsole uiConsole;

    public StartProgramView(UIConsole uiConsole) {
        this.uiConsole = uiConsole;
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Activ8 Terminal Program!");
    }

    public void displayMenuAndGetChoice() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please enter a valid option.");
    }

    public void displayExitMessage() {
        System.out.println("Exiting Terminal Program. Goodbye!");
    }

}