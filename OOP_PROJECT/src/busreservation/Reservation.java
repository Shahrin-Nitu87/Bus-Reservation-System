package busreservation;

import java.sql.*;
import java.util.Scanner;

public class Reservation {
    Scanner sc = new Scanner(System.in);

    // check seat availability
    public void checkSeatAvailability(int busId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps1 = con.prepareStatement("SELECT seat FROM bus WHERE bus_id=?");
            ps1.setInt(1, busId);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                int totalSeat = rs1.getInt("seat");

                PreparedStatement ps2 = con.prepareStatement("SELECT COUNT(*) AS booked FROM reservation WHERE bus_id=?");
                ps2.setInt(1, busId);
                ResultSet rs2 = ps2.executeQuery();
                int bookedSeat = 0;
                if (rs2.next()){
                    bookedSeat = rs2.getInt("booked");
                }

                System.out.println("Bus ID: " + busId);
                System.out.println("Total Seats: " + totalSeat);
                System.out.println("Booked Seats: " + bookedSeat);
                System.out.println("Available Seats: " + (totalSeat - bookedSeat));
            } else {
                System.out.println("No bus found with ID " + busId);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Book Ticket
    public void bookTicket() {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Bus ID: ");
            int busId = sc.nextInt();
            sc.nextLine();

            
            PreparedStatement psBus = con.prepareStatement("SELECT bus_name FROM bus WHERE bus_id=?");
            psBus.setInt(1, busId);
            ResultSet rsBus = psBus.executeQuery();

            if (!rsBus.next()) {
                System.out.println("Invalid Bus ID! Ticket booking failed.");
                return;
            }
            String busName = rsBus.getString("bus_name");

            // Passenger info input
            System.out.print("Passenger Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Gender: ");
            String gender = sc.nextLine();
            System.out.print("Contact Number: ");
            String contact = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();

            // Passenger insert
            PreparedStatement psPassenger = con.prepareStatement(
                    "INSERT INTO passenger(passenger_name, age, gender, contact_num, email) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            psPassenger.setString(1, name);
            psPassenger.setInt(2, age);
            psPassenger.setString(3, gender);
            psPassenger.setString(4, contact);
            psPassenger.setString(5, email);
            psPassenger.executeUpdate();

            ResultSet rs = psPassenger.getGeneratedKeys();
            int passengerId = -1;
            if (rs.next()){
                passengerId = rs.getInt(1);
            }

            // Seat number & travel date
            System.out.print("Seat Number: ");
            int seatNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Travel Date (YYYY-MM-DD): ");
            String travelDate = sc.nextLine();

            // Reservation insert 
            PreparedStatement psReservation = con.prepareStatement(
                    "INSERT INTO reservation(bus_id, bus_name, passenger_id, passenger_name, seat_number, reservation_date, reservation_time, travel_date) VALUES (?,?,?,?,?,CURDATE(),CURTIME(),?)"
            );
            psReservation.setInt(1, busId);
            psReservation.setString(2, busName);
            psReservation.setInt(3, passengerId);
            psReservation.setString(4, name);
            psReservation.setInt(5, seatNo);
            psReservation.setString(6, travelDate);

            psReservation.executeUpdate();

            System.out.println("Ticket Booked Successfully!");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Reservations
    public void viewReservations() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservation");

            System.out.printf("%-5s %-10s %-20s %-10s %-20s %-6s %-12s %-10s %-12s\n",
                    "ResID", "BusID", "Bus Name", "PassID", "Passenger", "Seat", "Res Date", "Res Time", "Travel Date");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-10d %-20s %-10d %-20s %-6d %-12s %-10s %-12s\n",
                        rs.getInt("reservation_id"),
                        rs.getInt("bus_id"),
                        rs.getString("bus_name"),
                        rs.getInt("passenger_id"),
                        rs.getString("passenger_name"),
                        rs.getInt("seat_number"),
                        rs.getDate("reservation_date"),
                        rs.getTime("reservation_time"),
                        rs.getDate("travel_date")
                );
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cancel Reservation
    public void cancelReservation() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Reservation ID to Cancel: ");
            int id = sc.nextInt();
            PreparedStatement ps = con.prepareStatement("DELETE FROM reservation WHERE reservation_id=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Reservation Cancelled!");
            else System.out.println("Not Found!");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Reservation
    public void updateReservation() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Reservation ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("New Seat Number: ");
            int newSeat = sc.nextInt();
            sc.nextLine();
            System.out.print("New Travel Date (YYYY-MM-DD): ");
            String newDate = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE reservation SET seat_number=?, travel_date=? WHERE reservation_id=?"
            );
            ps.setInt(1, newSeat);
            ps.setString(2, newDate);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Reservation Updated!");
            else System.out.println("Reservation Not Found!");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


