package Database;

import java.sql.*;

import java.util.*;

import Algorithm.Order;
import Blueprints.Customer;

public class DBUtilities {

    private static Connection con;
    private static Statement stmt;
    private static Scanner scan = new Scanner(System.in);
    private static int which;

    public static Connection createConnection() {
        
        System.out.println("What is the username for the database?");
        String user = scan.nextLine();
        System.out.println("What is the password?");
        String pass = scan.nextLine();
        System.out.println("What is the name of the database?");
        String name = scan.nextLine();

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + name;

        System.out.println(driver);
        System.out.println(url);

        try { // load the driver 
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successful!");

        } catch (Exception e) { // problem loading driver, class not exist?
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkConnect() {
        if (con == null) {
            con = createConnection();
        }
        if (stmt == null) {
            try {
                stmt = con.createStatement();
            } catch (SQLException e) {
                System.out.println("Cannot create the statement");
            }

        }
    }

    // we will assume that the database already exists
    public static void createTableOrder(boolean current) {
        String name = "";
        if (current == false) {
            System.out.println("What do you want to name the orders table?");
            name = scan.next();
        } else {
            name = "Orders";
        }
        String query = "create table " + name + " (orderId varchar(15), gizmoliosType varchar(20), gizmoliosTime smallint, "
        		+ "customerName varchar(50), latePeanlty smallint, orderArrivalDate varchar(20), orderArrivalTime varchar(6),"
        		+ " requestedDate varchar(20), requestedTime varchar(6), stratProcessingDate varchar(20), stratProcessingTime varchar(6),"
        		+ " endProcessingDate varchar(20), endProcessingTime varchar(6), penalty smallint)";
        String dropString = "drop table " + name;

        checkConnect();

        try {
            // drop the table if it exists.  If it does not, it will throw a SQLException
            stmt.executeUpdate(dropString);
            System.out.println("Orders table existed so I dropped it");

        } catch (SQLException e) {
            // catch that exception and do nothing
            System.out.println("Orders table did not exist so I will create it");
        }

        try {
            // now create the table
            stmt.executeUpdate(query);
            System.out.println("Orders table created");
        } catch (SQLException e) {
            // will catch bad SQL format
            System.out.println("execute update error");
        }

    }

//	 we will assume that the database already exists
   /* public static void createTableInvoice(boolean current) {
        String name = "";
        if (current == false) {
            System.out.println("What do you want to name the invoice table?");
            name = scan.next();
        } else {
            name = "invoice";
        }

        String query = "create table " + name + " (invId int not null, custId int, amount double, invDate date, primary key (invId))";
        String dropString = "drop table " + name;

        checkConnect();

        try {
            // drop the table if it exists.  If it does not, it will throw a SQLException
            stmt.executeUpdate(dropString);
            System.out.println("Invoice table existed so I dropped it");

        } catch (SQLException e) {
            // catch that exception and do nothing
            System.out.println("Invoice table did not exist so I will create it");
        }

        try {
            // now create the table
            stmt.executeUpdate(query);
            System.out.println("Invoice table created");
        } catch (SQLException e) {
            // will catch bad SQL format
            System.out.println("execute update error");
        }

    }*/

    public static void storeOrder(LinkedList<Order> order, boolean current) {
        checkConnect();

        String name = "";
        if (current == false) {
            System.out.println("What did you name the warehouse orders table?");
            name = scan.next();
        } else {
            name = "Orders";
        }

        for (int i = 0; i < order.size(); i++) {

            Order o = order.get(i);
            String query = "insert into " + name + "  values('" +"Order_"+ (i+1) + "\',\'" + o.getCandy().getType() + "\'," + o.getCandy().getTimeToMake() + ",\'" + o.getCustomer().getCustName() + "\'," 
            		+ o.getCustomer().getPenalty()+ ",\'" + o.getiTR().getArrival().getLd() + "\',\'" + o.getiTR().getArrival().getLt()+ "\',\'" + o.getiTR().getRequest().getLd() + "\',\'" + o.getiTR().getRequest().getLt() +
            		"\',\'" + o.getfTR().getStart().getLd() + "\',\'" + o.getfTR().getStart().getLt() + "\',\'" + o.getfTR().getEnd().getLd() + "\',\'" + o.getfTR().getEnd().getLt() + "\'," + o.getLastPenalty() + ")";
            System.out.println(query);
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL insert Exception");
            }
        }

    }

  /*  public static void storeInvoice(ArrayList<Invoice> inv, boolean current) {
        checkConnect();
        String name = "";
        if (current == false) {
            System.out.println("What did you name the warehouse invoice table?");
            name = scan.next();
        } else {
            name = "invoice";
        }
        String query;
        for (int i = 0; i < inv.size(); i++) {
            Invoice invoice = inv.get(i);

            if (which == 1) {
                query = "insert into " + name + " values (" + invoice.getInvoiceNum() + "," + invoice.getCust().getId() + "," + invoice.getAmount() + ",\'" + invoice.getDate().toString() + "\')";
            } else {
                query = "insert into " + name + " values (" + invoice.getInvoiceNum() + "," + invoice.getCust().getId() + "," + invoice.getAmount() + ",\'" + invoice.getDate().toStringMySQL() + "\')";
            };

            System.out.println(query);
            try {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL insert Exception");
            }
        }

    }

    public static void findCust() {
        System.out.println("Customer id?");
        int num = scan.nextInt();

        checkConnect();

        String query = "Select * from customer where custId = " + num;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null) {
                rs.next();
                Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
                System.out.println(c.toString());
            } else {
                System.out.println("That id does not exist");
            }
        } catch (SQLException e) {
            System.out.println("That customer id does not exist");
        }
    }

    public static void findInvoices() {
        System.out.println("Find all the invoices for what customer number");
        int num = scan.nextInt();

        checkConnect();
        Customer c = null;
        String query = "Select * from customer where custId= " + num;
        String query2 = "Select * from invoice where custId = " + num;

        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null) {
                rs.next();
                c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
                System.out.println(c.toString());
            } else {
                System.out.println("That customer id does not exist");
            }

            ResultSet rs2 = stmt.executeQuery(query2);
            if (rs2 != null) {
                while (rs2.next()) {
                    Invoice in = new Invoice(rs2.getInt(1), c, rs2.getDouble(3), rs2.getString(4));
                    System.out.println(in.toString());
                }
            } else {
                System.out.println("That customer does not have any invoices");
            }
        } catch (SQLException e) {
            System.out.println("That customer id does not exist");
        }

    }*/

  /* public static ArrayList populateArrayLists() {
        checkConnect();
        ArrayList<ArrayList> al = new ArrayList<ArrayList>();
        ArrayList<Customer> cust = new ArrayList<Customer>();
        ArrayList<Invoice> inv = new ArrayList<Invoice>();
        Customer c = null;
        String query = "Select * from customer ";
        String query2 = "Select * from invoice ";

        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
                    //System.out.println(c.toString());
                    cust.add(c);
                }
            } else {
                System.out.println("That customer id does not exist");
            }

            ResultSet rs2 = stmt.executeQuery(query2);
            if (rs2 != null) {
                Customer cu = null;
                while (rs2.next()) {
                    int custNum = rs2.getInt(2);
                    boolean found = false;
                    int i = 0;
                    while (!found && i < cust.size()) {
                        if (cust.get(i).getId() == custNum) {
                            cu = cust.get(i);
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    Invoice in = new Invoice(rs2.getInt(1), cu, rs2.getDouble(3), rs2.getString(4));
                    //System.out.println(in.toString());
                    inv.add(in);
                }
            } else {
                System.out.println("That customer does not have any invoices");
            }
        } catch (SQLException e) {
            System.out.println("That customer id does not exist");
        }
        al.add(cust);
        al.add(inv);
        return al;

    } */

}
