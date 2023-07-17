//STEP 1. Import required packages

//export CLASSPATH='/home/anshul/Downloads/session 10/mysql-connector-java-8.0.18.jar:.'
// source /home/anshul/Downloads/session 10/Library_create.sql

import java.sql.*;
import java.util.*;


public class Jdbc_IMT2020039 {

   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/Library?allowPublicKeyRetrieval=true&useSSL=false";   //"jdbc:mysql://localhost/Library?useSSL=false";	//"jdbc:mysql://localhost/companydb?allowPublicKeyRetrieval=true&useSSL=false";
   static final String USER = "anshul";      // add your user 
   static final String PASS = "";    // add password

   public static void main(String[] args) 
   {
      Connection conn = null;
      Statement stmt = null;
      Scanner sc = new Scanner(System.in);


      // STEP 2. Connecting to the Database
      try{
         //STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         //STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         //STEP 2c: Execute a query
         System.out.println("Creating statement...\n");
         stmt = conn.createStatement();

         //clearScreen();
         System.out.println("\n\n --- Welcome to Library Database Management System ---\n");

         int var = 1;
         while(var == 1)
         {
            System.out.println("Please Select an Operation: ");
            System.out.println("1. List of all books");
            System.out.println("2. List of all Students");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Add a book");
            System.out.println("6. Delete a book");
            System.out.println("7. Add a Student");
            System.out.println("8. Delete a Student");
            System.out.println("9. Add a Fine");
            System.out.println("10. Get Total Fine Due for a particular Student");
            System.out.println("11. Delete Fine");
            System.out.println("12. Exit");

            System.out.print("\nENTER YOUR CHOICE:  ");

            int choice = sc.nextInt();
            switch(choice)
            {
               case 1:
                  Get_List_Of_Books(stmt);      // Implements SELECT
                  break;
               
               case 2:
                  Get_List_Of_Students(stmt);   // Implements SELECT
                  break;

               case 3:
                  issue_book(stmt);       // Implements UPDATE
                  break;

               case 4:
                  Return_book(stmt);      // Implements UPDATE
                  break;

               case 5:
                  Add_Book(stmt);         // Implements INSERT
                  break;

               case 6:
                  Delete_Book(stmt);      // Implements DELETE
                  break;

               case 7:
                  Add_Student(stmt);      // Implements INSERT
                  break;

               case 8:
                  Delete_Student(stmt);   // Implements DELETE
                  break;
               
               case 9:
                  Add_Fine(stmt);      // Implements INSERT
                  break;

               case 10:
                  Get_Fine(stmt);      // Implements SELECT
                  break;
               
               case 11:
                  Delete_Fine(stmt);   // Implements DELETE
                  break;

               case 12:
                  System.out.println("\n---- Thank You For using Library Database !! ----\n");
                  System.exit(0);

               default:
                  System.out.println("\nPlease Enter a valid Choice\n");
                  break;
            }
         }

         stmt.close();
         conn.close();

      }catch(SQLException se){    	 //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){        	//Handle errors for Class.forName
         e.printStackTrace();
      }
      
   }
         
   static void Get_List_Of_Books(Statement stmt)
   {
      int count = 0;
      String sql = "select * from Book";
         
      try
      {
         ResultSet rs  = stmt.executeQuery(sql);
         System.out.println("\n-------------Output----------------\nList of all Books:\n");
         while (rs.next()) {
            count++;
            String id = rs.getString("Book_ID");
            String name = rs.getString("Book_Name");
            String author = rs.getString("Book_Author");
            String issuer = rs.getString("Student_ID");

            System.out.println("ID : " + id);
            System.out.println("Book Name: " + name);
            System.out.println("Author : " + author);
            System.out.println("Student_ID : " + issuer);
            
            System.out.println("");
         }
         System.out.println("-----------------------------\n");

         if(count == 0)
            System.out.println("No Books Present Currently");

      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }
   }


   static void Get_List_Of_Students(Statement stmt)
   {
      int count = 0;
      String sql = "select * from Student";
      ResultSet rs;
      try{
         rs = stmt.executeQuery(sql); 

         System.out.println("\n-------------Output----------------\nList of all Students:\n");
         while (rs.next()) {
            count++;
            String id = rs.getString("Student_ID");
            String name = rs.getString("Student_Name");
            String author = rs.getString("Student_email");

            System.out.println("Student ID : " + id);
            System.out.println("Student Name: " + name);
            System.out.println("Email : " + author);
            
            System.out.println("");
         }
         System.out.println("-----------------------------\n");

         if(count == 0)
            System.out.println("No Students Present Currently");
         
         rs.close();

      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }		//end try
   }
   

   static boolean Search_Book(Statement stmt, int ID)
   {
      int count = 0;
      String sql = "SELECT Book_ID from Book WHERE Book_ID = " + String.valueOf(ID);
      ResultSet rs;
      try{
         rs = stmt.executeQuery(sql); 

         while (rs.next())
            count++;

         if(count == 0)
            return false;
         
         rs.close();

         return true;
      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }
      					//end try

      return false;
   }
   static boolean Search_Student(Statement stmt, int ID)
   {
      int count = 0;
      String sql = "SELECT Student_ID from Student WHERE Student_ID = " + String.valueOf(ID);
      ResultSet rs;
      try{
         rs = stmt.executeQuery(sql); 

         while (rs.next())
            count++;

         if(count == 0)
            return false;
         
         rs.close();

         return true;
      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      return false;
   }
   static void issue_book(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Book ID: ");
      int Book_ID_to_be_issued = sc.nextInt();

      System.out.print("Enter Student ID: ");
      int student_ID = sc.nextInt();

      if(Search_Book(stmt, Book_ID_to_be_issued) && Search_Student(stmt, student_ID))
      {
         try {
            
            String sql = "UPDATE Book SET Student_ID = " + String.valueOf(student_ID) + " WHERE Book_ID = " + String.valueOf(Book_ID_to_be_issued) + " AND Student_ID is NULL";
            int Query_result = stmt.executeUpdate(sql);

            if (Query_result != 0)
               System.out.println("\nBook Successfully Issued");
            else
               System.out.println("\nAn Error Occurred - Issue Failed!");
            
         } catch(SQLException se){    	 //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){        	//Handle errors for Class.forName
         e.printStackTrace();
         }
      }
      else
         System.out.print("\nAn Error Occurred - Student or Book Doesn't Exist!");

      System.out.println("-----------------------------\n\n");

   }


   static void Return_book(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Book ID::  ");
         int Book_id = sc.nextInt();

         String sql = "UPDATE Book SET Student_ID = NULL WHERE Book_ID = " + String.valueOf(Book_id); 
         int Query_result = stmt.executeUpdate(sql);

         if (Query_result != 0)
            System.out.println("\nBook Return Success");
         else
            System.out.println("\nAn Error Occurred - Return Failed!");
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }

   static void Add_Book(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Book ID:  ");
         int book_id = sc.nextInt();
         String buff = sc.nextLine();
         System.out.print("Enter Book Name:  ");
         String book_name = sc.nextLine();
         System.out.print("Enter Book Author:  ");
         String book_author = sc.nextLine();

         String sql = "INSERT INTO Book(Book_ID, Book_Name, Book_Author, Student_ID) VALUES(" + String.valueOf(book_id) + ", " + "'" + String.valueOf(book_name) + "'" + ", " + "'" + String.valueOf(book_author) + "'" + ", NULL)";
         int Query_result = stmt.executeUpdate(sql);

         if (Query_result != 0)
            System.out.println("\nBook Add Success!!");
         else
            System.out.println("\nAn Error Occurred - Add Failed!");
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }

   static void Delete_Book(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Book ID: ");
         int Book_id = sc.nextInt();

         if(Search_Book(stmt, Book_id))
         {
            String sql = "DELETE FROM Book where Book_ID = " + String.valueOf(Book_id) + " AND Student_ID is NULL";
            int Query_result = stmt.executeUpdate(sql);

            if (Query_result != 0)
               System.out.println("\nBook Delete Success!!");
            else
               System.out.println("\nAn Error Occurred - Delete Failed!");
         }
         else  
            System.out.println("\nBook is not Present");
         
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }

   static void Add_Student(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Student ID:  ");
         int Student_id = sc.nextInt();
         String buff = sc.nextLine();
         System.out.print("Enter Student Name :  ");
         String Student_name = sc.nextLine();
         System.out.print("Enter Student Email :  ");
         String Student_email = sc.nextLine();

         String sql = "INSERT INTO Student(Student_ID, Student_Name, Student_email) VALUES(" + String.valueOf(Student_id) + ", " + "'" + String.valueOf(Student_name) + "'" + ", " + "'" + String.valueOf(Student_email) + "'" + ")";
         int Query_result = stmt.executeUpdate(sql);

         if (Query_result != 0)
            System.out.println("\nStudent Add Success!!");
         else
            System.out.println("\nAn Error Occurred - Add Failed!");
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }


   static void Delete_Student(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Student ID:  ");
         int Student_ID = sc.nextInt();

         if(Search_Student(stmt, Student_ID))
         {
            String sql = "DELETE FROM Student where Student_ID = " + String.valueOf(Student_ID);
            int Query_result = stmt.executeUpdate(sql);

            if (Query_result != 0)
               System.out.println("\nStudent Delete Success!!");
            else
               System.out.println("\nAn Error Occurred - Delete Failed!");
         }
         else
            System.out.println("\nStudent is not Present");
         
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }

   static void Add_Fine(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Fine ID:  ");
         int Fine_id = sc.nextInt();
         
         System.out.print("Enter Fine Amount:  ");
         int Fine_Amount = sc.nextInt();
         System.out.print("Enter Student_ID:  ");
         int Fined_To = sc.nextInt();

         String sql = "INSERT INTO Library_Fine(Fine_ID, Fine_Amount, Student_ID) VALUES(" + String.valueOf(Fine_id) + ", " + String.valueOf(Fine_Amount) + ", " + String.valueOf(Fined_To) + ")";
         int Query_result = stmt.executeUpdate(sql);

         if (Query_result != 0)
            System.out.println("\nFine Add Success!!");
         else
            System.out.println("\nAn Error Occurred - Add Failed!");
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }

   static void Get_Fine(Statement stmt)
   {
      System.out.println("\n-----------------------------");
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Student_ID:  ");
      int ID = sc.nextInt();

      String sql = "SELECT sum(Fine_Amount) AS Total_SUM from Library_Fine WHERE Student_ID = " + String.valueOf(ID);
      ResultSet rs;
      
      try{
         rs = stmt.executeQuery(sql);

         while (rs.next()) 
         {
            String Req_Sum = rs.getString("Total_SUM");
            System.out.print("\nTotol Pending Dues for Student is: ");
            System.out.println(Req_Sum);
         }

      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }


   static boolean Search_Fine(Statement stmt, int ID)
   {
      int count = 0;
      String sql = "SELECT Fine_ID from Library_Fine WHERE FIne_ID = " + String.valueOf(ID);
      ResultSet rs;
      try{
         rs = stmt.executeQuery(sql); 

         while (rs.next())
            count++;

         if(count == 0)
            return false;
         
         rs.close();

         return true;
      }catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      return false;
   }
   static void Delete_Fine(Statement stmt) 
   {
      System.out.println("\n-----------------------------");
      try {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Fine ID:  ");
         int Fine_Id = sc.nextInt();

         if(Search_Fine(stmt, Fine_Id))
         {
            String sql = "DELETE FROM Library_Fine where Fine_ID = " + String.valueOf(Fine_Id);
            int Query_result = stmt.executeUpdate(sql);

            if (Query_result != 0)
               System.out.println("\nFine Delete Success!!");
            else
               System.out.println("\nAn Error Occurred - Delete Failed!");
         }
         else  
            System.out.println("\nFine is not Present");
         
      } catch(SQLException se){    	 //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){        	//Handle errors for Class.forName
      e.printStackTrace();
      }

      System.out.println("-----------------------------\n\n");
   }   
}