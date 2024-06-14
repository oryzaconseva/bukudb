/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import model.Buku;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ORYZA CONSEVA
 */
public class BukuDb {
    public void insert(Buku buku) throws Exception{
        DBHelper db = new DBHelper();
        String query = "insert into buku values(?,?,?,?,?)";
        PreparedStatement ps = (PreparedStatement) db.getKoneksi().prepareStatement(query);
        ps.setString(1, buku.getKodebuku());
        ps.setString(2, buku.getJudul());
        ps.setString(3, buku.getPengarang());
        ps.setString(4, buku.getPenerbit());
        ps.setString(5, buku.getTahun());
        ps.executeUpdate();
    }
    
    public void update(Buku buku) throws Exception{
        DBHelper db = new DBHelper();
        String query = "update buku set kodebuku=?, judul=?,"+"pengarang=?, penerbit=? where tahun=?";
        PreparedStatement ps = (PreparedStatement) db.getKoneksi().prepareStatement(query);
        ps.setString(1, buku.getKodebuku());
        ps.setString(2, buku.getJudul());
        ps.setString(3, buku.getPengarang());
        ps.setString(4, buku.getPenerbit());
        ps.setString(5, buku.getTahun());
        ps.executeUpdate();
    }
    
    public void delete(String KodeBuku) throws Exception{
        DBHelper db = new DBHelper();
        String query = "delete from buku where tahun = ?";
        PreparedStatement ps = (PreparedStatement) db.getKoneksi().prepareStatement(query);
        ps.setString(1, KodeBuku);
        ps.executeUpdate();
    }
    
    public Buku getBuku(String KodeBuku) throws Exception{
        DBHelper db = new DBHelper();
        String query = "select * from buku where tahun=?";
        PreparedStatement ps = (PreparedStatement) db.getKoneksi().prepareStatement(query);
        ps.setString(1, KodeBuku);
        Buku buku = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            buku = new Buku();
            buku.setKodebuku(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
        }
        return buku;
    }
    
    public List<Buku> getAllBukuDb() throws Exception{
        DBHelper db = new DBHelper();
        String query = "select * from buku";
        PreparedStatement ps = (PreparedStatement) db.getKoneksi().prepareStatement(query);
        List<Buku> list = new ArrayList();
        Buku buku = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            buku = new Buku();
            buku.setKodebuku(rs.getString(1));
            buku.setJudul(rs.getString(2));
            buku.setPengarang(rs.getString(3));
            buku.setPenerbit(rs.getString(4));
            buku.setTahun(rs.getString(5));
            list.add(buku);
        }
        return list;
    }
}
