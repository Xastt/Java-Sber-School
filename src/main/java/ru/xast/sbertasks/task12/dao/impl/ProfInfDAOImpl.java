package ru.xast.sbertasks.task12.dao.impl;

import ru.xast.sbertasks.task12.dao.interfaces.ProfInfDao;
import ru.xast.sbertasks.task12.models.*;

import java.sql.*;
import java.util.*;

import static ru.xast.sbertasks.task12.utils.DataSource.connection;

public class ProfInfDAOImpl implements ProfInfDao {
    @Override
    public boolean create(ProfInf profInf, PersInf persInf) {
        String insertProfSql = "INSERT INTO profInf (persId, skillName, skillDescription, cost, persDescription, exp, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement profStmt = connection.prepareStatement(insertProfSql)) {
            profStmt.setString(1, persInf.getId());
            profStmt.setString(2, profInf.getSkillName());
            profStmt.setString(3, profInf.getSkillDescription());
            profStmt.setDouble(4, profInf.getCost());
            profStmt.setString(5, profInf.getPersDescription());
            profStmt.setDouble(6, profInf.getExp());
            profStmt.setDouble(7, profInf.getRating());
            int affectedRows = profStmt.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public ProfInf read(ProfInf profInf,String id) {
        String sql = "SELECT * FROM profInf WHERE persId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if(profInf != null ) {
                    profInf.setSkillName(rs.getString("skillName"));
                    profInf.setSkillDescription(rs.getString("skillDescription"));
                    profInf.setCost(rs.getDouble("cost"));
                    profInf.setPersDescription(rs.getString("persDescription"));
                    profInf.setExp(rs.getDouble("exp"));
                    profInf.setRating(rs.getDouble("rating"));
                }else{
                    throw new SQLException("PersInf object must not be null");
                }
            }else{
                throw new SQLException("Can't find person with id " + id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return profInf;
    }

    @Override
    public boolean update(ProfInf profInf) throws SQLException {
        if (profInf == null) {
            throw new SQLException("Profnf object must not be null");
        }
        String sql = "UPDATE profInf SET skillName = ?, skillDescription = ?, cost = ?, persDescription = ?, exp = ?, rating = ? WHERE persId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, profInf.getSkillName());
            ps.setString(2, profInf.getSkillDescription());
            ps.setDouble(3, profInf.getCost());
            ps.setString(4, profInf.getPersDescription());
            ps.setDouble(5, profInf.getExp());
            ps.setDouble(6, profInf.getRating());
            ps.setString(7, profInf.getPersId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM profInf WHERE persId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<SkillOut> readBySkillName(String skillPart) throws SQLException {
        String sql = "SELECT ps.id, ps.surname, ps.name, ps.phonenumber, ps.email,\n" +
                "\tpf.skillname, pf.skilldescription, pf.cost, pf.persdescription,\n" +
                "\tpf.exp, pf.rating FROM persinf ps JOIN profinf pf ON\n" +
                "\tps.id = pf.persid WHERE skillName LIKE ?";

        List<SkillOut> profInfList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + skillPart + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SkillOut skillOut = new SkillOut();
                skillOut.setId(rs.getString("id"));
                skillOut.setSurname(rs.getString("surname"));
                skillOut.setName(rs.getString("name"));
                skillOut.setPhoneNumber(rs.getString("phonenumber"));
                skillOut.setEmail(rs.getString("email"));
                skillOut.setSkillName(rs.getString("skillName"));
                skillOut.setSkillDescription(rs.getString("skillDescription"));
                skillOut.setCost(rs.getDouble("cost"));
                skillOut.setPersDescription(rs.getString("persDescription"));
                skillOut.setExp(rs.getDouble("exp"));
                skillOut.setRating(rs.getDouble("rating"));
                profInfList.add(skillOut);
            }

            if (profInfList.isEmpty()) {
                throw new SQLException("Can't find persons with skill name containing: " + skillPart);
            }
        } catch (SQLException e) {
            throw e;
        }
        return profInfList;
    }
}
