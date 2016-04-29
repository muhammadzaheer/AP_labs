package interpreter;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.HashMap;
//import java.util.Map.Entry;

public class Interpreter {

    LinkedList<String> codes;
    HashMap<String, Integer> variables;

    public Interpreter() {
        codes = new LinkedList<String>();
        variables = new HashMap<String, Integer>();
    }

    public void readFile(String fileName) {
        
        codes = new LinkedList<String>();
        variables = new HashMap<String, Integer>();
        File file = new File(fileName);

        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String code = in.nextLine();
                codes.add(code);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }

    }

    public void parseCodes() {

        for (String code : this.codes) {
            try {
                code = code.trim();
                if (!code.endsWith(";")) {
                    throw new IllegalArgumentException("Missing ';'");
                }
                if (code.startsWith("Let")) {
                    declareVariable(code);

                } else if (code.startsWith("Print")) {
                    printVariable(code);

                } else {
                    doArithmetic(code);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                return;
            }

        }
    }

    public void declareVariable(String code) {
        String pieces[] = code.split("=");
        if (pieces.length != 2) {
            throw new IllegalArgumentException("INVALID CODE " + code);
        }
        String name = pieces[0].split(" ")[1];
        if (isNumeric(name)) {
            System.out.println("Invalide Code : " + code);
            System.out.println("Left Handside of statement has to be a valid variable name\n");
            return;
        }
        
        if (!isValidName(name)) {
            System.out.println("Invalid Code : " + code);
            System.out.println("Invalid variable name\n");
            return;
        }

        pieces[1] = pieces[1].replace(";", "");
        pieces[1] = pieces[1].replace(" ", "");
        Integer value = 0;
        try {
            value = Integer.parseInt(pieces[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Code : " + code);
            System.out.println("Right Handside of statement has to be int\n");
            return;
        }
        if (variables.containsKey(name)) {
            throw new IllegalArgumentException("DUPLICATE DECLARATION OF VARIABLE " + name);
        }
        variables.put(name, value);
    }

    public void printVariable(String code) {
        code = code.replace(";", "");
        String pieces[] = code.split(" ");
        if (pieces.length != 2) {
            throw new IllegalArgumentException("INVALID CODE " + code);
        }
        String name = pieces[1];
        if (!(variables.containsKey(name))) {
            throw new IllegalArgumentException("VARIABLE " + name + " NOT FOUND");
        }

        System.out.println(variables.get(name));

    }

    public void doArithmetic(String code) {

        code = code.replace(";", "");
        String pieces[] = code.split("=");
        if (pieces.length != 2) {
            throw new IllegalArgumentException("INVALID CODE " + code);
        }
        String result = pieces[0].trim();
        pieces[1] = pieces[1].trim();
        if (!(variables.containsKey(result))) {
            throw new IllegalArgumentException("VARIABLE " + result + " NOT FOUND");
        }

        String op1, op2;
        Integer val1, val2;
        if (pieces[1].contains("+")) {
            pieces = pieces[1].split("\\+");
            op1 = pieces[0].trim();
            op2 = pieces[1].trim();
            val1 = parseOperand(op1);
            val2 = parseOperand(op2);
            variables.put(result, val1 + val2);

        } else if (pieces[1].contains("-")) {
            pieces = pieces[1].split("\\-");
            op1 = pieces[0].trim();
            op2 = pieces[1].trim();
            val1 = parseOperand(op1);
            val2 = parseOperand(op2);
            variables.put(result, val1 - val2);

        } else if (pieces[1].contains("/")) {
            pieces = pieces[1].split("\\/");
            op1 = pieces[0].trim();
            op2 = pieces[1].trim();
            val1 = parseOperand(op1);
            val2 = parseOperand(op2);
            variables.put(result, val1 / val2);

        } else if (pieces[1].contains("*")) {
            pieces = pieces[1].split("\\*");
            op1 = pieces[0].trim();
            op2 = pieces[1].trim();
            val1 = parseOperand(op1);
            val2 = parseOperand(op2);
            variables.put(result, val1 * val2);

        } else {
            if (!(variables.containsKey(pieces[1]))) {
                throw new IllegalArgumentException("VARIABLE " + pieces[1] + "NOT FOUND");
            }
            variables.put(result, variables.get(pieces[1]));
        }
    }

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean isValidName(String s) {
        if (!s.matches("^[a-zA-Z][a-zA-Z0-9_]*?$")) {
            return false;
        }
        return true;
    }

    public Integer parseOperand(String op) {

        if (isNumeric(op)) {
            return Integer.parseInt(op);
        } else {
            if (!(variables.containsKey(op))) {
                throw new IllegalArgumentException("Variable " + op + " not found");
            }
            return variables.get(op);
        }

    }

}
