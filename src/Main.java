import Railway.*;
import Railway.exceptions.*;
import dataStructures.*;
import java.io.*;
import java.util.Scanner;

/**
 * Main program for Railway Network
 * @author Ilia Taitsel (67258) i.taitsel@campus.fct.unl.pt
 * @author Oleksandra Kozlova (68739) o.kozlova@campus.fct.unl.pt
 */
public class Main {
    private static final String RAILWAY_FILE = "railway.dat";
    /**
     * Main method. Invokes the command interpreter
     * @param args command-line arguments (not used in this program)
     */
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

    /**
     * Saves Railway network into the file, when exiting from the application
     * @param rw instance of the railway network to save
     */
    private static void save(Railway rw) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RAILWAY_FILE));
            oos.writeObject(rw);
            oos.flush();
            oos.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Loads Railway Network from the file, if it's exists, otherwise create a new one
     * @return the instance of Railway - the railway network
     */
    @SuppressWarnings("unchecked")
    private static Railway load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RAILWAY_FILE));
            Railway rw = (Railway) ois.readObject();
            ois.close();
            return rw;
        } catch (IOException | ClassNotFoundException e) {
            return new RailwayClass();
        }
    }
    /**
     * Handle a command from an output and executes a corresponding method
     * @param in Scanner object to read user input
     * @param command String of a command from input
     * @param rw Railway object - the railway network
     */
    private static void handleCommand(Scanner in, String command, Railway rw) {
        switch (command) {
            case Commands.INSERT_LINE -> insertLine(in, rw);
            case Commands.REMOVE_LINE -> removeLine(in, rw);
            case Commands.LINE_STATIONS -> listStations(in, rw);
            // case Commands.STATION_LINES -> {}
            case Commands.INSERT_SCHEDULE -> insertSchedule(in, rw);
            case Commands.REMOVE_SCHEDULE -> removeSchedule(in, rw);
            case Commands.LIST_SCHEDULES -> listSchedules(in, rw);
            // case Commands.LIST_TRAINS -> {}
            case Commands.BEST_TIMETABLE -> bestTimetable(in, rw);
            case Commands.EXIT -> System.out.println(Feedback.BYE);
            default -> {}
        }
    }

    /**
     * Insertion of a line, given a name and a non-empty list of station names
     * @param in Scanner object to read user input
     * @param rw Railway object to that we want to insert a new line
     */
    public static void insertLine(Scanner in, Railway rw) {
        String name = in.nextLine().trim().toLowerCase();
        List<String> stations = new MyArrayList<>();
        String station;
        while (true) {
            station = in.nextLine();
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

    /**
     * Removal of a line with the given name
     * @param in Scanner object to read user input
     * @param rw Railway object from that we want to remove a line
     */
    public static void removeLine(Scanner in, Railway rw) {
        String name = in.nextLine().trim().toLowerCase();
        try {
            rw.removeLine(name);
            System.out.println(Feedback.LINE_REMOVED);
        } catch (LineNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lists the stations on a given line, if the line exists.
     * @param in Scanner object to read user input
     * @param rw Railway object from that we want to be informed about stations
     */
    public static void listStations(Scanner in, Railway rw) {
        String name = in.nextLine().trim();
        try {
            Iterator<Station> stations = rw.listStations(name);
            while (stations.hasNext()) {
                System.out.println(stations.next().getName());
            }
        } catch (LineNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a new schedule into the Railway Network
     * @param in Scanner object to read user input
     * @param rw Railway object to that we want to insert a schedule
     */
    public static void insertSchedule(Scanner in, Railway rw) {
        String name = in.nextLine().trim(); int number = Integer.parseInt(in.nextLine().trim());
        List<Entry<String, Time>> entries = new MyArrayList<>();
        while (true) {
            String input = in.nextLine();
            if (input.isEmpty()) break;
            String stationName = input.substring(0, input.lastIndexOf(' ')).trim();
            Time time = parseTime(input.substring(input.lastIndexOf(' ') + 1).trim());
            if (time == null) {
                System.out.println(Feedback.INVALID_SCHEDULE);
                return;
            }
            entries.addLast(new EntryClass<>(stationName, time));
        }
        try {
            rw.insertSchedule(name, number, entries);
            System.out.println(Feedback.SCHEDULE_INSERTED);
        } catch (InvalidScheduleException | LineNotExistsException |
                 StationNotExistsException | ScheduleNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removal of a schedule, if the line and schedule exist
     * @param in Scanner object to read user input
     * @param rw Railway object to that we want to insert a schedule
     */
    public static void removeSchedule(Scanner in, Railway rw) {
        String line = in.nextLine().trim();
        String input = in.nextLine().trim();
        String station = input.substring(0, input.lastIndexOf(' ')).trim();
        String timeStr = input.substring(input.lastIndexOf(' ') + 1).trim();
        Time time = parseTime(timeStr);
        if (time == null) {
            System.out.println(Feedback.INVALID_SCHEDULE);
            return;
        }
        try {
            rw.removeSchedule(line, station, time);
            System.out.println(Feedback.SCHEDULE_REMOVED);
        } catch (LineNotExistsException | ScheduleNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Time parsing from string (hh:mm) to an instance of {@link Time}
     * @param input time in the string format (hh:mm)
     * @return instance of {@link Time}
     */
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

    /**
     * Formats an instance of {@link Time} to {@link String}
     * @param time an instance that should be converted
     * @return a {@link String} in format (hh:mm) corresponding to the time
     */
    private static String timeToString(Time time) {
        return String.format("%02d:%02d", time.getHours(), time.getMinutes());
    }

    /**
     * Lists all the schedules for a given line, if the line exists
     * and the departure station is a terminal station
     * (determining the direction of travel direction)
     * @param in Scanner object to read user input
     * @param rw Railway object to that we want to list the schedules from
     */
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
                    System.out.printf("%s %s%n", entry.getStation().getName(),
                            timeToString(entry.getTime()));
                }
            }
        } catch (LineNotExistsException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines, if possible, the “best” route between two stations
     * (departure and destination) on a given line.
     * @param in Scanner object to read user input
     * @param rw Railway object that we want to find the best timetable from
     */
    private static void bestTimetable(Scanner in, Railway rw) {
        String name = in.nextLine().trim(), departureStation = in.nextLine().trim();
        String destinationStation = in.nextLine().trim(), arrivalTimeStr = in.nextLine().trim();
        try {
            Time arrivalTime = parseTime(arrivalTimeStr);
            if (arrivalTime == null) {
                System.out.println(Feedback.INVALID_SCHEDULE);
                return;
            }
            Schedule schedule = rw.bestTimetable(name, departureStation,
                    destinationStation, arrivalTime);
            Iterator<ScheduleClass.ScheduleEntry> entries = schedule.getEntries();
            System.out.println(schedule.getTrainNumber());
            while (entries.hasNext()) {
                ScheduleClass.ScheduleEntry entry = entries.next();
                System.out.printf("%s %s%n", entry.getStation().getName(),
                        timeToString(entry.getTime()));
            }
        } catch (LineNotExistsException | ImpossibleRouteException | StationNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Commands which allow users to interact with this program and the game
     */
    public static class Commands {
        public static final String
                INSERT_LINE = "il", REMOVE_LINE = "rl", LINE_STATIONS = "cl", STATION_LINES = "ce",
                INSERT_SCHEDULE = "ih", REMOVE_SCHEDULE = "rh", LIST_SCHEDULES = "ch", LIST_TRAINS = "lc",
                BEST_TIMETABLE = "mh", EXIT = "ta";
    }
    /**
     * Feedback given by the program
     */
    public static class Feedback {
        public static final String
                BYE = "Aplicação terminada.",
                LINE_INSERTED = "Inserção de linha com sucesso.",
                LINE_REMOVED = "Remoção de linha com sucesso.",
                SCHEDULE_INSERTED = "Criação de horário com sucesso.",
                INVALID_SCHEDULE = "Horário inválido.",
                SCHEDULE_REMOVED = "Remoção de horário com sucesso.";
    }
}