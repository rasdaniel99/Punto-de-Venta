/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ticket;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author Daniel
 */
public class Ticket {
    public String contentTicket ="\n"
            + "*******Recauderia y Misc********\n"
            + "    ********'La Joya'********\n"
            + " Avenida la joya esq. maravillas\n"
            + "    col. la joya Mz 6 Lt 44\n"
            + "================================\n"
            + "Ticket # {{ticket}}\n"
            + "{{dateTime}}\n"
            + "Cant.  Descripcion       Importe\n"
            + "================================\n"
            + "{{items}}\n"
            + "================================\n"
            + "TOTAL: {{total}}\n"
            + "================================\n"
            + "     GRACIAS POR SU COMPRA...\n"
            + "      ******::::::::*******"
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           ";

    //El constructor que setea los valores a la instancia
     public Ticket(String ticket,String dateTime, Stack items,String total) {
        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
        String p = items.toString();
        String a;
        String b;
        String c;
        a= p.replace("[", "");
        b= a.replace("]", "");
        c= b.replace(", ", "");
        this.contentTicket = this.contentTicket.replace("{{items}}", c);
        this.contentTicket = this.contentTicket.replace("{{total}}", total);
    }

    public void Imprimir() {
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        //Aca obtenemos el servicio de impresion por defatul
        //Si no quieres ver el dialogo de seleccionar impresora usa esto
        //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();


        //Con esto mostramos el dialogo para seleccionar impresora
        //Si quieres ver el dialogo de seleccionar impresora usalo
        //Solo mostrara las impresoras que soporte arreglo de bits
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);

        //Creamos un arreglo de tipo byte
        byte[] bytes;

        //Aca convertimos el string(cuerpo del ticket) a bytes tal como
        //lo maneja la impresora(mas bien ticketera :p)

        bytes = this.contentTicket.getBytes();

        //Creamos un documento a imprimir, a el se le appendeara
        //el arreglo de bytes
        Doc doc = new SimpleDoc(bytes, flavor, null);

        //Creamos un trabajo de impresiÃ³n
        DocPrintJob job = service.createPrintJob();
        System.out.println(this.contentTicket);
        //Imprimimos dentro de un try de a huevo
        try {
            //El metodo print imprime
            job.print(doc, null);
            
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
        }
}
}
