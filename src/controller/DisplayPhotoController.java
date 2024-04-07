package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;

import java.awt.*;

public class DisplayPhotoController {
    @FXML
    private HBox imageField;

    @FXML
    private TreeView<String> tagsField;

    @FXML
    private TextArea captionField;

    @FXML
    private TextField timeCapturedField, dateCapturedField;

    @FXML
    private Button returnButton;

    @FXML
    private ImageView imageView;

    private User currentUser;

    Stage primaryStage;

    Album album;

    Admin admin;

    Scene previous;

    LoginController lpg;

    Scene prev;

    AlbumViewController avc;

    Photo photo;

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
    public void setWindow(){
        captionField.setText(photo.getCaption());
        dateCapturedField.setText(STR."Date Captured: \{photo.getDate()}");
        timeCapturedField.setText(STR."Time Captured: \{photo.getTime()}");
        if(photo.getImage() != null){
            imageView.setImage(photo.getImage());
            imageView.setPreserveRatio(true);
//            imageView.fitHeightProperty().bind(imageField.heightProperty());
//            imageView.fitWidthProperty().bind(imageField.widthProperty());
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
    public void returnToPrev(ActionEvent actionEvent) {

    }
}