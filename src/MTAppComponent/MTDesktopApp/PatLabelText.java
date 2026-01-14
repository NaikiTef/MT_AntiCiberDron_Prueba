package MTAppComponent.MTDesktopApp;

import javax.swing.*;


import java.awt.*;

public class PatLabelText extends JPanel{
    private PatLabel    lblEtiqueta = new PatLabel();
    private PatTextBox  txtContenido= new PatTextBox();

    public PatLabelText(String etiqueta) {
        setLayout(new BorderLayout());

        // Si tienes métodos alternativos para el estilo, cámbialos aquí
        lblEtiqueta.setText(etiqueta);
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
    }
}
