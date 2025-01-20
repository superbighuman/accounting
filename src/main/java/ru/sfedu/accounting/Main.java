package ru.sfedu.accounting;
import java.util.Scanner;
public class Main {
    private static boolean running = true;
    public static void main(String[] args) {
        String version = "0.1";
        String helloMessage = "Добро пожаловать в программу учета v %s,\n чтобы узнать команды, введите help".formatted(version);
        System.out.println(helloMessage);
        while(running){
            pooling();
        }
    }
    private static void pooling(){
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        String[] params = query.split(" ");
        if (params.length == 0)
            return;
        else if(params[0].equals("help")){
            showHelp();
            return;
        }
    }
    private static void showHelp() {
        String help = "Введите следующие команды, чтобы обратиться к соответствующему меню:";
        String user = "user - добавление, изменение, удаление информации о пользователе";
        String technic = "technic - добавление, изменение, удаление информации о технике";
        String request = "request - добавление, изменение, удаление информации о запросах";
        String repair = "repair - добавление, изменение, удаление информации о ремонтах";
        String repairService = "repairService - добавление, изменение, удаление информации о сервисах ремонта";
        String processor = "processor - добавление, изменение, удаление информации о процессорах";
        String ram = "ram - добавление, изменение, удаление информации о модулях оперативной памяти";
        String permanentMemory = "permanentMemory - добавление, изменение, удаление информации о накопителях";

        System.out.println(help);
        System.out.println(user);
        System.out.println(technic);
        System.out.println(request);
        System.out.println(repair);
        System.out.println(repairService);
        System.out.println(processor);
        System.out.println(ram);
        System.out.println(permanentMemory);
    }


}