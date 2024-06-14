/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BukuDb;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Buku;
import view.FormBukuDb;
import java.util.List;

/**
 *
 * @author ORYZA CONSEVA
 */
public class BukuControllerDb {
    FormBukuDb formBukuDb;
    Buku buku;
    BukuDb bukuDb;
    
    public BukuControllerDb(FormBukuDb formBukuDb){
        this.formBukuDb = formBukuDb;
        bukuDb = new BukuDb();
    }
    
    public void cancel(){
        formBukuDb.getTxtKodeBuku().setText("");
        formBukuDb.getTxtJudul().setText("");
        formBukuDb.getTxtPengarang().setText("");
        formBukuDb.getTxtPenerbit().setText("");
        formBukuDb.getTxtTahun().setText("");
    }
    
    public void insert(){
        try {
            buku = new Buku();
            buku.setKodebuku(formBukuDb.getTxtKodeBuku().getText());
            buku.setJudul(formBukuDb.getTxtJudul().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setPenerbit(formBukuDb.getTxtPenerbit().getText());
            buku.setTahun(formBukuDb.getTxtTahun().getText());
            bukuDb.insert(buku);
            JOptionPane.showMessageDialog(formBukuDb, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formBukuDb, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formBukuDb.getTabelBukuDb().getModel();
            model.setNumRows(0);
            List<Buku> list = bukuDb.getAllBukuDb();
            for(Buku bk : list){
                Object[] data = {
                    bk.getKodebuku(),
                    bk.getJudul(),
                    bk.getPengarang(),
                    bk.getPenerbit(),
                    bk.getTahun()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MahasiswaControllerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String Kodebuku = formBukuDb.getTabelBukuDb()
                    .getValueAt(formBukuDb.getTabelBukuDb().getSelectedRow(), 0).toString();
            buku = bukuDb.getBuku(Kodebuku);
            formBukuDb.getTxtKodeBuku().setText(buku.getKodebuku());
            formBukuDb.getTxtJudul().setText(buku.getJudul());
            formBukuDb.getTxtPengarang().setText(buku.getPengarang());
            formBukuDb.getTxtPenerbit().setText(buku.getPenerbit());
            formBukuDb.getTxtTahun().setText(buku.getTahun());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BukuControllerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            buku.setKodebuku(formBukuDb.getTxtKodeBuku().getText());
            buku.setJudul(formBukuDb.getTxtPenerbit().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setPenerbit(formBukuDb.getTxtPenerbit().getText());
            buku.setTahun(formBukuDb.getTxtTahun().getText());
            bukuDb.update( buku);
            JOptionPane.showMessageDialog(formBukuDb, "Update Data Ok");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BukuControllerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try{    
            String Kodebuku = formBukuDb.getTabelBukuDb()
                    .getValueAt(formBukuDb.getTabelBukuDb().getSelectedRow(), 0).toString();
            bukuDb.delete(Kodebuku);
            JOptionPane.showMessageDialog(formBukuDb, "Delete Data Ok");
        }catch (Exception ex) {
            java.util.logging.Logger.getLogger(BukuControllerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}