package ru.xast.sbertasks.task12.dao.interfaces;

import ru.xast.sbertasks.task12.models.*;

import java.sql.SQLException;
import java.util.List;

public interface ProfInfDao {
    boolean create(ProfInf profInf, PersInf persInf);
    ProfInf read(ProfInf profInf, String id);
    boolean update(ProfInf profInf) throws SQLException;
    boolean delete(String id);
    List<SkillOut> readBySkillName(String skillPart) throws SQLException;
}
