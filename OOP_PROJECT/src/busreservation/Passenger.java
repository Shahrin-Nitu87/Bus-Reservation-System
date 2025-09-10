package busreservation;

import java.sql.*;
import java.util.Scanner;

public class Passenger {
    Scanner sc = new Scanner(System.in);

    public int addPassenger() {
        int id = -1;
        try (Connection con = DBConnection.getConnection()) {
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

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO passenger(passenger_name, age, gender, contact_num, email) VALUES(?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, contact);
            ps.setString(5, email);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            System.out.println("Passenger Added! ID: "+id);
            System.out.println();
        } 
        catch (Exception e) { 
            e.printStackTrace();
        }
        return id;
    }
}


