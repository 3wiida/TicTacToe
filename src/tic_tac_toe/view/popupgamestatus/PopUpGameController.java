
package tic_tac_toe.view.popupgamestatus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Khaled Mustafa
 */
public class PopUpGameController implements Initializable {
    
    @FXML
    private MediaView mediaView;
    
    private MediaPlayer mediaPlayer;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnPlayAgain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media(getClass().getResource("/tic_tac_toe/assets/tempVideo.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(false);
    }    

    @FXML
    private void newGameClicked(ActionEvent event) {
    }

    @FXML
    private void playAgainClicked(ActionEvent event) {
    }
    
}
