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

import expenses.model.Shop;

@Repository
public class ShopDao {

	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Shop> getShops() throws SQLException {
		ArrayList<Shop> shops = new ArrayList<Shop>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(SQL_GET_SHOPS);
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Long shopId = result.getLong("nshp");
				String name = result.getString("name");
				
				Shop shop = new Shop();
				shop.setId(shopId);
				shop.setName(name);
				shops.add(shop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return shops;
	}
	
	public Shop getShopByName(String shopName) throws SQLException {
		Shop shop = new Shop();
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(SQL_GET_SHOP_BY_NAME);
			ps.setString(1, shopName);
			
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				Long id = result.getLong("nshp");
				String name = result.getString("name");
				shop.setId(id);
				shop.setName(name);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return shop;
	}
	
	private static String SQL_GET_SHOPS = 
			"SELECT NSHP, NAME" +
			"  FROM SHP";
	
	private static String SQL_GET_SHOP_BY_NAME = 
			"SELECT NSHP, NAME" +
			"  FROM SHP" +
			" WHERE NAME ILIKE ?";
}
