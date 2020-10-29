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
        cinemaRoom.drawTheCinema();

        System.out.println("Enter a row number:");
        int aRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int aSeat = scanner.nextInt();

        cinemaRoom.reserveSeat(aRow, aSeat);
    }
}

class CinemaRoom {

    private int numberOfRows;
    private int seatsInEachRow;
    private char[][] seats;
    private int numberOfSeats;

    public CinemaRoom(int numberOfRows, int seatsInEachRow) {
        this.numberOfRows = numberOfRows;
        this.seatsInEachRow = seatsInEachRow;
        seats = new char[numberOfRows + 1][seatsInEachRow + 1];
        setNumberOfSeats(numberOfRows * seatsInEachRow);
        generateSeats();

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

    public void generateSeats() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < seatsInEachRow; j++) {
                seats[i][j] = 's';
            }
        }
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void reserveSeat(int aRow, int aSeat) {
        seats[aRow - 1][aSeat - 1] = 'B';
        printTicketPrice(aRow);
        drawTheCinema();
    }

    void printTicketPrice(int aRow) {

        this.numberOfSeats = numberOfRows * seatsInEachRow;

        int ticketPrice;

        if (numberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (numberOfRows / 2 < aRow) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }
        }

        System.out.printf("\nTicket price: $%d\n\n", ticketPrice);
    }
}