package com.example.projectWork.dto;

public class UtenteDTO {
    private String username;
    private String password;
    private Boolean amministratore;
    private ClienteDTO cliente;
    private AdminDTO admin;


    // Costruttore vuoto
    public UtenteDTO() {
    }


    public UtenteDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getAmministratore() {
        return amministratore;
    }


    public void setAmministratore(Boolean amministratore) {
        this.amministratore = amministratore;
    }


    public ClienteDTO getCliente() {
        return cliente;
    }


    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }


    public AdminDTO getAdmin() {
        return admin;
    }


    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
    }
}
