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

package test;

import entidades.Aulas;
import entidadesDAO.AulasDAOEXT;
import entidadesDAO.OpcionesHomeExt;

public class TestClass {
	public static void main(String[] args){
		//AulasDAOEXT daoext = new AulasDAOEXT();
		//Aulas aulas = daoext.findById(1);
		//System.out.println(aulas.getAula());
		
		OpcionesHomeExt daoext2 = new OpcionesHomeExt();
		System.out.println(daoext2.listMenus("admin", true, null).get(0).getTituloMenu());
	}
}