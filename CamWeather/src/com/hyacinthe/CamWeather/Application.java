package com.hyacinthe.CamWeather;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				NewJFrame newJFrame = new NewJFrame("CamWeather");
				newJFrame.setResizable(false);
				newJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newJFrame.pack();
				newJFrame.setLocationRelativeTo(null);
				newJFrame.setVisible(true);
			}
		});
	
	
	
	}
}
