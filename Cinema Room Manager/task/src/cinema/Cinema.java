package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInEachRow = scanner.nextInt();

        CinemaRoom cinemaRoom = new CinemaRoom(numberOfRows, seatsInEachRow);
        cinemaRoom.openCinema();
    }
}

class CinemaRoom {

    private int numberOfRows;
    private int seatsInEachRow;
    private char[][] seats;
    private int numberOfSeats;
    private Scanner scanner = new Scanner(System.in);

    private int tickets = 0;
    private int income = 0;
    private int totalIncome = 0;
    private int frontRows;
    private int backRows;

    public CinemaRoom(int numberOfRows, int seatsInEachRow) {
        this.numberOfRows = numberOfRows;
        this.seatsInEachRow = seatsInEachRow;

        setNumberOfSeats(numberOfRows * seatsInEachRow);
        setFrontRows(numberOfRows / 2);
        setBackRows(numberOfRows - frontRows);
        setTotalIncome();

        seats = new char[numberOfRows + 1][seatsInEachRow + 1];
        generateSeats();

    }

    void openCinema() {

        boolean exit = false;

        while (!exit) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            String option = scanner.next();

            switch (option) {
                case "1":
                    drawTheCinema();
                    break;
                case "2":
                    reserveSeat();
                    break;
                case "3":
                    statistics();
                    break;
                case "0":
                    exit = true;
            }
        }

    }

    void statistics() {
        System.out.printf("\nNumber of purchased tickets: %d", tickets);
        double percentage = (double) tickets * 100 / numberOfSeats;
        System.out.printf("\nPercentage: %.2f", percentage);
        System.out.print("%");
        System.out.printf("\nCurrent income: $%d", income);
        System.out.printf("\nTotal income: $%d\n", totalIncome);
    }

    void drawTheCinema() {
        System.out.println("Cinema:");

        for (int i = 0; i < seatsInEachRow; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsInEachRow; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    void generateSeats() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < seatsInEachRow; j++) {
                seats[i][j] = 's';
            }
        }
    }

    void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setFrontRows(int frontRows) {
        this.frontRows = frontRows;
    }

    public void setBackRows(int backRows) {
        this.backRows = backRows;
    }

    void reserveSeat() {

        System.out.println("Enter a row number:");
        int aRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int aSeat = scanner.nextInt();

        if (aRow > 0 && aRow <= numberOfRows && aSeat > 0 && aSeat <= seatsInEachRow) {
            if (seats[aRow - 1][aSeat - 1] != 'B') {
                seats[aRow - 1][aSeat - 1] = 'B';
                printTicketPrice(aRow);
                tickets++;
            } else {
                System.out.println("That ticket has already been purchased!");
                reserveSeat();
            }
        } else {
            System.out.println("Wrong input!");
            reserveSeat();
        }
    }

    void printTicketPrice(int aRow) {
        int ticketPrice;

        if (numberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (frontRows < aRow) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }
        }
        income += ticketPrice;
        System.out.printf("\nTicket price: $%d\n\n", ticketPrice);
    }

    void setTotalIncome() {
        if (numberOfSeats <= 60) {
            totalIncome = numberOfSeats * 10;
        } else {
            totalIncome = frontRows * seatsInEachRow * 10
                    + backRows * seatsInEachRow * 8;
        }
    }
}