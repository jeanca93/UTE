/*
   Copyright 2016 Jean Carlos Trivino Calderon

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/


package controladores.modelo;


public class Musuario {
	public String resultadoLogin,resultadoPass;
	public String usuario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getResultadoLogin() {
		return resultadoLogin;
	}

	public void setResultadoLogin(String resultadoLogin) {
		this.resultadoLogin = resultadoLogin;
	}

	public String getResultadoPass() {
		return resultadoPass;
	}

	public void setResultadoPass(String resultadoPass) {
		this.resultadoPass = resultadoPass;
	}

	

}
