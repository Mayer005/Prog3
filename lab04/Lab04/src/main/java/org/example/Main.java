package org.example;

import java.io.*;
import java.util.*;


public class Main{

    public static ArrayList<Beer> sorok = new ArrayList<>();
    public static void main(String[] args) {
        String[] cmd;
        Scanner sc = new Scanner(System.in);
        String oneline = "";
        System.out.println("S Ö R Ö K");

        while(true) {
            oneline = sc.nextLine();
            cmd = oneline.split(" ");
            switch (cmd[0]) {
                case "add" -> add(cmd);
                case "exit" -> System.exit(0);
                case "list" -> list(cmd);
                case "load" -> load(cmd);
                case "save" -> save(cmd);
                case "search" -> search(cmd);
                case "find" -> find(cmd);
                case "delete" -> delete(cmd);
            }
        }
    }

    protected static void save(String[] cmd) {
        if (cmd.length!=2){
            System.out.println("save parancs helyes használata: save <fájlnév>");
        }else{
            try {
                File f = new File(cmd[1]);
                ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(f));
                obj.writeObject(sorok);
                obj.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected static void load(String[] cmd) {
        if (cmd.length!=2){
            System.out.println("save parancs helyes használata: load <fájlnév>");
        }else{
            try{
                String f = cmd[1];
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
                sorok = (ArrayList<Beer>) in.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected static void list(String[] cmd) {
        if (cmd.length > 2) {
            System.out.println("A 'list' helyes használata: list [argumentum]");
        } else if (cmd.length == 2){
            switch (cmd[1]) {
                case "name":
                    Collections.sort(sorok, new NameComparator());
                    for(Beer b : sorok){
                        System.out.println(b);
                    }
                    break;
                case "style":
                    Collections.sort(sorok, new StyleComparator());
                    for(Beer b : sorok){
                        System.out.println(b);
                    }
                    break;
                case "strength":
                    Collections.sort(sorok, new StrengthComparator());
                    for(Beer b : sorok){
                        System.out.println(b);
                    }
                    break;
                default:
                    System.out.println("Nem jó argumentumot adtál meg. Lehetséges opciók: 'name', 'style', 'strength'");
            }
        } else {
            for (Beer temp : sorok) {
                System.out.println(temp);
            }
        }
    }

    protected static void add(String[] cmd) {
        if(cmd.length != 4) {
            System.out.println("add parancs helyes használata: add <név> <jelleg> <alkoholfok>");
        }else{
            try {
                double fok = Double.parseDouble(cmd[3]);
                sorok.add(new Beer(cmd[1], cmd[2],  fok));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    protected static void search(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Search használata: search [attribútum] [név]");
        } else {
            switch (cmd[1]) {
                case "name":
                    for (Beer b : sorok) {
                        if (b.getName().equals(cmd[2])) {
                            System.out.println(b);
                        }
                    }
                    break;
                case "style":
                    for (Beer b : sorok) {
                        if (b.getStyle().equals(cmd[2])) {
                            System.out.println(b);
                        }
                    }
                    break;
                case "strength":
                    for (Beer b : sorok) {
                        try {
                            if (Double.parseDouble(cmd[2]) == b.getStrength()) {
                                System.out.println(b);
                            }
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "weaker":
                    for (Beer b : sorok) {
                        try {
                            if (Double.parseDouble(cmd[2]) > b.getStrength()) {
                                System.out.println(b);
                            }
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
            }
        }
    }

    protected static void find(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Find használata: find <attribútum> <karakter>");
        } else {
            switch (cmd[1]) {
                case "name":
                    for (Beer b : sorok) {
                        if (b.getName().contains(cmd[2])) {
                            System.out.println(b);
                        }
                    }
                    break;
                case "style":
                    for (Beer b : sorok) {
                        if (b.getStyle().contains(cmd[2])) {
                            System.out.println(b);
                        }
                    }
                    break;
                case "strength":
                    for (Beer b : sorok) {
                        try {
                            if (Double.parseDouble(cmd[2]) == b.getStrength()) {
                                System.out.println(b);
                            }
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "weaker":
                    for (Beer b : sorok) {
                        try {
                            if (Double.parseDouble(cmd[2]) > b.getStrength()) {
                                System.out.println(b);
                            }
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
            }
        }
    }
    protected static void delete(String[] cmd) {

        //TODO: This function should search and delete exact instances.
        boolean talalt = false;
        if(cmd.length != 2){
            System.out.println("Delete használata: delete [név]");
        } else {
            Iterator<Beer> it = sorok.iterator();
            while(it.hasNext()){
                if(it.next().getName().equals(cmd[1])){
                    it.remove();
                    talalt = true;
                }
            }
        }
        if(!talalt){
            System.out.println(cmd[1] + " nem létező sör");
        }
    }
}