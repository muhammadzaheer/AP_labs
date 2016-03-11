/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.sql.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mzabbas.bscs12seecs
 */
public class CityManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test_db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public void readCSV(String csvFile) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement("insert into records (id,country,city,latitude,longitude) values (?,?,?,?,?)");

            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {

                br = new BufferedReader(new FileReader(csvFile));
                int count = 0;
                while ((line = br.readLine()) != null) {
                    count++;
                    if (count < 3) {
                        continue; // Hack to ignore starting rows
                    }
                    //line = line.replace("\"", "");
                    //System.out.println(line);

                    String[] cols = line.split(",");
                    String id = cols[0];
                    String country = cols[1].replace("\"", "");
                    String region = cols[2].replace("\"", "");
                    String city = cols[3].replace("\"", "");
                    String postalCode = cols[4].replace("\"", "");
                    String lat = cols[5];
                    String lon = cols[6];

                    stmt.setInt(1, Integer.parseInt(id));
                    stmt.setString(2, country);
                    stmt.setString(3, city);
                    stmt.setDouble(4, Double.parseDouble(lat));
                    stmt.setDouble(5, Double.parseDouble(lon));
                    stmt.executeUpdate();
                    /*
                     for (int i = 0; i < cols.length; i++)
                     System.out.print(cols[i] + " ");
                     System.out.println();*/

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }
    }

    public void createTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE records (id INTEGER not null, country VARCHAR(30),region VARCHAR(30),city VARCHAR(30), postalCode VARCHAR(30), latitude DECIMAL(5), longitude DECIMAL(5),metroCode INT,areaCode INT,PRIMARY KEY(id)   )";

            stmt.executeUpdate(sql);
            /*
             //STEP 5: Extract data from result set
             while (rs.next()) {
             //Retrieve by column name
             int id = rs.getInt("id");
             String age = rs.getString("number");

             //Display values
             System.out.print("ID: " + id);
             System.out.print(", Age: " + age);
             }
             //STEP 6: Clean-up environment
             rs.close();*/
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

    }

    public void searchCity(double lat, double lon) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT city FROM records where latitude =? and longitude =?");
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lon);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            System.out.println("Citis at latitude: " + lat + " longitude: " + lon);
            while (rs.next()) {
                //Retrieve by column name
                String city = rs.getString("city");
                if (city.length() == 0) {
                    continue;
                }
                //Display values
                System.out.print(city + " ");
            }
            System.out.println();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

    }

    public void searchNearby(String cityName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT latitude, longitude FROM records where city=?");
            stmt.setString(1, cityName);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            double lat = 0.0;
            double lon = 0.0;
            while (rs.next()) {
                //Retrieve by column name
                lat = rs.getDouble("latitude");
                lon = rs.getDouble("longitude");
                //System.out.println(lat + " " + lon);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT city FROM records where latitude < ? and latitude > ?"
                    + "                                                  and longitude < ? and longitude > ?");
            stmt.setDouble(1, lat + 10);
            stmt.setDouble(2, lat - 10);
            stmt.setDouble(3, lon + 10);
            stmt.setDouble(4, lon - 10);

            rs = stmt.executeQuery();
            System.out.println("Nearby cities to " + cityName);
            while (rs.next()) {
                //Retrieve by column name
                String city = rs.getString("city");
                if (city.length() == 0) {
                    continue;
                }
                //Display values
                System.out.print(city + " ");
            }
            System.out.println();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

    }

}
