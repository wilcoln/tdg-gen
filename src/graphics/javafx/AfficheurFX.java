package graphics.javafx;

import graphics.AfficheurGraphique;
import javafx.scene.control.Alert;
import jeu.Partie;



public class AfficheurFX implements  AfficheurGraphique {

    Partie partie;

    public AfficheurFX(Partie partie){
        this.partie = partie;
    }

    @Override
    public void afficherTerrain() {

    }

    @Override
    public void afficherMenuPause() {

    }

    @Override
    public void cacherMenuPause() {

    }

    @Override
    public void afficheDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait();
    }

}

