package ru.sfedu.accounting;
import org.apache.log4j.Logger;
import ru.sfedu.accounting.Models.Request;
import ru.sfedu.accounting.Models.Technic;
import ru.sfedu.accounting.Models.User;
import ru.sfedu.accounting.PostgresAPI.Create;
import ru.sfedu.accounting.PostgresAPI.Read;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Logger logger= Logger.getLogger(Main.class);
    private static boolean running = true;
    private static final boolean DEBUG = true;

    private static final String USERS_RELATION = "users";
    public static void main(String[] args) {
        if (!DEBUG){
            Logger.getRootLogger().removeAppender("stdout");
        }
        boolean initialisationSuccessful = Create.initialisation();
        logger.info("Инициализация - " + initialisationSuccessful);
        String version = "0.1";
        String helloMessage = "Добро пожаловать в программу учета v %s,\n чтобы узнать команды, введите help".formatted(version);
        System.out.println(helloMessage);
        pooling();

    }
    private static void pooling() {
        while (running) {
            Scanner sc = new Scanner(System.in);
            String query = sc.nextLine();
            String[] params = query.split(" ");
            if (params.length == 0)
                return;
            else if (params[0].equals("help")) {
                showHelp();
            }
            else if (params[0].equals("user")){
                userMenu();
            }
            else if (params[0].equals("technic")){
                technicMenu();
            }
            else if (params[0].equals("request")){
                requestMenu();
            }
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
        String exit = "exit - возвращает на один уровень меню назад";
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
    private static void userMenu() {
        String add = "add (ИНН, Имя, Фамилия, Рабочее место) - добавляет нового пользователя";
        String update = "update (ИНН, параметр, новое значение) - обновляет информацию о пользователе";
        String delete = "delete (ИНН) - удаляет пользователя по указанному ИНН";
        String read = "read (ИНН) - выводит информацию о пользователе с указанным ИНН";
        String list = "list - выводит список всех пользователей";

        System.out.println("Меню управления пользователями:");
        System.out.println(add);
        System.out.println(update);
        System.out.println(delete);
        System.out.println(read);
        System.out.println(list);

        while(true){
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            String[] query = line.split(" ");
            boolean queryResult = false;
            if (query.length == 0)
                continue;
            else if (query[0].equals("add")) {
                queryResult = addUser(query);
            }
            else if (query[0].equals("list")){
                Read PSQLRead = new Read(USERS_RELATION);
                queryResult = !PSQLRead.select("*").isEmpty();
                if(queryResult){
                    Optional<ResultSet> resultSet = PSQLRead.select("*");
                    printSelect(resultSet);
                }

            }
            else if (query[0].equals("delete")){
                queryResult = deleteUser(query);
            }
            else if(query[0].equals("update"))
                queryResult = updateUser(query);
            else if(query[0].equals("read"))
                queryResult = readUser(query);

            if (!queryResult){
                System.out.println("Ошибка в записи");
            }
            else{
                System.out.println("Команда успешно выполнена");
            }
        }
    }
    private static boolean addUser(String[] query){
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 4)
            return false;
        String inn = params.get(0);
        String name = params.get(1);
        String surname = params.get(2);
        String workPlace = params.get(3);

        if (isINN(inn)) {
            User user = new User(inn, name, surname, workPlace);
            boolean result = user.insertRecord();
            return result;
        }
        return false;
    }
    private static boolean deleteUser(String[] query){
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;
        String inn = params.get(0);
        if (isINN(inn)){
            User user = new User(inn);
            boolean result = user.deleteRecord();
            return result;
        }
        return false;
    }
    private static boolean updateUser(String[] query){
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 3)
            return false;
        String inn = params.get(0);
        if (isINN(inn)) {
            String attr = params.get(1);
            String newValue = params.get(2);
            User user = new User(inn);
            Class c = user.getClass();
            try {
                Method m = c.getMethod("set" + attr.substring(0, 1).toUpperCase() + attr.substring(1), String.class);
                m.invoke(user,newValue);
                user.updateRecord();
            }
            catch (Exception e){
                logger.info(e);
                return false;
            }
            boolean result = user.updateRecord();
            return result;
        }
        return false;
    }
    private static boolean readUser(String[] query){
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;
        String inn = params.get(0);
        if (isINN(inn)){
            User user = new User(inn);
            Read PSQLread = new Read(USERS_RELATION);
            printSelect(PSQLread.where("*", user.keyGet(), user.getItems().get(user.keyGet())));
            return true;
        }
        return false;
    }
    private static boolean isINN(String inn){
        Pattern pattern = Pattern.compile("\\d{12}");
        Matcher matcher = pattern.matcher(inn);
        return matcher.matches();
    }
    private static ArrayList<String> prepareParams(String[] query){
        ArrayList<String> params = new ArrayList<>(List.of(query));
        ArrayList<String> result = new ArrayList<>();
        params.remove(0);
        params.stream().map(x ->x.replaceAll("[,(=]", "")).forEach(result::add);

        return result;
    }
    private static void printSelect(Optional<ResultSet> resultSet){
        if (!resultSet.isEmpty()){
            ResultSet set = resultSet.get();
            try {
                int columnCount = set.getMetaData().getColumnCount();
                while (set.next()) {
                    for (int i = 1; i <= columnCount; i++)
                        System.out.print(set.getString(i) + " ");
                    System.out.println();
                }

            }
            catch (SQLException e){
                logger.info(e);
            }
        }
    }
    private static void technicMenu() {
        String add = "add (Инвентарный номер, Тип, Компьютер (true/false), Рабочее место, ИНН владельца) - добавляет новую технику";
        String update = "update (Инвентарный номер, параметр, новое значение) - обновляет информацию о технике";
        String delete = "delete (Инвентарный номер) - удаляет технику по указанному инвентарному номеру";
        String read = "read (Инвентарный номер) - выводит информацию о технике с указанным инвентарным номером";
        String list = "list - выводит список всей техники";

        System.out.println("Меню управления техникой:");
        System.out.println(add);
        System.out.println(update);
        System.out.println(delete);
        System.out.println(read);
        System.out.println(list);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            String[] query = line.split(" ");
            boolean queryResult = false;

            if (query.length == 0)
                continue;
            else if (query[0].equals("add")) {
                queryResult = addTechnic(query);
            } else if (query[0].equals("list")) {
                Read PSQLRead = new Read(Technic.TECHNIC_RELATION);
                queryResult = !PSQLRead.select("*").isEmpty();
                if (queryResult) {
                    Optional<ResultSet> resultSet = PSQLRead.select("*");
                    printSelect(resultSet);
                }
            } else if (query[0].equals("delete")) {
                queryResult = deleteTechnic(query);
            } else if (query[0].equals("update")) {
                queryResult = updateTechnic(query);
            } else if (query[0].equals("read")) {
                queryResult = readTechnic(query);
            }

            if (!queryResult) {
                System.out.println("Ошибка в записи");
            } else {
                System.out.println("Команда успешно выполнена");
            }
        }
    }

    private static boolean addTechnic(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 5)
            return false;

        String individualNumber = params.get(0);
        String type = params.get(1);
        boolean isComputer = Boolean.parseBoolean(params.get(2));
        String workingPlace = params.get(3);
        String ownerINN = params.get(4);

        Technic technic = new Technic(individualNumber, type, isComputer, workingPlace, ownerINN);
        return technic.insertRecord();
    }

    private static boolean deleteTechnic(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;

        String individualNumber = params.get(0);
        Technic technic = new Technic(individualNumber, "", false, "", "");
        return technic.deleteRecord();
    }

    private static boolean updateTechnic(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 3)
            return false;

        String individualNumber = params.get(0);
        String attr = params.get(1);
        String newValue = params.get(2);

        Technic technic = new Technic(individualNumber, "", false, "", "");
        Class<?> c = technic.getClass();

        try {
            Method m = c.getMethod("set" + attr.substring(0, 1).toUpperCase() + attr.substring(1), String.class);
            m.invoke(technic, newValue);
            technic.updateRecord();
        } catch (Exception e) {
            logger.info(e);
            return false;
        }
        return technic.updateRecord();
    }

    private static boolean readTechnic(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;

        String individualNumber = params.get(0);
        Technic technic = new Technic(individualNumber, "", false, "", "");
        Read PSQLread = new Read(Technic.TECHNIC_RELATION);
        printSelect(PSQLread.where("*", technic.keyGet(), technic.getItems().get(technic.keyGet())));
        return true;
    }
    private static void requestMenu() {
        String add = "add (Тело запроса, Статус, ИНН владельца, ИНН менеджера) - добавляет новый запрос";
        String update = "update (ID, параметр, новое значение) - обновляет информацию о запросе";
        String delete = "delete (ID) - удаляет запрос по указанному ID";
        String read = "read (ID) - выводит информацию о запросе с указанным ID";
        String list = "list - выводит список всех запросов";

        System.out.println("Меню управления запросами:");
        System.out.println(add);
        System.out.println(update);
        System.out.println(delete);
        System.out.println(read);
        System.out.println(list);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            String[] query = line.split(" ");
            boolean queryResult = false;

            if (query.length == 0)
                continue;
            else if (query[0].equals("add")) {
                queryResult = addRequest(query);
            } else if (query[0].equals("list")) {
                Read PSQLRead = new Read(Request.REQUEST_RELATION);
                queryResult = !PSQLRead.select("*").isEmpty();
                if (queryResult) {
                    Optional<ResultSet> resultSet = PSQLRead.select("*");
                    printSelect(resultSet);
                }
            } else if (query[0].equals("delete")) {
                queryResult = deleteRequest(query);
            } else if (query[0].equals("update")) {
                queryResult = updateRequest(query);
            } else if (query[0].equals("read")) {
                queryResult = readRequest(query);
            }

            if (!queryResult) {
                System.out.println("Ошибка в записи");
            } else {
                System.out.println("Команда успешно выполнена");
            }
        }
    }

    private static boolean addRequest(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 4)
            return false;

        String body = params.get(0);
        String status = params.get(1);
        String ownerINN = params.get(2);
        String managerINN = params.get(3);

        Request request = new Request(body, status, ownerINN, managerINN);
        return request.insertRecord();
    }

    private static boolean deleteRequest(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;

        String id = params.get(0);
        Request request = new Request("", "", "", "");
        request.setId(UUID.fromString(id));
        return request.deleteRecord();
    }

    private static boolean updateRequest(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 3)
            return false;

        String id = params.get(0);
        String attr = params.get(1);
        String newValue = params.get(2);

        Request request = new Request("", "", "", "");
        request.setId(UUID.fromString(id));
        Class<?> c = request.getClass();

        try {
            Method m = c.getMethod("set" + attr.substring(0, 1).toUpperCase() + attr.substring(1), String.class);
            m.invoke(request, newValue);
            request.updateRecord();
        } catch (Exception e) {
            logger.info(e);
            return false;
        }
        return request.updateRecord();
    }

    private static boolean readRequest(String[] query) {
        ArrayList<String> params = prepareParams(query);
        if (params.size() != 1)
            return false;

        String id = params.get(0);
        Request request = new Request("", "", "", "");
        request.setId(UUID.fromString(id));
        Read PSQLread = new Read(Request.REQUEST_RELATION);
        printSelect(PSQLread.where("*", request.keyGet(), request.getId().toString()));
        return true;
    }






}