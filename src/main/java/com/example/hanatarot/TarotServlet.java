package com.example.hanatarot;

import com.example.hanatarot.dao.TarotDAO;
import com.example.hanatarot.dto.TarotDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tarot-cards")
public class TarotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DAO를 사용하여 데이터 가져오기
        TarotDAO tarotDAO = new TarotDAO();
        ArrayList<TarotDTO> tarotList = tarotDAO.getTarotList();

        // JSON으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(tarotList);

        // JSON 응답 설정
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
