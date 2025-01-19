
package tic_tac_toe.view.popups.popupgamestatus;

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
import javafx.stage.Stage;
import javafx.util.Duration;
import tic_tac_toe.navigation.Navigator;

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
    private Button btnPlayAgain;
    
    @FXML
    private Button btnClosePopup;
    
    private Stage popupStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media(getClass().getResource("/tic_tac_toe/assets/tempVideo.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.seconds(1)));
        mediaPlayer.setAutoPlay(true);
    }    

    public void setPopupStage(Stage stage) {
        this.popupStage = stage;
    }
     
    public void setPlayAgainBtnFunc(Runnable func) {
        btnPlayAgain.setOnAction(e -> {
            func.run();
            closePopup();
            stopVideo();
        });
    }
     
    public void closePopup() {
        if (popupStage != null) {
                popupStage.close();
            }
    }
    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    
    public void setPopupStatusMsg(String msg){
        lblStatus.setText(msg);
    }
    @FXML
    private void closePopupBtnHandler(ActionEvent event) {
        closePopup();
        stopVideo();
    }
}
