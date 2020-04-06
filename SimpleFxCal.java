//A simple calculator made by javafx.
//Only support +-*/, and only do calculation one by one.
//Only support clicking on the button.
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.text.Font;

public class SimpleFxCal extends Application {
  public static void main(String [] args) {
    launch(args);
  }

  public void start (Stage primaryStage) throws Exception {
    Double storeVal = new Double(0);

    //Display what have been entered and result
    TextField text = new TextField();
    text.setEditable(false);
    text.setPrefSize(200, 100);
    text.setFont(new Font(30));

    Button numb1 = new Button("1");
    buttonEdit(numb1, 50, 50, 300, 0);
    numb1.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"1");

      }
    });

    Button numb2 = new Button("2");
    buttonEdit(numb2, 50, 50, 300, 50);
    numb2.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"2");

      }
    });

    Button numb3 = new Button("3");
    buttonEdit(numb3, 50, 50, 300, 100);
    numb3.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"3");

      }
    });

    Button numb4 = new Button("4");
    buttonEdit(numb4, 50, 50, 250, 0);
    numb4.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"4");

      }
    });

    Button numb5 = new Button("5");
    buttonEdit(numb5, 50, 50, 250, 50);
    numb5.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"5");

      }
    });

    Button numb6 = new Button("6");
    buttonEdit(numb6, 50, 50, 250, 100);
    numb6.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"6");

      }
    });

    Button numb7 = new Button("7");
    buttonEdit(numb7, 50, 50, 200, 0);
    numb7.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"7");

      }
    });

    Button numb8 = new Button("8");
    buttonEdit(numb8, 50, 50, 200, 50);
    numb8.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"8");

      }
    });

    Button numb9 = new Button("9");
    buttonEdit(numb9, 50, 50, 200, 100);
    numb9.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"9");

      }
    });

    Button numb0 = new Button("0");
    buttonEdit(numb0, 50, 50, 350, 50);
    numb0.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText(text.getText()+"0");

      }
    });

    Button neg = new Button("+-");
    buttonEdit(neg, 50, 50, 350, 0);
    neg.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
          return;
        }
        if (seeIfErrorOperation(text.getText()) &&
        text.getText(text.getLength()-1, text.getLength()).equals(")")==false) {
          System.out.println ("Invalid");
        }
        int i= findOperation(text.getText());
        if (seeIfOperation(text.getText()) == false) {
          if (text.getText(0, 1).equals("-") == false)
            text.setText("-"+text.getText());
          else
            text.deleteText(0,1);
        }
        else if (seeIfOperation(text.getText()) &&
        text.getText(i+1, i+2).equals("(")) {
          text.deleteText(i+1, i+3);
          text.deleteText(text.getLength()-1, text.getLength());
        }
        else {
          text.insertText(i+1 ,"(-");
          text.setText(text.getText()+")");
        }
      }
    });

    Button dot = new Button(".");
    buttonEdit(dot, 50, 50, 350, 100);
    dot.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
        }
        else if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
        }
        else if (seeIfTooMuchDots(text.getText())) {
          System.out.println ("Invalid");
        }
        else {
          text.setText(text.getText()+".");
        }
      }
    });

    Button plus = new Button("+");
    buttonEdit(plus, 50, 50, 150, 150);
    plus.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
        }
        else if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
        }
        else if (seeIfOperation(text.getText())) {
          text.setText(calculate(text.getText())+"+");
        }
        else
          text.setText(text.getText()+"+");
      }
    });

    Button minus = new Button("-");
    buttonEdit(minus, 50, 50, 200, 150);
    minus.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
        }
        else if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
        }
        else if (seeIfOperation(text.getText())) {
          text.setText(calculate(text.getText())+"-");
        }
        else
          text.setText(text.getText()+"-");
      }
    });

    Button times = new Button("*");
    buttonEdit(times, 50, 50, 250, 150);
    times.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
        }
        else if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
        }
        else if (seeIfOperation(text.getText())) {
          text.setText(calculate(text.getText())+"*");
        }
        else
          text.setText(text.getText()+"*");
      }
    });

    Button divide = new Button("/");
    buttonEdit(divide, 50, 50, 350, 150);
    divide.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println ("Invalid");
        }
        else if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
        }
        else if (seeIfOperation(text.getText())) {
          text.setText(calculate(text.getText())+"/");
        }
        else
          text.setText(text.getText()+"/");
      }
    });

    Button equal = new Button("=");
    buttonEdit(equal, 50, 50, 350, 150);
    equal.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (seeIfErrorOperation(text.getText())) {
          System.out.println ("Invalid");
          return;
        }
        text.setText(calculate(text.getText()));
      }
    });

    Button delete = new Button("<-");
    buttonEdit(delete, 50, 50, 150, 100);
    delete.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (text.getText().equals("")) {
          System.out.println("Invalid");
        }
        else {
          text.deleteText(text.getLength()-1, text.getLength());
        }
      }
    });

    Button clear = new Button("C");
    buttonEdit(clear, 50, 50, 150, 50);
    clear.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        text.setText("");
      }
    });

    Pane pane = new Pane();
    pane.getChildren().addAll(numb1, numb2, numb3, numb4, numb5, numb6, numb7,
    numb8, numb9, numb0, neg, dot, plus, minus, times, divide, equal, delete,
    text, clear);

    Scene scene = new Scene(pane, 200, 400);
    primaryStage.setTitle("Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public Double add(Double a, Double b){
    return a+b;
  }

  public Double minus(Double a, Double b){
    return a-b;
  }

  public Double times(Double a, Double b){
    return a*b;
  }

  public Double divide(Double a, Double b){
    return a/b;
  }

  public String calculate(String text) {
    for (int i= 1; i<text.length(); i++) {
      //Addition
      if (text.substring(i, i+1).equals("+")) {
        Double a = Double.valueOf(text.substring(0, i));
        Double b;
        if (text.substring(i+1, i+2).equals("(")) {
         b = Double.valueOf(text.substring(i+2, text.length()-1));
        }
        else {
         b = Double.valueOf(text.substring(i+1, text.length()));
        }
        Double c = add(a, b);
        if (c == Math.floor(c)) {
          Integer d = c.intValue();
          return Integer.toString(d);
        }
        else
          return Double.toString(c);
      }

      //Subtraction
      else if (text.substring(i, i+1).equals("-")) {
        Double a = Double.valueOf(text.substring(0, i));
        Double b;
        if (text.substring(i+1, i+2).equals("(")) {
          b = Double.valueOf(text.substring(i+2, text.length()-1));
        }
        else {
          b = Double.valueOf(text.substring(i+1, text.length()));
        }
        Double c = minus(a, b);
        if (c == Math.floor(c)) {
          Integer d = c.intValue();
          return Integer.toString(d);
        }
        else
          return Double.toString(c);
      }

      //Multiplication
      else if (text.substring(i, i+1).equals("*")) {
        Double a = Double.valueOf(text.substring(0, i));
        Double b;
        if (text.substring(i+1, i+2).equals("(")) {
          b = Double.valueOf(text.substring(i+2, text.length()-1));
        }
        else {
          b = Double.valueOf(text.substring(i+1, text.length()));
        }
        Double c = times(a, b);
        if (c == Math.floor(c)) {
          Integer d = c.intValue();
          return Integer.toString(d);
        }
        else
          return Double.toString(c);
      }

      //Division
      else if (text.substring(i, i+1).equals("/")) {
        Double a = Double.valueOf(text.substring(0, i));
        Double b;
        if (text.substring(i+1, i+2).equals("(")) {
          b = Double.valueOf(text.substring(i+2, text.length()-1));
        }
        else {
          b = Double.valueOf(text.substring(i+1, text.length()));
        }
        Double c = divide(a, b);
        if (c == Math.floor(c)) {
          Integer d = c.intValue();
          return Integer.toString(d);
        }
        else
          return Double.toString(c);
      }
    }
    return text;
  }

  //Check if there is operator
  public boolean seeIfOperation (String text) {
    for (int i=1; i<text.length(); i++) {
      if (text.substring(i, i+1).equals("+") ||
      text.substring(i, i+1).equals("-") ||
      text.substring(i, i+1).equals("*") ||
      text.substring(i, i+1).equals("/"))
        return true;
    }
    return false;
  }

  //Find out operator
  public int findOperation (String text) {
    for (int i=1; i<text.length(); i++) {
      if (text.substring(i, i+1).equals("+") ||
      text.substring(i, i+1).equals("-") ||
      text.substring(i, i+1).equals("*") ||
      text.substring(i, i+1).equals("/"))
        return i;
    }
    return -1;
  }

  public boolean seeIfErrorOperation (String text) {
    if (Character.isDigit(text.charAt(text.length()-1)))
      return false;
    else
      return true;
  }

  //Check for dots error
  public boolean seeIfTooMuchDots (String text) {
    int countDot=0;
    int countOp=0;
    for (int i=1; i<text.length(); i++) {
      if (text.substring(i, i+1).equals(".")) {
        countDot++;
      }
      if (text.substring(i, i+1).equals("+") ||
      text.substring(i, i+1).equals("-") ||
      text.substring(i, i+1).equals("*") ||
      text.substring(i, i+1).equals("/")) {
        countOp++;
      }
    }
    if (countDot==0 || countDot==1 && countOp==1)
      return false;
    else
      return true;
  }

  public void buttonEdit (Button button, int height, int width, int y, int x) {
    button.setPrefHeight(height);
    button.setPrefWidth(width);
    button.setLayoutY(y);
    button.setLayoutX(x);
  }
}
