import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class CLIMenu {
    Scanner scanner = new Scanner(System.in);
    Scanner in;
    String n;

    ArrayList<Todo> todos = new ArrayList();
    public CLIMenu() {


        //an array to add all the to do list


        //print the menu
        do {
            System.out.println("Please Select an item between 1 to 5 and press enter: ");
            System.out.println("1. List to do ");
            System.out.println("2. Add todo ");
            System.out.println("3. Update todo ");
            System.out.println("4. Delete todo ");
            System.out.println("5. Quit ");

            //scanner ask the user to enter what they want to do. user can choose numbers

            n = scanner.nextLine();


            //calling the list method if 1 is chosen,
            if (n.equals("1")) {
                //list();
                if (n.equals("1")) {
                    for (int i = 0; i < todos.size(); i++) {
                        System.out.println("the TODO List is: " + todos.get(i));
                    }
                }
            }

            //calling the addToDo method if 2 is chosen,
            else if (n.equals("2")) {
                todos.add(addToDo());
            }

            //calling the updateTodo method if 3 is chosen,
            else if (n.equals("3")) {
                updateToDo();
                System.out.println(todos);

            }

            //calling the deleteTodo method if 4 is chosen,
            else if (n.equals("4")) {
                deleteToDo();
                System.out.println(todos);
            }

            //close the program if 5 is chosen,
            else if (n.equals("5")) {
                System.out.println("Thank you! Good Bye..");
            }

            //print error message if something else is chosen.
            else {
                System.out.println("Invalid input, please re-enter");
            }
        } while (!scanner.nextLine().equals("5"));
    }

    //-------------------------------------- list do to ---------------------------------------------!!


    //list method;
    public Todo list(){
        //array where the to do store;
        ArrayList<Todo> todos = new ArrayList();

        //prints all to do
        for (int i = 0; i < todos.size(); i++) {
            System.out.println("the TODO List is: " + todos.get(i));
        }
        return null;
    }


    //----------------------------------------add to do start--------------------------------!!

    // addToDo method
    public Todo addToDo() {
        //initialise
        String text, category = null, importance, status;
        String due = null;
        Todo todo = null;

        // calling askText method to add name
        String name = askText();

        //calling askDate to add the due date
        LocalDateTime date = askDate();

        //calling askCategory method to change the colour
        Category cat = askCategory();

        //calling askImportance to add the priority
        Importance imp = askImportance();

        // calling askStatus  method to add the status
        Status statusE = askStatus();


        //print all the info that user added with a confirmation message.
        System.out.println("Name : " + name);
        System.out.println("due : " + date);
        System.out.println("Category : " + cat);
        System.out.println("Importance : " + imp);
        System.out.println("Status: " + statusE);
        System.out.println("\n" + name + " has been added to the to do list ");
        System.out.println("-------------------------------");

        //ask user if they want to continue or quit
        System.out.println("\n Press any key to see the Menu again");

        //Todo(String text, LocalDateTime due, Category cat, Importance importance, Status status)
        todo = new Todo(name, date, cat, imp, statusE);

        //return the to do list and store it in the array
        return todo;

    }
    //------------------------------ add to do done ----------------------------------------------!!

    //----------------------------- update to do start -------------------------------------------!!

    private Todo updateToDo() {

        int position;

        do {
            System.out.println("Please enter which to do you wish to update: ");
            System.out.println("the TODO List is:");
            for (int i = 0; i < todos.size(); i++) {
                System.out.println((i+1) + "." + todos.get(i));
            }

            position = scanner.nextInt();
        }
        while (position < 0 && position > todos.size() - 1);
        Todo todo = todos.get(position - 1);
        scanner.nextLine();
        String update;
        do {
            System.out.println("Please enter what do you want  to update: " +
                    "\n 1. Name" + "\n 2. Date" + "\n 3. Category" + "\n 4. Importance" + "\n 5. Status" +
                    "\n Please enter between 1-5 : ");
            update = scanner.nextLine();

        } while (!update.equals("1") &&
                !update.equals("2") &&
                !update.equals("3") &&
                !update.equals("4") &&
                !update.equals("5") &&
                !update.equals("6"));

        switch (update) {
            case "1":
                todo.setText(askText());
                return todo;
            case "2":
                todo.setDue(askDate());
                return todo;
            case "3":
                todo.setCat(askCategory());
                return todo;
            case "4":
                todo.setImportance(askImportance());
                return todo;
            case "5":
                todo.setStatus(askStatus());
                return todo;
            default:
                System.out.println("Please enter 1-6: ");
                return todo;

        }

    }

    //----------------------------- update to do done -------------------------------------------!!

    //----------------------------- delete to do start -------------------------------------------!!

    private Todo deleteToDo() {
        //check the to do index then remove that to do;

        int position;

        do {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println("the TODO List is: " + (i+1) + "." + todos.get(i));
            }
            System.out.println("Please enter which to do you wish to delete: ");
            position = scanner.nextInt();
        }
        while (position < 0 && position > todos.size() - 1);

        todos.remove(position - 1);

        return null;
    }
    //----------------------------- delete to do done -------------------------------------------!!

    //-------------------------------- asking user ---------------------------------------------!!

    ////asking for title///
    private String askText() {

        String text = null;

        //ask user to add name
        System.out.println("Please enter a title for the todo: ");
        text = scanner.nextLine();

        //return the name
        return text;
    }

    ////asking for the due date////

    //LocalDatetime method
    private LocalDateTime askDate() {

        String due = null;

        //flag method to check if the date format match with the given format.
        boolean flag = true;
        LocalDateTime dateTime = null;
        do {
            flag = true;
            try {
                System.out.print("Enter a due date for the to do in format (YYYY-MM-DDTHH:MM/ 2007-12-03T10:15 ");
                due = scanner.nextLine();
                dateTime = LocalDateTime.parse(due);
            }
            //if the format is different, gives an error message
            catch (DateTimeParseException e) {
                System.out.println("Please re-enter " + e.getMessage());
                flag = false;
            }
        } while (!flag);

        //return the date and time
        return dateTime;
    }

    ////asking for category////

    //askCategory method
    private Category askCategory() {

        String category = null;

        //keep asking to choose a category with right number until user select between 1-6
        do {
            System.out.println("Please select a category for the to do " +
                    "\n Select between 1 and 6 and press enter" +
                    "\n 1. RED" + "\n 2. WHITE" + "\n 3. BLUE" +
                    "\n 4. PURPLE" + "\n 5. YELLOW" + "\n 6. GREEN" +
                    "\n Please enter between 1-6 : ");
            category = scanner.nextLine().toUpperCase();

        } while (!category.equals("1") &&
                !category.equals("2") &&
                !category.equals("3") &&
                !category.equals("4") &&
                !category.equals("5") &&
                !category.equals("6"));

        //if the user select 1- 6 it set the category as the user choose.
        switch (category) {
            case "1":
                return Category.Red;
            case "2":
                return Category.White;
            case "3":
                return Category.Blue;
            case "4":
                return Category.Purple;
            case "5":
                return Category.Yellow;
            case "6":
                return Category.Green;
            default:
                System.out.println("Please enter 1-6: ");
                return null;

        }
    }


    //// asking for status////

    private Status askStatus() {
        //initialise

        String status = null;

        //keep asking to choose a status with right number until user select between 1-4
        do {
            System.out.print("Please enter the Status for the to do" +
                    "\n Select between 1 and 4 and press enter " +
                    "\n 1. PENDING" + "\n 2. STARTED" + "\n 3. PARTIAL" + "\n 4. COMPLETED \n");
            status = scanner.nextLine().toUpperCase();
        } while (!status.equals("1") &&
                !status.equals("2") &&
                !status.equals("3") &&
                !status.equals("4"));

        //if the user select 1- 4 it set the category as the user choose.
        switch (status) {
            case "1":
                return Status.Pending;
            case "2":
                return Status.Started;
            case "3":
                return Status.Partial;
            case "4":
                return Status.Completed;
            default:
                System.out.println("Please enter 1-4: ");
                return null;
        }
    }


    ////asking for importance////

    private Importance askImportance() {
        //initialise
        String importance = null;

        //keep asking to choose priority with right number until user select between 1-3
        do {
            System.out.println("Please select an importance for the to do " +
                    "\n Select between 1 an 3 and press enter" +
                    "\n 1. HIGH \n 2.NORMAL \n 3. LOW ");
            importance = scanner.nextLine().toUpperCase();
        } while (!importance.equals("1") &&
                !importance.equals("2") &&
                !importance.equals("3"));

        //if the user select 1- 3 it set the category as the user choose.
        switch (importance) {
            case "1":
                return Importance.High;
            case "2":
                return Importance.Normal;
            case "3":
                return Importance.Low;
            default:
                System.out.println("Please enter 1-3: ");
                return null;
        }

    }
}

/////////////////////////////////////comment out/////////////////////////////////////////////


//        if(category.equals("RED")){
//            return Category.Red;
//        }
//        if(category.equals("WHITE")){
//            return Category.White;
//        }
//        if(category.equals("BLUE")){
//            return Category.Blue;
//        }
//        if(category.equals("PURPLE")){
//            return Category.Purple;
//        }
//        if(category.equals("YELLOW")){
//            return Category.Yellow;
//        }
//        if(category.equals("GREEN")){
//            return Category.Green;
//        }

//return null
//return null;

///////////////////////////////////////////////////////////////////////////////////////

//        if(status.equals("PENDING")){
//            return Status.Pending;
//        }
//        if(status.equals("STARTED")){
//            return Status.Started;
//        }
//        if(status.equals("PARTIAL")){
//            return Status.Partial;
//        }
//        if(status.equals("COMPLETED")){
//            return Status.Completed;
//        }
//        return null;


///////////////////////////////////////////////////////////////////////////////////////////
//        if(importance.equals("HIGH")){
//            return Importance.High;
//        }
//        if(importance.equals("NORMAL")){
//            return Importance.Normal;
//        }
//        if(importance.equals("LOW")){
//            return Importance.Low;
//        }
//        return null;


////////////////////////////////////////////////////////////////////////////////////////

//    private Category category(){
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter the category for the to do. " +
//                "\n 1. RED" + "\n 2. WHITE" + "\n 3. BLUE" + "\n 4. PURPLE" + "\n 5. YELLOW" + "\n 6. GREEN" +
//                "\n Please enter between 1-6 : ");
//
//        String select = in.nextLine();
//        switch (select) {
//            case "1":
//                return Category.Red;
//            case "2":
//                return Category.White;
//            case "3":
//                return Category.Blue;
//            case "4":
//                return Category.Purple;
//            case "5":
//                return Category.Yellow;
//            case "6":
//                return Category.Green;
//            default:
//                System.out.println("Please enter 1-6: ");
//                return category();
//
//
//        }
//
//    }


