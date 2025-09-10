package busreservation;

import java.sql.*;
import java.util.Scanner;

public class Bus {
    Scanner sc = new Scanner(System.in);

    // Add Bus
    public void addBus() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Bus Name: ");
            String name = sc.nextLine();
            System.out.print("Source: ");
            String source = sc.nextLine();
            System.out.print("Destination: ");
            String destination = sc.nextLine();
            System.out.print("Seat Capacity: ");
            int seat = sc.nextInt();
            sc.nextLine();
            System.out.print("Starting Time: ");
            String time = sc.nextLine();
            System.out.print("Starting Place: ");
            String place = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bus(bus_name, source, destination, seat, starting_time, starting_place) VALUES(?,?,?,?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, source);
            ps.setString(3, destination);
            ps.setInt(4, seat);
            ps.setString(5, time);
            ps.setString(6, place);
            ps.executeUpdate();
            System.out.println("Bus Added Successfully!");
            System.out.println();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBuses() {
    try (Connection con = DBConnection.getConnection()) {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM bus");

        System.out.printf("%-5s %-20s %-15s %-15s %-6s %-10s %-15s\n", 
                          "ID", "Bus Name", "Source", "Destination", "Seat", "Time", "Start Place");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-5d %-20s %-15s %-15s %-6d %-10s %-15s\n",
                rs.getInt("bus_id"),
                rs.getString("bus_name"),
                rs.getString("source"),
                rs.getString("destination"),
                rs.getInt("seat"),
                rs.getString("starting_time"),
                rs.getString("starting_place")
            );
        }
        System.out.println();
    } 
    catch (Exception e) { 
        e.printStackTrace();
    }
}


    // Remove Bus
    public void removeBus() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Bus ID: ");
            int id = sc.nextInt();
            PreparedStatement ps = con.prepareStatement("DELETE FROM bus WHERE bus_id=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Bus Removed!");
            else System.out.println("Bus Not Found!");
            System.out.println();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Bus
   public void searchBus() {
    try (Connection con = DBConnection.getConnection()) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Source: ");
        String source = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM bus WHERE source=? AND destination=?");
        ps.setString(1, source);
        ps.setString(2, dest);
        ResultSet rs = ps.executeQuery();

        System.out.printf("%-5s %-20s %-15s %-15s %-6s %-10s\n", "ID", "Bus Name", "Source", "Destination", "Seat", "Time");
        System.out.println("---------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-5d %-20s %-15s %-15s %-6d %-10s\n",
                rs.getInt("bus_id"),
                rs.getString("bus_name"),
                rs.getString("source"),
                rs.getString("destination"),
                rs.getInt("seat"),
                rs.getString("starting_time")
            );
        }
        System.out.println();
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}

// Update Bus
public void updateBus() {
    try (Connection con = DBConnection.getConnection()) {
        System.out.print("Bus ID : ");
        int id = sc.nextInt(); 
        sc.nextLine();

        System.out.print("New Starting Time: ");
        String newTime = sc.nextLine();

        
        PreparedStatement ps = con.prepareStatement("UPDATE bus SET starting_time=? WHERE bus_id=?");
        ps.setString(1, newTime);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("Bus Time Updated Successfully!");
        } else {
            System.out.println("Bus Not Found!");
        }
        System.out.println();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


 


}
