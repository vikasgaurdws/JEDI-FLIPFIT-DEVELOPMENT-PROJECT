package com.flipkart.DAO;

import com.flipkart.bean.*;
import com.flipkart.utils.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerDAOImpl implements FlipFitCustomerDAO {
    
    private Connection connection = null;
    


    @Override
     public List<FlipFitGym> getAvailableGyms() {
        List<FlipFitGym> gyms = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT gymID, gymName, gymLocation, availableSlot, price, flagVerified FROM FlipFitGym Where flagVerified= true ";

        try {
            connection = DBconnection.getConnection();
            
            statement = connection.prepareStatement(query);
            
            rs = statement.executeQuery();

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(rs.getInt("gymID"));
                gym.setGymName(rs.getString("gymName"));
                gym.setGymLocation(rs.getString("gymLocation"));
                gym.setAvailableSlot(rs.getInt("availableSlot"));
                gym.setPrice(rs.getInt("price"));
                gym.setFlagVerified(rs.getBoolean("flagVerified"));
                gyms.add(gym);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return gyms;
    }
    
    @Override
    public List<FlipFitGym> getGymsByLocation(String location) {
        List<FlipFitGym> gyms = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String query = "SELECT gymID, gymName, gymLocation, availableSlot, price, flagVerified FROM gym WHERE flagVerified= true AND  gymLocation LIKE ?";

        try {
            connection = DBconnection.getConnection();
            
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + location + "%"); 

            rs = statement.executeQuery();

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(rs.getInt("gymID"));
                gym.setGymName(rs.getString("gymName"));
                gym.setGymLocation(rs.getString("gymLocation"));
                gym.setAvailableSlot(rs.getInt("availableSlot"));
                gym.setPrice(rs.getInt("price"));
                gym.setFlagVerified(rs.getBoolean("flagVerified"));
                gyms.add(gym);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return gyms;
    }
    
	    
	@Override
	public List<Slot> getSlots(int gymId) {
	        List<Slot> slots = new ArrayList<>();
	        PreparedStatement statement = null;
	        ResultSet rs = null;
	
	        String query = "SELECT gymSlotID, gymID, startTime, capacity, availableSeats FROM FlipfitGymSlot WHERE gymID = ?";
	
	        try {
	            connection = DBconnection.getConnection();
	            
	            statement = connection.prepareStatement(query);
	            statement.setInt(1, gymId);
	
	            rs = statement.executeQuery();
	
	            while (rs.next()) {
	                Slot slot = new Slot();
	                slot.setSlotId(rs.getInt("gymSlotID"));
	                slot.setGymId(rs.getInt("gymID"));
	                slot.setStartTime(rs.getTime("startTime").toLocalTime()); 
	                slot.setCapacity(rs.getInt("capacity"));
	                slot.setAvailableSeats(rs.getInt("availableSeats"));
	                slots.add(slot);
	            }
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        
	        return slots;
	    }
	
	
	//check the bookings table for any clashing entries for the userId that have exactly same starting time and  date
	//if any such record is found replace
	
	public int bookSlots(int userId, int slotId, int gymId, Date bookingDate) {
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    int bookingId = -1;
	    Time slotStartTime = getSlotStartTime(slotId);

	    // Check if a conflicting booking exists
	    String checkQuery = "SELECT bookingID, gymID, gymSlotID, bookingDate FROM FlipFitGymBookSlot " +
	                        "WHERE userID = ? AND gymSlotID IN (SELECT gymSlotID FROM FlipFitGymSlot WHERE startTime = ?) " +
	                        "AND bookingDate = ?";

	    try {
	        connection = DBconnection.getConnection();
	        statement = connection.prepareStatement(checkQuery);
	        statement.setInt(1, userId);
	        statement.setTime(2, slotStartTime);
	        statement.setDate(3, bookingDate);
	        rs = statement.executeQuery();

            // Conflicts found get their details need to implement the waitlist part 
	        if (rs.next()) {
	            bookingId = rs.getInt("bookingID");
	            int existingGymId = rs.getInt("gymID");
	            int existingSlotId = rs.getInt("gymSlotID");
	            Date existingDate = rs.getDate("bookingDate");

	            System.out.println("Conflict found! Deleting previous booking: " + bookingId);
	            
	            
	            String deleteQuery = "DELETE FROM FlipFitGymBookSlot WHERE bookingID = ?";
	            PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
	            deleteStmt.setInt(1, bookingId);
	            deleteStmt.executeUpdate();
	        }
	        
	        // Insert the new booking
	        String insertQuery = "INSERT INTO FlipFitGymBookSlot (userID, gymSlotID, gymID, bookingDate, bookingStatus) " +
	                             "VALUES (?, ?, ?, ?, 'Confirmed')";
	        PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
	        insertStmt.setInt(1, userId);
	        insertStmt.setInt(2, slotId);
	        insertStmt.setInt(3, gymId);
	        insertStmt.setDate(4, bookingDate);
	        insertStmt.executeUpdate();
	        
	       ResultSet generatedKeys = insertStmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            bookingId = generatedKeys.getInt(1);
	        }
	        
//	        System.out.println("New booking added with ID: " + bookingId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return bookingId;
	}


	// Helper method to get the slot's start time
	private Time getSlotStartTime(int slotId) {
	    Time startTime = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    
	    String query = "SELECT startTime FROM FlipfitGymSlot WHERE gymSlotID = ?";
	    
	    try {
	        connection = DBconnection.getConnection();
	        
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, slotId);
	        
	        rs = statement.executeQuery();
	        
	        if (rs.next()) {
	            startTime = rs.getTime("startTime");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Ensure resources are closed
	        try {
	            if (rs != null) rs.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    return startTime;
	}


	
	
	//search the number of booked slots for the given slotId and date combination

	@Override
 	public int bookedSeats(int slotId, LocalDate date) {
	    int count = 0;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    
	    String query = "SELECT COUNT(*) FROM FlipFitGymBookSlot WHERE slotID = ? AND bookingDate = ? AND bookingStatus = 'Confirmed'";
	    
	    try {
	        connection = DBconnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, slotId);
	        statement.setDate(2, java.sql.Date.valueOf(date));
	        
	        rs = statement.executeQuery();
	        
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return count;
	}

	
	
	
	@Override
	public List<Booking> viewBookings(int userId) {
	    List<Booking> bookings = new ArrayList<>();
	    PreparedStatement statement = null;
	    ResultSet rs = null;

	    String query = "SELECT * FROM booking WHERE userId = ?";

	    try {
	        connection = DBconnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);

	        rs = statement.executeQuery();

	        while (rs.next()) {
	            Booking booking = new Booking();
	            booking.setBookingId(rs.getInt("bookingID"));
	            booking.setUserId(rs.getInt("userID"));
	            booking.setSlotId(rs.getInt("gymslotID"));
	            booking.setGymId(rs.getInt("gymID"));
//	            booking.setTime(rs.getTime("time").toLocalTime());
	            booking.setBookingStatus(rs.getString("bookingStatus"));
	            booking.setBookingDate(rs.getDate("bookingDate").toLocalDate());
	            
	            bookings.add(booking);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return bookings;
	}

	@Override
	public void cancelBooking(int userId, int bookingId) {
	    PreparedStatement statement = null;

	    // SQL query to delete the booking
	    String query = "DELETE FROM FlipFitBooking WHERE userID = ? AND bookingID = ?";

	    try {
	        connection = DBconnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        statement.setInt(2, bookingId);

	        int affectedRows = statement.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Booking deleted successfully.");
	        } else {
	            System.out.println("No booking found with the given details.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	
	public void updateCustomerProfile(FlipFitCustomer flipFitCustomer) {
	    PreparedStatement statementUser = null;
	    PreparedStatement statementCustomer = null;
	    
	    // SQL queries to update FlipFitUser and FlipFitCustomer
	    String queryUser = "UPDATE FlipFitUser SET userName = ?, phoneNumber = ?, passwordHash = ? WHERE userID = ?";
	    String queryCustomer = "UPDATE FlipFitCustomer SET customerAge = ? WHERE userID = ?";

	    try {
	        connection = DBconnection.getConnection();
	        
	        // Start a transaction
	        connection.setAutoCommit(false);

	        // Update FlipFitUser table
	        statementUser = connection.prepareStatement(queryUser);
	        statementUser.setString(1, flipFitCustomer.getUserName());
	        statementUser.setString(2, flipFitCustomer.getUserMobile());
	        statementUser.setString(3, flipFitCustomer.getUserPassword());
	        statementUser.setInt(4, flipFitCustomer.getUserId());
	        
	        int userRowsUpdated = statementUser.executeUpdate();
	        
	        // Check if the user update was successful
	        if (userRowsUpdated == 0) {
	            System.out.println("No user found with the given userId.");
	            connection.rollback();
	            return;
	        }

	        // Update FlipFitCustomer table
	        statementCustomer = connection.prepareStatement(queryCustomer);
	        statementCustomer.setInt(1, flipFitCustomer.getCustomerAge());
	        statementCustomer.setInt(2, flipFitCustomer.getUserId());
	        
	        int customerRowsUpdated = statementCustomer.executeUpdate();
	        
	        // Check if the customer update was successful
	        if (customerRowsUpdated == 0) {
	            System.out.println("No customer profile found for the given userId.");
	            connection.rollback();
	            return;
	        }

	        // Commit the transaction if both updates are successful
	        connection.commit();
	        System.out.println("Customer profile updated successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } 
	    
	}

	
	@Override
	public FlipFitCustomer getCustomerProfile(int userId) {
	    FlipFitCustomer flipFitCustomer = null;
	    PreparedStatement statementUser = null;
	    PreparedStatement statementCustomer = null;
	    ResultSet rsUser = null;
	    ResultSet rsCustomer = null;

	    // SQL queries to get data from FlipFitUser and FlipFitCustomer
	    String queryUser = "SELECT userID, userName, email, passwordHash, phoneNumber FROM FlipFitUser WHERE userID = ?";
	    String queryCustomer = "SELECT customerAge FROM FlipFitCustomer WHERE userID = ?";

	    try {
	        connection = DBconnection.getConnection();
	        
	        // Get data from FlipFitUser table
	        statementUser = connection.prepareStatement(queryUser);
	        statementUser.setInt(1, userId);
	        rsUser = statementUser.executeQuery();

	        if (rsUser.next()) {
	            // Create FlipFitCustomer object and populate basic user details
	            flipFitCustomer = new FlipFitCustomer();
	            flipFitCustomer.setUserId(rsUser.getInt("userID"));
	            flipFitCustomer.setUserName(rsUser.getString("userName"));
	            flipFitCustomer.setUserEmail(rsUser.getString("email"));
	            flipFitCustomer.setUserPassword(rsUser.getString("passwordHash"));
	            flipFitCustomer.setUserMobile(rsUser.getString("phoneNumber"));
	            
	            // Get data from FlipFitCustomer table (customerAge)
	            statementCustomer = connection.prepareStatement(queryCustomer);
	            statementCustomer.setInt(1, userId);
	            rsCustomer = statementCustomer.executeQuery();

	            if (rsCustomer.next()) { 
	                flipFitCustomer.setCustomerAge(rsCustomer.getInt("customerAge"));
	            } else {
	                // Handle case where customer-specific info is not found
	                System.out.println("No customer profile found for userId: " + userId);
	            }
	        } else {
	            // Handle case where the user is not found
	            System.out.println("No user found with userId: " + userId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 

	    return flipFitCustomer;
	}

	@Override
	public void makePayment(int gymId, String paymentType, int bookingId) {
		// TODO Auto-generated method stub
		
	}
	
	private int getRoleIDFromRole(String roleName)
	{
		int roleId=-1;
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    
	    String query = "SELECT roleID FROM Role WHERE roleName = ?";
	    
	    try {
	        connection = DBconnection.getConnection();
	        
	        statement = connection.prepareStatement(query);
	        statement.setString(1, roleName);
	        
	        rs = statement.executeQuery();
	        
	        if (rs.next()) {
	        	roleId = rs.getInt("roleID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Ensure resources are closed
	        try {
	            if (rs != null) rs.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    return roleId;

		
	}
	
	@Override
	public int registerCustomer(FlipFitCustomer flipFitCustomer) {
	    PreparedStatement statementUser = null;
	    PreparedStatement statementCustomer = null;
	    ResultSet generatedKeys = null;
	    int userId = -1;
	    
	    int roleId= getRoleIDFromRole("Customer");

	    String queryUser = "INSERT INTO FlipFitUser (userName, email, passwordHash, phoneNumber, roleID) VALUES (?, ?, ?, ?, ?)";

	    String queryCustomer = "INSERT INTO FlipFitCustomer (userID, customerAge) VALUES (?, ?)";

	    try {
	        connection = DBconnection.getConnection();
	        connection.setAutoCommit(false); // Start transaction

	        // Insert into FlipFitUser table
	        statementUser = connection.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
	        statementUser.setString(1, flipFitCustomer.getUserName());
	        statementUser.setString(2, flipFitCustomer.getUserEmail());
	        statementUser.setString(3, flipFitCustomer.getUserPassword()); 
	        statementUser.setString(4, flipFitCustomer.getUserMobile());
	        statementUser.setInt(5, roleId);
	        
	        int affectedRows = statementUser.executeUpdate();

	        if (affectedRows > 0) {

	        	generatedKeys = statementUser.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                userId = generatedKeys.getInt(1);
	                flipFitCustomer.setUserId(userId);


	                statementCustomer = connection.prepareStatement(queryCustomer);
	                statementCustomer.setInt(1, userId);
	                statementCustomer.setInt(2, flipFitCustomer.getCustomerAge());
	                statementCustomer.executeUpdate();

	                connection.commit();
	            }
	        } else {
	            System.out.println("User registration failed.");
	            connection.rollback(); 
	        }
	    } catch (SQLException e) {
	        try {
	            if (connection != null) {
	                connection.rollback(); 
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statementUser != null) statementUser.close();
	            if (statementCustomer != null) statementCustomer.close();
	            if (generatedKeys != null) generatedKeys.close();
	            if (connection != null) connection.setAutoCommit(true); // Reset auto-commit
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return userId;
	    
	}

	@Override
	public int authenticateUser(String email, String password) {
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    int userId = -1; // Default value if authentication fails

	    // SQL query to check if the user exists with the given email and password
	    String query = "SELECT userID FROM FlipFitUser WHERE email = ? AND passwordHash = ?";

	    try {
	        connection = DBconnection.getConnection();
	        statement = connection.prepareStatement(query);
	        statement.setString(1, email);
	        statement.setString(2, password); // Ensure password is hashed if stored as a hash in DB
	        rs = statement.executeQuery();

	        if (rs.next()) {
	            userId = rs.getInt("userID"); // Authentication successful, return user ID
	        } else {
	            System.out.println("Invalid email or password.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userId; 
	}

	   

	
	






    
    
    
//TODO: waitlist(),waitlistpromotion(),bookingNotification(),makePayment()
    
    


}
