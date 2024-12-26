package ru.xast.sbertasks.task12.dao.interfaces;

import ru.xast.sbertasks.task12.models.PersInf;

import java.sql.SQLException;

public interface PersInfDao {
    boolean create(PersInf persInf);
    PersInf read(PersInf persInf, String id);
    boolean update(PersInf persInf) throws SQLException;
    boolean delete(String id);
}
