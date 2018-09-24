package com.venturedive.rotikhilao.DAO.officeBoy;

import com.venturedive.rotikhilao.model.OfficeBoy;

public interface IOfficeBoyDAO {

    public abstract OfficeBoy fetchOfficeBoyById(Long id) throws Exception;
}
