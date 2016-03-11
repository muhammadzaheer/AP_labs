/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author mzabbas.bscs12seecs
 */
public class Test {

    public static void main(String[] args) {
        CityManager cm = new CityManager();
        cm.createTable();
        cm.readCSV("GeoLiteCity-Location.csv");
        cm.searchCity(-34, 18);
        cm.searchNearby("Wynberg");

    }

}
