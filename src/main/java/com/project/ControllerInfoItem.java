package com.project;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ControllerInfoItem {

  @FXML
  private ImageView img;

  @FXML
  private Label nom = new Label();

  @FXML
  private Label nomjoc = new Label();

  @FXML
  private Label color = new Label();

  @FXML
  private Label data = new Label();

  @FXML
  private Label procesador = new Label();
  
  @FXML
  private Label venudes = new Label();

  @FXML
  private Label any = new Label();

  @FXML
  private Label tipus = new Label();

  @FXML
  private Label descripcio = new Label();

  public void setImage(Image image) {
    this.img.setImage(image);
  }

  public void setNom(String text) {
    this.nom.setText(text);
  }

  public void setNomJoc(String text) {
    this.nomjoc.setText(text);
  }

  public void setColor(String text) {
    Color color = Color.web(text.toLowerCase());
    this.color.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void setData(String text) {
    this.data.setText(text);
  }

  public void setProcesador(String text) {
    this.procesador.setText(text);
  }

  public void setVenudes(int text) {
    this.venudes.setText(Integer.toString(text));
  }

  public void setAny(int text) {
    this.any.setText(Integer.toString(text));
  }

  public void setTipus(String text) {
    this.tipus.setText(text);
  }

  public void setDescripcio(String text) {
    this.descripcio.setText(text);
  }

}

