package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Controller implements Initializable {
    private DataStore dataStore;

    @FXML
    private Button computeHit;

    @FXML
    private TextArea textCNum;

    @FXML
    private TextArea textc;

    @FXML
    private TextArea textAeqNum;

    @FXML
    private TextArea textAeq;

    @FXML
    private TextArea textbeqNum;

    @FXML
    private TextArea textbeq;

    @FXML
    private TextArea textAleqNum;

    @FXML
    private TextArea textAleq;

    @FXML
    private TextArea textbleqNum;

    @FXML
    private TextArea textbleq;

    @FXML
    private TextArea textlindexNum;

    @FXML
    private TextArea textlindex;

    @FXML
    private TextArea textnindexNum;

    @FXML
    private TextArea textnindex;

    @FXML
    private Label output;

    @FXML
    private Button InputEndButton;


    @FXML
    void onComputeButtonKeyboard(KeyEvent event) {

    }
    @FXML
    void onHitInputEndButtion(MouseEvent event) {
        dataStore.setcNum(textCNum.getText());
        dataStore.setC(textc.getText());
        dataStore.setAeq(textAeq.getText());
        dataStore.setAeqNum(textAeqNum.getText());
        dataStore.setBeqNum(textbeqNum.getText());
        dataStore.setBeq(textbeq.getText());
        dataStore.setAleqNum(textAleqNum.getText());
        dataStore.setAleq(textAleq.getText());
        dataStore.setBleqNum(textbleqNum.getText());
        dataStore.setBleq(textbleq.getText());
        dataStore.setLindexNum(textlindexNum.getText());
        dataStore.setLindex(textlindex.getText());
        dataStore.setNindexNum(textnindexNum.getText());
        dataStore.setNindex(textnindex.getText());
        output.setText("输入完毕，请点击Compute按钮进行计算！");
        System.out.println(dataStore.getLindex());
    }
    @FXML
    void onHitComputeButton(MouseEvent event) throws IOException {
        output.setText("该问题无解");
        output.setText(Client1.useSocket(dataStore.getcNum(), dataStore.getC(), dataStore.getAeqNum(), dataStore.getAeq(), dataStore.getBeqNum(), dataStore.getBeq(), dataStore.getAleqNum(), dataStore.getAleq(), dataStore.getBleqNum(), dataStore.getBleq(), dataStore.getLindexNum(), dataStore.getLindex(), dataStore.getNindexNum(), dataStore.getNindex()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        output.setText("HelloWorld!");
        textc.setText("Defaulte");
        textCNum.setText("Defaulte");
        textAeqNum.setText("Defaulte");
        textAeq.setText("Defaulte");
        textAleqNum.setText("Defaulte");
        textAleq.setText("Defaulte");
        textbeq.setText("Defaulte");
        textbeqNum.setText("Defaulte");
        textbleq.setText("Defaulte");
        textbleqNum.setText("Defaulte");
        textlindex.setText("Defaulte");
        textlindexNum.setText("Defaulte");
        textnindex.setText("Defaulte");
        textnindexNum.setText("Defaulte");
        dataStore=new DataStore();
    }
}
