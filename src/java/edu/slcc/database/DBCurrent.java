package edu.slcc.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "dbcurrent")
@ApplicationScoped
public class DBCurrent implements Serializable {

    String lolaID = "";
    String firstN = "";
    String middleN = "";
    String lastN = "";
    String street = "";
    String city = "";
    String zip = "";
    String state = "";
    String phone = "";
    String achieve = "";

    String result = "";

    public DBCurrent() {
        DatabaseConnection.connection();
    }

    public String getLolaID() {
        return lolaID;
    }

    public void setLolaID(String lolaID) {
        this.lolaID = lolaID;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getMiddleN() {
        return middleN;
    }

    public void setMiddleN(String middleN) {
        this.middleN = middleN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAchieve() {
        return achieve;
    }

    public void setAchieve(String achieve) {
        this.achieve = achieve;
    }

    public String getConnectionResponse() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return null;
        }
        if (con != null) {
            return "<p style=\"color:green\">Connection succesfull! <br />";
        } else {
            DatabaseConnection.connection();
            return "<p style=\"color:red\">Connection failed! <br />";
        }

    }

    public void clear() {
        lolaID = "";
        firstN = "";
        middleN = "";
        lastN = "";
        street = "";
        city = "";
        zip = "";
        state = "";
        phone = "";
        achieve = "";

        result = "";
    }

    public void listAll() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        String table = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM current";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next()) {
                String lolaID = rs.getString(1) + " ";
                String firstN = rs.getString(2) + " ";
                String middleN = rs.getString(3) + " ";
                String lastN = rs.getString(4) + " ";
                String street = rs.getString(5) + " ";
                String city = rs.getString(6) + " ";
                String zip = rs.getString(7) + " ";
                String state = rs.getString(8) + " ";
                String phone = rs.getString(9) + " ";
                String achieve = rs.getString(10) + " ";
                table += lolaID + firstN + middleN + lastN + street + city + zip +
                        state + phone + achieve + "</br>";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        result = table;
    }

    public void viewCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        String ret = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT *  FROM current WHERE lola_ID=?";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, lolaID);
            //execute
            rs = ps.executeQuery();
            if (rs.next()) {
                this.lolaID = rs.getString("lola_ID");
                ret += this.lolaID + " ";
                this.firstN = rs.getString("first");
                ret += this.firstN + " ";
                this.middleN = rs.getString("middle");
                ret += this.middleN + " ";
                this.lastN = rs.getString("last");
                ret += this.lastN + " ";
                this.street = rs.getString("street");
                ret += this.street + " ";
                this.city = rs.getString("city");
                ret += this.city + " ";
                this.zip = rs.getString("zip");
                ret += this.zip + " ";
                this.state = rs.getString("state");
                ret += this.state + " ";
                this.phone = rs.getString("phone");
                ret += this.phone + " ";
                this.achieve = rs.getString("achievements");
                ret += this.achieve + " ";
            } else {
                ret = this.lolaID + " doesn't exist.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        this.result = ret;
    }

    public void updateCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSupplier = null;
        try {    
            updateSupplier = con.prepareStatement(
                    "UPDATE current SET lola_ID=?, first=?, middle=?, last=?, "
                            + "street=?, city=?, zip=?, state=?, phone=?, achievements=? WHERE lolaID=?");
            updateSupplier.setString(1, lolaID);
            updateSupplier.setString(2, firstN);
            updateSupplier.setString(3, middleN);
            updateSupplier.setString(4, lastN);            
            updateSupplier.setString(5, street);            
            updateSupplier.setString(6, city);            
            updateSupplier.setString(7, zip);            
            updateSupplier.setString(8, state);            
            updateSupplier.setString(9, phone);            
            updateSupplier.setString(10, achieve);
            int updateCount = updateSupplier.executeUpdate();

            result = "number of rows affected: " + updateCount;

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (updateSupplier != null) {
                    updateSupplier.close();
                }

            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void deleteCurrent() {

        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try {
            String query = "DELETE FROM current WHERE lola_ID=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, lolaID);
            rowsAffected = ps.executeUpdate();
            result = "number of rows affected: " + rowsAffected;
        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void insertCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateCurrent = null;
        try {
            updateCurrent = con.prepareStatement(
                    "INSERT INTO current (lola_ID, first, middle, last, "
                            + "street, city, zip, state, phone, achievements) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            updateCurrent.setString(1, lolaID);
            updateCurrent.setString(2, firstN);
            updateCurrent.setString(3, middleN);
            updateCurrent.setString(4, lastN);            
            updateCurrent.setString(5, street);            
            updateCurrent.setString(6, city);            
            updateCurrent.setString(7, zip);            
            updateCurrent.setString(8, state);            
            updateCurrent.setString(9, phone);            
            updateCurrent.setString(10, achieve);

            int updateCount = updateCurrent.executeUpdate();

            result = "number of rows affected: " + updateCount;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            result = ex.toString();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (updateCurrent != null) {
                    updateCurrent.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                result = e.toString();
            }
        }
    }

    public String getResult() {
        return "<p style=\"color:green\">Current<br />" + result;

    }
}
