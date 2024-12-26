package ru.xast.sbertasks.task12.dao.impl;

import ru.xast.sbertasks.task12.dao.interfaces.PersInfDao;
import ru.xast.sbertasks.task12.models.PersInf;

import java.sql.*;

import static ru.xast.sbertasks.task12.utils.DataSource.connection;

public class PersInfDAOImpl implements PersInfDao {

    @Override
    public boolean create(PersInf persInf) {
        String sqlPersInf = "INSERT INTO persInf (id, surname, name, phoneNumber, email) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlPersInf)) {
            ps.setString(1, persInf.getId());
            ps.setString(2, persInf.getSurname());
            ps.setString(3, persInf.getName());
            ps.setString(4, persInf.getPhoneNumber());
            ps.setString(5, persInf.getEmail());
            int affectedRows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            return affectedRows > 0;
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public PersInf read(PersInf persInf, String id) {
        String sql = "SELECT * FROM persInf WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if( persInf!=null ) {
                    persInf.setName(rs.getString("name"));
                    persInf.setSurname(rs.getString("surname"));
                    persInf.setPhoneNumber(rs.getString("phoneNumber"));
                    persInf.setEmail(rs.getString("email"));
                }else{
                    throw new SQLException("PersInf object must not be null");
                }
            }else{
                throw new SQLException("Can't find person with id " + id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return persInf;
    }

    @Override
    public boolean update(PersInf persInf) throws SQLException {
        if (persInf == null) {
            throw new SQLException("PersInf object must not be null");
        }
        String sql = "UPDATE persInf SET surname = ?, name = ?, phoneNumber = ?, email = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, persInf.getSurname());
            ps.setString(2, persInf.getName());
            ps.setString(3, persInf.getPhoneNumber());
            ps.setString(4, persInf.getEmail());
            ps.setString(5, persInf.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM persInf WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            return false;
        }
    }
}
