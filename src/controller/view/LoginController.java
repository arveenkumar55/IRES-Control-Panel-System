/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package controller.view;
 
import java.io.IOException;

import controller.UserLogin;
import controller.model.PanelControlSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class LoginController {
	@FXML
	private TextField userNameField;
	@FXML
	private TextField pwdField;
	@FXML 
	private Label msglabel;
	private PanelControlSystem pcs;

	public LoginController(){
	
	}
	
	@FXML
	private void handleSignIn(){
		
		if(isInputValid()){
			//validate user name and password...
			String userName = userNameField.getText();
			String pwd = pwdField.getText();
			String ret = pcs.getLogIn(userName, pwd);
			System.out.println("ret valu: "+ret);
			msglabel.setText(ret);
			if( ret.equals("Logged in successfully!")){
				loadMenuFxml();
			}
			
		}
	}
	@FXML
	private void handleCreateAccount(){
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("account.fxml"));
		AnchorPane login = null;
		try {
			login = (AnchorPane) loader.load();
			AccountController AC = loader.getController();
			AC.setPanelControlSystem(pcs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(login);
		Stage stg = UserLogin.getStage();
		if( stg != null){
			stg.setScene(scene);
			stg.show();
		}
	}
	
	private void loadMenuFxml(){
		
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("menu.fxml"));
		AnchorPane menu = null;
		
		try {
			menu = (AnchorPane) loader.load();
			MenuController AC = loader.getController();
			AccountController.setType('d');
		
			if(pcs != null){
				if(AC!=null)
				AC.setPanelControlSystem(pcs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(menu);
		Stage stg = UserLogin.getStage();
		if( stg != null){
			stg.setScene(scene);
			stg.show();
		}
	}
	public void setPanelControlSystem(PanelControlSystem pcs){
		this.pcs = pcs;
	}
	private boolean isInputValid(){
		String errMessage = "";
		if( userNameField.getText() == null || userNameField.getText().length() == 0){
			errMessage += "Invalid user name entered!";
		}
		else if( pwdField.getText() == null || pwdField.getText().length() == 0 ){
			errMessage += "Invalid password entered!";
		}
		if( errMessage.length() == 0 ){
			return true;
		}
		else{
			
			printError(errMessage);
			return false;
		}
	}
	
	public void printError(String msg){
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(UserLogin.getStage());
        alert.setTitle("Log In");
        alert.setHeaderText("Invalid Information");
        alert.setContentText(msg);
        alert.showAndWait();
	}

}
