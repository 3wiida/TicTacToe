package tic_tac_toe.view.availableUsers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONObject;
import tic_tac_toe.common.ClientSocket;
import tic_tac_toe.model.Player;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.view.landing.LandingScreenController;
import tic_tac_toe.view.login.LoginScreenController;
import tic_tac_toe.view.offline.offline_with_computer.ModeSelectionScreenController;

/**
 * FXML Controller class
 *
 * @author reham
 */
public class AvailableUsersController implements Initializable {

    @FXML
    private Button reloadBtn; 
    @FXML
    private ListView<HBox> usersListView; 
    @FXML
    private Label availableLbl;
    @FXML
    private ImageView backBtn;
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloadBtn.setOnAction(event -> {
            /* هنجيب داتا من السيرفر */
            JSONObject request = new JSONObject();
            request.put("type", "get Avaliable users");
            JSONArray onlineUsers = new JSONArray();

            ClientSocket.sendRequest(request);
            
            new Thread(
                    ()->{
                         JSONObject response = ClientSocket.recieveResponse();
                         JSONArray onlinePlayers = response.getJSONArray("onlinePlayers");
                         
                         Platform.runLater(()->{
                             
                         });
                         //{"onlinePlayers" : [ {username:ahmed,}, {}, {} ]
                    }
            );
           
            addUser("User " + (usersListView.getItems().size() + 1));
        });
        
    }

    private void addUser(String userName) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user_item.fxml"));
            HBox userItem = loader.load();

           
            User_itemController controller = loader.getController();

            controller.setUserName(userName);

            controller.setInviteButtonAction(() -> {
                
                System.out.println("an invitaion sent to " + userName);
                // هنا  تضيف الكود اللي  
            });

          
            usersListView.getItems().add(userItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      @FXML
    private void photoClicked(MouseEvent event) {
        try {
            Navigator.navigateToLandingScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}