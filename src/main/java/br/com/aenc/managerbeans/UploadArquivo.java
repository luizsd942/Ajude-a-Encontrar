package br.com.aenc.managerbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

public class UploadArquivo {
    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private String nome;

    public UploadArquivo() {
    }

    public String getDiretorio() {
        return this.diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRealPath() {
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/");
    }

    public void fileUpload(FileUploadEvent event, String tipo, String diretorio) {
        try {
            this.nome = new java.util.Date().getTime() + "_"
    				+ event.getFile().getSize() + tipo;
            
            this.caminho = getRealPath() + diretorio + getNome();
            this.arquivo = event.getFile().getContents();
            
            File file = new File(getRealPath() + diretorio);
            file.mkdirs();
            
            System.out.println("caminho: " + caminho);
            System.out.println("Nome: " + getNome());

        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }
    
    public void gravar(){
        
        try {
            
            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            fos.close();
            System.out.println("Arquivo criado com sucesso...");
            
        } catch (Exception ex) {
        	System.out.println("Deu ruim na hora de criar e gravar arquivo");
            Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}