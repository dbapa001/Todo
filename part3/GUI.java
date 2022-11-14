//https://pastebin.com/wPuKAuQy

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GUI extends JFrame{
    private ArrayList<Todo> todos;

  public GUI(){
      todos = getSavedTodos();


      setTitle("✓ Do it!");
      setSize(400,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Object[] options = {"List","Add", "Update","Delete","Exit"};
      do{

          int menu = JOptionPane.showOptionDialog(this,
                  "What would you like to do?",
                  "✓ Do it!!",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.PLAIN_MESSAGE,
                  null,
                  options,
                  options[0]);
          if(menu==0){
              showList();
          }
          if(menu==1){
              Todo todo = askAddTodo();
              if(todo!=null){
                  todos.add(todo);
                  saveTodos();
              }

          }
          if(menu==2){
              askUpdate();
          }
          if(menu==3){
              askDeleteTodo();
          }


          System.out.println(menu);
          if(menu==4 || menu==-1){
              setVisible(false);
              dispose();
              break;
          }
      }while(true);




  }

    private void askDeleteTodo() {
        String[] list = new String[todos.size()];
        for(int i=0;i< todos.size(); i++){
            Todo todo = todos.get(i);
            list[i] = todo.getText();
        }
        if(todos.isEmpty()){
            list = new String[]{"Nothing to delete"};
            JOptionPane.showInputDialog(
                    this,
                    "which one would you like to delete?",
                    "✓ Do it!!",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    list,
                    list[0]
            );
            return;
        }
        String todoName = (String) JOptionPane.showInputDialog(
                this,
                "which one would you like to delete?",
                "✓ Do it!!",
                JOptionPane.QUESTION_MESSAGE,
                null,
                list,
                list[0]
        );

        int n = Arrays.asList(list).indexOf(todoName);
        todos.remove(n);
        saveTodos();
  }


    private void askUpdate() {
        String[] list = new String[todos.size()];
        for(int i=0;i< todos.size(); i++){
            Todo todo = todos.get(i);
            list[i] = todo.getText();
        }
        if(todos.isEmpty()){
            list = new String[]{"Nothing to update"};
            JOptionPane.showInputDialog(
                    this,
                    "which one would you like to update?",
                    "✓ Do it!!",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    list,
                    list[0]
            );
            return;
        }
        String todoName = (String) JOptionPane.showInputDialog(
                this,
                "which one would you like to update?",
                "✓ Do it!!",
                JOptionPane.QUESTION_MESSAGE,
                null,
                list,
                list[0]
        );

        int n = Arrays.asList(list).indexOf(todoName);
        Todo todo = todos.get(n);

        String name;
        String cat;
        String importance;
        String due;
        String status;
        name = JOptionPane.showInputDialog(
                this,
                "Please enter the title of the todo",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE
        );
        if(name==null){
            return;
        }

        String[] Categories = {"Red","White", "Blue", "Purple", "Yellow", "Green"};
        cat = (String) JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Categories,
                Categories[0]
        );
        if(cat==null){
            return;
        }
        System.out.println(cat);

        String[] importances = {"High","Normal", "low"};
        importance = (String) JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                importances,
                importances[1]
        );

        if(importance==null){
            return;
        }

        String[] statuses = {"Pending", "Started","Partial", "Completed"};
        status = (String)JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                statuses,
                statuses[0]
        );

        if(status==null){
            return;
        }
        LocalDateTime localDateTime;
        do{
            try{
                due = JOptionPane.showInputDialog(
                        this,
                        "Please enter date for todo (format YYYY-MM-DDTHH:MM)",
                        "✓ Do it!!",
                        JOptionPane.PLAIN_MESSAGE
                );
                if(due==null){
                    return;
                }
                localDateTime = LocalDateTime.parse(due);
                break;
            }catch (DateTimeParseException e){
                System.out.println("Incorrect format");
            }
        }while(true);


        Category category=null;
        Importance imp=null;
        Status stat=null;
        switch (cat){
            case "Red":
                category = Category.Red;
                break;
            case "White":
                category = Category.White;
                break;
            case "Blue":
                category = Category.Blue;
                break;
            case "Purple":
                category = Category.Purple;
                break;
            case "Yellow":
                category = Category.Yellow;
                break;
            case "Green":
                category = Category.Green;
                break;
        }
        switch (importance){
            case "Low":
                imp = Importance.Low;
                break;
            case "Normal":
                imp = Importance.Normal;
                break;
            case "High":
                imp = Importance.High;
                break;
        }
        switch (status){
            case"Pending":
                stat = Status.Pending;
                break;
            case"Started":
                stat = Status.Started;
                break;
            case"Partial":
                stat = Status.Partial;
                break;
            case"Completed":
                stat = Status.Completed;
                break;
        }

        todo.setText(name);
        todo.setCat(category);
        todo.setStatus(stat);
        todo.setImportance(imp);
        todo.setDue(localDateTime);
        saveTodos();



    }

    private void showList() {
      String[] columns = {"Text", "Due", "Category","Importance", "Status"};
      String[][] rows = new String[todos.size()][5];
      int index=0;
      for(Todo todo : todos){
          rows[index++] = new String[]{todo.getText(),
                                        todo.getDue().toString(),
                                        todo.getCat().toString(),
                                        todo.getImportance().toString(),
                                        todo.getStatus().toString()};
      }
        System.out.println(Arrays.toString(rows));
      JTable table = new JTable(rows, columns){
          @Override
          public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
              Component component = super.prepareRenderer(renderer, row, column);
              Todo todo = todos.get(row);
              String cat = todo.getCat().toString();
              switch (cat){
                  case "Red":
                        component.setBackground(Color.RED);
                      break;
                  case "White":
                      component.setBackground(Color.WHITE);
                      break;
                  case "Blue":
                      component.setBackground(new Color(55, 76, 248));
                      break;
                  case "Purple":
                      component.setBackground(new Color(200, 7, 227));
                      break;
                  case "Yellow":
                      component.setBackground(new Color(255,255,0));
                      break;
                  case "Green":
                      component.setBackground(new Color(0,188,0));
                      break;
              }

              return component;

          }
      };
      JOptionPane.showMessageDialog(this, new JScrollPane(table));
    }

    private Todo askAddTodo() {
        String name;
        String cat;
        String importance;
        String due;
        String status;
        name = JOptionPane.showInputDialog(
                this,
                "Please enter the title of the todo",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE
        );
        if(name==null){
            return null;
        }

        String[] Categories = {"Red","White", "Blue", "Purple", "Yellow", "Green"};
        cat = (String) JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Categories,
                Categories[0]
        );
        if(cat==null){
            return null;
        }


        String[] importances = {"High","Normal", "Low"};
        importance = (String) JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                importances,
                importances[1]
        );

        if(importance==null){
            return null;
        }

        String[] statuses = {"Pending", "Started","Partial", "Completed"};
        status = (String)JOptionPane.showInputDialog(
                this,
                "Choose the Category",
                "✓ Do it!!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                statuses,
                statuses[0]
        );

        if(status==null){
            return null;
        }
        LocalDateTime localDateTime;
        do{
            try{
                due = JOptionPane.showInputDialog(
                        this,
                        "Please enter date for todo (format YYYY-MM-DDTHH:MM)",
                        "✓ Do it!!",
                        JOptionPane.PLAIN_MESSAGE
                );
                if(due==null){
                    return null;
                }
                localDateTime = LocalDateTime.parse(due);
                break;
            }catch (DateTimeParseException e){
                System.out.println("Incorrect format");
            }
        }while(true);


        Category category=null;
        Importance imp=null;
        Status stat=null;
        switch (cat){
            case "Red":
                category = Category.Red;
                break;
            case "White":
                category = Category.White;
                break;
            case "Blue":
                category = Category.Blue;
                break;
            case "Purple":
                category = Category.Purple;
                break;
            case "Yellow":
                category = Category.Yellow;
                break;
            case "Green":
                category = Category.Green;
                break;
        }
        switch (importance){
            case "Low":
                imp = Importance.Low;
                break;
            case "Normal":
                imp = Importance.Normal;
                break;
            case "High":
                imp = Importance.High;
                break;
        }
        switch (status){
            case"Pending":
                stat = Status.Pending;
                break;
            case"Started":
                stat = Status.Started;
                break;
            case"Partial":
                stat = Status.Partial;
                break;
            case"Completed":
                stat = Status.Completed;
                break;
        }
        Todo todo = new Todo(name, localDateTime, category,imp, stat);
        return todo;
    }


    private ArrayList<Todo> getSavedTodos(){
        ArrayList<Todo> todos = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(new File("saves.txt"));
            while(scanner.hasNextLine()){
                String[] todo = scanner.nextLine().split("\\|");
                String name = todo[0];
                LocalDateTime localDateTime = LocalDateTime.parse(todo[1]);
                Category category=null;
                Importance imp=null;
                Status stat=null;
                switch (todo[2]){
                    case "Red":
                        category = Category.Red;
                        break;
                    case "White":
                        category = Category.White;
                        break;
                    case "Blue":
                        category = Category.Blue;
                        break;
                    case "Purple":
                        category = Category.Purple;
                        break;
                    case "Yellow":
                        category = Category.Yellow;
                        break;
                    case "Green":
                        category = Category.Green;
                        break;
                }
                switch (todo[3]){
                    case "Low":
                        imp = Importance.Low;
                        break;
                    case "Normal":
                        imp = Importance.Normal;
                        break;
                    case "High":
                        imp = Importance.High;
                        break;
                }
                switch (todo[4]){
                    case"Pending":
                        stat = Status.Pending;
                        break;
                    case"Started":
                        stat = Status.Started;
                        break;
                    case"Partial":
                        stat = Status.Partial;
                        break;
                    case"Completed":
                        stat = Status.Completed;
                        break;
                }
                todos.add(new Todo(name, localDateTime, category, imp, stat));

            }

        }catch (FileNotFoundException e){
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
        return todos;
    }

    private void saveTodos(){
      //clear the file first


        try{
            PrintWriter writer = new PrintWriter("saves.txt");
            writer.write("");
            writer.close();
        }catch (FileNotFoundException e){
            System.out.println("An error has occurred");
            e.printStackTrace();
        }

        //save to todo
        try{
            FileWriter writer = new FileWriter("saves.txt");
            for(Todo todo : todos){
                if(todo!=null){
                    writer.write(todo.getText()+"|"+todo.getDue()+"|"+todo.getCat()+"|"+todo.getImportance()+"|"+todo.getStatus()+"\n");
                }
            }

            writer.close();
        }catch (IOException e){
            System.out.println("An error has occurred");
            e.printStackTrace();
        }


    }



}
