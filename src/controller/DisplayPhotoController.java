package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;

import java.awt.*;
import java.io.IOException;

/** Displau photo controller controls the display window when it is the photos with the tags and caption
 * @author Jesse Graham | Arsal Shaikh
 * */

public class DisplayPhotoController {


    @FXML
    private TreeView<String> tagsField;

    @FXML
    private TextArea captionField;

    @FXML
    private TextField timeCapturedField, dateCapturedField;

    @FXML
    private ImageView imageView;

    private User currentUser;
    /*

    primaryStage - contains primary scene of application
    */

    Stage primaryStage;

    /*

    album - contains album
    */

    Album album;
    /*

    admin - contains admin
    */

    Admin admin;
    /*

    prev - contains previous scene
    */

    Scene prev;

    /*

    avc - contains controller to album view
    */

    AlbumViewController avc;

    /*

    photo - contains photo
    */

    Photo photo;

    /**

     Start initializes the  display photo controllers
     @param primaryStage Stage of the previous scene
     @param currentUser user currently logged in
     @param prev prev scene
     @param avc  controller to album view
     @param album - contains album
     @param admin - admin object
     @param photo - contains photo

     */

    public void start(Stage primaryStage, User currentUser, Scene prev, AlbumViewController avc, Album album, Admin admin, Photo photo){
        this.primaryStage = primaryStage;
        this.currentUser = currentUser;
        this.prev = prev;
        this.avc = avc;
        this.album = album;
        this.admin = admin;
        this.photo = photo;
        setWindow();
        primaryStage.setResizable(true);
    }
    /**

     sets window within stage for photo display

     */
    public void setWindow(){
        captionField.setText(photo.getCaption());
        dateCapturedField.setText(STR."Date Captured: \{photo.getDate()}");
        timeCapturedField.setText(STR."Time Captured: \{photo.getTime()}");
        if(photo.getImage() != null){
            imageView.setImage(photo.getImage());
            imageView.setPreserveRatio(true);
        }
        TreeItem<String> dumb = new TreeItem<String>("dumb");
        dumb.setExpanded(true);
        tagsField.setRoot(dumb);
        tagsField.setShowRoot(false);
        for(int i1 = 0; i1 < photo.getTags().size(); i1++){
            TreeItem<String> tagT = new TreeItem<String>(photo.getTags().get(i1).toString());
            for(int j = 0; j < photo.getTags().get(i1).getTagValues().size(); j++){
                TreeItem<String> tagV = new TreeItem<String>(photo.getTags().get(i1).getTagValues().get(j));
                tagT.getChildren().add(tagV);
            }
            dumb.getChildren().add(tagT);
        }


    }
    /**

     returns user to previous scene
     @param actionEvent - accepts click from user

     */
    public void returnToPrev(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/albumView.fxml"));
        AnchorPane root = (AnchorPane)loader.load();
        AlbumViewController apg = loader.getController();

        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/view/user.fxml"));
        AnchorPane root2 = (AnchorPane)loader2.load();
        UserController apg2 = loader2.getController();
        Scene userScene = new Scene(root2);

        apg.start(primaryStage, currentUser, userScene, apg2, album, admin,false);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        root.requestFocus();
    }
    /**

     Users quits application
     @param actionEvent - accepts click from user

     */

    public void quitApp(ActionEvent actionEvent) throws IOException {
        Admin.writeAdmin(admin);
        Platform.exit();
    }
}