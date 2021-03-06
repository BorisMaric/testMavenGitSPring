package fr.bonplans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;



import fr.bonplans.dao.interfaces.CategorieDAO;
import fr.bonplans.modele.Categorie;

public class JdbcCategorieDAO implements CategorieDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Categorie categorie) {
		String sql = "INSERT INTO Categorie " +
				"(nom) VALUES (?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categorie.getNom());


			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}

	}


	public ArrayList<Categorie> selectAll(){
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		String sql = "SELECT * FROM Categorie";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Categorie categorie = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				categorie = new Categorie();
				categorie.setNom(rs.getString("nom"));
				categorie.setId(rs.getString("id"));
				categories.add(categorie);
				
			}
			rs.close();
			ps.close();
			return categories;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
