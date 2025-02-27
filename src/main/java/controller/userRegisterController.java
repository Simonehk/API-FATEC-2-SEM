package controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.ConnectionFactory;
import view.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class userRegisterController {
    @FXML
    private TextField InputDocument;
    @FXML
    private TextField InputNome;
    @FXML
    private TextField InputEmail;
    @FXML
    private TextField InputTele;
    @FXML
    private TextField InputSenha;
    @FXML
    private TextField InputCSenha;

    public void enterUserOnAction() throws IOException {
        Main.changeScene("loginForm");
    }
    @FXML
    protected void Cadastrar() {
        String Docum, Nome, Email, Tele, Senha, Csenha;
        Docum = InputDocument.getText();
        Nome = InputNome.getText();
        Email = InputEmail.getText();
        Tele = InputTele.getText();
        Senha = InputSenha.getText();
        Csenha = InputCSenha.getText();

        if (Senha.equals(Csenha)){
            Connection conn;
            PreparedStatement pstm;
            String sql = "insert into users(document,name,email,phone,password) values (?,?,?,?,?)";
            conn = ConnectionFactory.getConnection();
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, Docum);
                pstm.setString(2, Nome);
                pstm.setString(3, Email);
                pstm.setString(4, Tele);
                pstm.setString(5, Senha);

                pstm.execute();
                pstm.close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Cadastro Feito");
                alert.show();
                Main.changeScene("loginForm");
            } catch (Exception erro) {
                System.out.println("Cadastro" + erro);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("As senhas não estão Corretas");
            alert.show();

        }

    }
    }
