package hibernateproject;


import java.util.List;
import java.util.Scanner;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class studentcontroller {
	public static void main(String[] args) {
		int ch;
		do {
			Scanner sc=new Scanner(System.in);
			displaymenu();
			System.out.println("enter your choice :");
			ch=Integer.parseInt(sc.next()) ;
			
			switch (ch) {
			case 1:
				insertion();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				getall();
				break;
			case 5:
				getbyid();
				break;
			case 6:
				System.exit(0);
				break;
			

			default:
				System.out.println("invalid input");
				break;
			}
			
		} while (ch>0);
	}

	private static void getbyid() {
		Scanner sc=new Scanner(System.in);

		StandardServiceRegistry service=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata md=new MetadataSources(service).getMetadataBuilder().build();
		
		SessionFactory sf=md.buildSessionFactory();
		
		Session s=sf.openSession();
		System.out.println("enter id :");
		int id=sc.nextInt();
	
		Transaction t=s.beginTransaction();
		student std=s.get(student.class, id);
		if(std != null) {
			System.out.println("------------------------------------------");
			System.out.println("id :"+std.getId());
			System.out.println("Name :"+std.getName());
			System.out.println("Email :"+std.getEmail());
			System.out.println("Password :"+std.getPassword());
			System.out.println("Confirmpassword :"+std.getConfirmpassword());
			System.out.println("------------------------------------------");
		}
		else {
			System.out.println("data not found");
		}
		t.commit();
		
		
	}

	private static void getall() {
		Scanner sc=new Scanner(System.in);
		
		StandardServiceRegistry service=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata md=new MetadataSources(service).getMetadataBuilder().build();
		
		SessionFactory sf=md.buildSessionFactory();
		
		Session s=sf.openSession();
		
		Transaction t=s.beginTransaction();
		
		List<student> std=s.createQuery("from student",student.class).list();
		
		t.commit();
		for(student st : std) {
			System.out.println("------------------------------------------");
			System.out.println("id : "+st.getId());
			System.out.println("Name : "+st.getName());
			System.out.println("Email : "+st.getEmail());
			System.out.println("password: "+st.getPassword());
			System.out.println("Confirmpassword : "+st.getConfirmpassword());
			System.out.println("------------------------------------------");

		}
		
		
	}

	@SuppressWarnings("deprecation")
	private static void update() {
		Scanner sc=new Scanner(System.in);

		StandardServiceRegistry service=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata md=new MetadataSources(service).getMetadataBuilder().build();
		
		SessionFactory sf=md.buildSessionFactory();
		
		Session s=sf.openSession();
		System.out.println("enter id :");
		int id=sc.nextInt();
	
		Transaction t=s.beginTransaction();
		student std=s.get(student.class, id);
		if(std != null) {
			System.out.println("enter new name :");
			String name=sc.next();
			System.out.println("enter new email :");
			String email=sc.next();
			System.out.println("enter new password :");
			String password=sc.next();
			System.out.println("enter new confirmpassword :");
			String confirmpassword=sc.next();
			std.setName(name);
			std.getEmail();
			std.setPassword(confirmpassword);
			std.getConfirmpassword();
			s.update(std);
			t.commit();
			System.out.println("data successfully updated");
			
		}
		else {
			System.out.println("data not successfully updated");
		}		
		
	}

	@SuppressWarnings("deprecation")
	private static void delete() {
		Scanner sc=new Scanner(System.in);
		StandardServiceRegistry service=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata md=new MetadataSources(service).getMetadataBuilder().build();
		
		SessionFactory sf=md.buildSessionFactory();
		
		Session s=sf.openSession();
		
		System.out.println("enter student id :");
		int id=sc.nextInt();
		Transaction t=s.beginTransaction();
		student std=s.get(student.class, id);
		s.delete(std);
		t.commit();
		System.out.println("sucessfully deleted");
		
		
		
	}

	@SuppressWarnings("deprecation")
	private static void insertion() {
		Scanner sc=new Scanner(System.in);
		
		StandardServiceRegistry service=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata md=new MetadataSources(service).getMetadataBuilder().build();
		
		SessionFactory sf=md.buildSessionFactory();
		
		Session s=sf.openSession();
		
		Transaction t=s.beginTransaction();
		student std=new student();
		
		System.out.println("enter name :");
		std.setName(sc.next());
		System.out.println("enter email :");
		std.setEmail(sc.next());
		System.out.println("enter password :");
		std.setPassword(sc.next());
		System.out.println("enter confirmpassword :");
		std.setConfirmpassword(sc.next());
		
		s.save(std);
		t.commit();
		
		System.out.println("data sucessfully inserted");
		
		
		
		
	}

	private static void displaymenu() {
		System.out.println(".................................");
		System.out.println("\t1.insertion");
		System.out.println("\t2.delete");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		System.out.println("\t6.exit");
		System.out.println(".................................");
		
	}

}
