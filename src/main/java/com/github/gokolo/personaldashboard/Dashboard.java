package com.github.gokolo.personaldashboard;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.github.gokolo.personaldashboard.dao.UserDAO;
import com.github.gokolo.personaldashboard.dao.UserDAOImpl;
import com.github.gokolo.personaldashboard.dto.UserDTO;

public final class Dashboard {
    private static MapStoreImpl<UserDTO> storeUsers = new MapStoreImpl<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final int EXIT_MENU = 0;
    private static final int REGISTER_MENU = 1;
    private static final int LIST_MENU = 2;
    private static final int MODIFY_MENU = 3;
    private static final int DELETE_MENU = 4;

    public static void main(final String... args) {
        UserDAO user = new UserDAOImpl();
        System.out.println(user.findAll());

        // System.out.println("Welcome to User Management System ");
        // int menu = 0;
        // String email;
        //
        // do {
        // System.out.println("[0] EXIT");
        // System.out.println("[1] REGISTER USER");
        // System.out.println("[2] LIST ALL USERS");
        // System.out.println("[3] MODIFY USER");
        // System.out.println("[4] DELETE USER");
        //
        // try {
        // System.out.println("Choose a menu: ");
        //
        // while (!scanner.hasNextInt()) {
        // scanner.next();
        // }
        //
        // menu = scanner.nextInt();
        // scanner.nextLine();
        //
        // switch (menu) {
        // case EXIT_MENU:
        // // Quit
        // System.out.println("GoodBye...");
        // System.exit(0);
        // break;
        //
        // case REGISTER_MENU:
        // // Register User
        // registerUser();
        // break;
        //
        // case LIST_MENU:
        // // List out all Users
        // listUsers();
        // break;
        //
        // case MODIFY_MENU:
        // // Modify a User
        // System.out.println("Please enter the current email of the user you wish to
        // modify:");
        // email = scanner.next();
        // if (findUser(email)) {
        // deleteUser(email);
        // scanner.nextLine();
        // registerUser();
        // System.out.println("User details modified:");
        //
        // } else {
        // System.out.println("This user does not exist \n \n");
        // }
        //
        // break;
        //
        // case DELETE_MENU:
        // // Delete a User
        // System.out.println("Please enter the current email of the user you wish to
        // delete:");
        // email = scanner.next();
        // deleteUser(email);
        // break;
        //
        // default:
        // System.out.println("You have entered an valid menu :( \nPlease try again");
        // break;
        // }
        // } catch (InputMismatchException e) {
        // System.out.println("Incorrect menu entered :( \nPlease try again");
        // }
        //
        // } while (true);

    }

    public static void registerUser() {
        UserDTO user = new UserDTO();
        System.out.println("Please enter name:");
        user.setName(scanner.nextLine());
        try {
            System.out.println("Please enter age:");
            // user.setBirthday(scanner.next);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect age entered! \n");
        }
        System.out.println("Please enter email:");
        user.setEmail(scanner.next());
        insertUser(user);
    }

    public static void insertUser(final UserDTO user) {
        storeUsers.store(user);
    }

    public static void listUsers() {
        System.out.println(storeUsers.retrieve());
    }

    public static boolean findUser(final String email) {
        boolean exists = false;
        for (UserDTO element : storeUsers.retrieve()) {
            if (element.getEmail().equals(email)) {
                exists = true;
            } else {
                exists = false;
            }
        }
        return exists;
    }

    public static void deleteUser(final String email) {
        for (UserDTO element : storeUsers.retrieve()) {
            if (element.getEmail().equals(email)) {
                storeUsers.delete(element);
            }
        }
    }

    private Dashboard() {
    }

}
