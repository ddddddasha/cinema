package util.constants;

public class Constants {
    public static final int FOUR_ATTEMPTS = 4;
    public static final int EIGHT = 8;
    public static final int SIX_INT = 6;
    public static final int ZERO_INT = 0;
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String ZERO = "0";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String START_MENU = """
                WELCOME TO THE SYSTEM
                1 - sign in
                2 - log in
                0 - exit
                """;
    public static final String ADMIN_MAIN_MENU = """
                WELCOME TO THE ADMIN MENU
                1 - working with users
                2 - working with films
                3 - working with tickets
                0 - back
                """;
    public static final String ADMIN_PERSON_MENU = """
                    Menu for working with users
                    1 - create a user
                    2 - update the user
                    3 - delete the user
                    4 - view all users
                    0 - back
                    """;
    public static final String MANAGER_MAIN_MENU = """
                WELCOME TO THE MANAGER'S MENU
                1 - view all films
                2 - edit the film
                3 - buy a ticket
                4 - return the ticket
                5 - buy a ticket for a certain user
                6 - return the ticket of a certain user
                0 - back
                """;
    public static final String USER_MENU = """
                WELCOME TO THE USER MENU
                1 - view all films
                2 - buy a ticket
                3 - delete ticket
                4 - view all my tickets
                5 - view available seats for the film
                0 - back
                """;
    public static final String ADMIN_FILM_MENU = """
                Film menu
                1 - add a film
                2 - view all films
                3 - delete the film
                5 - edit the film
                0 - back
                """;
    public static final String UPDATE_FILM_MENU = """
                1 - update film name
                2 - update film date
                3 - update film time
                4 - update film cost
                0 - exit
                """;
    public static final String TICKET_MENU = """
                Ticket menu
                1 - view all available film seats
                2 - buy a ticket
                3 - return the ticket
                4 - buy a ticket for a certain user
                5 - return the ticket of a certain user
                0 - back
                """;
    public static final String INCORRECT_INPUT = "incorrect input";
    public static final String REGISTRATION_MENU ="REGISTRATION MENU";
    public static final String ENTER_LOGIN = "Enter the login";
    public static final String ENTER_PASSWORD = "Enter the password";
    public static final String REPEAT_PASSWORD = "Repeat the entered password: ";
    public static final String REPEAT_PASS_ERROR = "The entered passwords are not equal";
    public static final String REPEAT = "Repeat, please...";
    public static final String TOO_MANY_ATTEMPTS = "Too many attempts. Try again later...";
    public static final String USERNAME_EXISTS = "A user with this username already exists";
    public static final String ENTRY_MENU = "ENTRY MENU";
    public static final String LOG_IN_FAILED = "Log into the system failed";
    public static final String ENTER_USER_ID_TO_CHANGE = "Enter the ID of the user you want to change: ";
    public static final String ENTER_NEW_LOGIN = "Enter the login of the user";
    public static final String ENTER_NEW_PASSWORD = "Enter the password of the user";
    public static final String ENTER_USER_ID_TO_DELETE = "Enter the user ID to delete";
    public static final String ENTER_FILM_ID_TO_UPDATE = "Enter the ID of the film to update:";
    public static final String ENTER_TICKET_ID_TO_DELETE = "Enter the ticket id to delete it";
    public static final String ENTER_USER_ID_TO_BUY_FOR_HIM = "Enter the ID of the user you want to buy a ticket for";
    public static final String ENTER_USER_ID_TO_DELETE_HIS_TICKET = "Enter the user ID to delete his ticket";
    public static final String ENTER_FILM_ID = "Enter the film ID";
    public static final String ENTER_FILM_ID_TO_DELETE = "Enter the id of the film to delete";
    public static final String ENTER_FILM_NAME = "Enter a new film name";
    public static final String ENTER_FILM_DATE = "Enter a new film date";
    public static final String ENTER_FILM_TIME = "Enter a new film time";
    public static final String ENTER_FILM_COST = "Enter a new film cost";
    public static final String ENTER_FILM_NAME_TO_CREATE = "Enter film name";
    public static final String ENTER_FILM_DATE_TO_CREATE = "Enter the date of the film (d.M.yyyy)";
    public static final String INCORRECT_DATE_INPUT = "incorrect date input";
    public static final String ENTER_FILM_TIME_TO_CREATE = "Enter the time of the film:";
    public static final String ENTER_FILM_COST_TO_CREATE = "Enter the cost";
    public static final String STATUS_REGISTERED = " is registered.";
    public static final String USER_REGISTERED = "User ";
    public static final String STATUS_AUTHORIZED = " is authorized.";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String INCORRECT_LOGIN = "Incorrect login";
    public static final String CHOOSE_SEAT = "---choose a seat---";
    public static final String ROW_NUMBER = "row number:";
    public static final String COL_NUMBER = "column number:";
    public static final String ENTER_TICKET_COST = "Enter the cost for payment:";
    public static final String TABLE_ROW = "¹|    0|       1|       2|       3|       4|       5|       6|       7|";
    public static final String TABLE_LINE = "-----------------------------------------------------------------------";
    public static final String TABLE_COL = "|";
    public static final String SEAT_STATUS_FREE = "free";
    public static final String SEAT_STATUS_NONE = "None";
    public static final String NOT_AN_INTEGER = "That's not an integer! Please enter an integer.";
    public static final String NOT_A_NUMBER = "That's not a number! Please enter a number.";

}
