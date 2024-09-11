package hotel.recommendation.system.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import hotel.recommendation.system.model.classes.ClientMasterModel;
import hotel.recommendation.system.model.classes.HotelMasterModel;
import hotel.recommendation.system.repository.HotelMasterRepository;

public class HotelMasterService { 
	
	HotelMasterRepository hotelmasterrepository=new HotelMasterRepository();
	
	public boolean isAddHotel(HotelMasterModel hotelmastermodel) throws SQLException {
		return hotelmasterrepository.isAddHotel(hotelmastermodel);
	}
	
	public ArrayList<HotelMasterModel> getAllHotelsAreaWise(String areaname){
	
		return hotelmasterrepository.getAllHotelsAreaWise(areaname);
	}
	
	public ArrayList getHotelRatingWise(String areaname) {
		return hotelmasterrepository.getHotelRatingWise(areaname);
	}
	
	public boolean isAddClientDetails(ClientMasterModel clientdetails) throws SQLException {
		
		return hotelmasterrepository.isAddClientDetails(clientdetails);
	}
	public int getHotelIdByHotelName(String cityname,String areaname,String hotelname) throws SQLException {
		return hotelmasterrepository.getHotelIdByHotelName(cityname,areaname,hotelname);
	}
	
}
