import Railway.*;
import Railway.dataStructures.MyArrayList;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.LineAlreadyExistsException;
import Railway.exceptions.LineNotExistsException;
import Railway.exceptions.StationNotExistsException;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Railway rw = new RailwayClass();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.next().toLowerCase();
            handleCommand(in, command, rw);
        }
        while (!command.equals(Commands.EXIT));
        in.close();
    }
    private static void handleCommand(Scanner in, String command, Railway rw) {
        switch (command) {
            case Commands.INSERT_LINE -> insertLine(in, rw);
            case Commands.REMOVE_LINE -> removeLine(in, rw);
            case Commands.LINE_STATIONS -> listStations(in, rw);
            case Commands.STATION_LINES -> {}
            case Commands.INSERT_SCHEDULE -> insertSchedule(in, rw);
            case Commands.REMOVE_SCHEDULE -> {}
            case Commands.LIST_SCHEDULES -> listSchedules(in, rw);
            case Commands.LIST_TRAINS -> {}
            case Commands.BEST_TIMETABLE -> {}
            case Commands.EXIT -> System.out.println(Feedback.BYE);
            default -> {}
        }
    }

    public static void insertLine(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        List<String> stations = new MyArrayList<>();
        String station;
        while (true) {
            station = in.nextLine().toLowerCase();
            if (station.isEmpty()) break;
            stations.addLast(station);
        }
        try {
            rw.insertLine(name, stations);
            System.out.println(Feedback.LINE_INSERTED);
        } catch (LineAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLine(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        try {
            rw.removeLine(name);
            System.out.println(Feedback.LINE_REMOVED);
        } catch (LineNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listStations(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        try {
            Iterator<Station> stations = rw.listStations(name);
            System.out.println(name);
            while (stations.hasNext()) {
                System.out.println(stations.next().getName());
            }
        } catch (LineNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertSchedule(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        int number = Integer.parseInt(in.nextLine().trim());
        List<String> entries = new MyArrayList<>();
        while (true) {
            String stationAndTime = in.nextLine();
            if (stationAndTime.isEmpty()) break;
            entries.addLast(stationAndTime);
        }
        try {
            rw.insertSchedule(name, number, entries);
            System.out.println(Feedback.SCHEDULE_INSERTED);
        } catch (InvalidScheduleException | LineNotExistsException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listSchedules(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        String departureStation = in.nextLine().trim();
        try {
            Iterator<Schedule> schedules = rw.listSchedules(name, departureStation);
            while (schedules.hasNext()) {
                Schedule schedule = schedules.next();
                System.out.println(schedule.getTrainNumber());
                Iterator<ScheduleClass.ScheduleEntry> entries = schedule.getEntries();
                while (entries.hasNext()) {
                    ScheduleClass.ScheduleEntry entry = entries.next();
                    System.out.printf("%s %s%n", entry.getStation().getName(), entry.getTime().toString());
                }
            }
        } catch (InvalidScheduleException | LineNotExistsException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class Commands {
        public static final String
                INSERT_LINE = "il", REMOVE_LINE = "rl", LINE_STATIONS = "cl", STATION_LINES = "ce",
                INSERT_SCHEDULE = "ih", REMOVE_SCHEDULE = "rh", LIST_SCHEDULES = "ch", LIST_TRAINS = "lc",
                BEST_TIMETABLE = "mh", EXIT = "ta";
    }

    public static class Feedback {
        public static final String
                BYE = "Aplicação terminada.",
                LINE_INSERTED = "Inserção de linha com sucesso.",
                LINE_EXISTS = "Linha existente.",
                LINE_REMOVED = "Remoção de linha com sucesso.",
                LINE_NOT_EXIST = "Linha inexistente.",
                STATION_NOT_EXIST = "Station does not exist.",
                SCHEDULE_INSERTED = "Criação de horário com sucesso.",
                INVALID_SCHEDULE = "Horário inválido.",
                SCHEDULE_REMOVED = "Remoção de horário com sucesso.",
                SCHEDULE_NOT_EXIST = "Horário inexistente.",
                INVALID_STATION = "Estação de partida inexistente.",
                INVALID_ROUTE = "Percurso impossível.";
    }
}