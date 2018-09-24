package com.venturedive.rotikhilao.DAO.officeBoy;

import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.repository.OfficeBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficeBoyDAO implements IOfficeBoyDAO {

    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Override
    public OfficeBoy fetchOfficeBoyById(Long id) throws Exception {

        OfficeBoy officeBoy = null;
        try{
            officeBoy = officeBoyRepository.getOne(id);
        } catch (Exception e){
            throw new Exception("Invalid OfficeBoy ID provided");
        }

        return officeBoy;
    }
}
