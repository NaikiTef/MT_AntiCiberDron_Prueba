package MTAppComponent.MTDesktopApp;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;


public class PatLabel extends JLabel{
    public PatLabel(){
        customizeComponent();
    }
    public PatLabel(String text){
        setText(text);
        customizeComponent();
    }
    private void customizeComponent(){
        setText(getText());
        setOpaque(false);
        setBackground(null);
        setForeground(Color.GRAY);
        setHorizontalAlignment(LEFT);
    }
    public void setCustomizeComponent(String text, Font  font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);

    }
}