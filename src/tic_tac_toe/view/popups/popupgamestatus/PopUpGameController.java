
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
import tic_tac_toe.utils.ImageRoutes;

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
    Media media;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        media = new Media(getClass().getResource(ImageRoutes.WIN_VIDEO).toExternalForm());
//        mediaPlayer = new MediaPlayer(media);
//        mediaView.setMediaPlayer(mediaPlayer);
//        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.seconds(1)));
//        mediaPlayer.setAutoPlay(true);
    }    

    public void setPopupStage(Stage stage) {
        this.popupStage = stage;
    }
    
    public void setPlayAgainVisablility(boolean isVisible){
        btnPlayAgain.setVisible(isVisible);
    }
     
    public void setPlayAgainBtnFunc(Runnable func) {
        btnPlayAgain.setOnAction(e -> {
            func.run();
            closePopup();
            stopVideo();
        });
    }
    
    public void setCloseBtnFunc(Runnable func) {
        btnClosePopup.setOnAction(e -> {
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
        if (msg.contains("lose") || msg.contains("lost")) {
            media = new Media(getClass().getResource(ImageRoutes.LOSE_VIDEO).toExternalForm());
            
        } else if(msg.contains("Draw") || msg.contains("draw")){
            media = new Media(getClass().getResource(ImageRoutes.DRAW_VIDEO).toExternalForm());
        } else if (msg.contains("won") || msg.contains("Won") || msg.contains("win") || msg.contains("Win")){
            media = new Media(getClass().getResource(ImageRoutes.WIN_VIDEO).toExternalForm());
        }
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.seconds(1)));
        mediaPlayer.setAutoPlay(true);
    }
    @FXML
    private void closePopupBtnHandler(ActionEvent event) {
        closePopup();
        stopVideo();
    }
}
