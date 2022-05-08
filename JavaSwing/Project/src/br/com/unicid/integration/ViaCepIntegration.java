package br.com.unicid.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import br.com.unicid.model.EnderecoModel;

public class ViaCepIntegration {
	
	public EnderecoModel ConsultarCEP(String cep) throws Exception {
		try {
			String urlParaChamada = "http://viacep.com.br/ws/" + cep + "/json";

			URL url = new URL(urlParaChamada);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			if (conexao.getResponseCode() != 200)
				throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

			BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "UTF-8"));
			String jsonEmString = converteJsonEmString(resposta);

			Gson gson = new Gson();
			EnderecoModel endereco = gson.fromJson(jsonEmString, EnderecoModel.class);

			return endereco;
		} catch (Exception e) {
			throw new Exception("ERRO: " + e);
		}
	}
	
	private static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }

}
