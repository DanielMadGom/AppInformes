/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package appinformes;

import static appinformes.AppInformesMain.conexion;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Daniel Madrid
 */
public class AppInformesViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void listadoFacturas(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformesMain.class.getResource("Facturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void ventasTotales(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformesMain.class.getResource("VentasTotales.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void facturaCliente(ActionEvent event) {
        TextField tituloIntro = new TextField("addressid");
        Button btn = new Button();
        btn.setText("Informe");

        VBox root = new VBox();
        root.getChildren().addAll(tituloIntro, btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformesMain.class.getResource("FacturasCliente.jasper"));
                    //Map de parámetros
                    Map parametros = new HashMap();
                    int addressId = Integer.valueOf(tituloIntro.getText());
                    parametros.put("codigoCliente", addressId);

                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
                    JasperViewer.viewReport(jp, false);
                } catch (JRException ex) {
                    System.out.println("Error al recuperar el jasper");
                    JOptionPane.showMessageDialog(null, ex);
                }
                System.out.println("Generando informe");
            }
        });

        Scene scene = new Scene(root, 300, 250);
        Stage stage = new Stage();

        stage.setTitle("Obtener informe");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void subinformeFacturas(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformesMain.class.getResource("subinformeProductos.jasper"));
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformesMain.class.getResource("SubinformeFacturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            parametros.put("addressId", jsr);
            //Ya tenemos los datos para instanciar un objeto JasperPrint que permite ver,
            //imprimir o exportar a otros formatos
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros,conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
