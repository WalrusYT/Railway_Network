import Railway.*;

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
            case Commands.INSERT_LINE -> {}
            case Commands.REMOVE_LINE -> {}
            case Commands.LINE_STATIONS -> {}
            case Commands.STATION_LINES -> {}
            case Commands.INSERT_SCHEDULE -> {}
            case Commands.REMOVE_SCHEDULE -> {}
            case Commands.LIST_SCHEDULES -> {}
            case Commands.LIST_TRAINS -> {}
            case Commands.BEST_TIMETABLE -> {}
            case Commands.EXIT -> System.out.println(Feedback.BYE);
            default -> {}
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