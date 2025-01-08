package tic_tac_toe.view.availableUsers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import tic_tac_toe.navigation.Navigator;
import tic_tac_toe.view.landing.LandingScreenController;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloadBtn.setOnAction(event -> {
            /* هنجيب داتا من السيرفر */
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
    void BackClicked(ActionEvent event) {
        try {
            Navigator.navigateToOfflineScreen(event);
        } catch (IOException ex) {
            Logger.getLogger(LandingScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}