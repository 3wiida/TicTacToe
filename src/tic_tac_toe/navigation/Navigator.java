/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.navigation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tic_tac_toe.model.GameModeEnum;
import tic_tac_toe.model.Player;
import tic_tac_toe.view.gameBoard.GameBoardFXMLController;

/**
 *
 * @author 3wiida
 */
public class Navigator {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void navigateToLandingScreen(Event event) throws IOException {
        if (event.getSource() instanceof Node) {
            Node sourceNode = (Node) event.getSource();
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.LANDING_SCREEN_ROUTE));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Can't navigate to landing screen");
        }
    }

    public static void navigateToLandingScreen() throws IOException {
        Parent landingRoot = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.LANDING_SCREEN_ROUTE));
        Scene landingScene = new Scene(landingRoot);
        mainStage.setScene(landingScene);
        mainStage.show();
    }

    public static void navigateToLandingScreen(Node node) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.LANDING_SCREEN_ROUTE));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToLoginScreen(Event event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.LOGIN_SCREEN));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToSignupScreen(Event event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.REGISTER_SCREEN));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToAskLoginOrSignupPopup(Event event) throws IOException {

    }

    public static void navigateToOfflineScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.OFFLINE_SCREEN_ROUTE));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void naviagteToGameBoardScreen(ActionEvent event, GameModeEnum gameMode) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.GAME_BOARD_SCREEN_ROUTE));
        Parent root = loader.load();
        GameBoardFXMLController controller = loader.getController();
        controller.setGameMode(gameMode);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void naviagteToGameBoardScreen(ActionEvent event, GameModeEnum gameMode, String recordName) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.GAME_BOARD_SCREEN_ROUTE));
        Parent root = loader.load();
        GameBoardFXMLController controller = loader.getController();
        controller.setFileNameForGameReplay(recordName);
        controller.setGameMode(gameMode);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void naviagteToGameBoardScreen(
            ActionEvent event,
            GameModeEnum gameMode,
            String playerOneName,
            String playerTwoName
    ) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.GAME_BOARD_SCREEN_ROUTE));
        Parent root = loader.load();
        GameBoardFXMLController controller = loader.getController();
        controller.setPlayersNames(playerOneName, playerTwoName);
        controller.setGameMode(gameMode);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void naviagteToGameBoardScreen(
            Node node,
            GameModeEnum gameMode,
            Player opponent,
            boolean isHosting
    ) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.GAME_BOARD_SCREEN_ROUTE));
        Parent root = loader.load();
        GameBoardFXMLController controller = loader.getController();
        controller.setOnlineParameters(opponent, isHosting);
        controller.setGameMode(gameMode);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void navigateToOfflineScreen(Event event) throws IOException {

        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.OFFLINE_SCREEN_ROUTE));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage navigateToWaitingPopup(Event event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.WAITING_POPUP_ROUTE));
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(scene);
        popupStage.show();
        return popupStage;
    }

    public static void navigateToModeSelectionScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.MODE_SELECTION_SCREEN));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToOnlineScreen(Event event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.ONLINE_SCREEN_ROUTE));
        Parent root = Loader.load();
        Stage stage = mainStage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToOnlineScreen(Node node) throws IOException {
        FXMLLoader Loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.ONLINE_SCREEN_ROUTE));
        Parent root = Loader.load();
        Stage stage = ((Stage) node.getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToAvailableUsersScreen(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.AVAILABLE_USERS_SCREEN_ROUTE));
        Parent root = Loader.load();
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToRecordsScreen(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.RECORDS_SCREEN_ROUTE));
        Parent root = Loader.load();
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateToInvitationPopup(Event event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.INVTATION_POPUP_ROUTE));
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public static void navigateSeverConnectionPopup(Event event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.SERVER_CONNECTION_POPUP_ROUTE));
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public static void navigateToProfileScreen(Event event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(Navigator.class.getResource(ScreensRoutes.PROFILE_SCREEN_ROUTE));
        Parent root = Loader.load();
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage navigateToServerShutdownScreen(Stage parentStage) throws IOException {
        // Load the FXML for the server shutdown popup
        Parent root = FXMLLoader.load(Navigator.class.getResource(ScreensRoutes.SERVER_SHUTDOWN_POPUP_ROUTE));
        Scene scene = new Scene(root);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Block interaction with parent stage
        popupStage.initStyle(StageStyle.UNDECORATED);         // No default stage decorations
        popupStage.setScene(scene);

        // Center the popup on the parent stage
        popupStage.setOnShowing(event -> {
            if (parentStage != null) {
                double parentX = parentStage.getX();
                double parentY = parentStage.getY();
                double parentWidth = parentStage.getWidth();
                double parentHeight = parentStage.getHeight();

                double popupWidth = popupStage.getWidth();
                double popupHeight = popupStage.getHeight();

                popupStage.setX(parentX + (parentWidth - 700) / 2);
                popupStage.setY(parentY + (parentHeight - 200) / 2);
            }
        });

        popupStage.show();
        return popupStage;
    }

}
