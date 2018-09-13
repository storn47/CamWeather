package com.hyacinthe.CamWeather;

import java.awt.Dimension;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.hyacinthe.CamWeather.models.CurrentCamWeather;
import com.hyacinthe.CamWeather.utilies.Alert;
import com.hyacinthe.CamWeather.utilies.Api;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainFrame extends JFrame {
	private static final String GENERIC_ERROR_MESSAGE = "\t\t\t\tOooops!!!\nune erreur est survenue veuillez SVP réessayer";
	private static final String INTERNET_CONNECTIVITY_ERROR_MESSAGE = "\t\t Oooops!!! \nVeillez verifier l'etat de votre connection!";
	private static final long serialVersionUID = -5855725216852398835L;

	public MainFrame(String title) {
		super(title);
		double longitude = -122.4233;
		double latitude = 37.8267;

		System.out.println("avant la requete...");

		// *********** Requte asynchrone ***********

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(Api.getForecastUrl(latitude, longitude)).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) {
				try (ResponseBody body = response.body()) {
					if (response.isSuccessful()) {
						String jsonData = body.string();
						JSONObject forecast = (JSONObject) JSONValue.parseWithException(jsonData);
						CurrentCamWeather currentCamWeather = new CurrentCamWeather();
						JSONObject currently = (JSONObject) forecast.get("currently");
						
						currentCamWeather.setTimezone((String) forecast.get("timezone"));
						currentCamWeather.setTime((long) currently.get("time"));
						currentCamWeather.setTemperature(Double.parseDouble(currently.get("temperature")+""));
						currentCamWeather.setHumidity(Double.parseDouble(currently.get("humidity") + ""));
						currentCamWeather.setPrecipProbability(Double.parseDouble(currently.get("precipProbability") + ""));
						currentCamWeather.setSummary((String) currently.get("summary"));
						
						
					} else {
						Alert.error(MainFrame.this, "ERROR ", GENERIC_ERROR_MESSAGE);
					}
				} catch (ParseException | IOException e) {
					Alert.error(MainFrame.this, "ERROR ", GENERIC_ERROR_MESSAGE);
				}

			}

			@Override
			public void onFailure(Call call, IOException e) {
				Alert.error(MainFrame.this, "ERROR", INTERNET_CONNECTIVITY_ERROR_MESSAGE);
			}
		});

		System.out.println("apres la requete.....");

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	@Override
	public Dimension getMinimumSize() {
		return super.getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	// ******requete get synchrone!!*******

	// class ForecastWorker extends SwingWorker<String, Void>{
	//
	// private String forecastUrl;
	// public ForecastWorker(String forecastUrl) {
	// this.forecastUrl = forecastUrl;
	//
	// }
	//
	// @Override
	// protected String doInBackground() throws Exception {
	//
	// OkHttpClient client = new OkHttpClient();
	// Request request = new Request.Builder()
	// .url(forecastUrl)
	// .build();
	// Call call = client.newCall(request);
	//
	// try {
	// Response response = call.execute();
	// if(response.isSuccessful()) {
	// return response.body().string();
	// }
	//
	// } catch (IOException e) {
	// System.err.println("Error "+e);
	// }
	//
	// return null;
	// }
	// @Override
	// protected void done() {
	// try {
	// System.out.println(get());
	// } catch (InterruptedException | ExecutionException e) {
	// System.err.println("Error "+e);
	// }
	// }
	//
	//
	// }

}
