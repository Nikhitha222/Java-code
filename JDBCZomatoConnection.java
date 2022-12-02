package com.edubridge.com.edubridge.example1;
import java.sql.*;
import java.util.Scanner;

public class JDBCZomatoConnection {

	static final Scanner obj = new Scanner(System.in);
	public static void createRestaurantName(Connection connection ,Zomato obj) throws SQLException {

		PreparedStatement pstmt = connection.prepareStatement("insert into zomato values(?,?,?,?,?,?)");

		pstmt.setString(1, obj.getrestaurantName());
		pstmt.setInt(2, obj.getrestaurantId());
		pstmt.setString(3, obj.getdeliveryMode());
		pstmt.setInt(4, obj.getaveragePrice());
		pstmt.setInt(5, obj.getrating());
		pstmt.setString(6, obj.getlocation());
		int records = pstmt.executeUpdate();
		System.out.println(records +"inserted succesfully");

	}	
	private  static void updateRestaurantName(Connection connection,int restaurantId)throws SQLException {
		System.out.println("enter the update the restarant name");
		String value=obj.next();
		PreparedStatement pstmt = connection.prepareStatement("update Zomato set restaurantName=? where restaurantId="+restaurantId);

		pstmt.setString(1, value);
		//pstmt.setInt(2, restaurantId);
		int records=pstmt.executeUpdate();
		System.out.println(records+"inserted succesfully");
	}

	private static void deleteRestaurantName(Connection connection)throws SQLException {

		System.out.println("enter the restaurant id to delete");
		int value1=obj.nextInt();
		PreparedStatement pstmt=connection.prepareStatement("delete from zomato where restaurantId=? ");
		pstmt.setInt(1, value1);

		int records=pstmt.executeUpdate();
		System.out.println(records+"inserted succesfully");
	}
	private static void viewRestaurantName(Connection connection) throws SQLException {
		System.out.println("enter the restaurant id to view");
		int value2=obj.nextInt();
		PreparedStatement pstmt=connection.prepareStatement("select * from zomato where restaurantId=?");
		pstmt.setInt(1,value2);

		ResultSet records=pstmt.executeQuery();
		if(records.next()==false)
		{
			System.out.println("There is no such record in this database");
		}
		else
		{
			System.out.println(records.getString(1)+" "+records.getInt(2)+" "+records.getString(3)+" "+records.getInt(4)+" "+records.getInt(5)+" "+records.getString(6)+"inserted succesfully");

		}
	}

	public static void main(String []args)throws Exception {
		//Connection connection=null;
		final Zomato zObj=new Zomato();
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Bellam@123");

		System.out.println("ENTER 1 TO ADD RESTAURANT DETAILS");
		System.out.println("ENTER 2 TO UPDATE RESTAURANT DETAILS");
		System.out.println("ENTER 3 TO DELETE RESTAURANT DETAILS");
		System.out.println("ENTER 4 TO VIEW RESTAURANT DETAILS");

		System.out.println("ENTER YOUR CHOICE");

		//Scanner obj=new Scanner(System.in);
		byte choice= obj.nextByte();
		switch(choice) {
			case 1:
				Zomato obj=getRestaurant();
				createRestaurantName(connection,obj);
				break;

			case 2:

				updateRestaurantName(connection,13);

				break;	

			case 3:
				deleteRestaurantName(connection);
				break;	

			case 4:
				viewRestaurantName(connection);
				break;	


			default:
				System.out.println("ENTER PROPER DETAILS");	
				connection.close();
		}

	}

	private static Zomato getRestaurant() {
		Zomato restaurant=new Zomato();
		System.out.println("enter a restaurant name");
		restaurant.setrestaurantName(obj.next());

		System.out.println("enter a restaurant Id");
		restaurant.setrestaurantId(obj.nextInt());

		System.out.println("enter a delivery mode");
		restaurant.setdeliveryMode(obj.next());

		System.out.println("enter a average price");
		restaurant.setaveragePrice(obj.nextInt());

		System.out.println("enter a rating");
		restaurant.setrating(obj.nextInt());

		System.out.println("enter a location");
		restaurant.setlocation(obj.next());

		return restaurant;
	}
}




