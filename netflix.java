import java.sql.* ;
import java.util.Scanner;


public class netflix {
	
    static String username;
	static String password;
	
	public static void main( String args[] )
    {
  
		//scanner for reading user inputs
		Scanner reader = new Scanner(System.in);
		try
      {
	  // Load the database driver
	  Class.forName( "oracle.jdbc.driver.OracleDriver" ) ;

	  // Get a connection to the database
	  
	  System.out.print("Enter username: ");
	  username = reader.nextLine();
	  System.out.print("Enter password: ");
	  password = reader.nextLine();
	 Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", username, password ) ;

	  // Print all warnings
	  for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
	      {
		  System.out.println( "SQL Warning:" ) ;
		  System.out.println( "State  : " + warn.getSQLState()  ) ;
		  System.out.println( "Message: " + warn.getMessage()   ) ;
		  System.out.println( "Error  : " + warn.getErrorCode() ) ;
	      }
	  
	  	int answer = 1;
      	String answer2;
      	while(answer!= 6)
      	{
      		//Menu options
      		System.out.println("Operations");
      		System.out.println("1: View Table");
      		System.out.println("2: Insert New Record");
      		System.out.println("3: Update Record");
      		System.out.println("4: Search For Movies");
      		System.out.println("5: Member Information");
      		System.out.println("6: Exit");
      		System.out.println("Choose operations");
      		answer = reader.nextInt();
      		//each case calls for different method
      		switch(answer){
      		case 1: 
      			System.out.println("Which Table To View: Account, Credit_Card, ActorsActress, Movie, Genre, Profile, has_creditcard_num," +
      " preferred_actors, preferred_movie_genres, starred_in, movie_genre, rental_history ");
      			answer2 = reader.next();
      			viewTable(conn, answer2);
      			break;
      		case 2:
      			insertRecord(conn, reader);
      			break;
      		case 3:
      			updateRecord(conn,reader);
      			break;
      		case 4:
      			searchMovie(conn, reader);
      			break;
      		case 5:
      			showProfile(conn, reader);
      			break;
      		case 6:
      			System.exit(0);
      			break;
      		default:
      			System.out.println("Invalid Input, Please choose from the available options");
      			break;
      			
      		}
      	}
	  reader.close();
      }
	
		
	  catch( SQLException se )
      {
	  System.out.println( "SQL Exception:" ) ;

	  // Loop through the SQL Exceptions
	  while( se != null )
	      {
		  System.out.println( "State  : " + se.getSQLState()  ) ;
		  System.out.println( "Message: " + se.getMessage()   ) ;
		  System.out.println( "Error  : " + se.getErrorCode() ) ;

		  se = se.getNextException() ;
	      }
      }
  catch( Exception e )
      {
	  System.out.println( e ) ;
      }
    }  
	
	//Execute query to select all records of table based on user input
	public static void viewTable(Connection connect, String s1) throws SQLException
	{
		
		// Get a statement from the connection
		Statement stmt = connect.createStatement() ;

		ResultSet rs = stmt.executeQuery( "SELECT *  FROM " + s1 ) ;
		switch(s1.toLowerCase())
		{
		case "account":
			System.out.println("MEMBER_ID	FIRST_NAME	LAST_NAME	BIRTHDAY	GENDER	ADDRESS		EMAIL_ADDRESS	");
			
			while( rs.next() ){
				System.out.println(( rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getString(5) + " " 
						+ rs.getString(6) + " " + rs.getString(7))) ;
			}
		break;
		case "credit_card":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getString(5) + "  " 
						+ rs.getString(6)));
			}
		break;
		case "actorsactress":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getString(5)));
			}
		break;
		case "movie":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getString(5)  + "  " 
						+ rs.getString(6) + " " + rs.getString(7))) ;
			}
		break;
		case "genre":
			while( rs.next() ){
				System.out.println(( rs.getString(1)));
			}
		break;
		case "profile":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2)));
			}
		break;
		case "has_creditcard_num":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2)));
			}
		break;
		case "preferred_actors":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3)));
			}
		break;
		case "preferred_movie_genres":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3)));
			}
		break;
		case "starred_in":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2)));
			}
		break;
		case "movie_genre":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2)));
			}
		break;
		case "rental_history":
			while( rs.next() ){
				System.out.println(( rs.getString(1) + "  " + rs.getString(2)  + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5)));
			}
		break;
		default:
			System.out.println("please choose from available options");
			break;
		
		
		}
		 

	}

	//Execute query to insert new tuple 
	public static void insertRecord(Connection connect, Scanner reader) throws SQLException
	{
				// Get a statement from the connection
				Statement stmt1 = connect.createStatement() ;
				
				System.out.println("Which Table to insert into ?: ");
				System.out.println("Account, Credit_Card, ActorsActress, Movie, Genre, Profile, has_creditcard_num," +
			      " preferred_actors, preferred_movie_genres, starred_in, movie_genre, rental_history ");
				String answer = reader.next();
				switch(answer.toLowerCase()){
				case "account":
				System.out.println("Enter Member ID:");
				int f1 = reader.nextInt();
				System.out.println("Enter first name:");
				String f2 =reader.next();
				System.out.println("Enter last name: ");
				String f3 = reader.next();
				System.out.println("Enter birthay: (Format = DD-MMM-YY ");
				String f4 = reader.next();
				System.out.println("Enter Gender: ");
				String f5 = reader.next();
				String s = reader.nextLine();
				System.out.println("Enter Address: ");
				String f6 = reader.nextLine();
				System.out.print("Enter Email Address: ");
				String f7 = reader.next();
				String sql = ("INSERT INTO " + answer + " VALUES " + "(" + f1 +"," +"\'"+ f2 +"\'"+ ","+ "\'"+ f3 + "\'"+
				"," +"\'"+ f4 +"\'"+ "," +"\'"+ f5 +"\'"+"," +"\'"+ f6 +"\'"+ "," +"\'"+ f7 +"\'" +")" );
				System.out.println(sql);
				stmt1.executeUpdate(sql);
				
				break;
				case "credit_card":
					System.out.println("Enter Credit Card Number: (Require 16 numbers )");
					String b1 = reader.next();
					System.out.println("Enter first name:");
					String b2 =reader.next();
					System.out.println("Enter last name: ");
					String b3 = reader.next();
					System.out.println("Enter Expiration Date: (Format = DD-MMM-YY ");
					String b4 = reader.next();
					System.out.println("Enter Security Code: ");
					String b5 = reader.next();
					String s1 = reader.nextLine();
					System.out.println("Brand Name: ");
					String b6 = reader.nextLine();
					String sql1 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'" + b1 + "\'"+"," +"\'"+ b2 +"\'"+ ","+ "\'"+ b3 + "\'"+
							"," +"\'"+ b4 +"\'"+ "," +"\'"+ b5 +"\'"+"," +"\'"+ b6 +"\'"+ ")" );
							System.out.println(sql1);
							stmt1.executeUpdate(sql1);
					break;
				case "actorsactress":
					System.out.println("Enter Actor ID: ");
					String c1 = reader.next();
					System.out.println("Enter first name:");
					String c2 =reader.next();
					System.out.println("Enter last name: ");
					String c3 = reader.next();
					System.out.println("Enter Birthday: (Format = DD-MMM-YY ");
					String c4 = reader.next();
					System.out.println("Enter Gender: ");
					String c5 = reader.next();
					String sql3 = ("INSERT INTO " + answer + " VALUES " + "(" + c1 +"," +"\'"+ c2 +"\'"+ ","+ "\'"+ c3 + "\'"+
							"," +"\'"+ c4 +"\'"+ "," +"\'"+ c5 +"\'"+ ")" );
							System.out.println(sql3);
							stmt1.executeUpdate(sql3);
					break;
				case "movie":
					System.out.println("Enter Movie ID:");
					int d1 = reader.nextInt();
					String s2 = reader.nextLine();
					System.out.println("Enter Movie Name:");
					String d2 =reader.nextLine();
					System.out.println("Enter Producer Name: ");
					String d3 = reader.nextLine();
					System.out.println("Enter Year Produced: (Format = DD-MMM-YY ");
					String d4 = reader.next();
					System.out.println("Enter Movie Length: (In minutes) ");
					String d5 = reader.next();
					String s3 = reader.nextLine();
					System.out.println("Enter Cast Members: ");
					String d6 = reader.nextLine();
					System.out.print("Enter Rating (From 1 to 5): ");
					double d7 = reader.nextDouble();
					String sql4 = ("INSERT INTO " + answer + " VALUES " + "(" + d1 +"," +"\'"+ d2 +"\'"+ ","+ "\'"+ d3 + "\'"+
					"," +"\'"+ d4 +"\'"+ "," +"\'"+ d5 +"\'"+"," +"\'"+ d6 +"\'"+ "," +"\'"+ d7 +"\'" +")" );
					System.out.println(sql4);
					stmt1.executeUpdate(sql4);
					break;
				case "genre":
					System.out.println("Enter Genre");
					String e1 = reader.next();
					String sql5 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ e1  +"\'" + ")" );
					System.out.println(sql5);
					stmt1.executeUpdate(sql5);
					break;
				case "profile":
					System.out.println("Enter Member ID:");
					int g1 = reader.nextInt();
					System.out.println("Enter Profile Name:");
					String g2 = reader.next();
					String sql6 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ g1  +"\'" + ",\'"+ g2 +"\'" + ")" );
					System.out.println(sql6);
					stmt1.executeUpdate(sql6);
					break;
				case "has_creditcard_num":
					System.out.println("Enter Member ID:");
					int h1 = reader.nextInt();
					System.out.println("Enter Credit Card Number:");
					String h2 = reader.next();
					String sql7 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ h1  +"\'" + ",\'"+ h2 +"\'" + ")" );
					System.out.println(sql7);
					stmt1.executeUpdate(sql7);
					break;
				case "preferred_actors":
					System.out.println("Enter Actor ID:");
					int i1 = reader.nextInt();
					System.out.println("Enter Profile Name:");
					String i2 = reader.next();
					System.out.println("Enter Member ID:");
					int i3 = reader.nextInt();
					String sql8 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ i1  +"\'" + ",\'"+ i2 +"\'" + ",\'"+ i3 +"\'" + ")" );
					System.out.println(sql8);
					stmt1.executeUpdate(sql8);
					break;
				case "preferred_movie_genres":
					System.out.println("Enter Genre Type:");
					String k4 = reader.nextLine();
					String k1 = reader.nextLine();
					System.out.println("Enter Profile Name:");
					String k2 = reader.next();
					System.out.println("Enter Member ID:");
					int k3 = reader.nextInt();
					String sql9 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ k1  +"\'" + ",\'"+ k2 +"\'" + ",\'"+ k3 +"\'" + ")" );
					System.out.println(sql9);
					stmt1.executeUpdate(sql9);
					break;	
				case "starred_in":
				System.out.println("Enter Actor ID");
				int a1 = reader.nextInt();
				System.out.println("Enter Movie ID");
				int a2 = reader.nextInt();
				String sql2 = ( "INSERT INTO " + answer + " VALUES " + "(" + a1 +"," +a2 +")"  );
				System.out.println(sql2);
				stmt1.executeUpdate(sql2);	
					break;
				case "movie_genre":
					System.out.println("Enter Movie ID:");
					int j1 = reader.nextInt();
					System.out.println("Enter Genre Type:");
					String jj = reader.nextLine();
					String j2 = reader.nextLine();
					String sql10 = ("INSERT INTO " + answer + " VALUES " + "(" + "\'"+ j1  +"\'" + ",\'"+ j2 +"\'" + ")" );
					System.out.println(sql10);
					stmt1.executeUpdate(sql10);
					break;
				case "rental_history":
					System.out.println("Enter Rating: ");
					int l1 = reader.nextInt();
					System.out.println("Enter Review:");
					String abc = reader.nextLine();
					String l2 =reader.nextLine();
					System.out.println("Enter Member ID: ");
					String l3 = reader.next();
					System.out.println("Enter Movie ID: ");
					String l4 = reader.next();
					System.out.println("Enter Profile Name: ");
					String cda = reader.nextLine();
					String l5 = reader.nextLine();
					String sql11 = ("INSERT INTO " + answer + " VALUES " + "(" + l1 +"," +"\'"+ l2 +"\'"+ ","+ "\'"+ l3 + "\'"+
							"," +"\'"+ l4 +"\'"+ "," +"\'"+ l5 +"\'"+ ")" );
							System.out.println(sql11);
							stmt1.executeUpdate(sql11);
					break;
					default:
						System.out.println("please choose from available options");
				}
				
	}
	
	//Execute query to update or delete Record
	public static void updateRecord(Connection connect, Scanner reader) throws SQLException
	{
		// Get a statement from the connection
		Statement stmt1 = connect.createStatement() ;
		
		System.out.println("Update:1 or Delete:2");
		int answer;
		answer = reader.nextInt();
		switch (answer)
		{
			case 1:
				System.out.println("Which Table would you like to update ?");
				System.out.println("Account, Credit_Card, ActorsActress, Movie, Profile, has_creditcard_num," +
					      " preferred_actors, preferred_movie_genres, starred_in, movie_genre, rental_history ");
				String z = reader.nextLine();
				String answerz = reader.nextLine();
				viewTable(connect, answerz);
				System.out.println("Same data must be reenter if you wish to keep it the same");
				System.out.println("");
				switch(answerz.toLowerCase())
				{
					case "account":
						System.out.println("choose Member ID to update:");
						int a1 = reader.nextInt();
						System.out.println("Enter first name:");
						String f2 =reader.next();
						System.out.println("Enter last name: ");
						String f3 = reader.next();
						System.out.println("Enter birthay: (Format = DD-MMM-YY ");
						String f4 = reader.next();
						System.out.println("Enter Gender: ");
						String f5 = reader.next();
						String s = reader.nextLine();
						System.out.println("Enter Address: ");
						String f6 = reader.nextLine();
						System.out.print("Enter Email Address: ");
						String f7 = reader.next();
						String sqlz = ("UPDATE account SET first_name =\'"+f2 +"\', "+"last_name =" + "\'"+f3 +"\'"+ " ,birthday = " +  "\'"+f4 +"\'"+ ",gender = " +
								"\'"+f5 +"\'"+ " ,address = " +  "\'"+f6 +"\'"+ " ,email_address = " + "\'"+f7 +"\'" + " WHERE member_ID = " + a1);
						System.out.println(sqlz);
						stmt1.executeUpdate(sqlz);
						break;
					case "credit_card":
						System.out.println("choose Credit Card Number to Update");
						String b1 = reader.next();
						System.out.println("Enter first name:");
						String b2 =reader.next();
						System.out.println("Enter last name: ");
						String b3 = reader.next();
						System.out.println("Enter Expiration Date: (Format = DD-MMM-YY ");
						String b4 = reader.next();
						System.out.println("Enter Security Code: ");
						String b5 = reader.next();
						String s1 = reader.nextLine();
						System.out.println("Brand Name: ");
						String b6 = reader.nextLine();
						String sql1 = ("UPDATE credit_card SET first_name= \'"+b2 +"\', "+"last_name =" + "\'"+b3 +"\'"+ " ,expiration_date = " +  "\'"+b4 +"\'"+ ",security_code = " +
								"\'"+b5 +"\'"+ " ,brand_name = " +  "\'"+b6 +"\'"+ " WHERE Card_Number = " + b1);
								System.out.println(sql1);
								stmt1.executeUpdate(sql1);
						break;
					case "actorsactress":
						System.out.println("Enter Actor ID: ");
						String c1 = reader.next();
						System.out.println("Enter first name:");
						String c2 =reader.next();
						System.out.println("Enter last name: ");
						String c3 = reader.next();
						System.out.println("Enter Birthday: (Format = DD-MMM-YY ");
						String c4 = reader.next();
						System.out.println("Enter Gender: ");
						String c5 = reader.next();
						String sql3 =("UPDATE ActorsActress SET first_name= \'"+c2 +"\', "+"last_name =" + "\'"+c3 +"\'"+ " ,birthday = " +  "\'"+c4 +"\'"+ ",gender = " +
								"\'"+c5 +"\'"+ " WHERE Actor_ID = " + c1);
								System.out.println(sql3);
								stmt1.executeUpdate(sql3);
						break;
					case "movie":
						System.out.println("Enter Movie ID:");
						int d1 = reader.nextInt();
						String s2 = reader.nextLine();
						System.out.println("Enter Movie Name:");
						String d2 =reader.nextLine();
						System.out.println("Enter Producer Name: ");
						String d3 = reader.nextLine();
						System.out.println("Enter Year Produced: (Format = DD-MMM-YY ");
						String d4 = reader.next();
						System.out.println("Enter Movie Length: (In minutes) ");
						String d5 = reader.next();
						String s3 = reader.nextLine();
						System.out.println("Enter Cast Members: ");
						String d6 = reader.nextLine();
						System.out.print("Enter Rating (From 1 to 5): ");
						double d7 = reader.nextDouble();
						String sql4 = ("UPDATE Movie SET name= \'"+d2 +"\', "+"producer =" + "\'"+d3 +"\'"+ " ,year_produced = " +  "\'"+d4 +"\'"+ ",movie_length = " +
								"\'"+d5 +"\'"+ ",cast = " + "\'"+d6 +"\'"+ ",average_rating" + "\'"+d7 +"\'"+ " WHERE Movie_ID = " + d1);
						System.out.println(sql4);
						stmt1.executeUpdate(sql4);
						break;
			
				}
				break;
			case 2:
		{
			System.out.println("Which Table would you like to delete from?");
			System.out.println("Account, Credit_Card, ActorsActress, Movie, Genre, Profile, has_creditcard_num," +
				      " preferred_actors, preferred_movie_genres, starred_in, movie_genre, rental_history ");
			String s22 = reader.nextLine();
			String answer2 = reader.nextLine();
			viewTable(connect, answer2);
			
			switch(answer2.toLowerCase())
			{
			case "account":
				System.out.println("choose Member ID to delete:");
				int a1 = reader.nextInt();
				String sql = ("DELETE FROM account WHERE MEMBER_ID = " + a1);
				System.out.println(sql);
				stmt1.executeUpdate(sql);
			break;
			case "credit_card":
				System.out.println("choose Credit Card Number to delete:");
				//String aa = reader.nextLine();
				String a2 = reader.nextLine();
				String sql2 = ("DELETE FROM credit_card WHERE CARD_NUMBER = " + "\'" +a2 +"\'");
				System.out.println(sql2);
				stmt1.executeUpdate(sql2);		
			break;
			case "actorsactress":
				System.out.println("choose Actor ID to delete:");
				int a3 = reader.nextInt();
				String sql3 = ("DELETE FROM actorsactress WHERE ACTOR_ID = " + a3);
				System.out.println(sql3);
				stmt1.executeUpdate(sql3);
			break;
			case "movie":
				System.out.println("choose Movie ID to delete:");
				int a4 = reader.nextInt();
				String sql4 = ("DELETE FROM movie WHERE MOVIE_ID = " + a4);
				System.out.println(sql4);
				stmt1.executeUpdate(sql4);
			break;
			case "genre":
				System.out.println("choose Genre to delete:");
				String a5 = reader.nextLine();
				String sql5 = ("DELETE FROM genre WHERE GENRE_TYPE = " + "\'" + a5 +"\'");
				System.out.println(sql5);
				stmt1.executeUpdate(sql5);
			break;
			case "profile":
				System.out.println("choose Member ID to delete:");
				int a6 = reader.nextInt();
				System.out.println("choose Profile Name to delete:");
				String a484 = reader.nextLine();
				String a66 = reader.nextLine();
				String sql6 = ("DELETE FROM profile WHERE MEMBER_ID = " + a6 +" AND profile_name="  + "\'"+ a66 +"\'"  );
				System.out.println(sql6);
				stmt1.executeUpdate(sql6);
			break;
			case "has_creditcard_num":
				System.out.println("choose Member ID to delete:");
				int a7 = reader.nextInt();
				System.out.println("choose Credit Card Number to delete:");
				String a889 = reader.nextLine();
				String a77 = reader.nextLine();
				String sql7 = ("DELETE FROM has_creditcard_num WHERE MEMBER_ID = " + a7 +" AND Card_Number="  + "\'"+ a77 +"\'" );
				System.out.println(sql7);
				stmt1.executeUpdate(sql7);
			break;
			case "preferred_actors":
				System.out.println("choose Actor ID to delete:");
				int a8 = reader.nextInt();
				System.out.println("choose Profile Name to delete:");
				String jjj = reader.nextLine();
				String a88 = reader.nextLine();
				System.out.println("choose Member ID to delete:");
				int a888 = reader.nextInt();
				String sql8 = ("DELETE FROM preferred_actors WHERE MEMBER_ID = " + a888 +" AND ACTOR_ID ="  + "\'"+ a8 +"\'" + " AND profile_name =" 
				+ "\'"+ a88 +"\'");
				System.out.println(sql8);
				stmt1.executeUpdate(sql8);
			break;
			case "preferred_movie_genres":
				System.out.println("choose Genre Type to delete:");
				String a9 = reader.nextLine();
				System.out.println("choose Profile Name to delete:");
				String a99 = reader.nextLine();
				System.out.println("choose Member ID to delete:");
				int a999 = reader.nextInt();
				String sql9 = ("DELETE FROM preferred_movie_genres WHERE MEMBER_ID = " + a999 +" AND GENRE_TYPE ="  + "\'"+ a9 +"\'" + " AND profile_name =" 
				+ "\'"+ a99 +"\'");
				System.out.println(sql9);
				stmt1.executeUpdate(sql9);
			break;
			case "starred_in":
				System.out.println("choose Actor ID to delete:");
				int b1 = reader.nextInt();
				System.out.println("choose Movie ID to delete:");
				int b11 = reader.nextInt();
				String sql10 = ("DELETE FROM starred_in WHERE ACTOR_ID = " + b1 +  " AND MOVIE_ID = " + b11);
				System.out.println(sql10);
				stmt1.executeUpdate(sql10);
			break;
			case "movie_genre":
				System.out.println("choose Movie ID to delete:");
				int b2 = reader.nextInt();
				System.out.println("choose Genre to delete:");
				String aaa = reader.nextLine();
				String b22 = reader.nextLine();
				String sql11 = ("DELETE FROM movie_genre WHERE MOVIE_ID = " + b2 +  " AND GENRE_TYPE = " + "\'" + b22 +"\'" );
				System.out.println(sql11);
				stmt1.executeUpdate(sql11);			
			break;
			case "rental_history":
				System.out.println("choose Movie ID to delete:");
				int b3 = reader.nextInt();
				System.out.println("choose Profile Name to delete:");
				String aaaa = reader.nextLine();
				String b33 = reader.nextLine();
				System.out.println("choose Member ID to delete:");
				int b333 = reader.nextInt();
				String sql12 = ("DELETE FROM rental_history WHERE MOVIE_ID = " + b3 +  " AND profile_name = " + "\'" + b33 +"\'" + " AND Member_ID = " 
				+ b333 );
				System.out.println(sql12);
				stmt1.executeUpdate(sql12);	
			break;
			default:
				System.out.println("please choose from available options");
				break;
			}
		}
	}
}

	//Execute query to Search for Movie
	public static void searchMovie(Connection connect, Scanner reader) throws SQLException
	{
		// Get a statement from the connection
		Statement stmt1 = connect.createStatement() ;
		
		System.out.println("By Movie Name:1 or By Actor/Actress Name:2");
		int answer = reader.nextInt();
		switch(answer)
		{
		case 1:
			System.out.println("Enter Movie Name:");
			String s11 = reader.nextLine();
			String s1 = reader.nextLine();
			
			String sql = ("SELECT name, year_produced, average_rating FROM Movie WHERE name LIKE(" + "\'"+ s1 +"%\')" );
			System.out.println(sql);
			ResultSet rs = stmt1.executeQuery( sql) ;
			while( rs.next() ){
				System.out.println(( rs.getString(1) +  "  " + rs.getDate(2) + "  " + rs.getString(3)));
			}
			break;
		case 2:
			System.out.println("Enter Actor or Actress First Name:");
			String s2 = reader.next();
			String s22 = reader.nextLine();
			System.out.println("Enter Actor or Actress Last Name:");
			String s3 = reader.nextLine();
			
			//get actor ID first then use later as condition for movie name 
			String sql2 = ("SELECT actor_id FROM ActorsActress WHERE first_name = "+ "\'"+ s2 +"\'" + "AND last_name = "   
			+ "\'"+ s3 +"\'"  );
			ResultSet rs2 = stmt1.executeQuery( sql2) ;
			int result = 0;
			while( rs2.next() ){
				result = rs2.getInt(1);
			}
			//get Movie ID from starred in 
			String sqla = ("SELECT movie_id FROM starred_in WHERE Actor_ID = "+ result );
			ResultSet rs5 = stmt1.executeQuery( sqla) ;
			int movieID = 0;
			while( rs5.next() ){
				movieID = rs5.getInt(1);
			}
			String sql3 = ("SELECT name, year_produced, average_rating FROM Movie WHERE Movie_ID = "+ movieID  );
			ResultSet rs3 = stmt1.executeQuery( sql3) ;
			while( rs3.next() ){
				System.out.println(( rs3.getString(1) +  "  " + rs3.getDate(2) + "  " + rs3.getString(3)));
			}
			
			break;
		}
		
	}

	
	//Execute query to Show member profile 
	public static void showProfile(Connection connect, Scanner reader) throws SQLException
	{
		// Get a statement from the connection
				Statement stmt1 = connect.createStatement() ;
				
				System.out.println("Enter Member ID:");
				int f1 = reader.nextInt();
				System.out.println("Enter Profile Name:");
				String f2 =reader.next();
				
				String sql = ( "SELECT *  FROM rental_history WHERE " + "Member_ID = " + f1 +" AND " + "profile_name="  + "\'"+ f2 +"\'" );
				System.out.println(sql);
				ResultSet rs = stmt1.executeQuery( sql) ;
				
				while( rs.next() ){
					System.out.println(( rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5)));
		
				}
	
	}
}


