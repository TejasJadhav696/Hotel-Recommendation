package hotel.recommendation.system.client;

import static java.lang.System.*;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import hotel.recommendation.system.model.classes.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import hotel.recommendation.system.service.*;
public class HotelRecommendationSystemMainApplicatioin {

	public static void main(String[] args) throws SQLException, IOException {
		
		out.println("\n\n\t\tWelCome To My Hotel Recommedation Application");
		
		
//                     ***** objects ******
		
		CityMasterService citymasterservice=new CityMasterService();
		Scanner sc= new Scanner(System.in);
		CityMasterModel citymastermodel=null;
		AreaMasterModel areamastermodel=null;
		ArrayList<String> listofcities=null;
		ArrayList<String> listofareas=null;
		HotelMasterModel hotelmastermodel=null;
		ArrayList ratinglist;
		
		HotelMasterService hotelmasterservice=new HotelMasterService();
		ArrayList<HotelMasterModel> listofhotels=null;
		ClientMasterModel clientdetails;
		
//-----------------------*****************************------------------------------------------------

		
//                *********  Variables ********	
		
		          String cityname=null;
		          String areaname=null;
		          String hotelname=null;
		          String hoteladdress=null;
		          String answer=null;
		          String admin_username=null;
		          String admin_password=null;
		          String clientname=null;
		          String textReview;
		          
		          int price_from=0;
		          int price_to=0;
		          int areaid=0;
		          int cityid=0;
		          boolean b;
		          int result=0;
		          int count=0;
		          int choice=0;
		          long clientnumber;
		          int hotelrating=0;
		          int hotelid=0;
		         
		          
		          
//-----------------------*****************************---------------------------------------------------
		out.println("\n\n");
		out.println("1 For Admin Long In");
		out.println("2 For User");
		choice=sc.nextInt();
		switch(choice) {
		case 1:
			do {
			out.println("Admin valid username");
			sc.nextLine();
			admin_username=sc.nextLine();
			out.println("Enter Admin Valid Password");
			admin_password=sc.nextLine();
			if(admin_username.equals("admin") && admin_password.equals("admin")) {
				
				out.println("Admin Successfully Loged In....... ");
			
			do {
				 out.println("");
				out.println("Enter 1 For Add City");
				out.println("Enter 2 For Add Area In City");
				out.println("Enter 3 For Show All Cities ");
				out.println("Enter 4 For Show Area for particular city");
				out.println("Enter 5 For Add Hotels In Area");
				out.println("Enter 6 For Show Hotels AreaWise");
				
				 choice=sc.nextInt();
				
				
				switch(choice) {
				case 1:
					citymastermodel=new CityMasterModel();
					out.println("Enter a City Id and City Name");
					cityid=sc.nextInt();
					sc.nextLine();
					cityname=sc.nextLine();
					citymastermodel.setCityName(cityname);
					citymastermodel.setCityId(cityid);
					b=citymasterservice.isAddCity(citymastermodel);
					if(b) 
						out.println("City Added Successfully..................");
					else 
						out.println("Some Problem Is There............");
				
					
					break;
				case 2:
					out.println("Enter a city name for add area");
					sc.nextLine();
					cityname=sc.nextLine();
					result=cityid=citymasterservice.getCityIdByCityName(cityname);
					if(result!=-1) {
						do {
					    out.println("Enter a area name ");
					    areaname=sc.nextLine();
					    areamastermodel=new AreaMasterModel();
					    areamastermodel.setAreaname(areaname);
					    areamastermodel.setCityId(cityid);
					    b=citymasterservice.isAddArea(areamastermodel);
					    if(b) {
					    	out.println("Area Added Successfully...........");
					    }
					    else
					    	out.println("Some Problem is There................");
					    
					    out.println("do you want to add city");
				        answer=sc.nextLine();
						}while(answer.equals("yes"));
					    
					    
					}
					else
						out.println("city is not present");
					
					break;
					
				case 3:
					out.println("Total Cities Present in Your Database\n");
					listofcities=new ArrayList<String>();
					listofcities=citymasterservice.getAllCities();
					if(listofcities!=null) {
					count=0;
					for(String city:listofcities) {
						count++;
						out.println(count+") "+city);
						
					}
					}else
						out.println("There is no area present in database");
					
					break;
					
				case 4:
					out.println("Enter a city for area name");
					sc.nextLine();
					cityname=sc.nextLine();
					listofareas=citymasterservice.getAreasCityWise(cityname);
				
					if(listofareas!=null) {
						count=0;
						for(String area:listofareas) {
							count++;
							out.println(count+") "+area);
						}
					}else
						out.println("There is no area present in "+cityname);
					
					break;
				case 5:
					out.println("Enter a city name where you want to add hotel");
					sc.nextLine();
					cityname=sc.nextLine();
					cityid=citymasterservice.getCityIdByCityName(cityname);
					listofareas=citymasterservice.getAreasCityWise(cityname);
					count=1;
					for(String area:listofareas) {
						out.println(count+") "+area);
						count++;
					}
					if(cityid!=-1) {
					out.println("Enter a area name where you want to add hotel");
					areaname=sc.nextLine();
					areaid=citymasterservice.getAreaIdByAreaName(areaname);
				    if(areaid!=-1)
				    {
				    	do {
				    	out.println("Enter Hotel Name");
				    	hotelname=sc.nextLine();
				    	out.println("Enter Hotel Address");
				    	hoteladdress=sc.nextLine();
				    	out.println("Enter Hotel Price Range From & To");
				    	price_from=sc.nextInt();
				    	price_to=sc.nextInt();
				    	sc.nextLine();
				    	hotelmastermodel=new HotelMasterModel();
				    	hotelmastermodel.setCityId(cityid);
				    	hotelmastermodel.setAreaid(areaid);
				    	hotelmastermodel.setHotelname(hotelname);
				    	hotelmastermodel.setHoteladdress(hoteladdress);
				    	hotelmastermodel.setPriceFrom(price_from);
				    	hotelmastermodel.setPriceTo(price_to);
				    	b=hotelmasterservice.isAddHotel(hotelmastermodel);
				    	if(b)
				    		out.println("Hotel Added Successfully.......");
				    	else
				    		out.println("Some problem is there for add hotel");
				    	
				    	out.println("do you want to add another hotel");
				    	answer=sc.nextLine();
				    	}while(answer.equals("yes"));
				    	
				    	
				    	
				    }else {
				    	out.println("No Area Present In this city");
				    	out.println("do you want to add this area in "+cityname);
				    	answer=sc.nextLine();
				    	if(answer.equals("yes")) {     
				    	out.println("Enter a area name ");
					    areaname=sc.nextLine();
					    areamastermodel=new AreaMasterModel();
					    areamastermodel.setAreaname(areaname);
					    areamastermodel.setCityId(cityid);
					    b=citymasterservice.isAddArea(areamastermodel);
					    if(b) {
					    	out.println("Area Added Successfully...........");
					    }
					    else
					    	out.println("Some Problem is There................");
				       }else {
				    	   out.println("Thank You For Visiting.........");
				       }
				     }
					}else
						out.println("city not present");
					
					
					
					break;
				case 6:
					
					// show all hotels particular area wise.......
					out.println("Enter a area for hotels ");
					sc.nextLine();
					areaname=sc.nextLine();
					listofhotels=hotelmasterservice.getAllHotelsAreaWise(areaname);
					if(listofhotels.size()>0) {
						out.println("hotelname\thotel address\t\thotel price range");
						out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
						for(HotelMasterModel hotel:listofhotels) {
							out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\n");
						}
						out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");

						
					}else
						out.println("No Hotel Present in This Area......");
					break;
				}
				}while(choice<=7);
			}
			else {
				out.println("wrong Admin Username or Password\n\n");
			}
			}while(choice>=0);
			
			break;
		case 2:
			do {
			out.println("\n\n Enter 1 For See All Hotel AreaWise");
			out.println("Enter 2 For Enter in hotel");
			
			out.println("\n\nEnter Your Choice");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				// show all hotels particular area wise.......
				out.println("Enter a area for hotels ");
				sc.nextLine();
				areaname=sc.nextLine();
				listofhotels=new ArrayList<HotelMasterModel>();
				listofhotels=hotelmasterservice.getAllHotelsAreaWise(areaname);
				if(listofhotels.size()>0) {
					out.println("hotelname\thotel address\t\thotel price range");
					out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
					for(HotelMasterModel hotel:listofhotels) {
						out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\n");
					}
					out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
				
				}else
					out.println("No Hotel Present in This Area......");
				
				break;
			case 2:
				
				// Enter Client Details 
				out.println("Enter Your Name");
				sc.nextLine();
				clientname=sc.nextLine();
				out.println("Enter Your contact Number");
				clientnumber=sc.nextLong();
				out.println("Enter City Name");
				sc.nextLine();
				cityname=sc.nextLine();
				out.println("Enter Area Name");
				areaname=sc.nextLine();
				
				// get city id and area id ..........
				
				areaid=citymasterservice.getAreaIdByAreaName(areaname);
				cityid=citymasterservice.getCityIdByCityName(cityname);

				out.println("\n\nEnter 1 For Recommendation bases on Hotel Rating");
				out.println("Enter 2 For Recommendation bases on Budget\n\n");
				choice=sc.nextInt();
				switch(choice) {
				case 1:
					listofhotels=new ArrayList<HotelMasterModel>();
					hotelmastermodel=new HotelMasterModel();
					
					listofhotels=hotelmasterservice.getHotelRatingWise(areaname);
					if(listofhotels.size()>0) {
					out.println("hotelname\thotel address\t\thotel price range\thotelrating");
					out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
					for(HotelMasterModel hotel:listofhotels) {
						out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\t\t"+hotel.getHotelRating()+"\n");
					}
					out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
					
					out.println("do you want to go in any above hotel");
					sc.nextLine();
					answer=sc.nextLine();
					
					if(answer.equals("yes")) {
					out.println("Enter hotel Name");
					hotelname=sc.nextLine();
					// here i get hotel id which user want to go.........
					hotelid=hotelmasterservice.getHotelIdByHotelName(cityname,areaname,hotelname);
					
					out.println("Enter Hotel Rating");
					hotelrating=sc.nextInt();
					sc.nextLine();
				    out.println("Enter you valuable review for this hotel");
					textReview=sc.nextLine();
					clientdetails=new ClientMasterModel();
					clientdetails.setHotelId(hotelid);
					clientdetails.setClientName(clientname);
					clientdetails.setClientNumber(clientnumber);
					clientdetails.setHotelRating(hotelrating);
					clientdetails.setTextReview(textReview);
					b=hotelmasterservice.isAddClientDetails(clientdetails);
					if(b)
						out.println("Client Review Added Successfully..........");
					else
						out.println("Some problem is there....................");
					}else {
						out.println("     Thank Your for Visiting............");
					}
					
					}else {
						out.println("There is no Hotels Present In this Area");
					}
					
					 break;
				
				}
				break;
				}
				break;
			}while(choice<=2);
			break;
		}
		
		
		
		

	}

}
