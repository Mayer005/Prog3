package lambeer;

import java.io.*;
import java.util.*;


public class Main{

    public static ArrayList<Beer> sorok = new ArrayList<>();
    private static Map<String, Command> commands = new HashMap<>();
    public static Map<String, Comparator<Beer>> comps = new HashMap<>();

    static {
        comps.put("name", Comparator.comparing(Beer::getName));
        comps.put("strength", Comparator.comparingDouble(Beer::getStrength));
        comps.put("style", Comparator.comparing(Beer::getStyle));
    }

    public static void main(String[] args) {
        commands.put("add", Main::add);
        commands.put("list", Main::list);
        commands.put("exit", Main::exit);
        commands.put("load", Main::load);
        commands.put("save", Main::save);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("delete", Main::delete);

        String[] cmd;
        Scanner sc = new Scanner(System.in);
        String oneline = "";
        System.out.println("S Ö R Ö K");

        while(true) {
            oneline = sc.nextLine();
            cmd = oneline.split(" ");
            String commandName = cmd[0];
            Command command = commands.get(commandName);
            if(command == null) {
                System.out.println("'" + commandName + "' egy nem létező parancs");
            } else {
                command.execute(cmd);
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

    protected static void exit(String[] cmd) {
        System.out.println("Kilépés...");
        System.exit(0);
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
            Comparator<Beer> cmp = comps.getOrDefault(cmd[1], comps.get("name"));
            sorok.sort(cmp);

            for(Beer b : sorok) {
                System.out.println(b);
            }
        } else {
            Comparator<Beer> cmp = comps.getOrDefault("name", comps.get("name"));
            sorok.sort(cmp);

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