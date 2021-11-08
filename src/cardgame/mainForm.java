package cardgame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mainForm {
    private JPanel mainPanel;
    private JLabel title;
    private JLabel stapel;
    private JLabel platz1;
    private JLabel platz2;
    private JLabel platz3;
    private JLabel platz4;
    private JButton karteZiehenButton;
    private JRadioButton rotRadioButton;
    private JRadioButton schwarzRadioButton;
    private JRadioButton höherRadioButton;
    private JRadioButton niedrigerRadioButton;
    private JRadioButton zwischenRadioButton;
    private JRadioButton außerhalbRadioButton;
    private JRadioButton pikRadioButton;
    private JRadioButton karoRadioButton;
    private JRadioButton herzRadioButton;
    private JRadioButton kreuzRadioButton;
    private JLabel verlorenLabel;
    private JLabel gewonnenLabel;

    card cardgame[]=new card[52];
    int pos=0, erg1, erg2;
    static int runde = 1;
    static int gewonnen=0;
    static int verloren=0;

    public mainForm() {
        for(int i=0; i<52; i++){
            cardgame[i]=new card(i);
        }
        kartenMischen();
        karteZiehenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                karteZiehen();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Schlag den Mahler");
        frame.setContentPane(new mainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    public void kartenMischen(){
        card tmp;
        for(int i=0; i<52; i++) {
            Random r = new Random();
            int nr = r.nextInt(52);
            tmp = cardgame[i];
            cardgame[i] = cardgame[nr];
            cardgame[nr] = tmp;
        }
    }
    public void karteZiehen(){
        ImageIcon card = cardgame[pos].pic;
        int nr=cardgame[pos].nr;
        if(pos>=51){
            JOptionPane.showMessageDialog(null, "Kartendeck leer, Karten werden neu gemischt.");
            pos=0;
            kartenMischen();
        } else {
            pos++;
        }
        switch (runde){
            case 1:
                platz1.setIcon(card);
                if((rotRadioButton.isSelected() && nr<26) || (schwarzRadioButton.isSelected() && nr>25)){
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    rotRadioButton.setEnabled(false);
                    schwarzRadioButton.setEnabled(false);
                    höherRadioButton.setEnabled(true);
                    niedrigerRadioButton.setEnabled(true);
                    erg1=nr;
                    runde++;
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Das Spiel wird neu gestartet.");
                    verloren++;
                    verlorenLabel.setText("verloren: "+verloren);
                    neustart();
                }
                break;
            case 2:
                platz2.setIcon(card);
                if((höherRadioButton.isSelected() && (nr%13)>=(erg1%13) ) || (niedrigerRadioButton.isSelected() && (nr%13)<=(erg1%13) )){
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    höherRadioButton.setEnabled(false);
                    niedrigerRadioButton.setEnabled(false);
                    zwischenRadioButton.setEnabled(true);
                    außerhalbRadioButton.setEnabled(true);
                    erg2=nr;
                    runde++;
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Das Spiel wird neu gestartet.");
                    verloren++;
                    verlorenLabel.setText("verloren: "+verloren);
                    neustart();
                }
                break;
            case 3:
                platz3.setIcon(card);
                if((zwischenRadioButton.isSelected() && ( ( (nr%13)>=(erg1%13) && (nr%13)<=(erg2%13) ) || ( (nr%13)<=(erg1%13) && (nr%13)>=(erg2%13) ) ) ) || (außerhalbRadioButton.isSelected() && ( ( (nr%13)<=(erg1%13) && (nr%13)<=(erg2%13) ) || ( (nr%13)>(erg1%13) && (nr%13)>(erg2%13) ) ) ) ){
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    zwischenRadioButton.setEnabled(false);
                    außerhalbRadioButton.setEnabled(false);
                    pikRadioButton.setEnabled(true);
                    karoRadioButton.setEnabled(true);
                    herzRadioButton.setEnabled(true);
                    kreuzRadioButton.setEnabled(true);
                    runde++;
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Das Spiel wird neu gestartet.");
                    verloren++;
                    verlorenLabel.setText("verloren: "+verloren);
                    neustart();
                }
                break;
            case 4:
                platz4.setIcon(card);
                if((karoRadioButton.isSelected() && nr<13) || (herzRadioButton.isSelected() && nr>=13 && nr<26) || (pikRadioButton.isSelected() && nr>=26 && nr<39) || (kreuzRadioButton.isSelected() && nr>=39)){
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    pikRadioButton.setEnabled(false);
                    karoRadioButton.setEnabled(false);
                    herzRadioButton.setEnabled(false);
                    kreuzRadioButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch, du hast gewonnen!");
                    gewonnen++;
                    gewonnenLabel.setText("gewonnen: "+gewonnen);
                    neustart();
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch! Das Spiel wird neu gestartet.");
                    verloren++;
                    verlorenLabel.setText("verloren: "+verloren);
                    neustart();
                }
                break;
        }
    }
    public void neustart(){
        kartenMischen();
        int pos=0;
        erg1 = 0;
        erg2 = 0;
        runde = 1;
        platz1.setIcon(new ImageIcon(getClass().getResource("./res/cards/pos1.png")));
        platz2.setIcon(new ImageIcon(getClass().getResource("./res/cards/pos2.png")));
        platz3.setIcon(new ImageIcon(getClass().getResource("./res/cards/pos3.png")));
        platz4.setIcon(new ImageIcon(getClass().getResource("./res/cards/pos4.png")));
        rotRadioButton.setEnabled(true);
        schwarzRadioButton.setEnabled(true);
        zwischenRadioButton.setEnabled(false);
        außerhalbRadioButton.setEnabled(false);
        niedrigerRadioButton.setEnabled(false);
        höherRadioButton.setEnabled(false);
        karoRadioButton.setEnabled(false);
        herzRadioButton.setEnabled(false);
        pikRadioButton.setEnabled(false);
        kreuzRadioButton.setEnabled(false);
    }
}

