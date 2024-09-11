package hotel.recommendation.system.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import hotel.recommendation.system.databaseconfigration.getDBConnection;
import hotel.recommendation.system.model.classes.ClientMasterModel;
import hotel.recommendation.system.model.classes.HotelMasterModel;

public class HotelMasterRepository extends getDBConnection {
    boolean b;
    int hotelid=0;
    int clientid=0;
    LinkedHashMap linkedhashmap=null;
    HotelMasterModel hotelmastermodel;
    ArrayList al;
    int result=0;
	public boolean isAddHotel(HotelMasterModel hotelmastermodel) throws SQLException {
		
	hotelid=this.getHotelId();
		cstmt=conn.prepareCall("call saveDataHotelmasterAndareahoteljoin(?,?,?,?,?,?,?)");
		cstmt.setInt(1,hotelmastermodel.getAreaid());
		cstmt.setInt(2,hotelid);
		cstmt.setString(3,hotelmastermodel.getHotelname());
		cstmt.setString(4,hotelmastermodel.getHoteladdress());
		cstmt.setInt(5,hotelmastermodel.getPriceFrom());
		cstmt.setInt(6,hotelmastermodel.getPriceTo());
		cstmt.setInt(7,0);
		b=cstmt.execute();
		if(!b)
			return true;
		else
			return false;
		
	}
	
	public int getHotelId() throws SQLException {
		pstmt=conn.prepareStatement("select max(hotelid)from hotelmaster");
		rs=pstmt.executeQuery();
		if(rs.next())
			hotelid=rs.getInt(1);
		return ++hotelid;
	}
	
	public ArrayList getAllHotelsAreaWise(String areaname){
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement(" select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster area on area.areaid=ah.areaid where area.areaname=?");
			pstmt.setString(1,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				al.add(hotelmastermodel);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
		
	}
	
	public ArrayList getHotelRatingWise(String areaname) {
		hotelmastermodel=new HotelMasterModel();
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating from hotelmaster h inner join areahoteljoin cs on cs.hotelid=h.hotelid inner join areamaster a on a.areaid=cs.areaid where a.areaname=? order by h.hotelrating desc");
			pstmt.setString(1,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getInt(6));
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
		
	}
	
	public int getHotelIdByHotelName(String cityname,String areaname,String hotelname) throws SQLException {
		pstmt=conn.prepareStatement("select h.hotelid from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster a on a.areaid=ah.areaid inner join cityareajoin ca on ca.areaid=a.areaid inner join citymaster c on c.cityid=ca.cityid where c.cityname=? and a.areaname=?");
		pstmt.setString(1,cityname);
		pstmt.setString(2,areaname);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}else {
			return -1;
		}
	}
	
	
	public boolean isAddClientDetails(ClientMasterModel clientdetails) throws SQLException {
		clientid=this.generateAutoClientId();
	    cstmt=conn.prepareCall("call clientandfeedback('0',?,?,?,?,?,?)");
	    cstmt.setInt(1,clientid);
	    cstmt.setInt(2,clientdetails.getHotelId());
	    cstmt.setString(3,clientdetails.getClientName());
	    cstmt.setLong(4,clientdetails.getClientNumber());
	    cstmt.setInt(5,clientdetails.getHotelRating());
	    cstmt.setString(6,clientdetails.getTextReview());
	    b=cstmt.execute();
		if(!b) {
		   

			return true;
		}
		else {
			return false;
			
		}
	}
	
	
	public int generateAutoClientId() throws SQLException {
		pstmt=conn.prepareStatement("select max(clientid) from clientmaster");
		rs=pstmt.executeQuery();
		if(rs.next()) {
			clientid=rs.getInt(1);
		}
		return ++clientid;
		
	}
	
}
