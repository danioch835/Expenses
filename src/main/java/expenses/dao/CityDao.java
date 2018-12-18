package expenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import expenses.model.City;

@Repository
public class CityDao {
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<City> getCitiesList() throws SQLException {
		List<City> cities = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_GET_CITIES);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				Long id = res.getLong("ncit");
				String name = res.getString("name");
				City city = new City();
				city.setId(id);
				city.setName(name);
				cities.add(city);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return cities;
	}
	
	public City getCityById(long cityId) throws SQLException {
		City city = new City();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_GET_CITY_BY_ID);
			ps.setLong(1, cityId);
			
			ResultSet res = ps.executeQuery();
			res.next();
			
			Long id = res.getLong("ncit");
			String name = res.getString("name");
			
			city.setId(id);
			city.setName(name);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return city;
	}
	
	public City getCityByName(String cityName) throws SQLException {
		City city = new City();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_GET_CITY_BY_NAME);
			ps.setString(1, cityName);
			
			ResultSet res = ps.executeQuery();
			res.next();
			
			Long id = res.getLong("NCIT");
			String name = res.getString("NAME");
			
			city.setId(id);
			city.setName(name);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return city;
	}
	
	private static String SQL_GET_CITIES = 
			"SELECT NCIT,NAME " +
			"  FROM CIT ";
	
	private static String SQL_GET_CITY_BY_ID = 
			"SELECT NCIT,NAME " +
			"  FROM CIT " +
			" WHERE NCIT=?";
	
	private static String SQL_GET_CITY_BY_NAME = 
			"SELECT NCIT,NAME " +
			"  FROM CIT " +
			" WHERE NAME ILIKE ?";
}
