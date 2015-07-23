/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conecta;
import dados.Atendente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author evandro
 */
public class AtendenteDao {

    public String addAtendente(Atendente atendente) {

        Conecta conecta = new Conecta();

        if ("sucesso".equals(conecta.getStm())) {
            String sql = "INSERT INTO atendente(CODIGO, NOME, EMAIL)"
                    + "VALUES ('" + atendente.getNome() + "','" + atendente.getEmail() + "')";
            try {
                conecta.getStm().equals(sql);
                return "sucesso";
            } catch (Exception e) {
                return ("erro" + e.getMessage());
            }
        } else {
            return ("erro:" + conecta.getMsg());
        }
    }

    public List<Atendente> getAtendentes() {
        List<Atendente> listAten = new ArrayList<>();

        Conecta conecta = new Conecta();

        if ("sucesso".equals(conecta.getMsg())) {

            String sql = "SELECT ATEN.* FROM ATEN";

            ResultSet rs;

            try {
                rs = conecta.getStm().executeQuery(sql);

                while (rs.next()) {
                    int cod = rs.getInt("CODIGO");
                    String nome = rs.getString("NOME");
                    String email = rs.getString("EMAIL");

                    Atendente atendente = new Atendente();
                    atendente.setCodigo(cod);
                    atendente.setNome(nome);
                    atendente.setEmail(email);
                }
            } catch (Exception e) {

            }
            return listAten;
        }
        return listAten;

    }
}