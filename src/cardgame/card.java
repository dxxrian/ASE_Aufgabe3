package cardgame;

import javax.swing.*;

public class card {
    int nr;
    ImageIcon pic;
    card(int n){
        nr = n;
        java.net.URL url=getClass().getResource("./res/cards/card"+nr+".png");
        pic = new ImageIcon(url, "card"+nr);
    }
}
