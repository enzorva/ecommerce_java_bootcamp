package model;

import java.util.UUID;

// Classe abstrata com atributos comuns
public abstract class Client {
    private String id;          // Identificador único do cliente
    private String name;
    private String email;
    private String phone;

    // Construtor
    public Client(String name, String email, String phone) {
        this.id = UUID.randomUUID().toString(); // gera id único
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Método abstrato que deve ser implementado pelas subclasses
    public abstract String getDocumentIdentification();

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email +
                " | Phone: " + phone + " | Document: " + getDocumentIdentification();
    }
}
