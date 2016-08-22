package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import entidades.Dias;
import entidadesDAO.HorasHomeExt;
import entidadesDAO.SchollaryearHomeExt;
import modelo.anioescolar.DiasDatos;

public class JornadaAcademicaComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 12L;
	private ListModel<Dias> allDias = new ListModelList<Dias>();
	private Timebox txtHoraIni, txtReceso1Ini, txtReceso1Fin, txtReceso2Ini, txtReceso2Fin;
	private Timebox txtHoraFin;
	private Listbox lbDias;
	private Window modalDialog;
	
	public JornadaAcademicaComposer() {
		// TODO Auto-generated constructor stub
		
		((ListModelList<Dias>)allDias).setMultiple(true);
		
		List<Dias> diasSeleccionados = new ArrayList<Dias>();
		
		for(Dias dias:new DiasDatos(false).getAllDias()){
			((ListModelList<Dias>)allDias).add(dias);
			
			if (!((dias.getDia().toUpperCase().equals("SÁBADO")) || (dias.getDia().toUpperCase().equals("DOMINGO"))))
				diasSeleccionados.add(dias);
		}
		

		((ListModelList<Dias>)allDias).setSelection(diasSeleccionados);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allProfesoresStatus = (ListModelList<ProfesorStatus>)execution.getArg().get("modelPrincipal");
		//GridProfesores = (Grid)execution.getArg().get("gridPrincipal");
		
		Calendar hora = Calendar.getInstance();
		
		hora.set(Calendar.HOUR_OF_DAY,7);
		hora.set(Calendar.MINUTE,0);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtHoraIni.setValue(hora.getTime());
		
		hora.set(Calendar.HOUR_OF_DAY,15);
		hora.set(Calendar.MINUTE,0);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtHoraFin.setValue(hora.getTime());
		
		hora.set(Calendar.HOUR_OF_DAY,10);
		hora.set(Calendar.MINUTE,0);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtReceso1Ini.setValue(hora.getTime());
		
		hora.set(Calendar.HOUR_OF_DAY,10);
		hora.set(Calendar.MINUTE,45);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtReceso1Fin.setValue(hora.getTime());
		
		hora.set(Calendar.HOUR_OF_DAY,13);
		hora.set(Calendar.MINUTE,45);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtReceso2Ini.setValue(hora.getTime());

		hora.set(Calendar.HOUR_OF_DAY,14);
		hora.set(Calendar.MINUTE,15);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtReceso2Fin.setValue(hora.getTime());
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		HorasHomeExt horasExt = new HorasHomeExt();
		int size = horasExt.listHorasActivos(false).size();
		
		if (size == 0)
			registroJornadaAcademica();
		else{
			Messagebox.show("¿Está seguro que desea modificar la jornada académica?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
				
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						registroJornadaAcademica();
			        }
				}
		   });
		}
	}
	
	private void registroJornadaAcademica(){
		try{
			String campo = "";
			
			Date horaIni = (Date)txtHoraIni.getRawValue();
			Date horaIniTMP = horaIni;
			Date horaFin = (Date)txtHoraFin.getRawValue();
			Date horaFinTMP = horaIniTMP;
			Date receso1Ini = (Date)txtReceso1Ini.getRawValue();
			Date receso1Fin = (Date)txtReceso1Fin.getRawValue();
			Date receso2Ini = (Date)txtReceso2Ini.getRawValue();
			Date receso2Fin = (Date)txtReceso2Fin.getRawValue();
			List<Dias> diasSeleccionados = new ArrayList<Dias>();
			
			if(horaIni.compareTo(horaFin) < 0){
				if(receso1Ini.compareTo(receso1Fin) < 0){
					if(receso2Ini.compareTo(receso2Fin) < 0){
						if(receso2Ini.after(receso1Fin)){
							if(((receso1Ini.before(horaFin) && receso1Ini.after(horaIni)) && 
									(receso1Fin.before(horaFin) && receso1Fin.after(horaIni))) &&
								((receso2Ini.before(horaFin) && receso2Ini.after(horaIni)) && 
											(receso2Fin.before(horaFin) && receso2Fin.after(horaIni)))){
								if(lbDias.getSelectedCount() > 3){
									Calendar nuevahora = Calendar.getInstance();
									nuevahora.setTime(new SchollaryearHomeExt().obtenerDuracionClase());
									int totalMinutosClases = ((nuevahora.get(Calendar.HOUR_OF_DAY)*60) + nuevahora.get(Calendar.MINUTE));
									
									boolean flag = true;
									
									while (!horaIniTMP.equals(horaFin)){							
										if (horaIniTMP.getTime() == receso1Ini.getTime()){
											horaFinTMP = receso1Fin;
										
											flag = false;
										}else if (horaIniTMP.getTime() == receso2Ini.getTime()){
											horaFinTMP = receso2Fin;
											
											flag = false;
										}else{
											nuevahora.setTime(horaIniTMP);
											nuevahora.add(Calendar.MINUTE, totalMinutosClases);
												
											horaFinTMP = nuevahora.getTime();
											/*
											Calendar c_horaIni = Calendar.getInstance(), c_horaFin = Calendar.getInstance();
											c_horaIni.setTime(horaIniTMP);
											c_horaFin.setTime(horaFinTMP);
											*/
											if ((receso1Ini.before(horaFinTMP) && receso1Ini.after(horaIniTMP)) ||
													(receso2Ini.before(horaFinTMP) && receso2Ini.after(horaIniTMP))){
												flag = false;
												
												break;
											}
										}
										
										if (flag){
											nuevahora.setTime(horaIniTMP);
											nuevahora.add(Calendar.MINUTE, totalMinutosClases);
											
											horaIniTMP = nuevahora.getTime();
										}else
											horaIniTMP = horaFinTMP;
										
										flag = true;
									}
									
									if (flag){
										Set<Listitem> itemsSelected= lbDias.getSelectedItems();
										Iterator<Listitem> itr = itemsSelected.iterator();
										while(itr.hasNext()){
											Listitem listItem = itr.next();
											diasSeleccionados.add((Dias)listItem.getValue());
										}

										new HorasHomeExt().registrarJornadaAcademica(horaIni, horaFin, receso1Ini, receso1Fin, receso2Ini, receso2Fin, diasSeleccionados, Integer.parseInt(session.getAttribute("idUsuario").toString()));
										
										Messagebox.show("Jornada académica registrada correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION);
									
										modalDialog.detach();
									}else
										Messagebox.show("Recesos no pueden interferir con duración de las clases", "Error",  Messagebox.OK,  Messagebox.ERROR);
								}else
									Messagebox.show("Debe escoger mínimo tres días de la semana", "Error",  Messagebox.OK,  Messagebox.ERROR);
							}else
								Messagebox.show("Recesos deben estar dentro del rango de la jornada académica", "Error",  Messagebox.OK,  Messagebox.ERROR);
						}else
							Messagebox.show("Receso 2 debe ser posterior a receso 1", "Error",  Messagebox.OK,  Messagebox.ERROR);
					}else
						campo = "Receso 2";
				}else
					campo = "Receso 1";
			}else
				campo = "Horario";
			
			if (!campo.equals(""))
				Messagebox.show("En " + campo + " hora incial debe ser menor al final", "Error",  Messagebox.OK,  Messagebox.ERROR);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public ListModel<Dias> getAllDias(){
		return allDias;
	}
}
