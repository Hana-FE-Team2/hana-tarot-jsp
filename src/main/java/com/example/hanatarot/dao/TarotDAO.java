package com.example.hanatarot.dao;

import com.example.hanatarot.dto.TarotDTO;
import com.example.hanatarot.utils.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TarotDAO {

    private static final Logger logger = Logger.getLogger(TarotDAO.class.getName());

    public ArrayList<TarotDTO> getTarotList() {
        String sql = "SELECT * FROM TAROT_CARDS";
        ArrayList<TarotDTO> tarotList = new ArrayList<>();
        try {
            ResultSet rs = DBUtil.queryData(sql, DBUtil.con);
            while (rs.next()) {
                TarotDTO tarot = new TarotDTO();
                String[] description = new String[2];
                tarot.setNameEn(rs.getString("NAME_EN"));
                tarot.setNameKo(rs.getString("NAME_KO"));
                tarot.setUrl(rs.getString("URL"));
                description[0] = rs.getString("description_upright");
                description[1] = rs.getString("description_reversed");
                tarot.setDescription(description);
                tarotList.add(tarot);
            }
            logger.info("Tarot list retrieved successfully from database");
        } catch (Exception e) {
            logger.severe("Error retrieving tarot list: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return tarotList;
    }
}
