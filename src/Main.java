import Railway.*;
import Railway.exceptions.*;
import dataStructures.*;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String RAILWAY_FILE = "railway.dat";
    public static void main(String[] args) {
        Railway rw = load();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.next().toLowerCase();
            handleCommand(in, command, rw);
        }
        while (!command.equals(Commands.EXIT));
        save(rw);
        in.close();
    }

    private static void save(Railway rw) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RAILWAY_FILE));
            oos.writeObject(rw);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Issue in writing");
        }
    }

    private static Railway load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RAILWAY_FILE));
            Railway rw = (Railway) ois.readObject();
            ois.close();
            return rw;
        } catch (IOException e) {
            System.out.println("Non existing serialization file: Creating new Object.");
            return new RailwayClass();
        } catch (ClassNotFoundException e) {
            System.out.println("Problems with serialization: Creating new Object.");
            return new RailwayClass();
        }
    }
    private static void handleCommand(Scanner in, String command, Railway rw) {
        switch (command) {
            case Commands.INSERT_LINE -> insertLine(in, rw);
            case Commands.REMOVE_LINE -> removeLine(in, rw);
            case Commands.LINE_STATIONS -> listStations(in, rw);
            case Commands.STATION_LINES -> {}
            case Commands.INSERT_SCHEDULE -> insertSchedule(in, rw);
            case Commands.REMOVE_SCHEDULE -> removeSchedule(in, rw);
            case Commands.LIST_SCHEDULES -> listSchedules(in, rw);
            case Commands.LIST_TRAINS -> {}
            case Commands.BEST_TIMETABLE -> bestTimetable(in, rw);
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
        List<Entry<String, Time>> entries = new MyArrayList<>();
        while (true) {
            String input = in.nextLine();
            if (input.isEmpty()) break;
            String[] stationAndTime = input.split(" ");
            String stationName = stationAndTime[0];
            Time time = parseTime(stationAndTime[1]);
            if (time == null) {
                System.out.println(Feedback.INVALID_SCHEDULE);
                return;
            }
            entries.addLast(new EntryClass<>(stationName, time));
        }
        try {
            rw.insertSchedule(name, number, entries);
            System.out.println(Feedback.SCHEDULE_INSERTED);
        } catch (InvalidScheduleException | LineNotExistsException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeSchedule(Scanner in, Railway rw) {
        String line = in.nextLine().trim();
        String station = in.next().trim();
        String timeStr = in.nextLine().trim();
        Time time = parseTime(timeStr);
        if (time == null) {
            System.out.println(Feedback.INVALID_SCHEDULE);
            return;
        }
        try {
            rw.removeSchedule(line, station, time);
            System.out.println(Feedback.SCHEDULE_REMOVED);
        } catch (LineNotExistsException | InvalidScheduleException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Time parseTime(String input) {
        String[] hoursAndMinutes = input.split(":");
        if (hoursAndMinutes.length != 2) return null;
        int h, m;
        try {
            h = Integer.parseInt(hoursAndMinutes[0]);
            m = Integer.parseInt(hoursAndMinutes[1]);
        } catch (NumberFormatException e) {
            return null;
        }
        return new Time(h, m);
    }

    private static String timeToString(Time time) {
        return String.format("%02d:%02d", time.getHours(), time.getMinutes());
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
                    System.out.printf("%s %s%n", entry.getStation().getName(), timeToString(entry.getTime()));
                }
            }
        } catch (InvalidScheduleException | LineNotExistsException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void bestTimetable(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        String departureStation = in.nextLine().trim();
        String destinationStation = in.nextLine().trim();
        String arrivalTimeStr = in.nextLine().trim();
        try {
            Time arrivalTime = parseTime(arrivalTimeStr);
            if (arrivalTime == null) {
                System.out.println(Feedback.INVALID_SCHEDULE);
                return;
            }
            Schedule schedule = rw.bestTimetable(name, departureStation, destinationStation, arrivalTime);
            Iterator<ScheduleClass.ScheduleEntry> entries = schedule.getEntries();
            while (entries.hasNext()) {
                ScheduleClass.ScheduleEntry entry = entries.next();
                System.out.printf("%s %s%n", entry.getStation().getName(), entry.getTime().toString());
            }
        } catch (LineNotExistsException | ImpossibleRouteException | StationNotExistsException e) {
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