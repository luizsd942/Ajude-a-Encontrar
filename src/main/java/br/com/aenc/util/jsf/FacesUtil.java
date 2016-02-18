package br.com.aenc.util.jsf;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addSuccessMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public static void addSuccessMessageWithDetail(String form, String detail,
			String message) {
		FacesContext.getCurrentInstance().addMessage(form,
				new FacesMessage(FacesMessage.SEVERITY_INFO, detail, message));
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, message,
								message));
	}

	public static void addErrorMessageWithDetail(String form, String detail,
			String message) {
		FacesContext.getCurrentInstance().addMessage(form,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, message));
	}

	// Calcula a Idade baseado em java.util.Date
	public static int calculaIdade(java.util.Date dataNasc) throws Exception{
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNasc);

		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);
		// se a data de hoje é antes da data de Nascimento, então diminui 1(um)
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}
	
	// Calcula a Idade baseado em duas datas
	public static int calculaIdadeDesapareceu(Date dataNasc, Date dataDesap) throws Exception{
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNasc);

		// Cria um objeto Calendar com a data do desaparecimento
		Calendar dateDesap = new GregorianCalendar();
		dateDesap.setTime(dataDesap);

		// Obtém a idade baseado no ano
		int age = dateDesap.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);
		// se a data de Desaparecimento for anterior a data de Nascimento, então diminui 1(um)
		if (dateDesap.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

}