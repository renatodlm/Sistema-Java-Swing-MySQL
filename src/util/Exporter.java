/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author StudioMicroWeb
 */
public class Exporter {

    private File file;
    private List<JTable> tabla;
    private List<String> nom_files;

    public Exporter(File file, List<JTable> tabla, List<String> nom_files) throws Exception {
        this.file = file;
        this.tabla = tabla;
        this.nom_files = nom_files;
        if (nom_files.size() != tabla.size()) {
            throw new Exception("Error");
        }
    }

    public boolean export() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object titulo = table.getColumnName(i);
                        s.addCell(new Label(i + 1, j + 1, String.valueOf(titulo)));
                    }
                }
                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i + 1, j + 2, String.valueOf(object)));
                    }
                }
            }
            w.write();
            w.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exportTotal(String aReceber, String adiantamento, String hrmaiskm, String tituloaReceber, String tituloaAdiantamento, String tituloaHrMaisKm, String jLTotalneg, String jLTotalHr, String jLTotalKmPerco, String jLTotalPgtoKm, String Totalneg, String TotalHr, String TotalKmPerco, String TotalPgtoKm) {
        int linha = 1;
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object titulo = table.getColumnName(i);
                        s.addCell(new Label(i + 1, j + 1, String.valueOf(titulo)));
                        linha = j+5;
                    }
                    
                    System.out.println(linha);
                }
                //TOTAIS SEPARADOS
                s.addCell(new Label(1 + 0, linha + 0, String.valueOf(jLTotalneg)));
                s.addCell(new Label(1 + 1, linha + 0, String.valueOf(Totalneg)));

                s.addCell(new Label(1 + 2, linha + 0, String.valueOf(jLTotalHr)));
                s.addCell(new Label(1 + 3, linha + 0, String.valueOf(TotalHr)));

                s.addCell(new Label(1 + 4, linha + 0, String.valueOf(jLTotalKmPerco)));
                s.addCell(new Label(1 + 5, linha + 0, String.valueOf(TotalKmPerco)));

                s.addCell(new Label(1 + 6, linha + 0, String.valueOf(jLTotalPgtoKm)));
                s.addCell(new Label(1 + 7, linha + 0, String.valueOf(TotalPgtoKm)));

                //TOTAIS
                s.addCell(new Label(1 + 0, linha + 2, String.valueOf(tituloaHrMaisKm)));
                s.addCell(new Label(1 + 0, linha + 3, String.valueOf(hrmaiskm)));

                s.addCell(new Label(1 + 2, linha + 2, String.valueOf(tituloaAdiantamento)));
                s.addCell(new Label(1 + 2, linha + 3, String.valueOf(adiantamento)));

                s.addCell(new Label(1 + 4, linha + 2, String.valueOf(tituloaReceber)));
                s.addCell(new Label(1 + 4, linha + 3, String.valueOf(aReceber)));

                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i + 1, j + 2, String.valueOf(object)));
                    }
                }
            }
            w.write();
            w.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
